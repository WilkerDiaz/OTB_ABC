package com.abc.otb.logico;

/**
 * 
 * @author ccastillo
 *
 */

/* la tabla en la base de datos de esta clase es XX_VMR_DEPARTMENT*/
public class Department {
	
	//no pertenece a la tabla
	private int posicionar; // permite que el combo box de departamento quede con la opcion seleccionada
	
	private int xx_vmr_department_id;
	private String name;  
	private String value;
	
	public int getxx_vmr_department_id() {
		return xx_vmr_department_id;
	}
	public void setxx_vmr_department_id(int xx_vmr_department_id) {
		this.xx_vmr_department_id = xx_vmr_department_id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getvalue() {
		return value;
	}
	public void setvalue(String value) {
		this.value = value;
	}
	public int getPosicionar() {
		return posicionar;
	}
	public void setPosicionar(int posicionar) {
		this.posicionar = posicionar;
	}
	
	
}
