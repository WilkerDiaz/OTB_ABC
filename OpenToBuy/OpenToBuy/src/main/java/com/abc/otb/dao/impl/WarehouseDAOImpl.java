package com.abc.otb.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.WarehouseDAO;
import com.abc.otb.logico.Warehouse;
import com.abc.otb.mediadorbd.MediadorBD;

public class WarehouseDAOImpl implements WarehouseDAO{
	
	private List<Warehouse> listaAlmacen = new LinkedList<Warehouse>();
	
	public List<Warehouse> cargarAlmacenes(String warehouse_id, String empresa){
		String sql = "", parametro;
		
		//inicializar la lista
		listaAlmacen = new LinkedList<Warehouse>();
		
		//query para sacar los almacenes
		if (empresa.equals("beco") || empresa.equals("amand")) //para amand y beco se utiliza esta consulta
		  sql = "SELECT M_WAREHOUSE_ID, NAME FROM M_WAREHOUSE ORDER BY VALUE";
		else  //para capuy,abstracta y bcmubles utilizar esta consulta
		  sql = "SELECT AD_ORG_ID,NAME FROM AD_ORG WHERE XX_CAP_ISSTORE = 'Y'";
		
	  //parametro que se va a obtener de la consulta
		if (empresa.equals("beco") || empresa.equals("amand"))
		  parametro = "M_WAREHOUSE_ID"; //para amand y beco se obtiene el m_warehouse_id
		else
		 parametro = "AD_ORG_ID"; //para el resto de las empresa el ad_org_id
		
		//espacio en blanco para el combo box
		Warehouse almacen = new Warehouse();
		listaAlmacen.add(almacen);

		//Realizar la consulta
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);
		
	 if (result != null){
	  for (int i=0;i<result.length;i++)
		{
		//Crear el objeto almacen y asignarle los valores de la consulta
		  almacen = new Warehouse();
		  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
		  int id = Integer.parseInt(result[i].get(parametro).toString());
		  almacen.setm_warehouse_id(id);
		  almacen.setname(result[i].get("NAME").toString());
		  
		  /*mantener en la interfaz el almacen que seleccionaron cuando se recarge la ventana
		   * NULL implica que no han seleccionado ninguna almacen
		   */
		   if (warehouse_id != null && id==Integer.parseInt(warehouse_id))
			  almacen.setPosicionar(1); //mantener en ese combo box
		   else
			  almacen.setPosicionar(0); // no mantener en ese combo box
		   
		 //añadir a la lista la categoria que se creo
		  listaAlmacen.add(almacen);
		}
  
	//Retorna la lista de la categorias
	  return listaAlmacen;
	 }
		
	  return null;
	}

}
