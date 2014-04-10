package com.abc.otb.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.COrderDAO;
import com.abc.otb.logico.AdRole;
import com.abc.otb.logico.COrder;
import com.abc.otb.mediadorbd.MediadorBD;

public class COrderDAOImpl implements COrderDAO {
	
  public List<COrder> obtenerOrdenesCompra (String empresa,String usuario,int cantidad){
	  String sql;
	  List<COrder> listaOrdenCompra = new LinkedList<COrder>();	
	  COrder orden = null;
	  SimpleDateFormat df = new SimpleDateFormat("m/dd/yyyy");
      SimpleDateFormat df2 = new SimpleDateFormat("dd/mm/yyyy");
      Date date = null;
	  
	  //consulta para obtener todas las ordenes de compra del usuario que se pasa por parametro
	  sql = "WITH vendedor AS (SELECT c_bpartner_id,NAME FROM c_bpartner), "+
	  "creador as (select ad_user_id,name from ad_user)"+
				"SELECT z.* FROM ( "+
				" SELECT x.* FROM ( "+
				" SELECT o.c_order_id orderId, "+
				"creado.name creadoPor,"+
				" o.documentno PurchaseOrdenNo, "+
				"  o.XX_ORDERTYPE TipoDeOrden, "+
				" (CASE "+
				"      WHEN o.XX_OrderStatus = 'AP' THEN 'Aprobada' "+
				"      WHEN o.XX_OrderStatus = 'AN' THEN 'Anulada' "+
				"      WHEN o.XX_OrderStatus = 'PRO' THEN 'Proforma' "+
				"      END) EstadoOC, "+
				
				"     (CASE "+
				"       WHEN o.XX_POType = 'POM' THEN 'O/C Productos para la venta' "+
				"       ELSE 'O/C Bienes y Servicios' "+
				"       END) TipoOC, "+
				
				"      d.NAME Departamento,c.NAME Categoria, "+
				"      o.created FechaCreacion,s.NAME Tema,se.NAME Temporada,co.NAME Coleccion, "+
				"      p.NAME Paquete,v.NAME Vendedor,u.NAME ContactoVendedor,bp.NAME Comprador,country.NAME Pais, "+
				
				"      (CASE WHEN o.XX_VLO_TypeDelivery = 'CD' THEN 'Centro de Distribución' "+
				"         WHEN o.XX_VLO_TypeDelivery = 'DD' THEN 'Despacho Directo' "+
				"         WHEN o.XX_VLO_TypeDelivery = 'PD' THEN 'Pre-Distribuida' "+
				"      END) TipoDespacho, "+
				
				"      w.NAME Tienda, o.XX_ArrivalDate FechaLlegada, o.XX_EstimatedDate FechaEstimada,o.XX_ProductQuantity CantidadProducto, "+
				"      round (o.XX_EstimatedMargin,2) MargenEstimado, round (o.TotalPVP,2) TotalPVP,o.XX_TotalPVPPlusTax TotalPVPIva, "+
				"      nvl (o.xx_alert2,0) mensaje, "+
				"      NVL (r.NAME,0) tareaCritica "+
				
				"   FROM c_order o INNER JOIN ad_user u ON (u.AD_User_ID = o.AD_User_ID "+
				"     AND o.createdby = (SELECT ad_user_id FROM ad_user WHERE upper(NAME)= upper('"+usuario+"') AND isactive = 'Y') "+
				"         AND o.issotrx = 'N' AND o.xx_alert2 IS NULL "+
				"         AND (o.XX_OrderStatus='AP' OR o.XX_OrderStatus='AN' OR o.XX_OrderStatus='PRO') "+
				"         AND (o.XX_OrderStatus='AP' OR o.XX_OrderStatus='AN' OR o.XX_OrderStatus='PRO') "+
				"         ) "+
				"  INNER JOIN creador creado ON creado.ad_user_id = o.createdby"+
				"  INNER JOIN xx_vmr_department d ON d.xx_vmr_department_id = o.xx_vmr_department_id "+
				"  INNER JOIN xx_vmr_category c  ON c.xx_vmr_category_id = o.xx_vmr_category_id "+
				"  INNER JOIN xx_vmr_subject s  ON s.xx_vmr_subject_id = o.xx_vmr_subject_id "+
				"  INNER JOIN XX_VMA_Season se  ON se.XX_VMA_Season_ID = o.XX_VMA_Season_ID "+
				"  INNER JOIN XX_VMR_Collection co  ON co.XX_VMR_Collection_ID = o.XX_VMR_Collection_ID "+
				"  INNER JOIN XX_VMR_Package p  ON p.XX_VMR_Package_ID = o.XX_VMR_Package_ID "+
				"  INNER JOIN C_BPartner bp  ON bp.C_BPartner_ID = o.XX_UserBuyer_ID "+
				"  INNER JOIN C_Country country  ON country.C_Country_ID = o.C_Country_ID "+
				"  INNER JOIN M_Warehouse w  ON w.M_Warehouse_ID = o.M_Warehouse_ID "+
				"  INNER JOIN vendedor v  ON v.c_bpartner_id = o.c_bpartner_id "+
				"  LEFT JOIN ad_role r  ON (r.ad_role_id = o.XX_CT_Responsible_ID) "+
				"   ) x "+
				" UNION "+
				
				" SELECT y.* FROM ( "+
				" SELECT o.c_order_id orderId, "+
				"creado.name creadoPor,"+
				" o.documentno PurchaseOrdenNo, "+
				"  o.XX_ORDERTYPE TipoDeOrden, "+
				" (CASE "+
				"      WHEN o.XX_OrderStatus = 'AP' THEN 'Aprobada' "+
				"      WHEN o.XX_OrderStatus = 'AN' THEN 'Anulada' "+
				"      WHEN o.XX_OrderStatus = 'PRO' THEN 'Proforma' "+
				"      END) EstadoOC, "+
				
				"     (CASE "+
				"       WHEN o.XX_POType = 'POM' THEN 'O/C Productos para la venta' "+
				"       ELSE 'O/C Bienes y Servicios' "+
				"       END) TipoOC, "+
				
				"      d.NAME Departamento,c.NAME Categoria, "+
				"      o.created FechaCreacion,s.NAME Tema,se.NAME Temporada,co.NAME Coleccion, "+
				"      p.NAME Paquete,v.NAME Vendedor,u.NAME ContactoVendedor,bp.NAME Comprador,country.NAME Pais, "+
				
				"      (CASE WHEN o.XX_VLO_TypeDelivery = 'CD' THEN 'Centro de Distribución' "+
				"         WHEN o.XX_VLO_TypeDelivery = 'DD' THEN 'Despacho Directo' "+
				"         WHEN o.XX_VLO_TypeDelivery = 'PD' THEN 'Pre-Distribuida' "+
				"      END) TipoDespacho, "+
				
				"      w.NAME Tienda,o.XX_ArrivalDate FechaLlegada,o.XX_EstimatedDate FechaEstimada,o.XX_ProductQuantity CantidadProducto, "+
				"      round (o.XX_EstimatedMargin,2) MargenEstimado, round (o.TotalPVP,2) TotalPVP,o.XX_TotalPVPPlusTax TotalPVPIva, "+
				"      nvl (o.xx_alert2,0) mensaje, "+
				"      NVL (r.NAME,0) tareaCritica "+
				
				"   FROM c_order o INNER JOIN ad_user u ON (u.AD_User_ID = o.AD_User_ID "+
				"         AND o.issotrx = 'N' "+
				"         AND o.XX_CT_Responsible_ID IN "+
				"         ( "+
				"            SELECT r.ad_role_id "+
				"            FROM ad_role r ,ad_user u,ad_user_roles ur "+
				"            WHERE "+
				"            r.ad_role_id = ur.ad_role_id AND "+
				"            u.ad_user_id = ur.ad_user_id AND "+
				"            upper (u.NAME) = upper('"+usuario+"') AND "+
				"            u.isactive ='Y' "+
				"         ) "+
				"         AND (o.XX_OrderStatus='AP' OR o.XX_OrderStatus='AN' OR o.XX_OrderStatus='PRO') "+
				"        ) "+
				
				"  INNER JOIN creador creado ON creado.ad_user_id = o.createdby"+
				"  INNER JOIN xx_vmr_department d ON d.xx_vmr_department_id = o.xx_vmr_department_id "+
				"  INNER JOIN xx_vmr_category c  ON c.xx_vmr_category_id = o.xx_vmr_category_id "+
				"  INNER JOIN xx_vmr_subject s  ON s.xx_vmr_subject_id = o.xx_vmr_subject_id "+
				"  INNER JOIN XX_VMA_Season se  ON se.XX_VMA_Season_ID = o.XX_VMA_Season_ID "+
				"  INNER JOIN XX_VMR_Collection co  ON co.XX_VMR_Collection_ID = o.XX_VMR_Collection_ID "+
				"  INNER JOIN XX_VMR_Package p  ON p.XX_VMR_Package_ID = o.XX_VMR_Package_ID "+
				"  INNER JOIN C_BPartner bp  ON bp.C_BPartner_ID = o.XX_UserBuyer_ID "+
				"  INNER JOIN C_Country country  ON country.C_Country_ID = o.C_Country_ID "+
				"  INNER JOIN M_Warehouse w  ON w.M_Warehouse_ID = o.M_Warehouse_ID "+
				"  INNER JOIN vendedor v  ON v.c_bpartner_id = o.c_bpartner_id "+
				"   LEFT JOIN ad_role r  ON (r.ad_role_id = o.XX_CT_Responsible_ID) "+
				"  ) y "+
			    ") z "+
				" ORDER BY z.PurchaseOrdenNo DESC";
	  
	  //realizar consulta en la base de datos
	  Map[] result = MediadorBD.realizarConsulta(sql, empresa);
	  
	  //si la consulta arrojo algun resultado
	   if (result != null){
		   orden = new COrder();
		   
		  //recorro toda la consulta
		  for (int i=0;i<result.length;i++){
			  
			if (i==(cantidad-1)){  
			  //crear el objeto de la orden de compra con los datos de la consulta
			  orden.setid(result[i].get("ORDERID").toString());
			  orden.setusuario(result[i].get("CREADOPOR").toString());
			  orden.setpurchase_no(result[i].get("PURCHASEORDENNO").toString());
			  orden.settipo(result[i].get("TIPODEORDEN").toString());
			  orden.setestado(result[i].get("ESTADOOC").toString());
			  orden.settipo_orden_compra(result[i].get("TIPOOC").toString());
			  orden.setdepartamento(result[i].get("DEPARTAMENTO").toString());
			  orden.setcategoria(result[i].get("CATEGORIA").toString());
			  
			  orden.settema(result[i].get("TEMA").toString());
			  orden.settemporada(result[i].get("TEMPORADA").toString());
			  orden.setcoleccion(result[i].get("COLECCION").toString());
			  orden.setpaquete(result[i].get("PAQUETE").toString());
			  orden.setvendedor(result[i].get("VENDEDOR").toString());
			  orden.setcontacto_vendedor(result[i].get("CONTACTOVENDEDOR").toString());
			  orden.setcomprador(result[i].get("COMPRADOR").toString());
			  orden.setpais(result[i].get("PAIS").toString());
			  orden.settipo_despacho(result[i].get("TIPODESPACHO").toString());
			  orden.settienda(result[i].get("TIENDA").toString());
			  
			  try {
				 if (result[i].get("FECHACREACION") != null){
				  date = df.parse(result[i].get("FECHACREACION").toString()); 
				  orden.setfecha_creacion(df2.format(date));
				 }
				 else{
				   orden.setfecha_creacion(" "); 
				 }
				  
				 if (result[i].get("FECHALLEGADA") != null){
				  date = df.parse(result[i].get("FECHALLEGADA").toString());
				  orden.setfecha_llegada(df2.format(date));
				 }
				 else{
					 orden.setfecha_llegada(" "); 
				 }
				
			 if (result[i].get("FECHAESTIMADA") != null){
				date = df.parse(result[i].get("FECHAESTIMADA").toString());
				orden.setfecha_estimada(df2.format(date));
			 }
			 else{
				 orden.setfecha_estimada(" "); 
			 }
			} catch (ParseException e) {
				e.printStackTrace();
			}
	  
			  orden.setcantidad_producto(result[i].get("CANTIDADPRODUCTO").toString());
			  orden.setmargen_estimado(result[i].get("MARGENESTIMADO").toString());
			  orden.settotal(result[i].get("TOTALPVP").toString());
			  orden.settotal_iva(result[i].get("TOTALPVPIVA").toString());
			  orden.setmensaje(result[i].get("MENSAJE").toString());
			  orden.settarea_critica(result[i].get("TAREACRITICA").toString());
			  
			}
		  }
		  orden.setLimite(result.length);
		  listaOrdenCompra.add(orden);
	   }
	  return listaOrdenCompra;
  }
  
  public List<COrder> obtenerOrdenesCompraCapuy (String empresa,String usuario,int cantidad){
	  String sql;
	  List<COrder> listaOrdenCompra = new LinkedList<COrder>();	
	  COrder orden = null;
	  SimpleDateFormat df = new SimpleDateFormat("m/dd/yyyy");
      SimpleDateFormat df2 = new SimpleDateFormat("dd/mm/yyyy");
      Date date = null;
      
      sql = "with cantidad_ordenes as ("+
			  "select o.c_order_id, sum (ol.qtyordered) as cantidadProducto, t.name as tax"+
			   " from c_orderline ol, c_order o, c_tax t"+
			   " where o.c_order_id = ol.c_order_id"+
			   " and o.IsSOTrx='N' and o.IsReturnTrx='N'"+
			   " and t.c_tax_id = ol.c_tax_id"+
			   " group by o.c_order_id,t.name)"+
			   
			   "select z.* from ("+
			    "select x.* from ("+
			     "select o.c_order_id orderId, org.name organizacion,o.documentno PurchaseOrdenNo, NVL (o.description,' ') descripcion,d.name tipoDocumento,"+
			            "bp.name proveedor,o.dateordered fechaOrdenada,o.datepromised fechaPrometida,bpl.name direccionProveedor,"+
			            "co.cantidadProducto,co.tax impuesto,round (o.totallines,2) totalPVP, round (o.GrandTotal,2) totalPVPIva,"+
			            "nvl (o.xx_alert2,0) mensaje,NVL (r.NAME,0) tareaCritica"+ 
			
			            " from c_order o INNER JOIN ad_user u on (o.salesrep_id = u.ad_user_id and upper(name)= upper('"+usuario+"')"+
			                    " and o.IsSOTrx='N' and o.IsReturnTrx='N')"+
			     "INNER JOIN ad_org org on (org.ad_org_id = o.ad_org_id)"+
			     "INNER JOIN c_doctype d on (o.C_DocTypeTarget_ID = d.C_DocType_id)"+
			     "INNER JOIN c_bpartner bp ON (o.c_bpartner_id = bp.c_bpartner_id)"+
			     "INNER JOIN c_bpartner_location bpl ON (o.c_bpartner_location_id = bpl.c_bpartner_location_id)"+
			     "INNER JOIN cantidad_ordenes co ON (o.c_order_id = co.c_order_id)"+
			     "LEFT JOIN ad_role r  ON (r.ad_role_id = o.XX_CT_Responsible_ID)"+
			     "order by o.documentno desc"+
			   ") x"+
			
			" union "+
			 	
			 "select y.* from ("+
              "select o.c_order_id orderId, org.name organizacion,o.documentno PurchaseOrdenNo, NVL (o.description,' ') descripcion,d.name tipoDocumento,"+
              "bp.name proveedor,o.dateordered fechaOrdenada,o.datepromised fechaPrometida,bpl.name direccionProveedor,"+
              "co.cantidadProducto,co.tax impuesto,round (o.totallines,2) totalPVP, round (o.GrandTotal,2) totalPVPIva,"+
              "nvl (o.xx_alert2,0) mensaje,"+
              "NVL (r.NAME,0) tareaCritica"+
      	
			  " from c_order o INNER JOIN ad_user u on (o.salesrep_id = u.ad_user_id"+
              " and o.IsSOTrx='N' and o.IsReturnTrx='N'"+
              " AND o.XX_CT_Responsible_ID IN"+ 
				         "("+ 
				            "SELECT r.ad_role_id"+ 
				            " FROM ad_role r ,ad_user u,ad_user_roles ur"+ 
				            " WHERE"+ 
				            " r.ad_role_id = ur.ad_role_id AND"+ 
				            " u.ad_user_id = ur.ad_user_id AND"+ 
				            " upper (u.NAME) = upper('"+usuario+"') AND"+ 
				            " u.isactive ='Y'"+ 
				         ")" +
				      ")"+
			 "INNER JOIN ad_org org on (org.ad_org_id = o.ad_org_id)"+
			 "INNER JOIN c_doctype d on (o.C_DocTypeTarget_ID = d.C_DocType_id)"+
			 "INNER JOIN c_bpartner bp ON (o.c_bpartner_id = bp.c_bpartner_id)"+
			 "INNER JOIN c_bpartner_location bpl ON (o.c_bpartner_location_id = bpl.c_bpartner_location_id)"+
			 "INNER JOIN cantidad_ordenes co ON (o.c_order_id = co.c_order_id)"+
             "LEFT JOIN ad_role r  ON (r.ad_role_id = o.XX_CT_Responsible_ID)"+
			 "order by o.documentno desc"+
		   ") y"+
		 ") z "+
		 "ORDER BY z.PurchaseOrdenNo DESC";
      
      //realizar consulta en la base de datos
	  Map[] result = MediadorBD.realizarConsulta(sql, empresa);
	  
	//si la consulta arrojo algun resultado
	   if (result != null){
		   orden = new COrder();
		  //recorro toda la consulta
		  for (int i=0;i<result.length;i++){
			  
			if (i==(cantidad-1)){  
			  //crear el objeto de la orden de compra con los datos de la consulta
			  orden.setusuario(usuario);
			  orden.setid(result[i].get("ORDERID").toString());
			  orden.settienda(result[i].get("ORGANIZACION").toString());
			  orden.setpurchase_no(result[i].get("PURCHASEORDENNO").toString());
			  orden.setdescripcion(result[i].get("DESCRIPCION").toString());
			  orden.settipo(result[i].get("TIPODOCUMENTO").toString());
			  orden.setproveedor(result[i].get("PROVEEDOR").toString());
			  
			  try {
				  date = df.parse(result[i].get("FECHAORDENADA").toString()); 
				  orden.setfecha_creacion(df2.format(date));
				  
				  date = df.parse(result[i].get("FECHAPROMETIDA").toString());
				  orden.setfecha_llegada(df2.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			  
			  orden.setdireccion_proveedor(result[i].get("DIRECCIONPROVEEDOR").toString());
			  orden.setcantidad_producto(result[i].get("CANTIDADPRODUCTO").toString());
			  orden.setiva(result[i].get("IMPUESTO").toString());
			  orden.settotal(result[i].get("TOTALPVP").toString());
			  orden.settotal_iva(result[i].get("TOTALPVPIVA").toString());
			  orden.setmensaje(result[i].get("MENSAJE").toString());
			  orden.settarea_critica(result[i].get("TAREACRITICA").toString());
			}
		  }
		  
		  orden.setLimite(result.length);
		  listaOrdenCompra.add(orden);
	   }
      
      return listaOrdenCompra;
  }
  
  public boolean POLimits(String idOrder,String empresa,String fechaEstimada){

	  boolean bandera = false;
	  
		try 
		{		
			BigDecimal totalOC = new BigDecimal(0);
			BigDecimal limit = new BigDecimal(0);
			BigDecimal invFinalProy = BigDecimal.ZERO; 
			BigDecimal invFinalPresu = BigDecimal.ZERO; 
			BigDecimal sumOCApproved = BigDecimal.ZERO; 
			
			String idLine,idDpto,MVMRPOApprovalId;
		
			System.out.println("sacando limites");
			
			String sql = ("DELETE FROM XX_VMR_PO_APPROVAL WHERE C_ORDER_ID = '"+idOrder+"' ");
			
		   //realizar consulta en la base de datos
//		   MediadorBD.realizarSentencia(sql, empresa);
			
			String sql1 = "SELECT DISTINCT B.XX_VMR_LINE_ID AS LINE, C.XX_VMR_DEPARTMENT_ID AS DPTO " +
					"FROM C_ORDER A, XX_VMR_PO_LINEREFPROV B, XX_VMR_DEPARTMENT C, XX_VMR_LINE D " +
					"WHERE A.C_ORDER_ID = B.C_ORDER_ID " +
					"AND A.XX_VMR_DEPARTMENT_ID = C.XX_VMR_DEPARTMENT_ID " +
					"AND B.XX_VMR_LINE_ID = D.XX_VMR_LINE_ID " +
					"AND A.C_ORDER_ID = "+ idOrder;
				
			Map[] result1 = MediadorBD.realizarConsulta(sql1, empresa);
		    
		 if (result1 != null){
		   for (int i=0;i<result1.length;i++)
		    {
			  //obtener el ID de la orden de compra aprobada
			   String sqlMVMRPOApproval = "select b.XX_VMR_PO_APPROVAL_ID " +
			   		"from C_ORDER A, XX_VMR_PO_Approval B WHERE A.C_ORDER_ID = B.C_ORDER_ID  AND A.C_ORDER_ID = "+idOrder;
			   
			   //Realizar la consulta
			   Map[] MVMRPOApproval = MediadorBD.realizarConsulta(sqlMVMRPOApproval, empresa);
			   
			   //obtener el Id
			   MVMRPOApprovalId = MVMRPOApproval[0].get("XX_VMR_PO_APPROVAL_ID").toString();
			   
		    	idLine = result1[i].get("LINE").toString();
		    	idDpto = result1[i].get("DPTO").toString();
		    	
		    	String sql2 = ("SELECT SUM (XX_SALEPRICE * QTY ) AS RESULTADO " +
		    			"FROM C_ORDER A, XX_VMR_PO_LINEREFPROV B " +
		    			"WHERE B.XX_VMR_LINE_ID = '"+idLine+"' " +
		    			"AND B.C_ORDER_ID = A.C_ORDER_ID " +
		    			"AND A.C_ORDER_ID = "+ idOrder);
		    	
		    	Map[] result2 = MediadorBD.realizarConsulta(sql2, empresa);

		    	 if (result2[0].get("RESULTADO").toString() != null)
			      {
			    	totalOC = new BigDecimal(result2[0].get("RESULTADO").toString());
			    		
			    	//formato fecha
			    	SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			    	SimpleDateFormat df2 = new SimpleDateFormat("yyyymm");
			    	
			    	//pasar de el formarto dd/mm/yyyy a yyyymm
			    	Date date = df.parse(fechaEstimada);
			    	String budgetYearMonth = df2.format(date);
			    	
			    	 invFinalProy = new BigDecimal(0); // Calculo del Inventario Final Proyectado
					 
					 if (invFinalProy.compareTo(BigDecimal.ZERO)==-1) //invenProy < 0
						 invFinalProy = BigDecimal.ZERO;   	
						 
				    	String sql3= "SELECT SUM (XX_SALEPRICE * QTY ) AS RESULTADO " +
				    			"\nFROM C_ORDER A, XX_VMR_PO_LINEREFPROV B " +
				    			"\nWHERE B.XX_VMR_LINE_ID = '"+idLine+"' " +
				    			"\nAND B.C_ORDER_ID = A.C_ORDER_ID " +
				    			"\nAND ( " +
				    			"\n	(XX_OrderType = 'Importada' and XX_OrderStatus = 'EP' and TRUNC(XX_InsertedStatusDate) = TRUNC(SYSDATE)) " +
				    			"\n OR 	 " +
				    			"\n	(XX_OrderType = 'Nacional' and XX_OrderStatus = 'AP' and TRUNC(XX_ApprovalDate) = TRUNC(SYSDATE)) " +
				    			"\n )";
				    	
				    	Map[] result3 = MediadorBD.realizarConsulta(sql3, empresa);
				    	
				     if (result3[0].get("RESULTADO") != null)
				    	sumOCApproved = new BigDecimal(result3[0].get("RESULTADO").toString());
					    				   
					 String sql4 = (" SELECT SUM (XX_FINALINVAMOUNTBUD2) AS Inventa_Final_Presu," +
					 				" SUM(XX_FINALINVAMOUNTPROJD) Inventa_Final_Proy" +
					 				" FROM XX_VMR_PRLD01 " +
							 		" WHERE XX_VMR_DEPARTMENT_ID = '"+idDpto+"' AND XX_VMR_LINE_ID = '"+idLine+"' " +
						    		" AND XX_BUDGETYEARMONTH='"+budgetYearMonth+ "' ");
						
					 Map[] result4 = MediadorBD.realizarConsulta(sql4, empresa);
								   
					 if (result4 != null)
					 {
						 invFinalPresu = new BigDecimal(result4[0].get("INVENTA_FINAL_PRESU").toString()); // Inventario Final Presupuestado
						 invFinalProy = new BigDecimal(result4[0].get("INVENTA_FINAL_PROY").toString()); // Inventario Final Proyectado
						 
						 System.out.println("inventario final presupuestado "+invFinalPresu);
						 System.out.println("");
						 System.out.println("inventario final proyectado "+invFinalProy);
						 
						 if(sumOCApproved == null)
							 sumOCApproved = BigDecimal.ZERO;
						 //Se suma al inventario final presupuestado el total de las OC aprobadas en el dia actual. 
						 invFinalProy.add(sumOCApproved);
						 
						 limit = invFinalPresu.subtract(invFinalProy);
					 }
					 
					 BigDecimal porcentaje = BigDecimal.ZERO;
					    
					 if(invFinalPresu.compareTo(BigDecimal.ZERO)>0){
						    
						 //Calculamos el porcentaje en exceso
						 if(totalOC.compareTo(limit)<=0)
						 {
							 porcentaje = new BigDecimal(-1);
							 bandera = true;
						 }
						 else{
							 porcentaje = ((totalOC.subtract(limit)).divide(invFinalPresu, 2, RoundingMode.HALF_UP)).abs();
						 }
					 }
					 else{
						 //Si el Inv. F. Presupuestano no existe el porcentaje deberia quedar en 100 para que lo aprueben los gerentes
						 porcentaje = BigDecimal.ONE;
					 }
							
					 //Para ver el porcentaje de 0 a 100
					 porcentaje = porcentaje.multiply(new BigDecimal(100));
					 
					 String update = "UPDATE XX_VMR_PO_Approval set xx_percentageexcess="+porcentaje+","+
							 		"xx_limit = "+limit+","+
							 		"xx_limittotal="+totalOC+","+
							 		"XX_VMR_Department_ID = "+idDpto+","+
							 		"XX_VMR_Line_ID = "+idLine+
							 		" WHERE XX_VMR_PO_Approval_ID = "+MVMRPOApprovalId;	
					MediadorBD.realizarSentencia(update, empresa);
					
					System.out.println("el porcentaje "+porcentaje+" el limite "+limit);
			    }
			      	
		    } // end while 
		  } // if de la primera consulta
		 

		}
	
		catch(Exception e) 
		{  
			e.printStackTrace();
		}
		return bandera;
	}
  
  
 public boolean rolesAprobacion (String idOrder,String empresa,String adRoleId,String adUserId,String usuario) throws SQLException{

	   boolean bandera = false;
	   
		// en primer lugar buscamos en todos los limites de la orden su mayor porcentaje de exceso
		String SQL_limits = "SELECT max(XX_PercentageExcess) porcentaje" +
							" FROM XX_VMR_PO_Approval " +
							" WHERE C_ORDER_ID = "+idOrder;

		Map[] result = MediadorBD.realizarConsulta(SQL_limits, empresa);
	
		BigDecimal porcentajeExcesoMax = BigDecimal.ZERO; 

		if (result != null)
			porcentajeExcesoMax = new BigDecimal(result[0].get("PORCENTAJE").toString());
		
		if(porcentajeExcesoMax.compareTo(BigDecimal.ZERO)<0)
			porcentajeExcesoMax = BigDecimal.ZERO;
		
		String sql_order = "select XX_FirstAppManager_ID, nvl (XX_CT_Responsible_ID,0) XX_CT_RESPONSIBLE_ID ,XX_OrderStatus from c_order " +
				"where c_order_id = "+idOrder;
		
		Map[] orden = MediadorBD.realizarConsulta(sql_order, empresa);
		
		/*
		 * Roles de Aprobación
		 */
		
		AdRole approvalRole = getApprovalRole(adRoleId,empresa);

		if(approvalRole!=null){ //Si el rol que intenta aprobar se encuentra en la tabla de roles de aprobacion
			
			if(approvalRole.getrank_low().compareTo(porcentajeExcesoMax)<=0 && approvalRole.getrank_high().compareTo(porcentajeExcesoMax)>=0){ //Si esta dentro del rango de aprobacion
				
				if(approvalRole.getneeds_other_role().equalsIgnoreCase("N")){ // Si no necesito otro Rol, aprueba
					
					String SQL_Update = "UPDATE XX_VMR_PO_Approval " +
										"SET IsApproved='Y'" +
										"WHERE C_ORDER_ID = "+idOrder;

				    MediadorBD.realizarSentencia(SQL_Update, empresa);

				    String respCT = " UPDATE C_ORDER set XX_CT_Responsible_ID=null  " +
							"WHERE C_ORDER_ID = "+idOrder;
				    
				    MediadorBD.realizarSentencia(respCT, empresa);
				    
				    bandera = true;
				}else
				{ //Si necesito otro Rol
					
					//Si no se ha aprobado por primera vez le asigno el primer gerente y luego digo que necesita otro gerente
					if(new Integer (orden[0].get("XX_FIRSTAPPMANAGER_ID").toString())==0){
						
						String sql1 = "UPDATE C_ORDER SET XX_FirstAppManager_ID = "+adUserId+"," +
								"XX_Alert2 = O/C sobregirada (último intento de aprobación: "+usuario.toUpperCase()+")"+
								" WHERE c_order_id = "+idOrder;
						
						MediadorBD.realizarSentencia(sql1, empresa);
																		
						//si el rol responsable es igual al rol actual, se actualiza el siguiente responsable
						//en caso contrario no se modifica el responsable. 
						if(adRoleId==orden[0].get("XX_CT_RESPONSIBLE_ID").toString() || new Integer (orden[0].get("XX_CT_RESPONSIBLE_ID").toString()) == 0){
							String sql2 = "UPDATE C_ORDER SET XX_CT_Responsible_ID = "+approvalRole.getnotifying_role()+
									" WHERE c_order_id = "+idOrder;
							
							MediadorBD.realizarSentencia(sql2, empresa);
						}
						//FIN GHUCHET
						
					/*	ADialog.info(1, new Container(), "XX_NotifyOtherManager");
						setProcessActive(false);
						return "";*/
						bandera = false;
			
					}
					else{
						//Si no es la primera vez que aprueba un gerente verifico si es el mismo que la volvio a aprobar
						
						if(orden[0].get("XX_FIRSTAPPMANAGER_ID").toString() !=adUserId || orden[0].get("XX_ORDERSTATUS").toString().equals("PEN")){ // Si no es el mismo, aprueba
							String SQL_Update = "UPDATE XX_VMR_PO_Approval " +
							 "SET IsApproved='Y' " +
							 "WHERE C_ORDER_ID = "+idOrder;
			
					    	MediadorBD.realizarSentencia(SQL_Update, empresa);
						    
						    String respCT = " UPDATE C_ORDER set XX_CT_Responsible_ID=null  " +
							"WHERE C_ORDER_ID = "+idOrder;
						    MediadorBD.realizarSentencia(respCT, empresa);
						    
						    bandera = true;
					    }
						else{
							bandera = false;
							//Si es el mismo primer gerente le vuelve a decir que necesita otro gerente
					    /*	ADialog.info(1, new Container(), "XX_NotifyOtherManager");
					    	setProcessActive(false);
							return "";*/
					    }	
					}
					
				}
				
			}else{ //Si no esta dentro de su rango de aprobacion, le enviamos un correo al siguiente rol
					

				String sql1 = "UPDATE C_ORDER SET XX_Alert2 = 'O/C sobregirada (último intento de aprobación: "+usuario.toUpperCase()+")'"+
						" WHERE c_order_id = "+idOrder;
				
				MediadorBD.realizarSentencia(sql1, empresa);
				
				//GHUCHET Si el rol responsable es igual al rol actual, se actualiza el siguiente responsable
				//en caso contrario no se modifica el responsable. 
				if(Integer.parseInt(adRoleId)==new Integer (orden[0].get("XX_CT_RESPONSIBLE_ID").toString())  || 
						new Integer (orden[0].get("XX_CT_RESPONSIBLE_ID").toString()) == 0  ){					
					
					String sql2 = "UPDATE C_ORDER SET XX_CT_Responsible_ID = "+approvalRole.getnotifying_role()+
							" WHERE c_order_id = "+idOrder;
					
					MediadorBD.realizarSentencia(sql2, empresa);
				}
				//FIN GHUCHET
				
				/*ADialog.info(1, new Container(), "XX_AccesDenied");
				setProcessActive(false);
				return "Acces Denied";*/
				bandera = false;
			}
			
		}else{
			bandera = false;
			/*ADialog.info(1, new Container(), "XX_NotPermissionApprovePO");
			setProcessActive(false);
			return Msg.getMsg(Env.getCtx(), "XX_NotPermissionApprovePO");*/
		}
		
	return bandera;
		
	}

private AdRole getApprovalRole(String adRoleId, String empresa){
	String sqlRole = "SELECT xx_ranklow,xx_rankhigh,xx_needsotherrole,XX_NotifyingRole_ID FROM XX_VMR_PO_ApprovalRole " +
			 "WHERE AD_ROLE_ID = "+adRoleId;
	
	Map[] result = MediadorBD.realizarConsulta(sqlRole, empresa);
	AdRole rol = new AdRole();
	
	if (result != null){
	   	rol.setrank_low(new BigDecimal (result[0].get("XX_RANKLOW").toString()));
	   	rol.setrank_high(new BigDecimal (result[0].get("XX_RANKHIGH").toString()));
	   	rol.setneeds_other_role(result[0].get("XX_NEEDSOTHERROLE").toString());
	   	rol.setnotifying_role(result[0].get("XX_NOTIFYINGROLE_ID").toString());
	}
  return rol;
 }
}
