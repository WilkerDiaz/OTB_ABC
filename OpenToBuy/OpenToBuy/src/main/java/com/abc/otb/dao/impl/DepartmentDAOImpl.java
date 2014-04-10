package com.abc.otb.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.DepartmentDAO;
import com.abc.otb.logico.Department;
import com.abc.otb.mediadorbd.MediadorBD;

public class DepartmentDAOImpl implements DepartmentDAO{

	
	private List<Department> listaDepartamento = new LinkedList<Department>();
	
	/**
	 * Carga del combobox de los departamentos
	 */
	public List<Department> cargarDepartamento(String category_id, String department_id, String empresa){
		String sql = "", parametro;
		
		//inicializar lista 
		listaDepartamento = new LinkedList<Department>();
		
		//query para sacar todos los departamentos
		if (empresa.equals("amand") || empresa.equals("beco")){ // consulta para las empresas amand y beco
		  sql = "SELECT DISTINCT XX_VMR_DEPARTMENT_ID, NAME, VALUE FROM XX_VMR_DEPARTMENT WHERE ISACTIVE = 'Y'";
		  parametro = "XX_VMR_DEPARTMENT_ID";
		}
		else{ // consulta para el resto de las empresas
		  sql = "SELECT XX_CAP_SECTION_ID, NAME, VALUE FROM XX_CAP_SECTION  WHERE ISACTIVE = 'Y'";
		  parametro = "XX_CAP_SECTION_ID";
		}
		
		//si se selecciono una categoria en la interfaz y la empresa es amand o beco
		if(category_id != null && Integer.parseInt(category_id)>0 && (empresa.equals("amand") || empresa.equals("beco"))) 
			sql += " AND XX_VMR_CATEGORY_ID=" + Integer.parseInt(category_id); //se añade al query la condicion con el ID de la categoria
		else if (category_id != null && Integer.parseInt(category_id)>0) 
			sql += " AND XX_CAP_DEPARTAMENTO_ID= "+Integer.parseInt(category_id); //se añade al query la condicion con el ID del departamento
	
		sql += " order by value";
		
		//espacio en blanco en el combo box de departamento
		Department departamento = new Department();
		listaDepartamento.add(departamento);
		
		//Realizar la consulta
			Map[] result = MediadorBD.realizarConsulta(sql,empresa);
		   if (result != null){
			   for (int i=0;i<result.length;i++)
				{
					//Crear un objeto Departamento, que va a guardar los resultados de la consulta
					departamento = new Department();
					  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
					int id = Integer.parseInt(result[i].get(parametro).toString());
					departamento.setxx_vmr_department_id(id);
					departamento.setname(result[i].get("NAME").toString());
					departamento.setvalue(result[i].get("VALUE").toString());
					
				 //permitir que se mantengan en la misma opcion que marco en el combo box para cuando se refresque la ventana
					if (department_id != null && id==Integer.parseInt(department_id))
					  departamento.setPosicionar(1); // mantener en la opcion que seleccionaron en el combo box
					else
					  departamento.setPosicionar(0); //no mantener en ese posicion
					
				//agregar el departamento a la lista
				  listaDepartamento.add(departamento);
				}
				//Retornar la lista de los departamentos
			 return listaDepartamento;
		   }
	 return null;
	}
	
	
	public boolean buscarPosicionar (){
		//recorrer toda la lista de departamento
		  for (int i=0;i<listaDepartamento.size();i++)
			  //buscar algun nodo que se haya seleccionado
			 if (listaDepartamento.get(i).getPosicionar()==1)
				return true; // si lo encuentra lo retorna TRUE
		 return false; // si no lo encuentra retorna FALSE
		}
	
}
