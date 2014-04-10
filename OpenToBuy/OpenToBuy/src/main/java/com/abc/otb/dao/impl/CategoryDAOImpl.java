package com.abc.otb.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.CategoryDAO;
import com.abc.otb.logico.Category;
import com.abc.otb.mediadorbd.MediadorBD;

public class CategoryDAOImpl implements CategoryDAO{
	
	private List<Category> listaCategoria = new LinkedList<Category>();
	
	
	public List<Category> cargarCategorias(String category_id, String empresa){
		String sql = "", parametro;
	
		//inicializar la lista
		listaCategoria = new LinkedList<Category>();
		
		//query para sacar las categorias
	    if (empresa.equals("beco") || empresa.equals("amand")){ //consulta para las empresas amand y beco
		  sql = "SELECT XX_VMR_Category_id, NAME, VALUE FROM XX_VMR_Category WHERE ISACTIVE = 'Y' order by value";
		  parametro = "XX_VMR_CATEGORY_ID";
	    }
	    else{
	      sql = "SELECT XX_CAP_DEPARTAMENTO_ID, NAME, VALUE FROM XX_CAP_DEPARTAMENTO WHERE ISACTIVE = 'Y' order by value";
	      parametro = "XX_CAP_DEPARTAMENTO_ID";
	    }
		
		//espacio en blanco para el combo box
		Category categoria = new Category();
		listaCategoria.add(categoria);
		
		//Realizar la conexion con la base de datos
		
		//Realizar la consulta
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);
		
      if (result != null){
		for (int i=0;i<result.length;i++)
		{
			//Crear el objeto categoria y asignarle los valores de la consulta
			categoria = new Category();
			  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			int id = Integer.parseInt(result[i].get(parametro).toString());
			categoria.setxx_vmr_category_id(id);
			categoria.setname(result[i].get("NAME").toString());
			categoria.setvalue(result[i].get("VALUE").toString());
			
			/*mantener la categoria que seleccionaron, cuando se recarge la pantalla
			 * si es NULL simboliza que no seleccionaron ninguna categoria
			 */
			 if (category_id != null && id==Integer.parseInt(category_id))
			   categoria.setPosicionar(1); // mantener en la opcion que seleccionaron en el combo box
			 else
			    categoria.setPosicionar(0); //no mantener en ese posicion
			 
			 //añadir a la lista la categoria que se creo
			listaCategoria.add(categoria);
		}
		
		//Retorna la lista de la categorias
  return listaCategoria;
 }
	  return null;
	}
	
}
