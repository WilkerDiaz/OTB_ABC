package com.abc.otb.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.LineRefProvDAO;
import com.abc.otb.logico.COrder;
import com.abc.otb.logico.LineRefProv;
import com.abc.otb.mediadorbd.MediadorBD;

public class LineRefProvDAOImpl implements LineRefProvDAO{
	
	public List<LineRefProv> obtenerProductosOrdenes (String empresa,String idOrder,String usuario,int cantidad){
		
		List<LineRefProv> listaProductosOrdenes = new LinkedList<LineRefProv>();	
		
		 String sql = "select l.name linea,"+
				 		"s.name seccion,"+
				 		"b.name marca,"+
				 		"lc.name caracteristica,"+
				 		"cv.name concepto,"+
				 		"lp.qty cantidad,"+ 
				 		"round (lp.xx_saleprice,2) precio,"+ 
				 		"round (lp.xx_taxamount,2) iva,"+
				 		"lp.XX_SALEPRICEPLUSTAX precioIva"+

						" from XX_VMR_PO_LineRefProv lp, xx_vmr_line l, xx_vmr_section s," +
						" xx_vme_conceptvalue cv, xx_vmr_brand b, xx_vmr_longcharacteristic lc"+
						
						" where c_order_id = "+idOrder+
						" and lp.xx_vmr_line_id = l.xx_vmr_line_id"+
						" and lp.xx_vmr_section_id = s.xx_vmr_section_id"+
						" and lp.xx_vme_conceptvalue_id = cv.xx_vme_conceptvalue_id"+
						" and lp.xx_vmr_brand_id = b.xx_vmr_brand_id"+
						" and lp.xx_vmr_longcharacteristic_id = lc.xx_vmr_longcharacteristic_id";
		 
		 	Map[] result = MediadorBD.realizarConsulta(sql, empresa);
		 	
		 	if (result != null){
		 		LineRefProv referencia = new LineRefProv();
		 		for (int i=0;i<result.length;i++){
		 			if (i==(cantidad-1)){ 
		 			  referencia.setusuario(usuario);
		 			  referencia.setlinea(result[i].get("LINEA").toString());
		 			  referencia.setseccion(result[i].get("SECCION").toString());
		 			  referencia.setmarca(result[i].get("MARCA").toString());
		 			  referencia.setcaracteristica(result[i].get("CARACTERISTICA").toString());
		 			  referencia.setconcepto(result[i].get("CONCEPTO").toString());
		 			  referencia.setcantidad(result[i].get("CANTIDAD").toString());
		 			  referencia.setprecio(result[i].get("PRECIO").toString());
		 			  referencia.setiva(result[i].get("IVA").toString());
		 			  referencia.setprecioIva(result[i].get("PRECIOIVA").toString());
		 			 
		 			  listaProductosOrdenes.add(referencia);
		 			}
		 		}
		 		 referencia.setlimite(result.length);
		 	}
		 return listaProductosOrdenes;
	  }
	  
public List<LineRefProv> obtenerProductosOrdenesCapuy (String empresa,String idOrder,String usuario,int cantidad){
		
		List<LineRefProv> listaProductosOrdenes = new LinkedList<LineRefProv>();	
		
		 String sql = "select p.name nombre,"+
						"ol.qtyordered cantidad,"+
						"ol.priceentered precio,"+
						"ol.linenetamt pvp,"+
						"d.name departamento,"+
						"s.name seccion,"+
						"t.name tipo"+
						" from c_orderline  ol,m_product p,"+
						"xx_cap_departamento d,"+
						"xx_cap_section s,"+ 
						"xx_cap_type t"+
						" where ol.m_product_id = p.m_product_id"+ 
						" and p.xx_cap_departamento_id = d.xx_cap_departamento_id"+
						" and p.xx_cap_departamento_id = s.xx_cap_departamento_id"+
						" and s.xx_cap_section_id = t.xx_cap_section_id"+
						" and p.xx_cap_type_id = t.xx_cap_type_id"+
						" and ol.c_order_id = "+idOrder;
		 
		 	Map[] result = MediadorBD.realizarConsulta(sql, empresa);
		 	
		 	if (result != null){
		 		LineRefProv referencia = new LineRefProv();
		 		for (int i=0;i<result.length;i++){
		 			if (i==(cantidad-1)){ 
		 			  referencia.setusuario(usuario);
		 			  referencia.setdepartamento(result[i].get("DEPARTAMENTO").toString());
		 			  referencia.setseccion(result[i].get("SECCION").toString());
		 			  referencia.settipo(result[i].get("TIPO").toString());
		 			  referencia.setconcepto(result[i].get("NOMBRE").toString());
		 			  referencia.setcantidad(result[i].get("CANTIDAD").toString());
		 			  referencia.setprecio(result[i].get("PRECIO").toString());
		 			  referencia.setprecioIva(result[i].get("PVP").toString());
		 			 
		 			  listaProductosOrdenes.add(referencia);
		 			}
		 		}
		 		 referencia.setlimite(result.length);
		 	}
		 return listaProductosOrdenes;
	  }
	  
}
