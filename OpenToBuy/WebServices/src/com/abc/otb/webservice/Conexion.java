package com.abc.otb.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.abc.otb.dao.impl.ConfiguracionBaseDatosDAOImpl;
import com.abc.otb.logico.ConfiguracionBaseDatos;

/**
 * 
 * @author ccastillo
 *
 */
public class Conexion {

	/**
	 * Metodo para realizar la conexion con la base de datos
	 * @param empresa nombre de la empresa a la que se quiere conectar (beco, amand, capuy, bcmuebles, abstracta)
	 * @return retorna la conexion con la base de datos
	 */
	public static Connection conectar (String empresa){
		
		try {
			ConfiguracionBaseDatosDAOImpl configuracionbd = new ConfiguracionBaseDatosDAOImpl();
			ConfiguracionBaseDatos configuracion = new ConfiguracionBaseDatos(); 
			
			//obtener toda la configuracion de la base de datos acerca de una empresa
			configuracion = configuracionbd.LecturaXML(empresa.toUpperCase());
			
			//hacer la conexion con el driver de la base de datos
			Class.forName(configuracion.getOjdbc());
			//obtener el url de conexion con la base de datos
			String url = configuracion.getUrl();
			//retornar la conexion con la base de datos
			return DriverManager.getConnection(url,configuracion.getUsuario(), configuracion.getPassword());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error class not found en conexion");
		} catch (SQLException e) {
			System.out.println("Error sql en conexion "+e);
		}
		
		return null;
	}
	
	/**
	 * cerrar la conexion con la base de datos
	 * @param conexion la conexion que se quiera cerrar
	 */
	public static void cerrarConexion (Connection conexion){
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error en cerrar conexion");
		}
	}
	
	/**
	 * realizar la consulta con la base de datos
	 * @param sql sql a ejecutar (consulta)
	 * @param empresa nombre de la empresa a la que se quiere conectar (beco, amand, capuy, bcmuebles, abstracta)
	 * @return retorna un arreglo de Map que internamente tiene la consulta
	 */
	
	public Map[] realizarConsulta (String sql, String empresa){
			
		ResultSet rs = null;
		Statement st = null;
		int pos = 0;
		Map<String, Object>[] row = null;
		
		//crear conexion con la base de datos
		Connection conexion = conectar(empresa);

		try {
			/*crear el statement, con los parametros permite que el puntero que retorna el resultSet sea capaz de utilizar el .first, .last, etc
			 * de lo contrario por defecto el puntero queda como TYPE_FORWARD_ONLY y no permite el usos de esas funciones 
			 */
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);			
			rs = st.executeQuery(sql); // ejecutar el query
			
			//sacar el numero de filas que tiene la consulta (cantidad de registro que tiene el resultSet)
			rs.last();
			int numFilas = rs.getRow();
			rs.beforeFirst();
			
			//obtener la data de la consulta (nombre de las columnas que arrojo la consulta)
			ResultSetMetaData metaData = rs.getMetaData();
			
			//obtener el numero de columnas
			int columnCount = metaData.getColumnCount();
			
			//crear el Map donde se va a almacenar la consulta
			row = new HashMap[numFilas];
			
			//recorrer la consulta
			 while (rs.next()) {
				 //inicializar el arreglo
			        row[pos] = new HashMap<String, Object>();
			        
			        //llenar el arreglo con los valores de la consulta
			        for (int i = 1; i <= columnCount; i++) {
			        	//guardar dentro del map el nombre de la columna (en MAYUSCULA) y su valor 
			            row[pos].put(metaData.getColumnName(i).toUpperCase(), rs.getString(i));
			        }
			        //incrementar en uno el arreglo, para que el siguiente registro del resultSet se guarde en otra casilla del arreglo
			        pos++;
			    }
			
		} catch (SQLException e) {
			System.out.println("Error SQL en la consulta "+sql);
			System.out.println(e);
		}
		finally{
			try {
				//cerrar el statement y la conexion con la base de datos
				st.close();
				cerrarConexion(conexion);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//retornar la consulta
		return row;		
	}	
	
	/**
	 * Método crearSentencia
	 * @param conexion conexion con la base de datos a la que se quiere crear la sentencia
	 * @return retorna el Statement
	 */
	public static Statement crearSentencia (Connection conexion)  {
		Statement s = null;
		try {
			s = conexion.createStatement();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	/**
	 * Método ejecutarLoteSentencias
	 * @param  elLote[] arreglo con el lote de sentencias
	 * @param empresa nombre de la empresa a la que se quiere conectar (beco, amand, capuy, bcmuebles, abstracta)
	 * @return int con la cantidad de registro afectados
	 */
	public static int realizarLoteSentencias(String[] elLote,String empresa){
		
		int[] filasAfectadas = null;
		
		//crear la conexion con la base de datos
		Connection conexion = conectar (empresa);
		
		//crear el lote de sentencias
		Statement loteSentencias = crearSentencia(conexion);
		
		try {
			//crear el batch con el lote de sentencias
			for (int i=0;i<elLote.length;i++)
			  loteSentencias.addBatch(elLote[i]);
			
			/*ejecutar el lote de sentencias en la base de datos. 
			 * Si tiene exito hace commit de todos los registro 
			 * Si falla hace rollback de todos los registros 
			 */
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			filasAfectadas = loteSentencias.executeBatch();
			conexion.commit();
			conexion.setAutoCommit(true);
		}catch (SQLException exSQL) {
			exSQL.printStackTrace();
			try {
					conexion.rollback();
					conexion.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
 		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//cerrar la conexion con la base de datos
		  cerrarConexion(conexion);
		}

		//retorna la cantidad de filas afectadas
 		return filasAfectadas.length;
	}
	
	/**
	 * Método realizarSentencia. Realiza las sentencias de insersion, actualizacion y borrar en la base de datos
	 * 
	 * @param String sentenciaSql de update, insert o delete
	 * @param empresa nombre de la empresa a la que se quiere conectar (beco, amand, capuy, bcmuebles, abstracta)
	 * @return int negativo => error, positivo o cero => numero de lineas afectadas 
	 */
	public static int realizarSentencia(String sentenciaSQL, String empresa) throws SQLException {
		Statement sentencia = null;
		
		//crear la conexion con la base de datos
		Connection conexion = conectar(empresa);
		int estado = -1;
		try {
			//crear sentencia
			sentencia = conexion.createStatement();
			//ejecutar la sentencia en la base de datos
			estado = sentencia.executeUpdate(sentenciaSQL);
			sentencia.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//cerrar la conexion con la base de datos
		  cerrarConexion(conexion);
		}
	
		return estado;
	}
	
	public static ConfiguracionBaseDatos obtenerDatosXML (String empresa){
		
		ConfiguracionBaseDatosDAOImpl configuracionbd = new ConfiguracionBaseDatosDAOImpl();
		
		//cargar la configuracion de beco desde el XML
		ConfiguracionBaseDatos configuracion = new ConfiguracionBaseDatos(); 
		
		configuracion = configuracionbd.LecturaXML(empresa); //buscar en el XML la configuracion de beco
		
		
		return configuracion;
	}

	public static void escribirDatosXML (String[] datosBeco,String[] datosAmand,String[] datosAbstracta,
			String[] datosBcMuebles,String[] datosCapuy){
		
		ConfiguracionBaseDatosDAOImpl configuracionbd = new ConfiguracionBaseDatosDAOImpl();
		configuracionbd.EscrituraXML(datosBeco, datosAmand, datosAbstracta, datosBcMuebles, datosCapuy);
	}
}
