package com.abc.otb.mediadorbd;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import com.abc.otb.logico.ConfiguracionBaseDatos;
import com.abc.otb.webservice.Conexion;
import com.abc.otb.webservice.ConexionServiceLocator;

/**
 * 
 * @author ccastillo
 *
 */
public class MediadorBD {
			
  //ruta en donde esta publicado el web service
   private static String endpoint = "http://localhost:8080/WebServices/services/Conexion";
	
	/**
	 * realizar la consulta con la base de datos
	 * @param sql sql a ejecutar (consulta)
	 * @param empresa la empresa en la que quiero hacer la consulta
	 * @return retorna un arreglo de Map<String,Object> donde internamente tiene la consulta
	 * 		   donde el String es el nombre de la columna de la consulta
	 * 		   y el Object es el valor de esa columna
	 */
	public static Map[] realizarConsulta (String sql,String empresa){
	
		ConexionServiceLocator loc = new ConexionServiceLocator();
		Conexion conexion;
		Map[] consulta = null;
		
		try {
			//buscar el web services en una ruta especifica
			conexion = loc.getConexion(new URL (endpoint));
			//llamar al metodo del web service que se encarga de realizar la consulta
			consulta = conexion.realizarConsulta(sql, empresa);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		//retornar la consulta
		return consulta;
	}
	
	
	/**
	 * Método para ejecutar un lote de sentencias
	 * @param loteSentencias una lista que internamente tiene todos los query que se van a ejecutar en la base de datos
	 * @param empresa la empresa en la que se quiere ejecutar el lote de sentencias
	 * @return retorna 0 -> si hubo un error en el lote de sentencias
	 * 		   retorna 1 -> si se ejecuto exitosamente el lote de sentencias
	 */
	public static int realizarLoteSentencias(ArrayList<String> loteSentencias,String empresa){
		
		ConexionServiceLocator loc = new ConexionServiceLocator();
		Conexion conexion;
		int resultado = 0;
		
		try {
			//buscar el web services en una ruta especifica
			conexion = loc.getConexion(new URL (endpoint));
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] elLote = loteSentencias.toArray(new String [loteSentencias.size()]);
			//llamar al metodo del web services encargado de realizar el lote de sentencias
			resultado = conexion.realizarLoteSentencias(elLote, empresa);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
 		return resultado;
	}
	
	/**
	 * Método realizarSentencia. Realiza las sentencias de insersion, actualizacion y borrado en la base de datos
	 * @param String sentenciaSql de update, insert o delete
	 * @param empresa en donde se quiere ejecutar la sentencia
	 * @return int negativo => error, positivo o cero => numero de lineas afectadas 
	 */
	public static int realizarSentencia(String sentenciaSQL, String empresa) throws SQLException {

		ConexionServiceLocator loc = new ConexionServiceLocator();
		Conexion conexion;
		int resultado = 0;
		
		try {
			//buscar el web services en una ruta especifica
			conexion = loc.getConexion(new URL (endpoint));
			//llamar al metodo del web services encargado de realizar la sentencia
			resultado = conexion.realizarSentencia(sentenciaSQL, empresa);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		//retorna el estado de la sentencia
		return resultado;
	}
	
	public static ConfiguracionBaseDatos obtenerDatosXML (String empresa){
		
		ConexionServiceLocator loc = new ConexionServiceLocator();
		ConfiguracionBaseDatos configuracion = new ConfiguracionBaseDatos(); 
		Conexion conexion;
		int resultado = 0;
		
		try {
			//buscar el web services en una ruta especifica
			conexion = loc.getConexion(new URL (endpoint));
			
			//llamar al metodo del web services encargado de realizar la sentencia
			configuracion = conexion.obtenerDatosXML(empresa);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		//retorna el estado de la sentencia
		return configuracion;
	}
	
	public static void escribirDatosXML (ArrayList<String> datosBeco,ArrayList<String> datosAmand,ArrayList<String> datosAbstracta,
			ArrayList<String> datosBcMuebles,ArrayList<String> datosCapuy){
		
		ConexionServiceLocator loc = new ConexionServiceLocator();
		Conexion conexion;
		
		try {
			//buscar el web services en una ruta especifica
			conexion = loc.getConexion(new URL (endpoint));
			
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] beco = datosBeco.toArray(new String [datosBeco.size()]);
			
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] amand = datosAmand.toArray(new String [datosAmand.size()]);
			
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] abstracta = datosAbstracta.toArray(new String [datosAbstracta.size()]);
			
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] bcmuebles = datosBcMuebles.toArray(new String [datosBcMuebles.size()]);
			
			//transformar la lista con las sentencias a un arreglo, debido a que el web services solo acepta arreglos
			String [] capuy = datosCapuy.toArray(new String [datosCapuy.size()]);
			
			//llamar al metodo del web services encargado de realizar el lote de sentencias
			conexion.escribirDatosXML(beco, amand, abstracta, bcmuebles, capuy);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
}
