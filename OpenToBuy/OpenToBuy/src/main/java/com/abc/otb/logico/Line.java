package com.abc.otb.logico;

/**
 * 
 * @author ccastillo
 *
 */

/*la tabla en la base de datos XX_VMR_Line*/
public class Line {

	//no pertenece a la tabla
	private int posicionar; // permite que el combo box de linea quede con la opcion seleccionada
	
	private int xx_vmr_line_id;
	private String name;
	private String value;
	
	public int getPosicionar (){
	  return posicionar;
	}
	
	public void setPosicionar (int posicionar){
		this.posicionar = posicionar;
	}
	
	public int getxx_vmr_line_id() {
		return xx_vmr_line_id;
	}
	public void setxx_vmr_line_id(int xx_vmr_line_id) {
		this.xx_vmr_line_id = xx_vmr_line_id;
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
	public void setvalues(String value) {
		this.value = value;
	}
	
	
}
