package com.abc.otb.dao;

import java.util.List;

import com.abc.otb.logico.PresupuestoComercial;

public interface PresupuestoComercialDAO {

	/**
	 * Metodo para sacar el presupuesto comercial de un mes en especifico
	 * @param monthAux mes del que se quiere sacar el presupuesto comercial
	 * @param warehouse_id el ID de la tienda (del que seleccionen en el combo box)
	 * @param category_id el ID de la categoria (del que seleccionen en el combo box)
	 * @param department_id el ID del departamento (del que seleccionen en el combo box)
	 * @param line_id el ID de la linea (del que seleccionen en el combo box)
	 * @param section_id el ID de la seccion (del que seleccionen en el combo box)
	 * @param registro_id el ID del tipo de registro (del que seleccionen en el combo box)
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void getMonth (String monthAux,String warehouse_id,String category_id,String department_id,String line_id,String section_id,String registro_id,String empresa);
	
	/**
	 * Metodo para sacar la conuslta del presupuesto comercial, saca el presupuesto de 6 meses
	 * @param mesAnio fecha de inicio a sacar el presupuesto comercial (es la concatenacion de los textfield (mes,anio) de la interfaz)
	 * @param warehouse_id el ID de la tienda (del que seleccionen en el combo box)
	 * @param category_id el ID de la categoria (del que seleccionen en el combo box)
	 * @param department_id el ID del departamento (del que seleccionen en el combo box)
	 * @param line_id el ID de la linea (del que seleccionen en el combo box)
	 * @param section_id el ID de la seccion (del que seleccionen en el combo box)
	 * @param registro_id el ID del tipo de registro (del que seleccionen en el combo box)
	 * @param meses cuantos meses del presupuesto se quieren obtener
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void presupuestoMeses (String mesAnio,String warehouse_id,String category_id,String department_id,String line_id,String section_id,String registro_id,int meses,String empresa);
	
	/**
	 * Metodo que retorna la lista con el presupuesto comercial de 6 meses, es la que se muestra como reporte
	 * @return un objeto List
	 */
	List<PresupuestoComercial> getListaPresupuestoComercial ();
	
	/**
	 * metodo para sacar el mes anterior o siguiente del que se pasa por parametro
	 * @param mes mes al que se le quiere saber su siguiente o anterior (sale del textfield de la interfaz)
	 * @param anterior FALSE si se quiere sacar el mes futuro (siguiente), TRUE si se quiere sacar el mes pasado (anterior)
	 * @return retorna el nuevo mes (sea el anterior o el siguiente)
	 */
	String siguienteAnteriorMes (String mes,boolean anterior);
	
	/**
	 * metodo para sacar el año anterior o siguiente del que se pasa por parametro
	 * @param mes mes que sale en el textfield de la interfaz
	 * @param anio año que sale en el textfield de la interfaz
	 * @param anterior FALSE si se quiere sacar el año futuro (siguiente), TRUE si se quiere sacar el año pasado (anterior)
	 * @return retorna el nuevo año (sea el anterior o el siguiente)
	 */
	String siguienteAnteriorAnio (String mes,String anio, boolean anterior);
	
	/**
	 * obtiene el mes anterior en formato año+mes (201312)
	 * @param yearMonth_Actual fecha a la que se le quiere saber el mes anterior
	 * @return retorna el mes anterior formato año+mes
	 */
	String getPastYearMonth(String yearMonth_Actual);
}
