package com.abc.otb.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.abc.otb.dao.ConfiguracionBaseDatosDAO;
import com.abc.otb.logico.ConfiguracionBaseDatos;

public class ConfiguracionBaseDatosDAOImpl implements ConfiguracionBaseDatosDAO{
	
	private static String rutaWindows = "C:\\OTB\\";
	private static String rutaLinux = "/OTB/";
	
	public void EscrituraXML (String[] datosBeco, String[] datosAmand, String[] datosAbstracta,
			String[] datosBcMuebles, String[] datosCapuy){
		
		java.io.File directorio = new File(rutaWindows);
        directorio.mkdir();
        
		try {			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = doc.createElement("BD");
			Element etiqueta = doc.createElement("empresa");
			
		//armar el XML para beco
			int i=0;
			while (i<(datosBeco.length-1)){
				Element nodo = doc.createElement(datosBeco[i]); //nombre que va a llevar el nodo
				Text texto = doc.createTextNode(datosBeco[i+1]); //texto del nodo	
				nodo.appendChild(texto); //a�adir el texto al nodo	
				etiqueta.appendChild(nodo); //colocar el nodo dentro de la etiqueta empresa
			    i+=2;
			}
	
			//a�adir el nodo a la raiz
			root.appendChild(etiqueta);
			
			
			//armar el XML para amand
			i = 0;
			etiqueta = doc.createElement("empresa");
			while (i<(datosAmand.length-1)){
				Element nodo = doc.createElement(datosAmand[i]); //nombre que va a llevar el nodo
				Text texto = doc.createTextNode(datosAmand[i+1]); //texto del nodo	
				nodo.appendChild(texto); //a�adir el texto al nodo	
				etiqueta.appendChild(nodo); //colocar el nodo dentro de la etiqueta empresa
				i+=2;
			}
	
			//a�adir el nodo a la raiz
			root.appendChild(etiqueta);
			
			
			//armar el XML para abstracta
			i = 0;
			etiqueta = doc.createElement("empresa");
			while (i<(datosAbstracta.length-1)){
				Element nodo = doc.createElement(datosAbstracta[i]); //nombre que va a llevar el nodo
				Text texto = doc.createTextNode(datosAbstracta[i+1]); //texto del nodo	
				nodo.appendChild(texto); //a�adir el texto al nodo	
				etiqueta.appendChild(nodo); //colocar el nodo dentro de la etiqueta empresa
				i+=2;
			}
	
			//a�adir el nodo a la raiz
			root.appendChild(etiqueta);
			
			
			//armar el XML para Bc Muebles
			i = 0;
			etiqueta = doc.createElement("empresa");
			while (i<(datosBcMuebles.length-1)){
				Element nodo = doc.createElement(datosBcMuebles[i]); //nombre que va a llevar el nodo
				Text texto = doc.createTextNode(datosBcMuebles[i+1]); //texto del nodo	
				nodo.appendChild(texto); //a�adir el texto al nodo	
				etiqueta.appendChild(nodo); //colocar el nodo dentro de la etiqueta empresa
				i+=2;
			}
	
			//a�adir el nodo a la raiz
			root.appendChild(etiqueta);
			
			
			//armar el XML para Capuy
			i = 0;
			etiqueta = doc.createElement("empresa");
			while (i<(datosCapuy.length-1)){
				Element nodo = doc.createElement(datosCapuy[i]); //nombre que va a llevar el nodo
				Text texto = doc.createTextNode(datosCapuy[i+1]); //texto del nodo	
				nodo.appendChild(texto); //a�adir el texto al nodo	
				etiqueta.appendChild(nodo); //colocar el nodo dentro de la etiqueta empresa
				i+=2;
			}
	
			//a�adir el nodo a la raiz
			root.appendChild(etiqueta);
					
			
			//a�adir toda la raiz al documento XML
			doc.appendChild(root);
			
			// volcamos el XML al fichero
			TransformerFactory transFact = TransformerFactory.newInstance();

			// a�adimos sangrado y la cabecera de XML
			transFact.setAttribute("indent-number", new Integer(3));
			Transformer trans = transFact.newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

			// hacemos la transformacion
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			DOMSource domSource = new DOMSource(doc);
			trans.transform(domSource, sr);
			
			// creamos fichero para escribir en modo texto
			PrintWriter writer;
			if (System.getProperty("os.name").startsWith("Windows")) 		
			   writer = new PrintWriter(new FileWriter(rutaWindows+"propiedades.xml"));
			else
			   writer = new PrintWriter(new FileWriter(rutaLinux+"propiedades.xml"));

			// escribimos todo el arbol en el fichero
			writer.println(sw.toString());

			// cerramos el fichero
			writer.close();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 public ConfiguracionBaseDatos LecturaXML (String empresa){

		 ConfiguracionBaseDatos configuracion = new ConfiguracionBaseDatos();
		
		 try {
			  Document doc = null;
			  
			  //crear el documento XML
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  
			  //definir la ruta donde se va a guardar
			  if (System.getProperty("os.name").startsWith("Windows"))
			    doc = dBuilder.parse(new File(rutaWindows+"propiedades.xml"));
			  else
				doc = dBuilder.parse(new File(rutaLinux+"propiedades.xml"));
			  
			  doc.getDocumentElement().normalize();
		 
			  //obtener todos los nodos que se llamen empresa
			  NodeList listaEmpresa = doc.getElementsByTagName("empresa");

			  //recorrer la lista de los nodos
			  for (int i = 0; i < listaEmpresa.getLength(); i ++) {

				 //obtener el nodo "i" de la lista
			    Node persona = listaEmpresa.item(i);

			    //si el nodo es un elemento
			    if (persona.getNodeType() == Node.ELEMENT_NODE) {

			    	//obtener el nodo
		            Element elemento = (Element) persona;
		            
		            //ver si el nodo sobre el que estoy es igual a la empresa que ando buscando
		             if (getTagValue("nombre", elemento).equals(empresa.toUpperCase())){
		            	 //si es igual seteo todos sus valores
		            	 configuracion.setOjdbc(getTagValue("ojdbc", elemento));
		            	 configuracion.setUrl(getTagValue("url", elemento));
		            	 configuracion.setUsuario(getTagValue("usuario", elemento));
		            	 configuracion.setPassword(getTagValue("password", elemento));  	 	            	 
		             }

			    }
		     }
			  
		    } catch (Exception e) {
		    e.printStackTrace();
		  }
		return configuracion;
	 }
	 
	  public String getTagValue(String tag, Element elemento) {

		  String nodo = "null";
		try{
			//obtener todos los tag (segun el parametro) del nodo que se esta pasando por parametro
			 NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
	  
			 //obtener el tag
			 Node valor = (Node) lista.item(0);
			 
			 //obtener el valor de ese tag
			 nodo = valor.getNodeValue();
			 
		}catch (NullPointerException e){
			System.out.println("No se encuentra el nodo ");
		}
		
		return nodo;
	}

}
