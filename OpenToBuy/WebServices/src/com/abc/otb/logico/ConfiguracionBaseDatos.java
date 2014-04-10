package com.abc.otb.logico;

import java.io.Serializable;

public class ConfiguracionBaseDatos implements Serializable{
	
    String nombre;
    String ojdbc;
    String url;
    String usuario;
    String password;
    String campo; //para saber con cual campo estoy trabajando (nombre,ojdbc,url,usuario,password) 
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOjdbc() {
		return ojdbc;
	}
	public void setOjdbc(String ojdbc) {
		this.ojdbc = ojdbc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario; 
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
    
    

}
