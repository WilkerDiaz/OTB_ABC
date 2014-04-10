package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.Line;

public interface LineDAO {
	
	/**
	 * Metodo para cargar el combo box con todas las lineas, partiendo del ID del departamento
	 * @param department_id ID del departamento, que se obtiene del combo box de departamento
	 * @param line_id ID de la linea que seleccionaron en el combo box
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna una lista con todos las lineas de acuerdo al departamento que se haya seleccionado
	 */
	List<Line> cargarLinea (String department_id,String line_id,String empresa);
	
	/**
	 * metodo para saber si han seleccionado una opcion en el combo box de linea
	 * @return TRUE si se ha seleccionado alguna opcion en el combo box de departamento
	 * 		   FALSE no se ha seleccionado alguna opcion en el combo box de departamento
	 */
	boolean buscarPosicionar ();
	
	/**
	 * metodo para borrar la lista de las lineas
	 * @return retorna una lista vacia 
	 */
	List<Line> borrarListaLinea();

}
