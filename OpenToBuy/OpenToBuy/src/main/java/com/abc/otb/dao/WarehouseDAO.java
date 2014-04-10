package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.Warehouse;

public interface WarehouseDAO {
	
	/**
	 * metodo para cargar todos los almacenes
	 * @param warehouse_id  id del almacen que se selecciono en el combo box
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna una lista con todas los almacenes
	 */
	List<Warehouse> cargarAlmacenes(String warehouse_id, String empresa);

}
