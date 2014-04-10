package com.abc.otb.dao;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.abc.otb.logico.ConfiguracionBaseDatos;

public interface ConfiguracionBaseDatosDAO {
	
	/**
	 * metodo para crear un xml con la configuracion de las base de datos de todas las empresas
	 * @param datosBeco arreglo que contiene la configuracion de la base de datos de beco
	 * @param datosAmand arreglo que contiene la configuracion de la base de datos de amand
	 * @param datosAbstracta arreglo que contiene la configuracion de la base de datos de abstracta
	 * @param datosBcMuebles arreglo que contiene la configuracion de la base de datos de bc muebles
	 * @param datosCapuy arreglo que contiene la configuracion de la base de datos de capuy
	 */
	void EscrituraXML (String[] datosBeco, String[] datosAmand, String[] datosAbstracta, String[] datosBcMuebles, String[] datosCapuy);
	
	/**
	 * metodo para obtener del xml todos los datos de la configuracion de la base de datos de una empresa en especifico
	 * @param empresa nombre de la empresa a la que se quiere saber su configuracion (beco,amand,capuy,abstracta,bcmuebles,central)
	 * @return retorna un objeto ConfiguracionBaseDatos que contiene toda la configuracion de la base de datos de esa empresa
	 */
	ConfiguracionBaseDatos LecturaXML (String empresa);
	
	/**
	 * metodo para obtener el valor de un tag perteneciente a un nodo
	 * @param tag nombre de la etiqueta (nombre,ojdbc,url,usuario,password)
	 * @param elemento nodo del cual se quiere obtener la informacion
	 * @return String con el contenido del tag
	 */
	String getTagValue(String tag, Element elemento);

}
