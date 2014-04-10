package com.abc.otb.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abc.otb.dao.impl.AdRoleDAOImpl;
import com.abc.otb.dao.impl.COrderDAOImpl;
import com.abc.otb.dao.impl.LineRefProvDAOImpl;
import com.abc.otb.logico.COrder;
import com.abc.otb.logico.LineRefProv;
import com.abc.otb.mediadorbd.MediadorBD;

@Controller
public class OrdenCompraController {

	/**
	 * Controlador para mostrar la página de inicio
	 */
	@RequestMapping(value = "/ordendecompra.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView ordenesDeCompra (HttpServletRequest request,HttpServletResponse response) {

		List<COrder> listaOrdenCompra = new LinkedList<COrder>();	
		List<LineRefProv> listaProductosOrden = new LinkedList<LineRefProv>();	
		String usuario,empresa,cantidad,aprobar,rechazar,anterior,proximo,fecha,idOrder,ordenes,productos,cantidadProducto,anteriorProducto,proximoProducto;
		int auxCantidad,auxCantidadProducto;
		ModelAndView modelAndView = null;
		
		usuario = request.getParameter("usuario");
		empresa = request.getParameter("empresa");
		cantidad = request.getParameter("cantidad");
		aprobar = request.getParameter("aprobar");
		rechazar = request.getParameter("rechazar");
		anterior = request.getParameter("anterior");
		proximo = request.getParameter("proximo");
		fecha = request.getParameter("fecha");
		idOrder = request.getParameter("idOrder");
		ordenes = request.getParameter("ordenes");
		productos = request.getParameter("productos");
		cantidadProducto = request.getParameter("cantidadProducto");
		anteriorProducto = request.getParameter("anteriorProducto");
		proximoProducto = request.getParameter("proximoProducto");
		
		System.out.println("anterior "+anterior);
		System.out.println("proximo "+proximo);
		System.out.println("usuario "+usuario);
		System.out.println("empresa "+empresa);
		System.out.println("cantidad "+cantidad);
		System.out.println("usuario "+usuario);
		System.out.println("aprobar "+aprobar);
		System.out.println("rechazar "+rechazar);
		System.out.println("fecha "+fecha);
		System.out.println("clave "+idOrder);
		System.out.println("----------------------------");
		
		COrderDAOImpl orden = new COrderDAOImpl ();
		LineRefProvDAOImpl referencia = new LineRefProvDAOImpl();
		AdRoleDAOImpl rol = new AdRoleDAOImpl();
		
		if (empresa.equals("beco") || empresa.equals("amand")){
		  if (ordenes != null)	
			modelAndView = new ModelAndView ("ordenCompra");
		  else if (productos != null)
			modelAndView = new ModelAndView ("productosOrdenes");
		}
		else{
			if (ordenes != null)	
		      modelAndView = new ModelAndView ("ordenCompraCapuy");
			else if (productos != null)
			  modelAndView = new ModelAndView ("productosOrdenesCapuy");
		}
		 
		 if (cantidad == null)
			  cantidad = "1";
	 
		 //presionaron el boton anterior
		 if (anterior != null && proximo ==null){
		  auxCantidad = Integer.parseInt(cantidad);
		  auxCantidad--;
		  cantidad = String.valueOf(auxCantidad);
		 }
		 //presionaron el boton proximo
		 else if (anterior ==null && proximo != null){
			auxCantidad = Integer.parseInt(cantidad);
			auxCantidad++;
			cantidad = String.valueOf(auxCantidad);	 
		 }
		 
		 
		 if (cantidadProducto == null)
			 cantidadProducto = "1";
		 
		 //presionaron el boton anterior producto
		 if (anteriorProducto != null && proximoProducto ==null){
			 auxCantidadProducto = Integer.parseInt(cantidadProducto);
			 auxCantidadProducto--;
			 cantidadProducto = String.valueOf(auxCantidadProducto);
		 }
		 //presionaron el boton proximo producto
		 else if (anteriorProducto ==null && proximoProducto != null){
			 auxCantidadProducto = Integer.parseInt(cantidadProducto);
			 auxCantidadProducto++;
			 cantidadProducto = String.valueOf(auxCantidadProducto);	 
		 }
		 
		 List<String> adRoleUser = new LinkedList<String>();
		 adRoleUser = rol.getRoleUserId(empresa, usuario);
		 
		 String adUserId = adRoleUser.get(0);
		 String adRoleId = adRoleUser.get(1);
		 
		 boolean bandera = false;
		 
		 if (aprobar != null){
			bandera = orden.POLimits(idOrder, empresa, fecha);
		 
		 if (bandera == true){ //aprobar orden
			try {
				String sql = "update c_order set XX_OrderStatus='AP', XX_ApprovalDate = sysdate  where c_order_id = "+idOrder; 
				MediadorBD.realizarSentencia(sql, empresa);
			} catch (SQLException e) {
				System.out.println("error al aprobar la orden de compra "+idOrder+" error "+e);;
			}
		 }
		 else{ //no se pudo aprobar
			try {
				orden.rolesAprobacion(idOrder, empresa, adRoleId, adUserId, usuario);
			} catch (SQLException e) {
				System.out.println("exploto roles de aprobacion por error de sql "+e);
			} 
		 }
		}
		 
		 if (empresa.equals("beco") || empresa.equals("amand")){
		  if (ordenes != null)
		    listaOrdenCompra = orden.obtenerOrdenesCompra(empresa, usuario,Integer.parseInt(cantidad));
		  else if (productos != null)
		    listaProductosOrden = referencia.obtenerProductosOrdenes(empresa, idOrder, usuario, Integer.parseInt(cantidadProducto));
		 }
		 else{
		  if (ordenes != null)
		     listaOrdenCompra = orden.obtenerOrdenesCompraCapuy(empresa, usuario, Integer.parseInt(cantidad));
		  else if (productos != null)
		     listaProductosOrden = referencia.obtenerProductosOrdenesCapuy(empresa, idOrder, usuario, Integer.parseInt(cantidadProducto));
		 }
		   
		modelAndView.addObject("empresa",empresa);
		modelAndView.addObject("usuario",usuario);
		
		if (ordenes != null){
		  modelAndView.addObject("ordenes",listaOrdenCompra);
		  modelAndView.addObject("cantidad",cantidad);
		}
		else if (productos != null){
		  modelAndView.addObject("productos",listaProductosOrden);
		  modelAndView.addObject("idOrder",idOrder);
		  modelAndView.addObject("cantidadProducto",cantidadProducto);
		}
		
		return modelAndView;
	}
}
