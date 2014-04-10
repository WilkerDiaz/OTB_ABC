package com.abc.otb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.LineDAO;
import com.abc.otb.logico.Line;
import com.abc.otb.mediadorbd.MediadorBD;

public class LineDAOImpl implements LineDAO{
	
	
	private List<Line> listaLinea = new LinkedList<Line>();
	
	/**
	 * Carga el combobox de las lineas
	 */
	public List<Line> cargarLinea (String department_id,String line_id,String empresa){
		String sql = "", parametro;
		
		//query para sacar todos los departamentos
		if (empresa.equals("amand") || empresa.equals("beco")){ // consulta para las empresas amand y beco
		  sql = "SELECT XX_VMR_Line_id, NAME,VALUE FROM XX_VMR_Line WHERE ISACTIVE = 'Y' ";
		  parametro = "XX_VMR_LINE_ID";
		}
		else {
		  sql = "SELECT XX_CAP_TYPE_ID, NAME, VALUE FROM XX_CAP_TYPE WHERE ISACTIVE = 'Y'";
		  parametro = "XX_CAP_TYPE_ID";
		}
		
		//si se selecciono un departamento
		if(department_id != null && Integer.parseInt(department_id)>0 && (empresa.equals("amand") || empresa.equals("beco")))
			sql += " AND XX_VMR_Department_ID = " +department_id; //se añade al query la condicion con el ID del departamento
		else
		    sql += " AND XX_CAP_SECTION_ID = "+department_id; // se añade al query la condicion con el ID de la seccion
		
		sql += " order by value";
		
		//espacio en blanco en el combo box de la linea
		Line linea = new Line();
		listaLinea.add(linea);
		
		//Realizar la consulta
	 Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	  if (result != null){
		  for (int i=0;i<result.length;i++)
			{
				//Crear un objeto linea, que va a guardar los resultados de la consulta
			   linea = new Line();
			   //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			   int id = Integer.parseInt(result[i].get(parametro).toString());
			   linea.setxx_vmr_line_id(id);
			   linea.setname(result[i].get("NAME").toString());
			   linea.setvalues(result[i].get("VALUE").toString());
			   
			 //permitir que se mantengan en la misma opcion que marco en el combo box para cuando se refresque la ventana
			   if (line_id != null && id==Integer.parseInt(line_id))
				   linea.setPosicionar(1); //mantener en la opcion seleccionada del combo
			   else
				   linea.setPosicionar(0); //no mantener en la opcion seleccionada del combo
			   
			 //agregar la linea a la lista
			   listaLinea.add(linea);
			}	
		
		//Retornar la lista de los departamentos
		return listaLinea;
    }
		
	 return null;
	}
	
	public List<Line> borrarListaLinea(){
		return this.listaLinea = new LinkedList<Line>();
	}
	
	public boolean buscarPosicionar (){
		//recorrer toda la lista de la linea
		  for (int i=0;i<listaLinea.size();i++)
			  //buscar algun nodo que se haya seleccionado
			 if (listaLinea.get(i).getPosicionar()==1)
				return true; // si lo encuentra lo retorna TRUE
		 return false; // si no lo encuentra retorna FALSE
		}

}
