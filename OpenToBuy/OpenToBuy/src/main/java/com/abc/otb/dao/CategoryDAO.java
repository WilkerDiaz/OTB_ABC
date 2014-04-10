package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.Category;

public interface CategoryDAO {
	
	/**
	 * metodo para cargar las categorias
	 * @param category_id id de la categoria que se selecciono en el combo box
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna una lista con todas las categorias
	 */
	List<Category> cargarCategorias(String category_id, String empresa);
}
