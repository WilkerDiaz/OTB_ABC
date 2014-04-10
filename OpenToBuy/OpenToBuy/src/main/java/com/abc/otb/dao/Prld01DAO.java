package com.abc.otb.dao;

import java.util.Vector;

public interface Prld01DAO {

	/**
	 * metodo para obtener todas las tiendas de la base de datos
	 * @param storeIDs vector donde se guardaran todos los ID de las tiendas
	 * @param storeCodes vector donde se guardaran todos los codigos de las tiendas. 
	 *                   Este campo sirve para hacer el match con el archivo .csv
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void getAllStores(Vector<Integer> storeIDs, Vector<String> storeCodes, String empresa);
	
	/**
	 * metodo para obtener todos los departamentos de la base de datos
	 * @param departmentIDs vector donde se guardaran todos los ID de los departamentos
	 * @param departmentCodes vector donde se guardaran todos los codigos de los departamentos.
	 * 						  Este campo sirve para hacer el match con el archivo .csv
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void getAllDeparments(Vector<Integer> departmentIDs, Vector<String> departmentCodes, Vector<Integer> categoryIDs, String empresa);
	
	/**
	 * metodo para obtener todas las lineas de la base de datos
	 * @param lineIDs vector donde se guardaran todas las ID de las lineas
	 * @param lineDepIDs Vector que tiene los ID de los departamentos, correspondientes a cada linea
	 * @param lineCodes vector donde se guardaran todos los codigos de las lineas. 
	 *                   Este campo sirve para hacer el match con el archivo .csv
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void getAllLines(Vector<Integer> lineIDs,Vector<Integer> lineDepIDs, Vector<String> lineCodes, String empresa);
	
	/**
	 * metodo para obtener todas las secciones de la base de datos
	 * @param sectionIDs  donde se guardaran todas las ID de las secciones
	 * @param sectionLinIDs Vector que tiene los ID de las lineas, correspondientes a cada seccion
	 * @param sectionCodes vector donde se guardaran todos los codigos de las lineas. 
	 *                     Este campo sirve para hacer el match con el archivo .csv
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 */
	void getAllSections(Vector<Integer> sectionIDs, Vector<Integer> sectionLinIDs, Vector<String> sectionCodes, String empresa);
	
	/**
	 * metodo para obtener el ID de una tienda en especifico
	 * @param storeIDs vector que tiene el ID de todas las tiendas
	 * @param storeCodes vector que tiene los codigos de todas las tiendas
	 * @param cellValue tienda que se desea buscar
	 * @return retorna el ID de la tienda
	 */
	int getStoreID(Vector<Integer> storeIDs, Vector<String> storeCodes, String cellValue);
	
	/**
	 * metodo para obtener el ID de una categoria en especifico
	 * @param categoryIDs vector que tiene el ID de todas las categorias
	 * @param categoryCodes vector que tiene los codigos de todas las categorias
	 * @param cellValue categoria que se desea buscar
	 * @return retorna el ID de la categoria
	 */
	int getCategoryID(Vector<Integer> categoryIDs, Vector<String> categoryCodes,String cellValue);
	
	/**
	 * metodo para obtener el ID de un departamento en especifico
	 * @param departmentIDs vector que tiene el ID de todas los departamentos 
	 * @param departmentCodes vector que tiene los codigos de todas los departamentos
	 * @param cellValue departamento que se desea buscar
	 * @return retorna el ID del departamento
	 */
	int getDepartmentID(Vector<Integer> departmentIDs, Vector<String> departmentCodes, String cellValue);
	
	/**
	 * metodo para obtener el ID de una linea en especifico
	 * @param lineIDs vector que tiene los ID de todas las lineas 
	 * @param lineDepIDs Vector que tiene los ID de las lineas, correspondientes a cada departamento 
	 * @param lineCodes Vector que tiene los codigos de las lineas
	 * @param cellValue linea que se desea buscar
	 * @param depID ID del departamento, correspondiente a la linea
	 * @return retorna el ID de la linea
	 */
	int getLineID(Vector<Integer> lineIDs,Vector<Integer> lineDepIDs, Vector<String> lineCodes, String cellValue, int depID);
	
	/**
	 * metodo para obtener el ID de una seccion en especifico
	 * @param sectionIDs  vector que tiene los ID de todas las secciones 
	 * @param sectionLinIDs Vector que tiene los ID de las secciones, correspondientes a cada linea 
	 * @param sectionCodes Vector que tiene los codigos de las secciones
	 * @param cellValue seccion que se desea buscar
	 * @param lineID ID de la linea
	 * @return retorna el ID de la seccion
	 */
	int getSectionID(Vector<Integer> sectionIDs, Vector<Integer> sectionLinIDs, Vector<String> sectionCodes, String cellValue, int lineID);
	
	/**
	 * metodo para porder cargar el presupuesto comercial, desde el archivo hacia compiere
	 * @param ruta ruta del archivo que se desea cargar
	 * @param modificar booleano para indicar si se quiere modificar o no el presupuesto comercial cargado
	 * 					TRUE: se va a modificar el presupuesto comercial
	 * 					FALSE: no se va a modificar el presupuesto comercial (se utiliza para indicar que
	 * 						   se quiere hacer una carga de presupuesto)
	 * @param meses mes a realizar la modificacion de la carga del presupuesto
	 * @param anio año a realizar la modificacion de la carga del presupuesto
	 * @param almacen ID del almacen que se seleccion en la pantalla de modificarPresupuesto
	 * @param categoria ID de la categoria que se seleccion en la pantalla de modificarPresupuesto
	 * @param departamento ID del departamento que se seleccion en la pantalla de modificarPresupuesto
	 * @param linea ID de la linea que se seleccion en la pantalla de modificarPresupuesto
	 * @param seccion ID de la seccion que se seleccion en la pantalla de modificarPresupuesto
	 * @param registro ID del registro que se seleccion en la pantalla de modificarPresupuesto
	 * @param empresa la empresa en la que quiero buscar las categorias (amand,beco,capuy,bcmuebles,abstracta)
	 * @return retorna un String un un mensaje 
	 * 			"Exito" : Si realiza la carga sin ningun problema
	 * 			Cualquier otro mensaje, indicara el error que ocurrio durante la carga del presupuesto
	 */
	String cargarPresupuesto (String ruta,boolean modificar,String meses,String anio,String almacen,String categoria,String departamento,String linea,String seccion,String registro,String empresa);
	
	/**
	 * metodo para obtener el mes y el año desde donde se empezo a realizar la carga del presupuesto
	 * @param rutaArchivo la ruta del archivo de donde se va a sacar la fecha
	 * @return retorna un String con el mes y el año (año+mes) desde donde se empezo a realizar la carga
	 *     		del presupuesto comercial
	 */
	String obtenerAnioMes (String rutaArchivo);
	
	/**
	 * metodo para poder modificar el presupuesto comercial que ya esta cargado en el sistema
	 * @param mesAnio
	 * @param warehouse_id
	 * @param category_id
	 * @param department_id
	 * @param line_id
	 * @param section_id
	 * @param registro_id
	 * @param inventarioInicial
	 * @param compras
	 * @param ventas
	 * @param rebajasTotal
	 * @param rebajasDef
	 * @param inventarioFinal
	 * @param empresa
	 */
	void modificarPresupuesto (String mesAnio,String warehouse_id,String category_id,String department_id,String line_id,String section_id,String registro_id,
			String inventarioInicial,String compras,String ventas,String rebajasTotal,String rebajasDef,String inventarioFinal,String empresa);
}
