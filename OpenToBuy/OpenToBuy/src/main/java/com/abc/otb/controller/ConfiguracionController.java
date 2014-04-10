package com.abc.otb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abc.otb.logico.ConfiguracionBaseDatos;
import com.abc.otb.mediadorbd.MediadorBD;

@Controller
public class ConfiguracionController {
	
	/**
	 * metodo para cargar la configuracion de las base de datos de todas las empresas a partir de un xml
	 * @param request parametros que recibe desde la interfaz
	 * @param response
	 * @return retorna una vista a la pantalla "configuracion" con todos los datos que obtuvo del xml
	 */
	@RequestMapping(value = "/configuracion.*",  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cargarConfiguracion(HttpServletRequest request,HttpServletResponse response){

		ModelAndView modelAndView = new ModelAndView ("configuracion");
		ConfiguracionBaseDatos configuracion = new ConfiguracionBaseDatos();
		
		//cargar la configuracion de beco desde el XML
		configuracion = MediadorBD.obtenerDatosXML("BECO"); //buscar en el XML la configuracion de beco
		
		modelAndView.addObject("ojdbcBeco",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlBeco",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioBeco",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordBeco",configuracion.getPassword()); // cargar la contraseña
		
		//cargar la configuracion de amand desde el XML
		configuracion = MediadorBD.obtenerDatosXML("AMAND");  //buscar en el XML la configuracion de amand
		
		modelAndView.addObject("ojdbcAmand",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlAmand",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioAmand",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordAmand",configuracion.getPassword()); // cargar la contraseña
		
		//cargar la configuracion de abstracta desde el XML
		configuracion = MediadorBD.obtenerDatosXML("ABSTRACTA"); //buscar en el XML la configuracion de abstracta
		
		modelAndView.addObject("ojdbcAbstracta",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlAbstracta",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioAbstracta",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordAbstracta",configuracion.getPassword()); // cargar la contraseña
		
		//cargar la configuracion de bc muebles desde el XML
		configuracion = MediadorBD.obtenerDatosXML("BCMUEBLES"); //buscar en el XML la configuracion de bc muebles
		
		modelAndView.addObject("ojdbcBcMuebles",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlBcMuebles",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioBcMuebles",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordBcMuebles",configuracion.getPassword()); // cargar la contraseña
		
		//cargar la configuracion de capuy desde el XML
		configuracion = MediadorBD.obtenerDatosXML("CAPUY"); //buscar en el XML la configuracion de capuy
		 
		modelAndView.addObject("ojdbcCapuy",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlCapuy",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioCapuy",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordCapuy",configuracion.getPassword()); // cargar la contraseña
		
		
		//cargar la configuracion del servidor central desde el XML
		configuracion = MediadorBD.obtenerDatosXML("CENTRAL"); //buscar en el XML la configuracion de capuy
				 
		modelAndView.addObject("ojdbcCentral",configuracion.getOjdbc()); //cargar el ojdbc
		modelAndView.addObject("urlCentral",configuracion.getUrl()); // cargar el url
		modelAndView.addObject("usuarioCentral",configuracion.getUsuario()); // cargar el usuario
		modelAndView.addObject("passwordCentral",configuracion.getPassword()); // cargar la contraseña
		 
		return modelAndView;
	}
	
	/**
	 * metodo para guardar la configuracion de las base de datos, lo guarda en un xml
	 * @param request parametros que recibe desde la pantalla "configuracion"
	 * @param response
	 * @return retorna una vista a la pantalla configuracion, la cual se encarga de cargar la configuracion de las base de datos
	 */
	@RequestMapping(value = "/guardarconfiguracion.*",  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView guardarConfiguracion(HttpServletRequest request,HttpServletResponse response){

		ArrayList <String> listaBeco = new ArrayList<String>();
		ArrayList <String> listaAmand = new ArrayList<String>();
		ArrayList <String> listaAbstracta = new ArrayList<String>();
		ArrayList <String> listaBcMuebles = new ArrayList<String>();
		ArrayList <String> listaCapuy = new ArrayList<String>();
		ArrayList <String> listaServCentral = new ArrayList<String>();
		
				
	   //valores beco
		listaBeco.add("nombre"); 
		listaBeco.add("BECO"); //nombre de la empresa
		
		listaBeco.add("ojdbc");
		listaBeco.add(request.getParameter("ojdbcBeco")); //driver de conexion de beco
		
		listaBeco.add("url");
		listaBeco.add(request.getParameter("urlBeco")); //url de conexion de la base de datos de beco
		
		listaBeco.add("usuario");
		listaBeco.add(request.getParameter("usuarioBeco")); //usuario de base de datos de beco
		
		listaBeco.add("password");
		listaBeco.add(request.getParameter("passwordBeco")); //contraseña de base de datos de beco
		
		
		//valores amand		
		listaAmand.add("nombre");
		listaAmand.add("AMAND"); //nombre de la empresa 
		
		listaAmand.add("ojdbc");
		listaAmand.add(request.getParameter("ojdbcAmand")); //driver de conexion de amand
		
		listaAmand.add("url");
		listaAmand.add(request.getParameter("urlAmand")); //url de conexion de la base de datos de amand
		
		listaAmand.add("usuario");
		listaAmand.add(request.getParameter("usuarioAmand")); //usuario de base de datos de amand
		
		listaAmand.add("password");
		listaAmand.add(request.getParameter("passwordAmand")); //contraseña de base de datos de amand
		
		
		//valores abstracta		
		listaAbstracta.add("nombre");
		listaAbstracta.add("ABSTRACTA"); //nombre de la empresa 
				
		listaAbstracta.add("ojdbc");
		listaAbstracta.add(request.getParameter("ojdbcAbstracta")); //driver de conexion de abstracta
				
		listaAbstracta.add("url");
		listaAbstracta.add(request.getParameter("urlAbstracta")); //url de conexion de la base de datos de abstracta
			
		listaAbstracta.add("usuario");
		listaAbstracta.add(request.getParameter("usuarioAbstracta")); //usuario de base de datos de abstracta
				
		listaAbstracta.add("password");
		listaAbstracta.add(request.getParameter("passwordAbstracta")); //contraseña de base de datos de abstracta
		
		
		 //valores Bc Muebles
		listaBcMuebles.add("nombre"); 
		listaBcMuebles.add("BCMUEBLES"); //nombre de la empresa
		
		listaBcMuebles.add("ojdbc");
		listaBcMuebles.add(request.getParameter("ojdbcBcMuebles")); //driver de conexion de bc muebles
		
		listaBcMuebles.add("url");
		listaBcMuebles.add(request.getParameter("urlBcMuebles")); //url de conexion de la base de datos de bc muebles
		
		listaBcMuebles.add("usuario");
		listaBcMuebles.add(request.getParameter("usuarioBcMuebles")); //usuario de base de datos de bc muebles
		
		listaBcMuebles.add("password");
		listaBcMuebles.add(request.getParameter("passwordBcMuebles")); //contraseña de base de datos de bc muebles
		
		
		//valores capuy		
		listaCapuy.add("nombre");
		listaCapuy.add("CAPUY"); //nombre de la empresa 
				
		listaCapuy.add("ojdbc");
		listaCapuy.add(request.getParameter("ojdbcCapuy")); //driver de conexion de capuy
				
		listaCapuy.add("url");
		listaCapuy.add(request.getParameter("urlCapuy")); //url de conexion de la base de datos de capuy
			
		listaCapuy.add("usuario");
		listaCapuy.add(request.getParameter("usuarioCapuy")); //usuario de base de datos de capuy
				
		listaCapuy.add("password");
		listaCapuy.add(request.getParameter("passwordCapuy")); //contraseña de base de datos de capuy
		
		//valores servidor central		
		listaServCentral.add("nombre");
		listaServCentral.add("CENTRAL"); //nombre de la empresa 
					
		listaServCentral.add("ojdbc");
		listaServCentral.add(request.getParameter("ojdbcCentral")); //driver de conexion del servidor central
						
		listaServCentral.add("url");
		listaServCentral.add(request.getParameter("urlCentral")); //url de conexion de la base de datos del servidor central
				
		listaServCentral.add("usuario");
		listaServCentral.add(request.getParameter("usuarioCentral")); //usuario de base de datos del servidor central
					
		listaServCentral.add("password");
		listaServCentral.add(request.getParameter("passwordCentral")); //contraseña de base de datos del servidor central
		
		//crear el archivo XML con todos los valores de la interfaz
		MediadorBD.escribirDatosXML(listaBeco, listaAmand, listaAbstracta, listaBcMuebles, listaCapuy);
		
		//retorna una vista de la carga de la configuracion, para que en pantalla se vean los cambios que se hicieron
		return cargarConfiguracion (request,response);
	}

}
