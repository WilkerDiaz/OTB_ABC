package com.abc.otb.dao.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import com.abc.otb.dao.Prld01DAO;
import com.abc.otb.logico.PresupuestoComercial;
import com.abc.otb.mediadorbd.MediadorBD;
import com.csvreader.CsvReader;

/**
 * 
 * @author ccastillo
 *
 */
public class Prld01DAOImpl implements Prld01DAO{
	
private String ad_client_id = "1000012";
private String ad_org_id = "0";
private char isActive = 'Y';
private String created = "sysdate";
private String createdby = "100";
private String updated = "sysdate";
private String updatedby = "100";
private static String rutaWindows = "C:\\";
private static String rutaLinux = "/";

//obtener todas los ID de las tiendas de la base de datos
public void getAllStores(Vector<Integer> storeIDs, Vector<String> storeCodes,String empresa){
	
	String sql = "SELECT VALUE, M_WAREHOUSE_ID " + 
			     "FROM M_WAREHOUSE " +
			     "WHERE UPPER(VALUE) = LOWER(VALUE) " +
			     "AND AD_CLIENT_ID = "+ad_client_id;
	
	//realizar la consulta
	Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	
	//si arrojo algun valor la consulta
	if (result != null){
		//obtener los value y el ID de la tienda
		for (int i=0;i<result.length;i++){
			  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			storeCodes.add(result[i].get("VALUE").toString());
			storeIDs.add(Integer.parseInt(result[i].get("M_WAREHOUSE_ID").toString()));
		}		
	}
 }

	//obtener todos los departamentos de la base de datos
public void getAllDeparments(Vector<Integer> departmentIDs, Vector<String> departmentCodes, Vector<Integer> categoryIDs,String empresa){
	
	String sql = "SELECT VALUE, XX_VMR_DEPARTMENT_ID,XX_VMR_CATEGORY_ID " +
			     "FROM XX_VMR_DEPARTMENT WHERE AD_CLIENT_ID = "+ad_client_id;

	//realizar la consulta
	Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	
	//si la consulta arrojo algun valor
    if (result != null){
		//obtener el value, el ID del departamento y el ID de la categoria
		for (int i=0;i<result.length;i++){
			  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			departmentCodes.add(result[i].get("VALUE").toString());
			departmentIDs.add(Integer.parseInt(result[i].get("XX_VMR_DEPARTMENT_ID").toString()));
			categoryIDs.add(Integer.parseInt(result[i].get("XX_VMR_CATEGORY_ID").toString()));
		}	
     }
  }

//obtener todas las lineas de la base de datos
public void getAllLines(Vector<Integer> lineIDs,Vector<Integer> lineDepIDs, Vector<String> lineCodes,String empresa){
	
	String sql = "SELECT VALUE, XX_VMR_DEPARTMENT_ID, XX_VMR_LINE_ID " +
			     "FROM XX_VMR_LINE WHERE " +
			     "AD_CLIENT_ID = "+ad_client_id;

		//realizar la consulta
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	
	//si la consulta arrojo algun valor
	 if (result != null){
		//obtener el value, el ID de la linea y el ID del departamento
	    for (int i=0;i<result.length;i++){
	    	  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			lineCodes.add(result[i].get("VALUE").toString());
			lineIDs.add(Integer.parseInt(result[i].get("XX_VMR_LINE_ID").toString()));
			lineDepIDs.add(Integer.parseInt(result[i].get("XX_VMR_DEPARTMENT_ID").toString()));
		}	
	 }
   }

//obtener las secciones de la base de datos
public void getAllSections(Vector<Integer> sectionIDs, Vector<Integer> sectionLinIDs, Vector<String> sectionCodes, String empresa){
	
	String sql = "SELECT VALUE, XX_VMR_LINE_ID, XX_VMR_SECTION_ID " +
			     "FROM XX_VMR_SECTION WHERE AD_CLIENT_ID = "+ad_client_id;

		//realizar la consulta
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);
	
	//si la consulta arrojo algun valor
	 if (result != null){
		//obtener el value, el ID de la seccion y el ID de la linea
		 for (int i=0;i<result.length;i++){
			  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
			sectionCodes.add(result[i].get("VALUE").toString());
			sectionIDs.add(Integer.parseInt(result[i].get("XX_VMR_SECTION_ID").toString()));
			sectionLinIDs.add(Integer.parseInt(result[i].get("XX_VMR_LINE_ID").toString()));
		}
	 }
  }

//obtener el ID de un tienda en especifico
public int getStoreID(Vector<Integer> storeIDs, Vector<String> storeCodes, String cellValue){
	
	int index=-1;  // OJO CON ESTO (ACTUALMENTE LAS TIENDAS TIENEN 3 DIGITOS Ejm: 002)
	if(cellValue.length()==1){
		cellValue = "00" + cellValue;
	}else if (cellValue.length()==2) {
		cellValue = "0" + cellValue;
	}
	
	//recorrer la lista de los codigos de la tienda
	for(int i=0; i<storeCodes.size(); i++){
		
		//buscar cual codigo es igual al que se esta buscando
		if(storeCodes.get(i).equals(cellValue)){
			index=i;
			break;
		}
	}
	
	//si se encontro algo en la lista
	if(index!=-1)
		//se retorna el ID de esa tienda
		return storeIDs.get(index);
	else // sino se retorna cero
		return 0;
}

//obtener la ID de una categoria en especifico
public int getCategoryID(Vector<Integer> categoryIDs, Vector<String> departmentCodes,String cellValue){
	
	int index=-1;
	
	//formatear el valor a la mismo longitud que tiene la base de datos (ejm pasar 2 a 02)
	if(cellValue.length()==1){
		cellValue = "0" + cellValue;
	}
	
	//buscar en la lista de los codigos de los departamentos
	for(int i=0; i<departmentCodes.size(); i++){
		//si encuentro un codigo de departamento que se igual al que estoy buscando
		if(departmentCodes.get(i).equals(cellValue)){
			//obtengo la posicion en la que lo encontre
			index=i;
			break;
		}
	}
	
	//si encoentre algun valor
	if(index!=-1)
		//Retorno el ID de esa categoria
		return categoryIDs.get(index);
	else // sino retorno cero
		return 0;
}

//obtener el ID de un departamento en especifico
public int getDepartmentID(Vector<Integer> departmentIDs, Vector<String> departmentCodes, String cellValue){
	
	int index=-1;
	
	//formatear el valor que se busca a como estan en la base de datos
	if(cellValue.length()==1){
		cellValue = "0" + cellValue;
	}
	
	//busco en la lista de los codigos de departamento
	for(int i=0; i<departmentCodes.size(); i++){
		//comparo los valores de la lista contra el que quiero buscar
		if(departmentCodes.get(i).equals(cellValue)){
			//si lo encuentro obtengo la posicion en la que lo encontre
			index=i;
			break;
		}
	}
	
	//si encontre algun valor
	if(index!=-1)
		//retorno el ID de ese departamento
		return departmentIDs.get(index);
	else // sino retorno cero
		return 0;
}

//obtener el ID de una linea en especifico
public int getLineID(Vector<Integer> lineIDs,Vector<Integer> lineDepIDs, Vector<String> lineCodes, String cellValue, int depID){
	
	int index=-1;
	
	//formatear el valor a buscar iguales a como estan en la base de datos
	if(cellValue.length()==1){
		cellValue = "0" + cellValue;
	}
	
	//buscar en la lista de codigos de linea el valor que estoy buscando
	for(int i=0; i<lineCodes.size(); i++){
		//si en la lista esta un valor igual al que estoy buscando
		if(lineCodes.get(i).equals(cellValue) && lineDepIDs.get(i)==depID){
			//me quedo con la posicion en la que lo encontre
			index=i;
			break;
		}
	}
	
	//Si encuentre algun valor
	if(index!=-1)
		//retorno el ID de la linea
		return lineIDs.get(index);
	else //sino retorno cero
		return 0;
}

//obtener el ID de una seccion en especifico
public int getSectionID(Vector<Integer> sectionIDs, Vector<Integer> sectionLinIDs, Vector<String> sectionCodes, String cellValue, int lineID){
	
	int index=-1;
	
	//formatear el valor a buscar igual a como esta en la base de datos
	if(cellValue.length()==1){
		cellValue = "0" + cellValue;
	}

	//buscar en la lista de los codigo de seccion si hay un valor igual al que quiero buscar
	for(int i=0; i<sectionCodes.size(); i++){
		//si encuentro un valor en la lista igual al que quiero
		if(sectionCodes.get(i).equals(cellValue) && sectionLinIDs.get(i)==lineID){
			//obtengo la posicion de la lista en donde lo encontre
			index=i;
			break;
		}
	}
	
	//Si encontre algun valor
	if(index!=-1)
		//retorno el ID de la seccion
		return sectionIDs.get(index);
	else // sino retorno cero
		return 0;
   }

//obtener el mes y año del archivo .csv
public String obtenerAnioMes (String rutaArchivo){
	String añomespre = "";
	try {
		//abrir el archivo separandolo por ;
		CsvReader reader = new CsvReader(rutaArchivo, ';');
		//posicionarlo en el cabecera del archivo
		reader.readHeaders();
		//moverlo a la primera fila del archivo
		reader.readRecord();
		//obtengo el año del primer registro
		String año = reader.get("AÑO");
		//obtengo el mes del primer registro
		String mes = reader.get("MES");
		//formatear el mes para que tenga dos digitos
		 if (mes.length() == 1)
		   mes = "0"+mes;
		 //setear el año Mes 
		añomespre = año+mes;
		//cerrar el archivo
		reader.close();
	} catch (IOException e) {
		System.out.println("Error al obtener la fecha");
	}
	//Retorna el valor del año+mes
 return añomespre;
}

//cargar el presupuesto comercial partiendo de un archivo .csv
 public String cargarPresupuesto (String rutaArchivo,boolean modificar,String meses,String anio,
		 String almacen,String categoria,String departamento,String linea,String seccion,String registro, String empresa){
	 	int i = 1;
		int j = 0;
		int estado = 0;
		int ejecutar = 5000;
		String mensaje = "";
		boolean continuar = true;
		
		double POREBPRPRTEMP = 0;
		double POREBPRPR = 0;
		double POREBFRPRTEMP = 0;
		double POREBFRPR = 0;
		double POREBDFPRTEMP = 0;
		double POREBDFPR = 0;
		double PORROTPRE = 0;
		double POMARNGPR = 0;
		double POMARBGPR = 0;
		double POMARSCPR = 0;
		double POMARPGPR = 0;
		double PORCOBPRE = 0;
		String sql ="";
		
		int category=0;
		int dep=0;
		int line=0;
		int section=0;
		int store=0;
	
		Vector<Integer> categoryIDs = new Vector<Integer>();
		Vector<Integer> departmentIDs = new Vector<Integer>();
		Vector<String> departmentCodes = new Vector<String>();
		Vector<Integer> lineIDs = new Vector<Integer>();
		Vector<Integer> lineDepIDs = new Vector<Integer>();
		Vector<String> lineCodes = new Vector<String>();
		Vector<Integer> sectionIDs = new Vector<Integer>();
		Vector<Integer> sectionLinIDs = new Vector<Integer>();
		Vector<String> sectionCodes = new Vector<String>();
		Vector<String> storeCodes = new Vector<String>();
		Vector<Integer> storeIDs = new Vector<Integer>();
		
		ArrayList<String> loteSentencias = new ArrayList<String>();
		ArrayList<String> tablas = new ArrayList<String>();
		ArrayList valores = new ArrayList();
		
		//obtener la lista con todos los ID de los departamentos
		getAllDeparments(departmentIDs, departmentCodes,categoryIDs,empresa);
		//obtener la lista con todos los ID de las lineas
		getAllLines(lineIDs, lineDepIDs, lineCodes,empresa);
		//obtener la lista de todos los ID de las secciones
		getAllSections(sectionIDs, sectionLinIDs, sectionCodes,empresa);
		//obtener la lista de todos los ID de las tiendas
		getAllStores(storeIDs, storeCodes,empresa);
		
		try {
			
			//sacar cual es el mayor ID del presupuesto de la tabla
			sql = "select max(xx_vmr_prld01_id) as maximo from xx_vmr_prld01";
			Map[] result = MediadorBD.realizarConsulta(sql,empresa);
			
			//obtener el resultado de la consulta (utilizar SIEMPRE en el .get el parametro en MAYUSCULA)
			Integer prld01Id = Integer.parseInt(result[0].get("MAXIMO").toString());
			
			//si se quiere modificar el presupuesto comercial
			if (modificar == true){
				//se crea un query en base a lo que se quiere modificar
				 String sqlDelete = "delete from xx_vmr_prld01 where XX_BUDGETYEARMONTH="+(anio+meses);
				 
				 //si se quiere modificar a nivel de almacen
				  if (almacen != null && Integer.parseInt(almacen)>0)
					sqlDelete += " AND  M_WAREHOUSE_ID ="+almacen;
				  
				  //si se quiere modificar a nivel de categoria
				  if (categoria != null && Integer.parseInt(categoria)>0)
					sqlDelete += " AND  XX_VMR_CATEGORY_ID ="+categoria;
				  
				  //si se quiere modificar a nivel de departamento
				  if (departamento != null && Integer.parseInt(departamento)>0)
					sqlDelete += " AND  XX_VMR_DEPARTMENT_ID ="+departamento;
				  
				  //si se quiere modificar a nivel de linea
				  if (linea != null && Integer.parseInt(linea)>0)
					sqlDelete += " AND  XX_VMR_LINE_ID ="+linea;
				  
				  //si se quiere modificar a nivel de seccion
				  if (seccion != null && Integer.parseInt(seccion)>0)
					sqlDelete += " AND  XX_VMR_SECTION_ID ="+seccion;
				  
				  //realizar el sql en la base de datos
				  estado = MediadorBD.realizarSentencia(sqlDelete, empresa);
			 }

			/* Archivo de Entrada */
			CsvReader reader = new CsvReader(rutaArchivo, ';');
			reader.readHeaders();
			System.out.println("Leyendo el archivo... \n");
			
			while (continuar == true && reader.readRecord()) {
				sql = "";
				tablas = new ArrayList<String>();
				valores = new ArrayList();
				
				//añadir las tablas (fijas) al insert
				 tablas.add("XX_VMR_PRLD01_ID");
				 tablas.add("AD_CLIENT_ID");
				 tablas.add("AD_ORG_ID");
				 tablas.add("ISACTIVE");
				 tablas.add("CREATED");
				 tablas.add("CREATEDBY");
				 tablas.add("UPDATED");
				 tablas.add("UPDATEDBY");
				 
			   //añadir los valores que van a tener los campos 
				 valores.add(prld01Id+i);
				 valores.add(ad_client_id);
				 valores.add(ad_org_id);
				 valores.add("'"+isActive+"'");
				 valores.add(created);
				 valores.add(createdby);
				 valores.add(updated);
				 valores.add(updatedby);
				
				//obtener la columna del archivo CODTIE
				String codtie = reader.get("CODTIE");
				
				//chequear que haya el codigo de la tienda en el archivo
				if (!codtie.isEmpty()){
					store = getStoreID(storeIDs, storeCodes, codtie); //Codigo de Tienda
					tablas.add("M_WAREHOUSE_ID"); //añadir la tabla a la lista
				    valores.add(store); // y el valor de ese campo
				}
					
				//obtener la columna del archivo CODDEP
				String coddep = reader.get("CODDEP");
				
				//chequear que haya el codigo del departamento en el archivo
			     if (!coddep.isEmpty()){	 
					category = getCategoryID(categoryIDs, departmentCodes, coddep);	 //Codigo de Categoria	
					tablas.add("XX_VMR_CATEGORY_ID"); //añadir la tabla a la lista
					valores.add(category); // y el valor de ese campo
												
					dep = getDepartmentID(departmentIDs, departmentCodes, coddep); //Codigo de Departamento	
					tablas.add("XX_VMR_DEPARTMENT_ID"); //añadir la tabla a la lista
					valores.add(dep); // y el valor de ese campo
			     }
					
			   //obtener la columna del archivo CODLIN
				String codlin = reader.get("CODLIN");
					
			     //chequear que haya el codigo de la linea en el archivo
			     if (!codlin.isEmpty())	{
					line = getLineID(lineIDs, lineDepIDs, lineCodes, codlin, dep); //Codigo de Linea		 
					tablas.add("XX_VMR_LINE_ID"); //añadir la tabla a la lista
					valores.add(line); // y el valor de ese campo
			     }
					
			   //obtener la columna del archivo CODSEC
				String codsec = reader.get("CODSEC");
					
			    //chequear que haya el codigo de la seccion en el archivo
			    if (!codsec.isEmpty()){
					section = getSectionID(sectionIDs, sectionLinIDs, sectionCodes, codsec, line); //Codigo de Seccion
					tablas.add("XX_VMR_SECTION_ID"); //añadir la tabla a la lista
					valores.add(section); // y el valor de ese campo
			    }
				
				//obtener las columnas del archivo AÑO y MES
				String año = reader.get("AÑO");
				String mes = reader.get("MES");
				
				//chequear si en el archivo existe la columna MES y AÑO y que tengan valores
				int añomespre = 0;
				 if (!año.isEmpty() && !mes.isEmpty()){
				   añomespre = Integer.parseInt(año) * 100 + Integer.parseInt(mes);
				   tablas.add("XX_BUDGETYEARMONTH"); //añadir la tabla a la lista
				   valores.add(añomespre); // y el valor de ese campo
				 }
				 	
				 /* borrar el presupuesto comercial viejo, para colocar el nuevo
				 * si existe en el archivo el mes y el año y no se quiere modificar el presupuesto (es decir se va a cargar)
				 */
				 if (i == 1 && modificar == false && !año.isEmpty() && !mes.isEmpty()){ 
				   String sqlDelete = "delete from xx_vmr_prld01 where XX_BUDGETYEARMONTH>="+añomespre; //query para borrar los presupuesto comerciales viejos
				   estado = MediadorBD.realizarSentencia(sqlDelete, empresa); //ejecutar query
				 }
				  
				//si no pudo borrar los registros
				 if (estado == -1){
					mensaje = "Error de base de datos al intentar actualizar los registros. Intentar más tarde.";
					continuar = false;
				 }
			  else{	 
				
			   /*  Ventas Presupuestadas
				*  chequear si en el archivo existe la columna MONVENPRE y que tenga valores
			    */
			   double monvenpre = 0;
			  if (!reader.get("MONVENPRE").isEmpty()){
				 monvenpre = (new Double(reader.get("MONVENPRE"))).doubleValue();
				 tablas.add("XX_SALESAMOUNTBUD2"); //añadir la tabla a la lista
				 valores.add(monvenpre); // y el valor de ese campo
			  }
			  
			   /*  Inventario inicial Presupuestadas
				*  chequear si en el archivo existe la columna MOINVINPR y que tenga valores
			    */
			  double moinvinpr = 0;
			  if (!reader.get("MOINVINPR").isEmpty()){
				 moinvinpr = (new Double(reader.get("MOINVINPR"))).doubleValue();
				 tablas.add("XX_INVEFECBUDGETEDAMOUNT"); //añadir la tabla a la lista
				 valores.add(moinvinpr); // y el valor de ese campo
			  }
			  
			  
			  /*  Inventario final Presupuestadas
				*  chequear si en el archivo existe la columna MOINVFIPR y que tenga valores
			    */
			  double moinvfipr = 0;
			  if (!reader.get("MOINVFIPR").isEmpty()){
				moinvfipr = (new Double(reader.get("MOINVFIPR"))).doubleValue();
				tablas.add("XX_FINALINVAMOUNTBUD2"); //añadir la tabla a la lista
				valores.add(moinvfipr); // y el valor de ese campo
			  }

			  /*
			   * Rebjas promocionales presupuestadas y Rebajas definitivas presupuestadas
			   * chequear si en el archivo existe la columna MONREBAJAS y que tenga valores 
			   */
			  double morebpr = 0, morebprpr = 0, morebfrpr = 0;
			  if (!reader.get("MONREBAJAS").isEmpty()){
				morebpr = (new Double(reader.get("MONREBAJAS"))).doubleValue();
				morebprpr = morebpr * 0.5;
				morebfrpr = morebpr * 0.5;
				
				tablas.add("XX_AMOUNTSALEFRBUD"); //añadir la tabla a la lista
				valores.add(morebfrpr); // y el valor de ese campo
				
				tablas.add("XX_PROMSALEAMOUNTBUD"); //añadir la tabla a la lista
				valores.add(morebprpr); // y el valor de ese campo
			  }
			  
			  //rebajas FR presupuestadas
			    double morebdfpr = 0;
			    tablas.add("XX_FINALSALEAMOUNTBUD"); //añadir la tabla a la lista
				valores.add(morebdfpr); // y el valor de ese campo
				
				
				double momermpre = monvenpre * 0.2; //(new Double(reader.get("MOMERMPRE"))).doubleValue();
				tablas.add("XX_BUDDDECLINE"); //añadir la tabla a la lista
				valores.add(momermpre); // y el valor de ese campo
				
				
		    //chequear si en el archivo existe la columna PORROTPRE y que tenga valores 
			  if (!reader.get("PORROTPRE").isEmpty()){
				PORROTPRE = (new Double(reader.get("PORROTPRE"))).doubleValue();
				tablas.add("XX_ROTATIONBUD"); //añadir la tabla a la lista
				valores.add(PORROTPRE); // y el valor de ese campo
			  }
			  
			  
			//chequear si en el archivo existe la columna POMARBGPR y que tenga valores 
			  if (!reader.get("POMARBGPR").isEmpty()){
				POMARBGPR = (new Double(reader.get("POMARBGPR"))).doubleValue()*100;
				tablas.add("XX_LISCKGROSSMARGPERCTBUD"); //añadir la tabla a la lista
				valores.add(POMARBGPR); // y el valor de ese campo
				
				// MARGEN NETO
				POMARNGPR = POMARBGPR - 2; //2% por defecto
				tablas.add("XX_NETMARGPERTCATTLEBUD"); //añadir la tabla a la lista
				valores.add(POMARNGPR); // y el valor de ese campo				
			  }
			  
			  
			//chequear si en el archivo existe la columna PORMARSCPR y que tenga valores 
			  if (!reader.get("PORMARSCPR").isEmpty()){
				POMARSCPR = (new Double(reader.get("PORMARSCPR"))).doubleValue()*100;
				tablas.add("XX_MARGACCORDINGBUDPURCH"); //añadir la tabla a la lista
				valores.add(POMARSCPR); // y el valor de ese campo	
				
				// Margen Por Ganar = MB del inventario final o igual a MBSC
				// Margen Por Ganar %
				POMARPGPR = POMARSCPR;
				tablas.add("XX_BYWINMARGPERTBUD"); //añadir la tabla a la lista
				valores.add(POMARPGPR); // y el valor de ese campo	
			  }
			  
				/*
				 * Compras presupuestadas
				 * chequear si en el archivo existe la columna MONCOMPRE y que tenga valores 
				 */
			    String moncompre = reader.get("MONCOMPRE");
			    
				if (!moncompre.isEmpty()){
					tablas.add("XX_PURCHAMOUNTBUDGETED"); //añadir la tabla a la lista
					valores.add(moncompre); // y el valor de ese campo	
				}				
				
			  /*
			   * ventas presupuestadas en cantidad
			   * chequear si en el archivo existe la columna CANVENPRE y que tenga valores 
			   */
				String canvenpre = reader.get("CANVENPRE");
				
				if (!canvenpre.isEmpty()){
					tablas.add("XX_SALESAMOUNTBUD"); //añadir la tabla a la lista
					valores.add(canvenpre); // y el valor de ese campo	
				}
				
				/*
				 * compras presupuestadas en cantidad
				 * chequear si en el archivo existe la columna CANCOMPRE y que tenga valores 
				 */
				String cancompre = reader.get("CANCOMPRE");
				
				if (!cancompre.isEmpty()){
					tablas.add("XX_QUANTBUDGETEDSHOPPING"); //añadir la tabla a la lista
					valores.add(cancompre); // y el valor de ese campo	
				}
				
				
				/*
				 * Inventario inicial presupuestado en cantidad
				 * chequear si en el archivo existe la columna CAINVINPR y que tenga valores 
				 */
				String cainvinpr = reader.get("CAINVINPR");
				
				if (!cainvinpr.isEmpty()){
					tablas.add("XX_INVAMOUNTORIGBUDGETED"); //añadir la tabla a la lista
					valores.add(cainvinpr); // y el valor de ese campo	
				}	
				
				/*
				 * inventario final presupuestado en cantidad
				 * chequear si en el archivo existe la columna CAINVFIPR y que tenga valores 
				 */
				String cainvfipr = reader.get("CAINVFIPR");
				
				if (!cainvfipr.isEmpty()){
					tablas.add("XX_FINALINVAMOUNTBUD"); //añadir la tabla a la lista
					valores.add(cainvfipr); // y el valor de ese campo	
				}
				
				String canrebajas = "0"; //reader.get("CANREBAJAS");
				double carebpr = (new Double(canrebajas)).doubleValue();
				double carebprpr = carebpr * 0.5;
				double carebfrpr = carebpr * 0.5;
				double carebdfpr = 0;
				
			  //rebajas promocionales presupuestadas en cantidad
				tablas.add("XX_PROMSALENUMBUD"); //añadir la tabla a la lista
				valores.add(carebprpr); // y el valor de ese campo	
				
			  //rebajas FR presupuestadas en cantidad
				tablas.add("XX_BUDAMOUNTFRSALE"); //añadir la tabla a la lista
				valores.add(carebfrpr); // y el valor de ese campo	
				
			  //rebajas definitivas presupuestadas en cantidad
				tablas.add("XX_FINALBUDAMOUNTSALE"); //añadir la tabla a la lista
				valores.add(carebdfpr); // y el valor de ese campo	
				
				// REBAJAS
				if (morebpr != 0) {
					POREBPRPRTEMP = (morebprpr * 100) / morebpr;
					
					if (POREBPRPRTEMP > 999)
						POREBPRPRTEMP = 999;
					else if (POREBPRPRTEMP < 0)
						POREBPRPRTEMP = 0;
					
					POREBPRPR = POREBPRPRTEMP;
					
					POREBFRPRTEMP = (morebfrpr * 100) / morebpr;
					
					if (POREBFRPRTEMP > 999)
						POREBFRPRTEMP = 999;
					else if (POREBFRPRTEMP < 0)
						POREBFRPRTEMP = 0;
					
					POREBFRPR = POREBFRPRTEMP;
					
					POREBDFPRTEMP = (morebdfpr * 100) / morebpr;
				}
				
				if (POREBDFPRTEMP > 999)
					POREBDFPRTEMP = 999;
				else if (POREBDFPRTEMP < 0)
					POREBDFPRTEMP = 0;
				
				POREBDFPR = POREBDFPRTEMP;	
				
				 if (!reader.get("PORCOBPRE").isEmpty()){
					PORCOBPRE = new Double (reader.get("PORCOBPRE")).doubleValue();
					tablas.add("XX_PERCNBUDCOVERAGE");
					valores.add(PORCOBPRE);
				 }
				
				tablas.add("XX_PECTSALEPROMBUD"); //añadir la tabla a la lista
				valores.add(POREBPRPR); // y el valor de ese campo	
				
				tablas.add("XX_PORTSALEFRBUD"); //añadir la tabla a la lista
				valores.add(POREBFRPR); // y el valor de ese campo	
				
			  //rebakas definitivas presupuestadas en cantidad
				tablas.add("XX_PERCENTSQALEFINALBUD"); //añadir la tabla a la lista
				valores.add(POREBDFPR); // y el valor de ese campo			
		    
		    //construir el insert
		    sql = "INSERT INTO XX_VMR_PRLD01 ("; //tabla en la que voy a insertar los registros
		    
		  //las columnas de la tabla donde voy a insertar
		    for (int k=0;k<tablas.size();k++)
		      if (k<(tablas.size()-1)) //si todavia no ha llegado al ultimo nodo de la lista
		        sql += tablas.get(k)+",";  //concatena la coma
		     else // si esta en el ultimo nodo
		       sql += tablas.get(k)+")"; //no coloca la coma, solo el parentesis que cierra las tablas del insert
		    
		    sql += " VALUES ("; //añadir los valores que van a tener los campos
		    
		    //sacar de la lista los valores
		    for (int k=0;k<valores.size();k++)
		      if (k<(valores.size()-1)) //si todavia no ha llegado al ultimo nodo de la lista
			     sql += valores.get(k)+",";  //concatena la coma
			  else // si esta en el ultimo nodo
			     sql += valores.get(k)+")"; //no coloca la coma, solo el parentesis que cierra el VALUE

			   loteSentencias.add(sql);	
			   i++;
			   j++;
			   
			   //realizar commit en la base de datos cada "n" sentencias
			    if (j>=ejecutar){
			      MediadorBD.realizarLoteSentencias(loteSentencias, empresa);
			      loteSentencias = new ArrayList<String>();
			      j = 0;
			    } 	
			}	
		  } // while para recorrer el archivo
			
			//si sobraron registros que no se insertaron
			 if (j != 0 && continuar == true){
			  //se insertan y se hace commit en la base de datos
			    MediadorBD.realizarLoteSentencias(loteSentencias, empresa);
			 }
			
			 //inserto todos los registros exitosamente
			 mensaje = "Exito";
		
			 //Cerrar el archivo
			 reader.close();
			System.out.println("se insertaron "+(i-1));
			
		} catch (NumberFormatException e) { //error en el formato de los numeros del archivo
			String numero = e.getMessage().replace("For input string:", " ");
			mensaje = "Error de sintaxis en el archivo. Línea "+i+", número"+numero;
			System.out.println("Error en la carga de presupuesto "+e);
		}
		catch (Throwable e1) { //cualquier otro error
			mensaje = "Error en la carga de presupuesto. Error: "+e1.getClass();
			System.out.println("Error en la carga de presupuesto "+e1.getLocalizedMessage());
		}
	 
	 return mensaje;
   }
 
 //metodo para modificar el presupuesto comercial
 public void modificarPresupuesto (String mesAnio,String warehouse_id,String category_id,String department_id,String line_id,String section_id,String registro_id,
		String inventarioInicial,String compras,String ventas,String rebajasTotal,String rebajasDef,String inventarioFinal,String empresa){
	 
	 String sql = "";
	 
	 //transformar las variables a un formato numerico quitando la coma del decimal por un punto
	if (inventarioInicial != null && !inventarioInicial.isEmpty())
	 inventarioInicial = inventarioInicial.replace(",", ".");
	 
	if (compras != null && !compras.isEmpty())
	 compras = compras.replace(",", ".");
	 
	if (ventas != null && !ventas.isEmpty())
	 ventas = ventas.replace(",", ".");
	
	if (rebajasTotal != null && !rebajasTotal.isEmpty())
	 rebajasTotal = rebajasTotal.replace(",", ".");
	 
	if (rebajasDef != null && !rebajasDef.isEmpty())
	 rebajasDef = rebajasDef.replace(",", ".");
	 
	if (inventarioFinal != null && !inventarioFinal.isEmpty())
	 inventarioFinal = inventarioFinal.replace(",", ".");
	 
	 sql = "UPDATE XX_VMR_PRLD01 SET ";
	 
	 //cuando el tipo de registro es bolivares
	 if (registro_id.equals("bolivares")){
		 
		//actualizar el campo del inventario inicial (si sufrio algun cambio en la interfaz)
		 if (inventarioInicial != null && !inventarioInicial.isEmpty())
		   sql += "XX_INVEFECBUDGETEDAMOUNT = "+inventarioInicial+",";
		 
		//actualizar el campo de compras (si sufrio algun cambio en la interfaz)
		 if (compras != null && !compras.isEmpty())
		   sql += "XX_PURCHAMOUNTBUDGETED = "+compras+","; 
		 
		//actualizar el campo de ventas (si sufrio algun cambio en la interfaz)
		 if (ventas != null && !ventas.isEmpty())
		   sql += "XX_SALESAMOUNTBUD2 = "+ventas+","; 
		 
		//actualizar el campo de rebajas total (si sufrio algun cambio en la interfaz)
		 if (rebajasTotal != null && !rebajasTotal.isEmpty()){
		 //para este caso se modifica los campos de rebajas promocionales
		   sql += "XX_PROMSALEAMOUNTBUD = "+new BigDecimal(rebajasTotal).multiply(new BigDecimal (0.5))+",";
		  //y se modifica el campo de rebajas FR
		   sql += "XX_AMOUNTSALEFRBUD = "+new BigDecimal(rebajasTotal).multiply(new BigDecimal (0.5))+",";
		 }
		 
		//actualizar el campo de rebajas definitivas (si sufrio algun cambio en la interfaz)
		 if (rebajasDef != null && !rebajasDef.isEmpty())
		   sql += "XX_FINALSALEAMOUNTBUD = "+rebajasDef+",";
		 
		//actualizar el campo de inventario final (si sufrio algun cambio en la interfaz)
		 if (inventarioFinal != null && !inventarioFinal.isEmpty())
		  sql += "XX_FINALINVAMOUNTBUD2 = "+inventarioFinal+",";
	 }
	 //cuando el tipo de registro es piezas (cantidad)
	 else if (registro_id.equals("piezas")){
		 
		//actualizar el campo del inventario inicial (si sufrio algun cambio en la interfaz)
		 if (inventarioInicial != null && !inventarioInicial.isEmpty())
			 sql += "XX_INVAMOUNTORIGBUDGETED = "+inventarioInicial+",";
			 
		//actualizar el campo de compras (si sufrio algun cambio en la interfaz)
		 if (compras != null && !compras.isEmpty())
			 sql += "XX_QUANTBUDGETEDSHOPPING = "+compras+","; 
			 
		//actualizar el campo de ventas (si sufrio algun cambio en la interfaz)
		 if (ventas != null && !ventas.isEmpty())
			 sql += "XX_SALESAMOUNTBUD = "+ventas+","; 
			 
		//actualizar el campo de rebajas total (si sufrio algun cambio en la interfaz)
		 if (rebajasTotal != null && !rebajasTotal.isEmpty()){
			//para este caso se modifica los campos de rebajas promocionales
			 sql += "XX_PROMSALENUMBUD = "+new BigDecimal(rebajasTotal).multiply(new BigDecimal (0.5))+",";
			//y se modifica el campo de rebajas FR
			 sql += "XX_BUDAMOUNTFRSALE = "+new BigDecimal(rebajasTotal).multiply(new BigDecimal (0.5))+",";
			}
			 
		//actualizar el campo de rebajas definitivas (si sufrio algun cambio en la interfaz)
		 if (rebajasDef != null && !rebajasDef.isEmpty())
			 sql += "XX_FINALBUDAMOUNTSALE = "+rebajasDef+",";
			 
		//actualizar el campo de inventario final (si sufrio algun cambio en la interfaz)
		 if (inventarioFinal != null && !inventarioFinal.isEmpty())
			 sql += "XX_FINALINVAMOUNTBUD = "+inventarioFinal+","; 
	 }
	 
	 //agregar la condicion
	 sql += " WHERE XX_BUDGETYEARMONTH = "+mesAnio;
	 
	 //si seleccionaron una tienda en combo box de la interfaz
		if(warehouse_id != null && Integer.parseInt(warehouse_id)>0 && !registro_id.equals("rotacion")){
			sql += " AND M_Warehouse_ID=" +warehouse_id; // se añade al query
		 }
			
		//si seleccionaron una categoria en combo box de la interfaz
		if(category_id != null && Integer.parseInt(category_id)>0 && !registro_id.equals("rotacion")){
			sql += " AND XX_VMR_CATEGORY_ID=" + category_id; // se añade al query
		}
		
		//si seleccionaron un departamento en combo box de la interfaz
		if(department_id != null && Integer.parseInt(department_id)>0 && !registro_id.equals("rotacion")){
			sql += " AND XX_VMR_DEPARTMENT_ID=" +department_id; // se añade al query
		}
			
		//si seleccionaron una linea en combo box de la interfaz
		if(line_id != null && Integer.parseInt(line_id)>0 && !registro_id.equals("rotacion")){
			sql += " AND XX_VMR_LINE_ID = " +line_id; // se añade al query
		}
			
		//si seleccionaron una seccion en combo box de la interfaz
		if(section_id != null && Integer.parseInt(section_id)>0 && !registro_id.equals("rotacion")){
			sql += " AND XX_VMR_SECTION_ID=" + section_id; // se añade al query
		}
		
		//eliminar la coma antes del WHERE (si llegara a quedar de esa manera)
		sql = sql.replace(", WHERE"," WHERE");
		
		try {
			//ejecutar sql en la base de datos
			MediadorBD.realizarSentencia(sql, empresa);
		} catch (SQLException e) {
			System.out.println("Error al actualizar la modificacion del presupuesto comercial "+e);
		}
   } 
 
 public void exportarPresupuestoExcel (String anioMes,String warehouse_id,String category_id,
		 String department_id,String line_id,String section_id,String empresa){
	 
	 String sql = "SELECT to_number(d.value) codDep,"+
			 		"to_number(l.value) codLin,"+
			 		"to_number(s.value) codSec,"+
			 		"SUBSTR(p.XX_BUDGETYEARMONTH, 0, 4) año,"+
			 		"SUBSTR(p.XX_BUDGETYEARMONTH, 5, 6) mes,"+
			 		"to_number(w.value) codTie,"+
			 		"p.XX_INVEFECBUDGETEDAMOUNT MOINVINPR,"+
			 		"p.XX_SALESAMOUNTBUD2 MONVENPRE,"+
			 		"(p.XX_AMOUNTSALEFRBUD * 2) MONREBAJAS,"+
			 		"round(p.XX_BUDDDECLINE / 10, 2) MOMERMPRE,"+
			 		"p.XX_PURCHAMOUNTBUDGETED MONCOMPRE,"+
			 		"p.XX_FINALINVAMOUNTBUD2 MOINVFIPR,"+
			 		"p.XX_PERCNBUDCOVERAGE PORCOBPRE,"+
			 		"p.XX_ROTATIONBUD PORROTPRE,"+
			 		"round(p.XX_MARGACCORDINGBUDPURCH / 100, 2) PORMARSCPR,"+
			 		"round(p.XX_LISCKGROSSMARGPERCTBUD / 100, 2) POMARBGPR,"+
			 		"p.XX_INVAMOUNTORIGBUDGETED CAINVINPR,"+
			 		"p.XX_SALESAMOUNTBUD CANVENPRE,"+
			 		"(XX_PROMSALENUMBUD * 2) CANREBAJAS,"+
			 		"XX_FINALBUDAMOUNTSALE CANMERMA,"+	
			 		"XX_QUANTBUDGETEDSHOPPING CANCOMPRE,"+
			 		"XX_FINALINVAMOUNTBUD CAINVFIPR"+
			 		
			 		" FROM xx_vmr_prld01 p, xx_vmr_department d, xx_vmr_line l, xx_vmr_section s, m_warehouse w"+
			 		
			 		" WHERE d.xx_vmr_department_id = p.xx_vmr_department_id"+
			 		" AND l.xx_vmr_line_id = p.xx_vmr_line_id"+
			 		" AND s.xx_vmr_section_id = p.xx_vmr_section_id"+
			 		" AND w.m_warehouse_id = p.m_warehouse_id"+
			 		" AND p.XX_BUDGETYEARMONTH = "+anioMes;
			 		
	 		//si seleccionaron una tienda en combo box de la interfaz
			if(warehouse_id != null && Integer.parseInt(warehouse_id)>0){
				sql += " AND p.M_Warehouse_ID=" +warehouse_id; // se añade al query
			 }
			
			//si seleccionaron una categoria en combo box de la interfaz
			if(category_id != null && Integer.parseInt(category_id)>0){
				sql += " AND p.XX_VMR_CATEGORY_ID=" + category_id; // se añade al query
			}
			
			//si seleccionaron un departamento en combo box de la interfaz
			if(department_id != null && Integer.parseInt(department_id)>0){
				sql += " AND p.XX_VMR_DEPARTMENT_ID=" +department_id; // se añade al query
			}
				
			//si seleccionaron una linea en combo box de la interfaz
			if(line_id != null && Integer.parseInt(line_id)>0){
				sql += " AND p.XX_VMR_LINE_ID = " +line_id; // se añade al query
			}
				
			//si seleccionaron una seccion en combo box de la interfaz
			if(section_id != null && Integer.parseInt(section_id)>0){
				sql += " AND p.XX_VMR_SECTION_ID=" + section_id; // se añade al query
			}
			
			//eliminar la coma antes del WHERE (si llegara a quedar de esa manera)
			sql = sql.replace(", WHERE"," WHERE");
			
			 		
			sql += " ORDER BY CODDEP,CODLIN,CODSEC,AÑO,MES,CODTIE";
	 
	   Map[] result = MediadorBD.realizarConsulta(sql, empresa);
	   
	   try {
		   
		   String outputFile = rutaWindows+"Users\\ccastillo\\Desktop\\presupuestoComercial.xls";
		
		boolean alreadyExists = new File(outputFile).exists();
        
        if(alreadyExists){
            File ficheroUsuarios = new File(outputFile);
            ficheroUsuarios.delete();
        } 
        
		 //Se crea el libro Excel
		WritableWorkbook workbook =
		           Workbook.createWorkbook(new File(outputFile));
        
		//Se crea una nueva hoja dentro del libro
        WritableSheet sheet =
                workbook.createSheet("Presupuesto Comercial", 0);
        
        //agregar nombre de las columnas, primer parametro columna, segundo la fila
        sheet.addCell(new jxl.write.Label(0, 0, "CODDEP")); 
        sheet.addCell(new jxl.write.Label(1, 0, "CODLIN")); 
        sheet.addCell(new jxl.write.Label(2, 0, "CODSEC")); 
        sheet.addCell(new jxl.write.Label(3, 0, "AÑO")); 
        sheet.addCell(new jxl.write.Label(4, 0, "MES")); 
        sheet.addCell(new jxl.write.Label(5, 0, "CODTIE")); 
        sheet.addCell(new jxl.write.Label(6, 0, "MOINVINPR"));    
        sheet.addCell(new jxl.write.Label(7, 0, "MONVENPRE")); 
        sheet.addCell(new jxl.write.Label(8, 0, "MONREBAJAS")); 
        sheet.addCell(new jxl.write.Label(9, 0, "MOMERMPRE")); 
        sheet.addCell(new jxl.write.Label(10, 0, "MONCOMPRE")); 
        sheet.addCell(new jxl.write.Label(11, 0, "MOINVFIPR")); 
        sheet.addCell(new jxl.write.Label(12, 0, "PORCOBPRE")); 
        sheet.addCell(new jxl.write.Label(13, 0, "PORROTPRE")); 
        sheet.addCell(new jxl.write.Label(14, 0, "PORMARSCPR"));    
        sheet.addCell(new jxl.write.Label(15, 0, "POMARBGPR")); 
        sheet.addCell(new jxl.write.Label(16, 0, "CAINVINPR")); 
        sheet.addCell(new jxl.write.Label(17, 0, "CANVENPRE")); 
        sheet.addCell(new jxl.write.Label(18, 0, "CANREBAJAS")); 
        sheet.addCell(new jxl.write.Label(19, 0, "CANMERMA")); 
        sheet.addCell(new jxl.write.Label(20, 0, "CANCOMPRE"));    
        sheet.addCell(new jxl.write.Label(21, 0, "CAINVFIPR")); 
	  
	   if (result != null){
		   for (int i=0;i<result.length;i++){
			   //agregar los valores 
			    sheet.addCell(new jxl.write.Number(0, (i+1), new Integer (result[i].get("CODDEP").toString()))); 
		        sheet.addCell(new jxl.write.Number(1, (i+1), new Integer (result[i].get("CODLIN").toString()))); 
		        sheet.addCell(new jxl.write.Number(2, (i+1), new Integer (result[i].get("CODSEC").toString()))); 
		        sheet.addCell(new jxl.write.Number(3, (i+1), new Integer (result[i].get("AÑO").toString()))); 
		        sheet.addCell(new jxl.write.Number(4, (i+1), new Integer (result[i].get("MES").toString()))); 
		        sheet.addCell(new jxl.write.Number(5, (i+1), new Integer (result[i].get("CODTIE").toString()))); 
		        
		         if (result[i].get("MOINVINPR").toString().contains(".")) //si es decimal
		           sheet.addCell(new jxl.write.Label(6, (i+1), result[i].get("MOINVINPR").toString()));  
		         else
		           sheet.addCell(new jxl.write.Number(6, (i+1), new Integer (result[i].get("MOINVINPR").toString())));
		         
		         if (result[i].get("MONVENPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(7, (i+1), result[i].get("MONVENPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(7, (i+1), new Integer (result[i].get("MONVENPRE").toString())));
		         
		         if (result[i].get("MONREBAJAS").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(8, (i+1), result[i].get("MONREBAJAS").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(8, (i+1), new Integer (result[i].get("MONREBAJAS").toString())));
		         
		         if (result[i].get("MOMERMPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(9, (i+1), result[i].get("MOMERMPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(9, (i+1), new Integer (result[i].get("MOMERMPRE").toString())));
		         
		         
		         if (result[i].get("MONCOMPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(10, (i+1), result[i].get("MONCOMPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(10, (i+1), new Integer (result[i].get("MONCOMPRE").toString())));
		         
		         if (result[i].get("MOINVFIPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(11, (i+1), result[i].get("MOINVFIPR").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(11, (i+1), new Integer (result[i].get("MOINVFIPR").toString())));
		         
		         
		         if (result[i].get("PORCOBPRE").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(12, (i+1), result[i].get("PORCOBPRE").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(12, (i+1), new Integer (result[i].get("PORCOBPRE").toString())));
		         
		         
		         if (result[i].get("PORROTPRE").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(13, (i+1), result[i].get("PORROTPRE").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(13, (i+1), new Integer (result[i].get("PORROTPRE").toString())));
		          
		         if (result[i].get("PORMARSCPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(14, (i+1), result[i].get("PORMARSCPR").toString()));  
				 else
				  sheet.addCell(new jxl.write.Number(14, (i+1), new Integer (result[i].get("PORMARSCPR").toString())));
		         
		         
		         if (result[i].get("POMARBGPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(15, (i+1), result[i].get("POMARBGPR").toString()));  
				 else
				  sheet.addCell(new jxl.write.Number(15, (i+1), new Integer (result[i].get("POMARBGPR").toString())));
		         
		        sheet.addCell(new jxl.write.Number(16, (i+1), new Integer (result[i].get("CAINVINPR").toString()))); 
		        sheet.addCell(new jxl.write.Number(17, (i+1), new Integer (result[i].get("CANVENPRE").toString()))); 
		        sheet.addCell(new jxl.write.Number(18, (i+1), new Integer (result[i].get("CANREBAJAS").toString()))); 
		        sheet.addCell(new jxl.write.Number(19, (i+1), new Integer (result[i].get("CANMERMA").toString()))); 
		        sheet.addCell(new jxl.write.Number(20, (i+1), new Integer (result[i].get("CANCOMPRE").toString())));    
		        sheet.addCell(new jxl.write.Number(21, (i+1), new Integer (result[i].get("CAINVFIPR").toString()))); 		
	       
		   }
 
		 //Escribimos los resultados al fichero Excel
	       workbook.write();  
	       
           workbook.close();
	   }
       
	} catch (IOException e) {
		System.out.println("Error al crear el archivo excel");
	} catch (RowsExceededException e) {
		System.out.println("Error en la columna");
	} catch (WriteException e) {
		 System.out.println("Error al escribir el fichero.");
	}
 }
 
 public List<PresupuestoComercial> sacarPresupuestoHistorico(String anioMes, String empresa,String warehouse_id,String category_id,
		 String department_id,String line_id,String section_id,String registro_id){
	 
	 String sql = "";
	 int anio;
	 List<PresupuestoComercial> listaPresupuestoComercial = new LinkedList<PresupuestoComercial>();	
	 
	 anio = Integer.parseInt(anioMes.substring(0,4));
	 
	 		if (registro_id.equals("bolivares")){
	 			sql = "select round (sum (p.XX_INVEFECBUDGETEDAMOUNT)/3,2) INV_INICIAL_PRES,"+
	 					"round (sum (p.XX_SALESAMOUNTBUD2)/3,2) VENTAS_PRESUPU,"+
	 					"round (sum ((p.XX_AMOUNTSALEFRBUD*2))/3,2) REBAJAS_TOTAL_PRESU,"+
	 					"round (sum (p.XX_PURCHAMOUNTBUDGETED)/3,2) COMPRAS_PRES,"+
	 					"round (sum(p.XX_FINALINVAMOUNTBUD2)/3,2) INVENTA_FINAL_PRESU,"+
	 					"NVL (round (SUM(p.XX_FINALSALEAMOUNTBUD),2),0) Reb_Def_Pres" ;
	 			}
	  	     else if (registro_id.equals("piezas")){
	  		 	sql ="select round (sum (p.XX_PROMSALENUMBUD * 2)/3,2) REBAJAS_TOTAL_PRESU,"+
	  		 		  "round (sum (p.XX_INVAMOUNTORIGBUDGETED)/3,2) INV_INICIAL_PRES,"+
	  		 		  "round (sum (p.XX_SALESAMOUNTBUD)/3,2) VENTAS_PRESUPU,"+
	  		 		  "round (sum (p.XX_QUANTBUDGETEDSHOPPING)/3,2) COMPRAS_PRES,"+
	  		 		  "round (sum (p.XX_FINALINVAMOUNTBUD)/3,2) INVENTA_FINAL_PRESU,"+
	  		 		  "NVL (round (SUM(p.XX_FINALBUDAMOUNTSALE),2),0) Reb_Def_Pres";
	  	 	    }
	 		
			sql +=" from xx_vmr_prld01 p,xx_vmr_department d,xx_vmr_line l,xx_vmr_section s,m_warehouse w"+
				" where"+  
				" d.xx_vmr_department_id = p.xx_vmr_department_id"+
				" and l.xx_vmr_line_id = p.xx_vmr_line_id"+
				" and s.xx_vmr_section_id = p.xx_vmr_section_id"+
				" and w.m_warehouse_id = p.m_warehouse_id"+
				" and p.XX_BUDGETYEARMONTH in ("+(anio-1)+anioMes.substring(4,6)+","+(anio-2)+anioMes.substring(4,6)+","+(anio-3)+anioMes.substring(4,6)+")"+
				" and p.XX_INVEFECBUDGETEDAMOUNT >0"+
				" and p.XX_SALESAMOUNTBUD2 > 0"+
				" and p.XX_BUDDDECLINE > 0"+
				" and p.XX_PURCHAMOUNTBUDGETED > 0"+
				" and p.XX_FINALINVAMOUNTBUD2 > 0";
			 	 	
			 	 //si seleccionaron una tienda en combo box de la interfaz
					if(warehouse_id != null && Integer.parseInt(warehouse_id)>0){
						sql += " AND o.M_Warehouse_ID=" +warehouse_id; // se añade al query
					 }
						
					//si seleccionaron una categoria en combo box de la interfaz
					if(category_id != null && Integer.parseInt(category_id)>0){
						sql += " AND o.XX_VMR_CATEGORY_ID=" + category_id; // se añade al query
					}
					
					//si seleccionaron un departamento en combo box de la interfaz
					if(department_id != null && Integer.parseInt(department_id)>0){
						sql += " AND o.XX_VMR_DEPARTMENT_ID=" +department_id; // se añade al query
					}
						
					//si seleccionaron una linea en combo box de la interfaz
					if(line_id != null && Integer.parseInt(line_id)>0){
						sql += " AND o.XX_VMR_LINE_ID = " +line_id; // se añade al query
					}
						
					//si seleccionaron una seccion en combo box de la interfaz
					if(section_id != null && Integer.parseInt(section_id)>0){
						sql += " AND o.XX_VMR_SECTION_ID=" + section_id; // se añade al query
					}

		 Map[] result = MediadorBD.realizarConsulta(sql, empresa);
		 
		 PresupuestoComercial presupuestoComercial = new PresupuestoComercial();
		 SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	     SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
		 
		 presupuestoComercial.setAnio(anio);
			
			try {
				//pasar un mes (dado en numero) a texto, ejemplo: 2 -> Febrero, 6 -> Junio, etc
				String mes = monthDisplay.format(monthParse.parse(anioMes.substring(4,6)));
				//poner la primer letra en mayuscula
				mes = mes.toUpperCase().charAt(0)+mes.substring(1,mes.length());
	            //asignarle el mes del presupuesto comercial (el nombre, Junio)			
				presupuestoComercial.setMesNombre(mes);
				//asignarle el mes del presupuesto comercial (el numero, 06)
				presupuestoComercial.setMesNumero(Integer.parseInt(anioMes.substring(4,6)));
			} catch (ParseException e1) {
				System.out.println("Error al convertir el mes a texto");
			}
		 
		 if (result != null){
			 for (int i=0;i<result.length;i++){

	    		  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
				 presupuestoComercial.setinv_inicial_presupuestado(new BigDecimal (result[i].get("INV_INICIAL_PRES").toString()));
				 presupuestoComercial.setcompras_presupuestadas(new BigDecimal (result[i].get("COMPRAS_PRES").toString()));
				 presupuestoComercial.setventas_presupuestadas(new BigDecimal (result[i].get("VENTAS_PRESUPU").toString()));
				 presupuestoComercial.setrebajas_total_presupuestas(new BigDecimal (result[i].get("REBAJAS_TOTAL_PRESU").toString()));
				 presupuestoComercial.setrebajas_definitivas_presupuestadas(new BigDecimal (result[i].get("REB_DEF_PRES").toString()));
				 presupuestoComercial.setinv_final_presupuestado(new BigDecimal (result[i].get("INVENTA_FINAL_PRESU").toString()));
	 
				 //guardo el objeto que contiene la consulta en la lita del presupuesto comercial
				 listaPresupuestoComercial.add(presupuestoComercial);
		  
		 }
	 }
  return listaPresupuestoComercial;
 }
 
 public void exportarExcelPresupuestoCreado(String anioMes,String warehouse_id,String category_id,
		 String department_id,String line_id,String section_id,String empresa){

	 int anio = Integer.parseInt(anioMes.substring(0,4));
	 
	 String sql = "select "+
			 		"to_number (d.value) codDep,"+
			 		"to_number (l.value) codLin,"+ 
			 		"to_number (s.value) codSec,"+ 
			 		"to_number (w.value) codTie,"+
			 		"round (sum (p.XX_INVEFECBUDGETEDAMOUNT)/3,2) MOINVINPR,"+
			 		"round (sum (p.XX_SALESAMOUNTBUD2)/3,2) MONVENPRE,"+
			 		"round (sum ((p.XX_AMOUNTSALEFRBUD*2))/3,2) MONREBAJAS,"+
			 		"round (sum (round (p.XX_BUDDDECLINE/10,2))/3,2) MOMERMPRE,"+
			 		"round (sum (p.XX_PURCHAMOUNTBUDGETED)/3,2) MONCOMPRE,"+
			 		"round (sum(p.XX_FINALINVAMOUNTBUD2)/3,2) MOINVFIPR,"+
			 		"round (sum (p.XX_PERCNBUDCOVERAGE)/3,2) PORCOBPRE,"+
			 		"round (sum (p.XX_ROTATIONBUD)/3,2) PORROTPRE,"+
			 		"round (sum (p.XX_MARGACCORDINGBUDPURCH/300),2) PORMARSCPR,"+
			 		"round (sum (p.XX_LISCKGROSSMARGPERCTBUD/300),2) POMARBGPR,"+
			 		"round (sum (XX_PROMSALENUMBUD * 2)/3,0) CANREBAJAS,"+
			 		"round (sum (p.XX_INVAMOUNTORIGBUDGETED)/3,0) CAINVINPR,"+
			 		"round (sum (p.XX_SALESAMOUNTBUD)/3,0) CANVENPRE,"+
			 		"round (sum (p.XX_FINALBUDAMOUNTSALE)/3,0) CANMERMA,"+
			 		"round (sum (p.XX_QUANTBUDGETEDSHOPPING)/3,0) CANCOMPRE,"+
			 		"round (sum (p.XX_FINALINVAMOUNTBUD)/3,0) CAINVFIPR"+

			 		" from xx_vmr_prld01 p,xx_vmr_department d,xx_vmr_line l,xx_vmr_section s,m_warehouse w"+
			 		" where"+  
			 		" d.xx_vmr_department_id = p.xx_vmr_department_id"+
			 		" and l.xx_vmr_line_id = p.xx_vmr_line_id"+
			 		" and s.xx_vmr_section_id = p.xx_vmr_section_id"+
			 		" and w.m_warehouse_id = p.m_warehouse_id"+
			 		" and p.XX_BUDGETYEARMONTH in ("+(anio-1)+anioMes.substring(4,6)+","+(anio-2)+anioMes.substring(4,6)+","+(anio-3)+anioMes.substring(4,6)+")"+
	 				" and p.XX_INVEFECBUDGETEDAMOUNT >0"+
	 				" and p.XX_SALESAMOUNTBUD2 > 0"+
	 				" and p.XX_BUDDDECLINE > 0"+
	 				" and p.XX_PURCHAMOUNTBUDGETED > 0"+
	 				" and p.XX_FINALINVAMOUNTBUD2 > 0";
	 				
	 		//si seleccionaron una tienda en combo box de la interfaz
			if(warehouse_id != null && Integer.parseInt(warehouse_id)>0){
				sql += " AND p.M_Warehouse_ID=" +warehouse_id; // se añade al query
			 }
			
			//si seleccionaron una categoria en combo box de la interfaz
			if(category_id != null && Integer.parseInt(category_id)>0){
				sql += " AND p.XX_VMR_CATEGORY_ID=" + category_id; // se añade al query
			}
			
			//si seleccionaron un departamento en combo box de la interfaz
			if(department_id != null && Integer.parseInt(department_id)>0){
				sql += " AND p.XX_VMR_DEPARTMENT_ID=" +department_id; // se añade al query
			}
				
			//si seleccionaron una linea en combo box de la interfaz
			if(line_id != null && Integer.parseInt(line_id)>0){
				sql += " AND p.XX_VMR_LINE_ID = " +line_id; // se añade al query
			}
				
			//si seleccionaron una seccion en combo box de la interfaz
			if(section_id != null && Integer.parseInt(section_id)>0){
				sql += " AND p.XX_VMR_SECTION_ID=" + section_id; // se añade al query
			}
			
			//eliminar la coma antes del WHERE (si llegara a quedar de esa manera)
			sql = sql.replace(", WHERE"," WHERE");
			
			sql += 	" group by d.value,l.value,s.value,w.value"+
	 				" order by CODDEP,CODLIN,CODSEC,CODTIE";
			 	
	 
	   Map[] result = MediadorBD.realizarConsulta(sql, empresa);
	   
	   try {
		   
		   String outputFile = rutaWindows+"Users\\ccastillo\\Desktop\\presupuestoCreado.xls";
		
		boolean alreadyExists = new File(outputFile).exists();
        
        if(alreadyExists){
            File ficheroUsuarios = new File(outputFile);
            ficheroUsuarios.delete();
        } 
        
		 //Se crea el libro Excel
		WritableWorkbook workbook =
		           Workbook.createWorkbook(new File(outputFile));
        
		//Se crea una nueva hoja dentro del libro
        WritableSheet sheet =
                workbook.createSheet("Presupuesto Comercial", 0);
        
        //agregar nombre de las columnas, primer parametro columna, segundo la fila
        sheet.addCell(new jxl.write.Label(0, 0, "CODDEP")); 
        sheet.addCell(new jxl.write.Label(1, 0, "CODLIN")); 
        sheet.addCell(new jxl.write.Label(2, 0, "CODSEC")); 
        sheet.addCell(new jxl.write.Label(3, 0, "AÑO")); 
        sheet.addCell(new jxl.write.Label(4, 0, "MES")); 
        sheet.addCell(new jxl.write.Label(5, 0, "CODTIE")); 
        sheet.addCell(new jxl.write.Label(6, 0, "MOINVINPR"));    
        sheet.addCell(new jxl.write.Label(7, 0, "MONVENPRE")); 
        sheet.addCell(new jxl.write.Label(8, 0, "MONREBAJAS")); 
        sheet.addCell(new jxl.write.Label(9, 0, "MOMERMPRE")); 
        sheet.addCell(new jxl.write.Label(10, 0, "MONCOMPRE")); 
        sheet.addCell(new jxl.write.Label(11, 0, "MOINVFIPR")); 
        sheet.addCell(new jxl.write.Label(12, 0, "PORCOBPRE")); 
        sheet.addCell(new jxl.write.Label(13, 0, "PORROTPRE")); 
        sheet.addCell(new jxl.write.Label(14, 0, "PORMARSCPR"));    
        sheet.addCell(new jxl.write.Label(15, 0, "POMARBGPR")); 
        sheet.addCell(new jxl.write.Label(16, 0, "CAINVINPR")); 
        sheet.addCell(new jxl.write.Label(17, 0, "CANVENPRE")); 
        sheet.addCell(new jxl.write.Label(18, 0, "CANREBAJAS")); 
        sheet.addCell(new jxl.write.Label(19, 0, "CANMERMA")); 
        sheet.addCell(new jxl.write.Label(20, 0, "CANCOMPRE"));    
        sheet.addCell(new jxl.write.Label(21, 0, "CAINVFIPR")); 
	  
	   if (result != null){
		   for (int i=0;i<result.length;i++){
			   //agregar los valores 
			    sheet.addCell(new jxl.write.Number(0, (i+1), new Integer (result[i].get("CODDEP").toString()))); 
		        sheet.addCell(new jxl.write.Number(1, (i+1), new Integer (result[i].get("CODLIN").toString()))); 
		        sheet.addCell(new jxl.write.Number(2, (i+1), new Integer (result[i].get("CODSEC").toString()))); 
		        sheet.addCell(new jxl.write.Number(3, (i+1), new Integer (anioMes.substring(0,4)))); 
		        sheet.addCell(new jxl.write.Number(4, (i+1), new Integer (anioMes.substring(4,6)))); 
		        sheet.addCell(new jxl.write.Number(5, (i+1), new Integer (result[i].get("CODTIE").toString()))); 
		        
		         if (result[i].get("MOINVINPR").toString().contains(".")) //si es decimal
		           sheet.addCell(new jxl.write.Label(6, (i+1), result[i].get("MOINVINPR").toString()));  
		         else
		           sheet.addCell(new jxl.write.Number(6, (i+1), new Integer (result[i].get("MOINVINPR").toString())));
		         
		         if (result[i].get("MONVENPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(7, (i+1), result[i].get("MONVENPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(7, (i+1), new Integer (result[i].get("MONVENPRE").toString())));
		         
		         if (result[i].get("MONREBAJAS").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(8, (i+1), result[i].get("MONREBAJAS").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(8, (i+1), new Integer (result[i].get("MONREBAJAS").toString())));
		         
		         if (result[i].get("MOMERMPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(9, (i+1), result[i].get("MOMERMPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(9, (i+1), new Integer (result[i].get("MOMERMPRE").toString())));
		         
		         if (result[i].get("MONCOMPRE").toString().contains(".")) //si es decimal
			       sheet.addCell(new jxl.write.Label(10, (i+1), result[i].get("MONCOMPRE").toString()));  
			     else
			       sheet.addCell(new jxl.write.Number(10, (i+1), new Integer (result[i].get("MONCOMPRE").toString())));
		         
		         if (result[i].get("MOINVFIPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(11, (i+1), result[i].get("MOINVFIPR").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(11, (i+1), new Integer (result[i].get("MOINVFIPR").toString())));
		         
		         if (result[i].get("PORCOBPRE").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(12, (i+1), result[i].get("PORCOBPRE").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(12, (i+1), new Integer (result[i].get("PORCOBPRE").toString())));
		         
		         if (result[i].get("PORROTPRE").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(13, (i+1), result[i].get("PORROTPRE").toString()));  
				 else
				   sheet.addCell(new jxl.write.Number(13, (i+1), new Integer (result[i].get("PORROTPRE").toString())));
		          
		         if (result[i].get("PORMARSCPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(14, (i+1), result[i].get("PORMARSCPR").toString()));  
				 else
				  sheet.addCell(new jxl.write.Number(14, (i+1), new Integer (result[i].get("PORMARSCPR").toString())));
		         
		         if (result[i].get("POMARBGPR").toString().contains(".")) //si es decimal
				   sheet.addCell(new jxl.write.Label(15, (i+1), result[i].get("POMARBGPR").toString()));  
				 else
				  sheet.addCell(new jxl.write.Number(15, (i+1), new Integer (result[i].get("POMARBGPR").toString())));
		         
		        sheet.addCell(new jxl.write.Number(16, (i+1), new Integer (result[i].get("CAINVINPR").toString()))); 
		        sheet.addCell(new jxl.write.Number(17, (i+1), new Integer (result[i].get("CANVENPRE").toString()))); 
		        sheet.addCell(new jxl.write.Number(18, (i+1), new Integer (result[i].get("CANREBAJAS").toString()))); 
		        sheet.addCell(new jxl.write.Number(19, (i+1), new Integer (result[i].get("CANMERMA").toString()))); 
		        sheet.addCell(new jxl.write.Number(20, (i+1), new Integer (result[i].get("CANCOMPRE").toString())));    
		        sheet.addCell(new jxl.write.Number(21, (i+1), new Integer (result[i].get("CAINVFIPR").toString()))); 		
	       
		   }
 
		 //Escribimos los resultados al fichero Excel
	       workbook.write();  
	       
           workbook.close();
	   }
       
	} catch (IOException e) {
		System.out.println("Error al crear el archivo excel");
	} catch (RowsExceededException e) {
		System.out.println("Error en la columna");
	} catch (WriteException e) {
		 System.out.println("Error al escribir el fichero.");
	}
 
 }
}
