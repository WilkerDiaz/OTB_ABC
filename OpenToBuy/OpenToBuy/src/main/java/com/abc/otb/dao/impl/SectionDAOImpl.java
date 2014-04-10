package com.abc.otb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.SectionDAO;
import com.abc.otb.logico.Section;
import com.abc.otb.mediadorbd.MediadorBD;

public class SectionDAOImpl implements SectionDAO{

	private List<Section> listaSeccion = new LinkedList<Section>();
	
	
	/**
	 * Carga del combobox de las secciones
	 */
	public List<Section> cargarSeccion (String line_id, String section_id,String empresa){
		
		//query para sacar todos las secciones
		String sql = "SELECT XX_VMR_Section_id, NAME, VALUE FROM XX_VMR_Section WHERE ISACTIVE = 'Y' ";
		
		//si se selecciono una seccion 
		if(line_id != null && Integer.parseInt(line_id)>0){
			sql += " AND XX_VMR_Line_ID=" + line_id; //se añade al query la condicion con el ID de la linea
		}
		
		sql += " order by value";
		
		//dejar un espacio en blanco en el combo box
		Section seccion = new Section();
		listaSeccion.add(seccion);
		
		//Realizar la consulta
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	
		//si la consulta arrojo algun valor
	   if (result != null){
		  for (int i=0;i<result.length;i++)
			{
				//Crear un objeto seccion, que va a guardar los resultados de la consulta
				seccion = new Section();
				  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
				int id = Integer.parseInt(result[i].get("XX_VMR_SECTION_ID").toString());
				seccion.setxx_vmr_section_id(id);
				seccion.setname(result[i].get("NAME").toString());
				seccion.setvalue(result[i].get("VALUE").toString());
				
				//permitir que se mantengan en la misma opcion que marco en el combo box para cuando se refresque la ventana
				if (section_id != null && id==Integer.parseInt(section_id))
					seccion.setPosicionar(1); //mantener en la opcion seleccionada del combo
				   else
					 seccion.setPosicionar(0); //no mantener en la opcion seleccionada del combo
				
				 //agregar la seccion a la lista
				listaSeccion.add(seccion);
			}
			
			//Retornar la lista de las secciones
		  return listaSeccion;	
		
		}
	 return null;	
	}
	
	
	public List<Section> borrarListaSeccion(){
		return this.listaSeccion = new LinkedList<Section>();
	}
}
