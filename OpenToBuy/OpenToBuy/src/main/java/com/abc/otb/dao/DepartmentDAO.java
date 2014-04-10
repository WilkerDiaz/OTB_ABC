package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.Department;

public interface DepartmentDAO {
    
	/**
	 * metodo para saber si han seleccionado una opcion en el combo box de departamento
	 * @return TRUE si se ha seleccionado alguna opcion en el combo box de departamento
	 * 		   FALSE no se ha seleccionado alguna opcion en el combo box de departamento
	 */
	boolean buscarPosicionar ();
	
	/**
	 * Metodo para cargar el combo box con todo los departamentos, partiendo del ID de la categoria
	 * @param category_id ID de la categoria, que se obtiene del combo box de categoria
	 * @param department_id ID del departamento que seleccionaron en el combo box
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna una lista con todos los departamentos de acuerdo a la categoria que se haya seleccionado
	 */
	List<Department> cargarDepartamento(String category_id, String department_id,String empresa);
}
