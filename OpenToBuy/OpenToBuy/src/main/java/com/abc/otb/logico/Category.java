package com.abc.otb.logico;

/**
 * 
 * @author ccastillo
 *
 */

/*nombre de la tabla en la base de datos: XX_VMR_CATEGORY*/
public class Category {
	
	//no pertenece a la tabla
	private int posicionar; // permite que el combo box de categoria quede con la opcion seleccionada
	
	private int xx_vmr_category_id;  
	private String name;     
	private String value;
	
	public int getPosicionar (){
		return posicionar;
	}
	
	public void setPosicionar (int posicionar){
		this.posicionar = posicionar;
	}
	
	public int getxx_vmr_category_id() {
		return xx_vmr_category_id;
	}
	public void setxx_vmr_category_id(int xx_vmr_category_id) {
		this.xx_vmr_category_id = xx_vmr_category_id;
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
	
	
}
