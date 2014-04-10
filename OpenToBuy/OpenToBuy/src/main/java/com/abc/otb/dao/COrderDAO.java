package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.COrder;

public interface COrderDAO {
	
	/**
	 * metodo para obtener todas las ordenes de compra de un usuario en particular
	 * @param empresa la empresa en la que quiero buscar las ordenes de compra (amand,beco,capy,abstracta,bcmuebles)
	 * @param usuario el usuario al que le quiero buscar las ordenes de compra
	 * @param cantidad cantidad de registros que quiere que arroje la consulta
	 * @return retorna una lista con todas las ordenes de compra del usuario buscado
	 */
	List<COrder> obtenerOrdenesCompra (String empresa,String usuario,int cantidad);

}
