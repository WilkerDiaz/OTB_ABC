package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.Section;

public interface SectionDAO {
	
	/**
	 * Metodo para cargar el combo box con todas las secciones, partiendo del ID de la linea
	 * @param line_id ID de la linea, que se obtiene del combo box de linea
	 * @param section_id ID de la seccion que seleccionaron en el combo box
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna una lista con todos las secciones de acuerdo a la linea que se haya seleccionado
	 */
	List<Section> cargarSeccion (String line_id, String section_id, String empresa);
	
	/**
	 * metodo para borrar la lista de las secciones 
	 * @return retorna una lista vacia
	 */
	List<Section> borrarListaSeccion();


}
