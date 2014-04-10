package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.AdRole;

public interface AdRoleDAO {

	/**
	 * metodo para obtener los roles del usuario en todas las empresa
	 * @param usuario nombre del usuario al que se le va a buscar los roles
	 * @return retorna la lista con todos los roles
	 */
	List<AdRole> getListaRoles (String usuario);
}
