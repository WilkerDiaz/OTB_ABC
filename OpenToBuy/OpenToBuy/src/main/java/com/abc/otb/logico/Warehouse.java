package com.abc.otb.logico;

/**
 * 
 * @author ccastillo
 *
 */

/*nombre de la tabla en la base de datos: M_WAREHOUSE*/
public class Warehouse {

	//no pertenece a la tabla
	private int posicionar;  // permite que el combo box de seccion quede con la opcion seleccionada
  
	private int m_warehouse_id;             
	private String name;
	
	public int getPosicionar(){
	  return posicionar;
	}
	
	public void setPosicionar(int posicionar){
	  this.posicionar = posicionar;
	}
	
	public int getm_warehouse_id() {
		return m_warehouse_id;
	}
	public void setm_warehouse_id(int m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
        

}
