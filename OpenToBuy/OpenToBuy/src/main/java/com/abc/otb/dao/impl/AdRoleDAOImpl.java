package com.abc.otb.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.AdRoleDAO;
import com.abc.otb.logico.AdRole;
import com.abc.otb.mediadorbd.MediadorBD;

public class AdRoleDAOImpl implements AdRoleDAO{

	public List<AdRole> getListaRoles (String usuario){
		List<AdRole> listaRoles = new LinkedList<AdRole>();	
		List <String> losRoles = null;
		AdRole rol = null;
		Map[] result;
		String [] empresas = {"beco","capuy","bcmuebles","abstracta"};
		
		String sql;
		
		sql = "select r.name "+
				"from ad_role r ,ad_user u,ad_user_roles ur "+ 
				"where "+
				"r.ad_role_id = ur.ad_role_id and "+
				"u.ad_user_id = ur.ad_user_id and "+
				"upper (u.name) = upper('"+usuario+"') and "+ 
				"u.isactive ='Y'";
		
	//ciclo para recorrer todas las empresas y sacar los roles que tiene el usuario en cada empresa
	for (int i=0;i<empresas.length;i++){
		//ver si el usuario tiene roles en la empresa [i]
		result  = MediadorBD.realizarConsulta(sql, empresas[i]);
		
	  //si la consulta arrojo algun valor
		if (result != null){
			rol = new AdRole();
			losRoles = new LinkedList<String>();
			
			//setear la empresa
			rol.setEmpresa(empresas[i]);
			
			//obtener los roles de la persona en esa empresa
			for (int j=0;j<result.length;j++){
				//obtener el rol de esa persona
				 //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
				losRoles.add(result[j].get("NAME").toString());
				//setear el rol de esa persona
				rol.setName(losRoles);
				
			}
	     //añadir los roles a lista de los roles que tiene el usuario en una empresa
		  listaRoles.add(rol);
		}
	}
	
	   //query para obtener los roles de una persona en amand
		sql = "select r.name "+
				"from ad_role r ,ad_user u,ad_user_roles ur "+ 
				"where "+
				"r.ad_role_id = ur.ad_role_id and "+
				"u.ad_user_id = ur.ad_user_id and "+
				"upper (u.value) = upper('"+usuario+"') and "+  
				"u.isactive ='Y'";
		
		//ver si el usuario tiene roles en amand
		result  = MediadorBD.realizarConsulta(sql, "amand");
			
		 //si la consulta arrojo algun valor
		if (result != null){
			rol = new AdRole();
			losRoles = new LinkedList<String>();
			
			//setear la empresa
			rol.setEmpresa("amand");
			
			//obtener los roles de la persona en esa empresa
			for (int i=0;i<result.length;i++){
				//obtener el rol de esa persona
				 //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
				losRoles.add(result[i].get("NAME").toString());
				//setear el rol de esa persona
				rol.setName(losRoles);
				
			}
			 //añadir los roles a lista de los roles que tiene el usuario en una empresa
		  listaRoles.add(rol);
		}
	//retornar la lista con todos los roles que tiene una persona en cada empresa
	return listaRoles;
	}
	
public List<String> getRoleUserId (String empresa,String usuario){
	List <String> lista = new LinkedList<String>();
	
	String sql = "select r.ad_role_id,u.ad_user_id "+
			"from ad_role r ,ad_user u,ad_user_roles ur "+ 
			"where "+
			"r.ad_role_id = ur.ad_role_id and "+
			"u.ad_user_id = ur.ad_user_id and "+
			"upper (u.name) = upper('"+usuario+"') and "+ 
			"u.isactive ='Y'";
	
	Map[] result = MediadorBD.realizarConsulta(sql, empresa);
	lista.add(result[0].get("AD_USER_ID").toString());
	lista.add(result[0].get("AD_ROLE_ID").toString());
	
	return lista;
	
  }
}
