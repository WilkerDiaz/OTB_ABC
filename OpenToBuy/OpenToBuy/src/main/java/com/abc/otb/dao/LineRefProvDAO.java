package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.LineRefProv;

public interface LineRefProvDAO {

	 List<LineRefProv> obtenerProductosOrdenes (String empresa,String purchaseOrden,String usuario,int cantidad);
}
