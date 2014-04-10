package com.abc.otb.logico;

/**
 * 
 * @author ccastillo
 *
 */

/*la tabla en la base de datos XX_VMR_Section*/
public class Section {

	//no pertenece a la tabla
	private int posicionar; // permite que el combo box de seccion quede con la opcion seleccionada
	
	private int xx_vmr_section_id;
	private String name;
	private String value;
	
	public int getXx_vmr_section_id() {
		return xx_vmr_section_id;
	}
	public void setxx_vmr_section_id(int xx_vmr_section_id) {
		this.xx_vmr_section_id = xx_vmr_section_id;
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
