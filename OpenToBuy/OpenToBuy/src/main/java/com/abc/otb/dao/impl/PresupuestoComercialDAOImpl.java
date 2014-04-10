package com.abc.otb.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.abc.otb.dao.PresupuestoComercialDAO;
import com.abc.otb.logico.PresupuestoComercial;
import com.abc.otb.mediadorbd.MediadorBD;

public class PresupuestoComercialDAOImpl implements PresupuestoComercialDAO{

	private List<PresupuestoComercial> listaPresupuestoComercial = new LinkedList<PresupuestoComercial>();	
	

	public void presupuestoMeses (String mesAnio,String warehouse_id,String category_id,String department_id,
			String line_id,String section_id,String registro_id,int meses,String empresa){
		Integer mesAnioAux = Integer.parseInt(mesAnio);
		
		//inicializar la lista, para borrar todos los meses almacenados en ella
		listaPresupuestoComercial = new LinkedList<PresupuestoComercial>();
		
		//sacar los seis meses para el reporte
		for (int i=0;i<meses;i++){
			//sacar el presupuesto comercial para ese mes
			getMonth (mesAnioAux.toString(),warehouse_id,category_id,department_id,line_id,section_id,registro_id,empresa);
			//si es el ultimo mes del año
			 if (mesAnioAux.toString().substring(4, 6).equals("12")){
				Integer auxYear = Integer.parseInt(mesAnioAux.toString().substring(0,4)); //sacar el año en el que estoy
				auxYear++; // pasar al siguiente año
				String nuevoMes = auxYear.toString()+"01"; //el nuevo mes al que le voy a sacar el presupuesto comercial
				mesAnioAux = Integer.parseInt(nuevoMes);
			 }
			 else // si no es el ultimo mes del año
			   mesAnioAux++; // se pasa al siguiente mes	
		}
	}
	
	public String siguienteAnteriorMes (String mes,boolean anterior){
		Integer mesAux = Integer.parseInt(mes);
		String mesNuevo;
		
	 /* TRUE sacar el mes anterior
	  * FALSE no sacar el mes anterior, sacar el mes proximo
	  */
	 if (anterior == false){ //sacar el mes proximo
		if (mesAux == 12) // si es el ultimo mes
		  mesAux = 1; // se reinicia en al primer mes
		else // sino es el ultimo mes
		  mesAux++; // se pasa al siguiente mes
			  
		 mesNuevo = mesAux.toString();
		 
	   }
	 //sacar el mes anterior
	 else {
		if (mesAux == 1) // si es el primer mes
		  mesAux = 12; // ahora el nuevo mes va ser el utlimo
		else // sino es el ultimo mes
		  mesAux--; // se pasa al anterior mes
		
	   mesNuevo = mesAux.toString();
	  }
	 
	 //si es el mes tiene longitud 1 (Enero hasta Septiembre)
	   if (mesNuevo.length() == 1)
		 mesNuevo = "0"+mesNuevo; // se le concatena el cero
	   
	 return mesNuevo;		  
	}
	
	public String siguienteAnteriorAnio (String mes, String anio,boolean anterior){
		Integer mesAux = Integer.parseInt(mes);
		Integer anioAux = Integer.parseInt(anio);
		
		/* TRUE sacar el mes anterior
		  * FALSE no sacar el mes anterior, sacar el mes proximo
		  */
	 if (anterior == false){ //año proximo
		if (mesAux == 12) // si es el ultimo mes
			anioAux++; // paso al proximo año
	 }
	 else{ //año anterior
	    if (mesAux == 1) //si estoy en el primer mes
	      anioAux--; // paso al año anterior
	 }
		    
	 return String.valueOf(anioAux);		  
	}
	
	public String getPastYearMonth(String yearMonth_Actual)
	{
		Integer year = new Integer (yearMonth_Actual.substring(0, 4));
		Integer month = new Integer (yearMonth_Actual.substring(4, 6));
		
		//sacar el mes: formato año+mes (201312)
		if(yearMonth_Actual.substring(4, 6).equals("01")){ //si es el primer mes
			year = year - 1; //regreso un año
			month = 12; // y seteo el mes en Diciembre
		} //si no es el primer mes
		else{
			month = month - 1; //paso al mes anterior
		}
		
		String myMonthAux = month.toString();	
		
		//si el mes esta entre Enero o Septiembre (son de 1 solo digito)
		if(myMonthAux.length()==1)
			myMonthAux = "0"+month.toString(); // se les concatena el cero
		
		return year.toString() + myMonthAux;
		
	}
	
	
	public void getMonth (String monthAux,String warehouse_id,String category_id,String department_id,
			String line_id,String section_id,String registro_id,String empresa){

		String sql;
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        
		PresupuestoComercial presupuestoComercial = new PresupuestoComercial();
		
		//sacar el mes anterior al que le quiero sacar el presupuesto comercial
		String pastYearMonth = getPastYearMonth(monthAux);

		//setear el año del presupuesto comercial
		presupuestoComercial.setAnio(Integer.parseInt(monthAux.substring(0, 4)));
		
		try {
			//pasar un mes (dado en numero) a texto, ejemplo: 2 -> Febrero, 6 -> Junio, etc
			String mes = monthDisplay.format(monthParse.parse(monthAux.substring(4, 6)));
			//poner la primer letra en mayuscula
			mes = mes.toUpperCase().charAt(0)+mes.substring(1,mes.length());
            //asignarle el mes del presupuesto comercial (el nombre, Junio)			
			presupuestoComercial.setMesNombre(mes);
			//asignarle el mes del presupuesto comercial (el numero, 06)
			presupuestoComercial.setMesNumero(Integer.parseInt(monthAux.substring(4, 6)));
		} catch (ParseException e1) {
			System.out.println("Error al convertir el mes a texto");
		}
		
	//si seleccionaron bolivares en el combo box de tipo de registro de la interfaz
	  if (registro_id.equals("bolivares")){
		  //query para sacar el presupuesto comercial
		sql = "select " +
				 "NVL (SUM(XX_INVEFECBUDGETEDAMOUNT),0) Inv_Inicial_Pres," +
				 "NVL (SUM(XX_AMOUNTINIINVEREAL),0) Inv_Inicial_Real," +
				 "NVL (SUM(XX_PURCHAMOUNTBUDGETED),0) Compras_Pres," +
				 "NVL (SUM(XX_AMOUNTPLACEDNACPURCHCOST+XX_PURCHAMOUNTPLACEDCOSTIMP+XX_PURCHAMOUNTPLADPASTMONTHS),0) Compras_Col," +
				 "NVL (SUM(XX_AMOUNTPLACEDNACPURCHCOST),0) Compras_Col_Nac," +
				 "NVL (SUM(XX_PURCHAMOUNTPLACEDCOSTIMP),0) Compras_Col_Imp," +
				 "NVL (SUM(XX_PURCHAMOUNTPLADPASTMONTHS),0) Comp_Col_Mes_Ant," +
				 "NVL (SUM(XX_ReceiptPVP),0) Rec," +
				 "NVL (SUM(NVL(XX_NACPURCHAMOUNTRECEIVED, 0) + NVL(XX_PURCHAMOUNTREVIMPD, 0) " +
				   "+ NVL(XX_PURCHAMOUNTREDPASTMONTHS, 0) + NVL(XX_PlacedOrderPVPAdjustment, 0)),0) Com_CHEQ_Total," +
				 "NVL (SUM(XX_NACPURCHAMOUNTRECEIVED),0) Comp_CHEQ_Nacio," +
				 "NVL (SUM(XX_PURCHAMOUNTREVIMPD),0) Comp_CHEQ_Imp," +
				 "NVL (SUM(XX_PURCHAMOUNTREDPASTMONTHS),0) Comp_CHEQ_Mes_Ant," +
				 "NVL (SUM(XX_SALESAMOUNTBUD2),0) Ventas_Presupu," +
				 "NVL (SUM(XX_AMOUNTACTUALSALE),0) Ventas_Reales," +
				 "NVL (SUM(XX_TRANSFAMOUNTSENT),0) Traspasos_Enviados," +
				 "NVL (SUM(XX_TRANSFAMOUNTRECEIVED),0) Traspasos_Recibidos," +
				 "NVL (SUM(XX_RETURNSPVP),0) Dev," +
				 "NVL (SUM(XX_PROMSALEAMOUNTBUD + XX_AMOUNTSALEFRBUD + XX_FINALSALEAMOUNTBUD),0) Rebajas_Total_Presu," +
				 "NVL (SUM(XX_ACTAMOUNTSALEPROM + XX_ACTAMOUNTSALEFR + XX_FINALACTAMOUNTSALE),0) Rebajas_Total_Real," +
				 "NVL (SUM(XX_PROMSALEAMOUNTBUD),0) Reb_Promoci_Presup," +
				 "NVL (SUM(XX_ACTAMOUNTSALEPROM),0) Reb_Promoci_Real," +
				 "NVL (SUM(XX_AMOUNTSALEFRBUD),0) Reb_FR_Presu," +
				 "NVL (SUM(XX_ACTAMOUNTSALEFR),0) Reb_FR_Real," +
				 "NVL (SUM(XX_FINALSALEAMOUNTBUD),0) Reb_Def_Pres," +
				 "NVL (SUM(XX_FINALACTAMOUNTSALE),0) Reb_Def_Real," +
				 "NVL (SUM(XX_FINALINVAMOUNTBUD2),0) Inventa_Final_Presu," +
				 "NVL (SUM(XX_INVAMOUNTFINALREAL),0) Inventa_Final_Real," +
				 "NVL (SUM(XX_FINALINVAMOUNTPROJD),0) Inventa_Final_Proy," +
				 "NVL (SUM(XX_AMOUNTPURCHASELIMIT),0) Limite_de_Compras " +
				 "from xx_vmr_prld01 B " +
				 "where B.XX_BUDGETYEARMONTH = " + monthAux + " ";
	    }
	//si seleccionaron piezas en el combo box de tipo de registro de la interfaz
	  else if (registro_id.equals("piezas")){
		  //query para sacar el presupuesto comercial
		  sql = "select " +
				  "NVL (SUM(XX_INVAMOUNTORIGBUDGETED),0) Inv_Inicial_Pres," +
				  "NVL (SUM(XX_NUMINIINVEREAL),0) Inv_Inicial_Real," +
				  "NVL (SUM(XX_QUANTBUDGETEDSHOPPING),0) Compras_Pres," +
				  "NVL (SUM(XX_NUMNACSHOPPINGPLACED+XX_PURCHQUANTIMPDPLACED+XX_PURCHQUANTPLACEDMONTHS),0) Compras_Col," +
				  "NVL (SUM(XX_NUMNACSHOPPINGPLACED),0) Compras_Col_Nac," +
				  "NVL (SUM(XX_PURCHQUANTIMPDPLACED),0) Compras_Col_Imp," +
				  "NVL (SUM(XX_PURCHQUANTPLACEDMONTHS),0) Comp_Col_Mes_Ant," +
				  "NVL (SUM(XX_ReceiptQTY),0) Rec," +
				  "NVL (SUM(XX_QUANTPURCHNAC+XX_QUANTPURCHAMOUNTSREV+XX_NUMMONTHSREDSHOP),0) Com_CHEQ_Total," +
				  "NVL (SUM(XX_QUANTPURCHNAC),0) Comp_CHEQ_Nacio," +
				  "NVL (SUM(XX_QUANTPURCHAMOUNTSREV),0) Comp_CHEQ_Imp," +
				  "NVL (SUM(XX_NUMMONTHSREDSHOP),0) Comp_CHEQ_Mes_Ant," +
				  "NVL (SUM(XX_SALESAMOUNTBUD),0) Ventas_Presupu," +
			      "NVL (SUM(XX_QUANTACTUALSALE),0) Ventas_Reales," +
				  "NVL (SUM(XX_NUMTRANSFSENT),0) Traspasos_Enviados," +
				  "NVL (SUM(XX_NUMBTRANSFREV),0) Traspasos_Recibidos," +
				  "NVL (SUM(XX_RETURNSQTY),0) Dev," +
				  "NVL (SUM(XX_PROMSALENUMBUD + XX_BUDAMOUNTFRSALE+ XX_FINALBUDAMOUNTSALE),0) Rebajas_Total_Presu," +
				  "NVL (SUM(XX_AMOUNTSALEPROMINTERESTS + XX_AMOUNTSALEFRINTERESTS +  XX_FINALSALEAMOUNTINTERESTS),0) Rebajas_Total_Real," +
				  "NVL (SUM(XX_PROMSALENUMBUD),0) Reb_Promoci_Presup," +
				  "NVL (SUM(XX_AMOUNTSALEPROMINTERESTS),0) Reb_Promoci_Real," +
				  "NVL (SUM(XX_BUDAMOUNTFRSALE),0) Reb_FR_Presu," +
				  "NVL (SUM(XX_AMOUNTSALEFRINTERESTS),0) Reb_FR_Real," +
				  "NVL (SUM(XX_FINALBUDAMOUNTSALE),0) Reb_Def_Pres," +
				  "NVL (SUM(XX_FINALSALEAMOUNTINTERESTS),0) Reb_Def_Real," +
				  "NVL (SUM(XX_FINALINVAMOUNTBUD),0) Inventa_Final_Presu," +
				  "NVL (SUM(XX_NUMREALFINALINV),0) Inventa_Final_Real," +
				  "NVL (SUM(XX_NUMPROJDFINALINV),0) Inventa_Final_Proy," +
				  "NVL (SUM(XX_QUANTITYPURCHLIMIT),0) Limite_de_Compras " +
				  "from xx_vmr_prld01 B where B.XX_BUDGETYEARMONTH = " + monthAux;
	  }
	//si seleccionaron rotacion en el combo box de tipo de registro de la interfaz
	  else{
		  //query para sacar la rotacion
		  sql = "WITH TABLA as (SELECT * FROM XX_VMR_PRLD01 A WHERE  A.XX_BUDGETYEARMONTH=" + pastYearMonth + ")" +
				  "select " +

				  "ROUND(CASE WHEN sum(NVL(past.XX_FINALINVAMOUNTBUD2,0) + NVL(p.XX_FINALINVAMOUNTBUD2,0)) = 0 THEN 0 " +
				  "ELSE (sum(NVL(p.XX_SALESAMOUNTBUD2,0)) * 12) / (sum(NVL(past.XX_FINALINVAMOUNTBUD2,0) + NVL(p.XX_FINALINVAMOUNTBUD2,0))/2) END,2) ROTACION_PRES, " +

				  "ROUND(CASE WHEN sum(NVL(past.XX_INVAMOUNTFINALREAL,0) + NVL(p.XX_INVAMOUNTFINALREAL,0)) = 0 THEN 0 " +
				  "ELSE (sum(NVL(p.XX_AmountActualSale,0)) * 12) / (sum(NVL(past.XX_INVAMOUNTFINALREAL,0) + NVL(p.XX_INVAMOUNTFINALREAL,0))/2) END,2) ROTACION_REAL, " +
				  
				  "NVL(ROUND(CASE WHEN SUM(p.XX_SALESAMOUNTBUD2) <> 0 THEN SUM(p.XX_INVEFECBUDGETEDAMOUNT)/SUM(p.XX_SALESAMOUNTBUD2) ELSE 0 END, 2), 0) Cober_Pres, " +
				  "NVL(ROUND(CASE WHEN SUM(p.XX_AmountActualSale) <> 0 THEN SUM(p.XX_AMOUNTINIINVEREAL)/SUM(p.XX_AmountActualSale) ELSE 0 END, 2), 0) Cober_Real " +
				  
				  "FROM XX_VMR_PRLD01 p, TABLA past " +
				  "WHERE p.XX_BUDGETYEARMONTH = "+ monthAux + " " + 
				  "AND p.M_WareHouse_ID = past.M_WareHouse_ID and p.XX_VMR_Category_ID = past.XX_VMR_Category_ID " +
				  "AND p.XX_VMR_Department_ID = past.XX_VMR_Department_ID AND p.XX_VMR_LINE_ID = past.XX_VMR_LINE_ID " +
				  "AND p.XX_VMR_Section_ID = past.XX_VMR_Section_ID";
	  }
		
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

				
	 //realizar la consulta con la base de datos
		Map[] result = MediadorBD.realizarConsulta(sql,empresa);		
			
		//si la consulta tiene valores
			 if (result != null){	    	
		    	//obtener todos los datos de la consulta y guardarlos dentro del objeto PresupuestoComercial
			   for (int i=0;i<result.length;i++){
		    	  //si no seleccionaron la opcion de rotacion en el combo box de tipo registro de la interfaz
		    	  if (!registro_id.equals("rotacion")){
		    		  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
					 presupuestoComercial.setinv_inicial_presupuestado(new BigDecimal (result[i].get("INV_INICIAL_PRES").toString()));
					 presupuestoComercial.setinv_inicial_real(new BigDecimal (result[i].get("INV_INICIAL_REAL").toString()));
					 presupuestoComercial.setcompras_presupuestadas(new BigDecimal (result[i].get("COMPRAS_PRES").toString()));
					 presupuestoComercial.setcompras_colocadas_total(new BigDecimal (result[i].get("COMPRAS_COL").toString()));
					 presupuestoComercial.setcompras_colocadas_nacionales(new BigDecimal (result[i].get("COMPRAS_COL_NAC").toString()));
					 presupuestoComercial.setcompras_colocadas_importadas(new BigDecimal (result[i].get("COMPRAS_COL_IMP").toString()));
					 presupuestoComercial.setcompras_colocadas_mes_anteriores(new BigDecimal (result[i].get("COMP_COL_MES_ANT").toString()));
					 presupuestoComercial.setcompras_recibidas_total(new BigDecimal (result[i].get("REC").toString()));
					 presupuestoComercial.setcompras_chequeadas_total(new BigDecimal (result[i].get("COM_CHEQ_TOTAL").toString()));
					 presupuestoComercial.setcompras_chequeadas_nacional(new BigDecimal (result[i].get("COMP_CHEQ_NACIO").toString()));
					 presupuestoComercial.setcompras_chequeadas_importadas(new BigDecimal (result[i].get("COMPRAS_COL_IMP").toString()));
					 presupuestoComercial.setcompras_chequedas_mes_anterior(new BigDecimal (result[i].get("COMP_CHEQ_MES_ANT").toString()));	 
					 presupuestoComercial.setventas_presupuestadas(new BigDecimal (result[i].get("VENTAS_PRESUPU").toString()));
					 presupuestoComercial.setventas_reales(new BigDecimal (result[i].get("VENTAS_REALES").toString()));
					 presupuestoComercial.settraspasos_enviados(new BigDecimal (result[i].get("TRASPASOS_ENVIADOS").toString()));
					 presupuestoComercial.settraspasos_recibidos(new BigDecimal (result[i].get("TRASPASOS_RECIBIDOS").toString()));
					 presupuestoComercial.setdevoluciones(new BigDecimal (result[i].get("DEV").toString()));
					 presupuestoComercial.setrebajas_total_presupuestas(new BigDecimal (result[i].get("REBAJAS_TOTAL_PRESU").toString()));
					 presupuestoComercial.setrebajas_total_real(new BigDecimal (result[i].get("REBAJAS_TOTAL_REAL").toString()));
					 presupuestoComercial.setrebajas_promocionales_presupuestadas(new BigDecimal (result[i].get("REB_PROMOCI_PRESUP").toString()));
					 presupuestoComercial.setrebajas_promocionales_real(new BigDecimal (result[i].get("REB_PROMOCI_REAL").toString()));
					 presupuestoComercial.setrebajas_fr_presupuestadas(new BigDecimal (result[i].get("REB_FR_PRESU").toString()));
					 presupuestoComercial.setrebajas_fr_real(new BigDecimal (result[i].get("REB_FR_REAL").toString()));
					 presupuestoComercial.setrebajas_definitivas_presupuestadas(new BigDecimal (result[i].get("REB_DEF_PRES").toString()));
					 presupuestoComercial.setrebajas_definitivas_real(new BigDecimal (result[i].get("REB_DEF_REAL").toString()));
					 presupuestoComercial.setinv_final_presupuestado(new BigDecimal (result[i].get("INVENTA_FINAL_PRESU").toString()));
					 presupuestoComercial.setinv_final_real(new BigDecimal (result[i].get("INVENTA_FINAL_REAL").toString()));
					 presupuestoComercial.setinv_final_proyectado(new BigDecimal (result[i].get("INVENTA_FINAL_PROY").toString()));
					 presupuestoComercial.setlimite_de_compras(new BigDecimal (result[i].get("LIMITE_DE_COMPRAS").toString()));	 
		    	  }
		    	//si seleccionaron la opcion de rotacion en el combo box de tipo registro de la interfaz
		    	else{
		    		  //utilizar SIEMPRE en el .get el parametro en MAYUSCULA
		    		presupuestoComercial.setrotacion_presupuestada(new BigDecimal (result[i].get("ROTACION_PRES").toString()));	
		    		presupuestoComercial.setrotacion_real(new BigDecimal (result[i].get("ROTACION_REAL").toString()));	
		    		presupuestoComercial.setcobertura_presupuestada(new BigDecimal (result[i].get("COBER_PRES").toString()));	
		    		presupuestoComercial.setcobertura_real(new BigDecimal (result[i].get("COBER_REAL").toString()));	
		    	}
		    	 //guardo el objeto que contiene la consulta en la lita del presupuesto comercial
		    	listaPresupuestoComercial.add(presupuestoComercial);
			  }
	      }   
		}  		
	public List<PresupuestoComercial> getListaPresupuestoComercial (){
		return listaPresupuestoComercial;
	}
}
