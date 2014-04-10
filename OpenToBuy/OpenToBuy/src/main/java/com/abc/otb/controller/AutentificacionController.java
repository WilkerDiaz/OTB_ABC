package com.abc.otb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abc.otb.dao.impl.AdRoleDAOImpl;
import com.abc.otb.logico.AdRole;

@Controller
public class AutentificacionController {

	/**
	 * Controlador para mostrar la página de inicio
	 */
	@RequestMapping(value = "/autentificar.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView autentificar(HttpServletRequest request,HttpServletResponse response) {

		ModelAndView modelAndView;
		String usuario = request.getParameter("usuario");
		AdRoleDAOImpl roles = new AdRoleDAOImpl();
		List <AdRole> listaRoles = roles.getListaRoles(usuario);
		
		if (listaRoles.size() == 1){
		     PresupuestoComercialController presupuestoComercial = new PresupuestoComercialController();
		     request.setAttribute("empresa", listaRoles.get(0).getEmpresa());
		     request.setAttribute("usuario", usuario);
		     return presupuestoComercial.consultaPresupuesto(request, response, false);
		}
		else{
			modelAndView = new ModelAndView ("home");	
			modelAndView.addObject ("roles",listaRoles);
			modelAndView.addObject("usuario",usuario);
		}

		return modelAndView;
	}
}
