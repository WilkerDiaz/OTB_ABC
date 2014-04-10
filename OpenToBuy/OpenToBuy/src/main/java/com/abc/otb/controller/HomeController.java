package com.abc.otb.controller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	/**
	 * Controlador para mostrar la página de inicio
	 */
	@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
	public String home(Locale locale, Model model) {

		System.out.println("hola");
		return "index";
	}
	
}
