package com.abc.otb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abc.otb.dao.impl.CategoryDAOImpl;
import com.abc.otb.dao.impl.DepartmentDAOImpl;
import com.abc.otb.dao.impl.LineDAOImpl;
import com.abc.otb.dao.impl.PresupuestoComercialDAOImpl;
import com.abc.otb.dao.impl.Prld01DAOImpl;
import com.abc.otb.dao.impl.SectionDAOImpl;
import com.abc.otb.dao.impl.WarehouseDAOImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PresupuestoComercialController {
	
	private String rutaLinux = "/";
	private String rutaWindows = "C:\\Users\\ccastillo\\Desktop\\";
	
	@RequestMapping(value = "/verificador.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView home(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView ("home");  
        
        modelAndView.addObject("mail",request.getParameter("texto"));
        
        return modelAndView;
	}
	
	/**
	 * metodo para hacer la consulta del presupuesto comercial
	 * @param request parametros que recibe de la pantalla "presupuestocomercial"
	 * @param response
	 * @return retorna los valores de la consulta y se los envia a la pantalla "presupuestocomercial"
	 */
	@RequestMapping(value = "/presupuestocomercial.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView consultaPresupuesto(HttpServletRequest request,HttpServletResponse response, boolean modificar) {
		
		//instancias
		PresupuestoComercialDAOImpl presupuestoComercial = new PresupuestoComercialDAOImpl();
		WarehouseDAOImpl almacen = new WarehouseDAOImpl();
		CategoryDAOImpl categoria = new CategoryDAOImpl();
		DepartmentDAOImpl departamento = new DepartmentDAOImpl();
		LineDAOImpl linea = new LineDAOImpl();
		SectionDAOImpl seccion = new SectionDAOImpl();
		
		//request que recibe del POST
		String mes,anio,warehouse,category,department,line,section,consultar,proximo,anterior,registro,empresa,usuario;
		
		mes = anio = warehouse = category = department = line = section = consultar = proximo = anterior = registro = empresa = usuario = null;
		
		//se chequea primero si se le asignaron parametros nuevos al request (con el getAttribute) si tiene, se trabajan con estos valores, sino con los que obtuvo de la ventana
		mes = (String) (request.getAttribute("mes") == null ? request.getParameter("mes") : request.getAttribute("mes")); //valor que tiene el combo box mes
		anio = (String) (request.getAttribute("anio") == null ? request.getParameter("anio") : request.getAttribute("anio")); //valor que tiene el combo box año
		warehouse = (String) (request.getAttribute("almacen") == null ? request.getParameter("almacen") : request.getAttribute("almacen")); //ID del almacen que hayan seleccionado en el combo box
		category = (String) (request.getAttribute("categoria") == null ? request.getParameter("categoria") : request.getAttribute("categoria")); //ID de la categoria que hayan seleccionado en el combo box
		department = (String) (request.getAttribute("departamento") == null ? request.getParameter("departamento") : request.getAttribute("departamento")); //ID del departamento que hayan seleccionado en el combo box
		line = (String) (request.getAttribute("linea") == null ? request.getParameter("linea") : request.getAttribute("linea")); //ID de la linea que hayan seleccionado en el combo box
		section = (String) (request.getAttribute("seccion") == null ? request.getParameter("seccion") : request.getAttribute("seccion")); //ID de la seccion que hayan seleccionado en el combo box
		consultar = (String) (request.getAttribute("consulta") == null ? request.getParameter("consulta") : request.getAttribute("consulta")); //boton que permite la consulta (Boton Buscar)
		proximo = (String) (request.getAttribute("proximo") == null ? request.getParameter("proximo") : request.getAttribute("proximo")); //boton que permite sacar el presupuesto comercial del mes proximo
		anterior = (String) (request.getAttribute("anterior") == null ? request.getParameter("anterior") : request.getAttribute("anterior")); //boton que permite sacar el presupuesto comercial del mes anterior
		registro = (String) (request.getAttribute("registro") == null ? request.getParameter("registro") : request.getAttribute("registro"));  //combo box para saber el tipo de regu
		empresa = (String) (request.getAttribute("empresa") == null ? request.getParameter("empresa") : request.getAttribute("empresa"));  //parametro que se pasa oculto, indica la empresa sobre la cual voy a trabajar
		usuario = (String) (request.getAttribute("usuario") == null ? request.getParameter("usuario") : request.getAttribute("usuario"));  //parametro que se pasa oculto, indica el nombre del usuario sobre la cual voy a trabajar
		
		//Variables
		ModelAndView modelAndView = null;
		int meses = 0, isCapuy = 0;
		
	  //chequear la empresa a la que pertenece el usuario
		if (empresa.equals("beco") || empresa.equals("amand")) // si es amand o beco
		  isCapuy = 0; //pongo en cero "isCapuy" permitiendo ver en la tabla de reportes campos extras
		else //si es capuy, abstracta o bcmuebles
		  isCapuy = 1; //pongo en uno "isCapuy" para quitar campos de la tabla que sale de la consulta del presupuesto
			
	//si no se quiere modificar el presupuesto comercial
	 if (modificar == false){
		 //se llama en el model and view la ventana de presupuesto comercial
		modelAndView = new ModelAndView ("presupuestoComercial");
		//y se saca el presupuesto para 6 meses
		meses = 6;
	   }
	 else{ // si se quiere modificar el presupuesto comercial
		//se llama en el model and view la ventana de modificar el presupuesto comercial
		modelAndView = new ModelAndView ("modificarPresupuesto");
		//y se saca el presupuesto para un solo mes (el que se quiere modificar)
		meses = 1;
	  }
		
		/*System.out.println("mes "+request.getParameter("mes")+"  año  "+request.getParameter("anio"));
		System.out.println("id almacen "+request.getParameter("almacen"));
		System.out.println("id categoria "+request.getParameter("categoria"));
		System.out.println("id departamento "+request.getParameter("departamento"));
		System.out.println("id line "+request.getParameter("linea"));
		System.out.println("id section "+request.getParameter("seccion"));
		System.out.println("value boton "+request.getParameter("consulta"));
		System.out.println("value mes anterior "+request.getParameter("anterior"));
		System.out.println("value mes proximo "+request.getParameter("proximo"));*/
	
		//setear la tabla, para que en la interfaz muestre o la tabla de rotacion o la de bolivares o piezas
		if (registro != null && registro.equals("rotacion"))
		  modelAndView.addObject("tablas","rotacion"); //mostrar en la interfaz la tabla de rotacion
		else
		  modelAndView.addObject("tablas","bspieza"); //mostrar en la interfaz la tabla de bolivares o piezas
		
		//setear el mes
		modelAndView.addObject("mes",mes);
		
		//setear el año
		 modelAndView.addObject("anio",anio);
		
		//cargar combo box con todos los almacenes
		modelAndView.addObject("almacenes",almacen.cargarAlmacenes(warehouse,empresa));
		
		//cargar combo box con las categorias
		modelAndView.addObject("categorias",categoria.cargarCategorias(category,empresa));
		
		//cargar combo box con los departamentos
		if (category != null && !category.equals("0")) //validar que hayan seleccionado alguna categoria
		  modelAndView.addObject("departamentos",departamento.cargarDepartamento(category, department,empresa));
			
		//cargar combo box con las lineas
		if (department != null && !department.equals("0")) //validar que hayan seleccionado algun departamento
		  modelAndView.addObject("lineas",linea.cargarLinea(department, line,empresa)); 
		
		//cargar combo box con las secciones
		 if (line != null && !line.equals("0") && (empresa.equals("beco") || empresa.equals("amand"))) //validar que hayan seleccionado alguna linea
		//solo cargar este combo para las empresas amand y beco
		   modelAndView.addObject("secciones",seccion.cargarSeccion(line, section,empresa)); 
		 
		 //setear el combo box de los tipo de registro
		 modelAndView.addObject("seleccionar", registro);
		
		//si escogen una nueva categoria, borrar las lineas y las secciones que aparecen en el combo box
		if (consultar==null && !departamento.buscarPosicionar()){
			modelAndView.addObject("lineas",linea.borrarListaLinea()); //borrar lineas
			modelAndView.addObject("secciones",seccion.borrarListaSeccion()); //borrar secciones
		}

		//si escogen un nuevo departamento, borrar las secciones que aparecen en el combo box
		if (consultar==null && !linea.buscarPosicionar()){
			modelAndView.addObject("secciones",seccion.borrarListaSeccion());
		}
	
		/*nombre que van a tener los texto al lado de cada combo box
		 * dependiendo de la empresa va a tener uno o otro combo
		 * para el caso de abstracta, capuy y bcmuebles el combo seccion no va a existir
		 */
	//para beco y amand
		if (empresa.equals("beco") || empresa.equals("amand")){
			modelAndView.addObject("combo1","Almacen"); //para el primer combo en la interfaz (arriba a la izquierda)
			modelAndView.addObject("combo2","Categoría"); //para el segundo combo en la interfaz (arriba a la derecha)
			modelAndView.addObject("combo3","Departamento"); //para el tercer combo en la interfaz (en el medio a la izquierda)
			modelAndView.addObject("combo4","Línea"); // para el cuarto combo en la interfaz (en el medio a la derecha)
			modelAndView.addObject("combo5","Sección"); // para el quinto combo (abajo a la izquierda)
		}
	  else{ //para abstracta, bcmuebles y capuy
		  modelAndView.addObject("combo1","Organización"); //para el primer combo en la interfaz (arriba a la izquierda)
		  modelAndView.addObject("combo2","Departamento");  //para el segundo combo en la interfaz (arriba a la derecha)
		  modelAndView.addObject("combo3","Sección"); //para el tercer combo en la interfaz (en el medio a la izquierda)
		  modelAndView.addObject("combo4","Tipo"); // para el cuarto combo en la interfaz (en el medio a la derecha)
		// para el quinto combo (abajo a la izquierda), se le pasa cero para que una validacion en la interfaz lo quite
		  modelAndView.addObject("combo5","0"); 
		}
		
		/*validar que los parametros no sean null
		 * sucede cuando la peticion (request) llegar por medio de un GET y no un POST.
		 * El GET sucede cuando colocan el URL completo de la consulta del presupuesto
		 */
		if (mes != null && anio != null){
		 /*
		  * Validar que haya introducido los valores en los campos de mes, año y hayan presionado el boton de "Buscar"
		  * si el request llega por medio de un POST y los parametros estan vacios, simboliza que no se coloco
		  * nada en los campos de mes y año
		  */
		  if (!mes.equals("") && !anio.equals("") && consultar != null){
			  
			   String mesAux = mes;
			   if (mesAux.length() == 1) // si colocan un solo digito, ejemplo: 2, 4, 7 para el mes
				   mesAux = "0"+mesAux; // se concatena el 0, para que el resultado sea 02 04 07
			   
			 //sacar el presupuesto comercial
			presupuestoComercial.presupuestoMeses(anio+mesAux,warehouse,category,department,line,section,registro,meses,empresa);	
			
			//mandar a la interfaz el presupuesto comercial
			modelAndView.addObject("consulta",presupuestoComercial.getListaPresupuestoComercial());
			
			//permitir que se muestre la tabla con los valores
			modelAndView.addObject("borde","1"); //setea los bordes de la tabla en 1 (para visualizarlos)
			modelAndView.addObject("porcentaje","100%"); //setea el ancho de la tabla en un 100%
			modelAndView.addObject("permitir","1"); //permite que se observen los campos y los resultados de la tabla
			modelAndView.addObject("isCapuy",isCapuy); //permite que se observen los campos y los resultados de la tabla (para amand y beco)
			modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
			modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
		}
		 //sacar el presupuesto del mes proximo
		else if (!mes.equals("") && !anio.equals("") && proximo != null){
			String mesAux,anioAux;
			
			//sacar el siguiente mes
			mesAux = presupuestoComercial.siguienteAnteriorMes(mes, false);
			
			//sacar el siguiente año
			anioAux = presupuestoComercial.siguienteAnteriorAnio(mes,anio, false);
			
			//sacar el presupuesto comercial a partir del mes y año calculados anteriormente
			presupuestoComercial.presupuestoMeses(anioAux+mesAux,warehouse,category,department,line,section,registro,meses,empresa);
			
			//mandar a la interfaz el presupuesto comercial
			modelAndView.addObject("consulta",presupuestoComercial.getListaPresupuestoComercial());
			
			//permitir que se muestre la tabla con los valores
			modelAndView.addObject("borde","1"); //setea los bordes de la tabla en 1 (para visualizarlos)
			modelAndView.addObject("porcentaje","100%");  //setea el ancho de la tabla en un 60%
			modelAndView.addObject("permitir","1"); //permite que se observen los campos y los resultados de la tabla
			modelAndView.addObject("isCapuy",isCapuy); //permite que se observen los campos y los resultados (para amand y beco)
			modelAndView.addObject("mes",mesAux); //setea el mes desde el cual se saco el presupuesto comercial
			modelAndView.addObject("anio",anioAux); //setea el año desde el cual se saco el presupuesto comercial
			modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
			modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
			
		}
		 //sacar el presupuesto del mes anterior
		else if (!mes.equals("") && !anio.equals("") && anterior != null){ 
			String mesAux,anioAux;
			
			//sacar el anterior mes 
			mesAux = presupuestoComercial.siguienteAnteriorMes(mes, true);
			
			//sacar el anterior año
			anioAux = presupuestoComercial.siguienteAnteriorAnio(mes,anio, true);
			
			//sacar el presupuesto comercial a partir del mes y año calculados anteriormente
			presupuestoComercial.presupuestoMeses(anioAux+mesAux,warehouse,category,department,line,section,registro,meses,empresa);
			
			//mandar a la interfaz el presupuesto comercial
			modelAndView.addObject("consulta",presupuestoComercial.getListaPresupuestoComercial());
			
			//permitir que se muestre la tabla con los valores
			modelAndView.addObject("borde","1"); //setea los bordes de la tabla en 1 (para visualizarlos)
			modelAndView.addObject("porcentaje","100%");  //setea el ancho de la tabla en un 60%
			modelAndView.addObject("permitir","1"); //permite que se observen los campos y los resultados de la tabla
			modelAndView.addObject("isCapuy",isCapuy); //permite que se observen los campos y los resultados (para amand y beco)
			modelAndView.addObject("mes",mesAux); //setea el mes desde el cual se saco el presupuesto comercial
			modelAndView.addObject("anio",anioAux); //setea el año desde el cual se saco el presupuesto comercial
			modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
			modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
			
		}
		else{ // si no ha colocado año y mes en los textfiedl, no se muestra la tabla
		 //no permitir que se muestre la tabla
		   modelAndView.addObject("borde","0"); //no permite que se vea el borde de la tabla
		   modelAndView.addObject("porcentaje","0%"); // no le setea ninguna anchura a la tabla
		   modelAndView.addObject("permitir","0"); //permite que se observen los campos y los resultados de la tabla
		   modelAndView.addObject("isCapuy",isCapuy); //permite que se observen los campos y los resultados (para amand y beco)
		   modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
		   modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
		}    
	   }
	 else{ //la primera vez, antes de que realice cualquier tipo de consulta
		 modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
		 modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
	 }
		
        return modelAndView;
	}
	
	/**
	 * metodo para manejar todo lo relacionado con la carga del presupuesto comercial
	 * @param request  parametros que recibe de la pantalla "cargarPresupuesto"
	 * @param response
	 * @return retorna los valores de la carga y se los envia a la pantalla "cargarPresupuesto"
	 */
	@RequestMapping(value = "/cargarpresupuesto.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cargarPresupuesto(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("cargarPresupuesto"); 
		Prld01DAOImpl presupuesto = new Prld01DAOImpl();

		        String ruta = "", mensaje = "", anioMes = "";
				FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        int cantidadMeses = 6;
		        boolean modificar = false;
		        
		        //parametros que recibe desde la pantalla 
		        String meses, anio,almacen,categoria,departamento,linea,seccion,registro,empresa,usuario;
		        meses = anio = almacen = categoria = departamento = linea = seccion = registro = empresa = usuario = null;
		       
		        /*
		         * Si viene una peticion por GET sin ningun archivo retorna -1 getContentLength
		         * Si viene una peticion por POST sin ningun archivo retorna 50 getContentLength
		         * Si se adjunto algun archivo el getContentLength traera un numero mayor a 50
		         */
		      if (request.getContentLength() > 50){
		        try {
		        	//obtener los parametros del request
		            List items = upload.parseRequest(request);
		            Iterator iter = items.iterator();
		
		            while (iter.hasNext()) {
		            	
		            	//agarra el parametro que se obtienen desde la interfaz
		                FileItem item = (FileItem) iter.next();
		                item.getString();
		                
		                //si el parametro que estoy comparando es el mes
		                if (item.getFieldName().equals("mes")){
		                	//guardo el mes y lo formateo para que tenga dos numeros
		                	meses = item.getString();
		                	if (meses.length() == 1)
		                	  meses = "0"+meses;
		                }
		                else if (item.getFieldName().equals("anio")) //si el parametro que estoy comparando es el año
		                	anio = item.getString(); //guardo el año        
		                else if (item.getFieldName().equals("almacen")) //si el parametro que estoy comparando es el almacen
		                	almacen = item.getString(); //guardo el almacen
		                else if (item.getFieldName().equals("categoria")) //si el parametro que estoy comparando es la categoria
		                	categoria = item.getString(); //guardo la categoria
		                else if (item.getFieldName().equals("departamento")) //si el parametro que estoy comparando es el departamento
		                	departamento = item.getString(); //guardo el departamento
		                else if (item.getFieldName().equals("linea")) //si el parametro que estoy comparando es la linea
		                	linea = item.getString(); //guardo la linea
		                else if (item.getFieldName().equals("seccion")) //si el parametro que estoy comparando es la seccion
		                	seccion = item.getString(); //guardo la seccion 
		                else if (item.getFieldName().equals("registro")) //si el parametro que estoy comparando es el tipo de registro
		                	registro = item.getString(); // guardo el tipo de registro
		                else if (item.getFieldName().equals("empresa")) //si el parametro que estoy comparando es la empresa 
		                	empresa =  item.getString(); //guardo la empresa en donde voy hacer las insersiones
		                else if (item.getFieldName().equals("usuario"))
		                	usuario = item.getString(); //guardo el usuario que esta en el sistema
		                
		                //chequeo que tipo de archivo estan enviando, si es archivo para cargar o si es archivo para modificar
		                else if (item.getFieldName().equals("fileCargar") || item.getFieldName().equals("fileModificar")){ 
				           	 if (System.getProperty("os.name").startsWith("Windows")) //veo el sistema operativo en el que estoy trabajando
				           		ruta = rutaWindows+item.getName(); //si es windows coloco la ruta de windows
				           	 else
				           		ruta = rutaLinux+item.getName(); //si es linux coloco la ruta de linux
			           	 
				           	 //creo la variable en donde voy a guardar el archivo que estan pasando del cliente al servidor
			                File disk = new File(ruta);       
			                
			                //si pasan un archivo para cargar el presupuesto
			                if (item.getFieldName().equals("fileCargar")){
			                	modificar = false; //FALSE la variable que identifica que no se va a modificar el presupuesto
			                }
			                else{ //de lo contrario coloco TRUE para saber que voy a modificar el presupuesto
			                	modificar = true;
			                	modelAndView = new ModelAndView("modificarPresupuesto"); //y cargo el model and view de modificar presupuesto
			                }
			                
			                //guardo el archivo del cliente en el servidor
			                item.write(disk);   
			                Date actualDate = new Date();
			        		System.out.println("empezo la carga a las: " + actualDate.toString());
			        		//mando a cargar o modificar el presupuesto comercial 
			                mensaje = presupuesto.cargarPresupuesto(ruta, modificar, meses, anio, almacen, categoria, departamento, linea, seccion, registro,empresa);
			                actualDate = new Date();
			        		System.out.println("termino la carga a las: " + actualDate.toString());
			        		
			        		//saco el mes y el año del presupuesto que se esta montando, 
			        		//con esto puedo realizar la consulta de lo que se cargo al sistema
			        		anioMes = presupuesto.obtenerAnioMes(ruta);
		               }
		            }      
		         
		            //chequeo si se cargo o modifico exitosamente el presupuesto comercial
		            if (mensaje.toLowerCase().equals("exito")){
		              if (modificar == false){ // si no modifique el presupuesto
		            	PresupuestoComercialDAOImpl presupuestoComercial = new PresupuestoComercialDAOImpl();
		            	
		            	//obtener la consulta de la carga del presupuesto comercial en bolivares
		            	presupuestoComercial.presupuestoMeses(anioMes,null,null,null,null,null,"bolivares",cantidadMeses,empresa);
		            	modelAndView.addObject("consultaBolivares",presupuestoComercial.getListaPresupuestoComercial());
		            	
		            	//obtener la consulta de la carga del presupuesto comercial en piezas
		            	presupuestoComercial.presupuestoMeses(anioMes,null,null,null,null,null,"piezas",cantidadMeses,empresa);
		            	modelAndView.addObject("consultaPieza",presupuestoComercial.getListaPresupuestoComercial());
		              }
		              else{ //si modifique el presupuesto
		            	  //le asigno al request los parametros que obtuve de la ventana para que el metodo de consultar presupuesto
		            	  //pueda trabajar con ellos
		            	 request.setAttribute("mes", meses); //asigno el mes
		            	 request.setAttribute("anio", anio); //asigno el año
		            	 request.setAttribute("almacen", almacen); //asigno el almacen
		            	 request.setAttribute("categoria", categoria); //asigno la categoria
		            	 request.setAttribute("departamento", departamento); //asigno el departamento
		            	 request.setAttribute("linea", linea); //asigno la linea
		            	 request.setAttribute("seccion", seccion); //asigno la seccion
		            	 request.setAttribute("consulta", "consultar"); //seteo un valor a la consulta (cualquiera mientas sea distinto de null)
		            	 request.setAttribute("registro", registro); // asigno el tipo de registro 
		            	 request.setAttribute("empresa", empresa); //indica la empresa en donde voy a modificar el presupuesto comercial
		            	 request.setAttribute("usuario",usuario); //indica el usuario que esta en el sistema
		            	 return consultaPresupuesto (request,response,true); // y hago la consulta del presupuesto con estos parametros
		              }
		            	
		            	modelAndView.addObject("borde","1"); //no permite que se vea el borde de la tabla
						modelAndView.addObject("porcentaje","90%"); // no le setea ninguna anchura a la tabla
						modelAndView.addObject("permitir","1"); //permite que se observen los campos y los resultados de la tabla
						modelAndView.addObject("permitirCapuy","1"); //permite que se observen los campos y los resultados de la tabla para capuy
						modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
						modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
		            	
		            }
		            else //si ocurrio un error durante la carga o la modificacion
		             modelAndView.addObject("mensaje",mensaje); //se manda el error a la pantalla
		         
		        }catch (FileUploadException fue) {
		        	modelAndView.addObject("mensaje","Error no se ha seleccionado ningun archivo");
		        	System.out.println("error por "+fue);
		        } catch (Exception e) {
		        	modelAndView.addObject("mensaje","Error por "+e);
		        	System.out.println("error excepcion "+e);
		        }
		      }
		      else{
		    	  //no permitir que se muestre la tabla
				   modelAndView.addObject("borde","0"); //no permite que se vea el borde de la tabla
				   modelAndView.addObject("porcentaje","0%"); // no le setea ninguna anchura a la tabla
				   modelAndView.addObject("permitir","0"); //permite que se observen los campos y los resultados de la tabla
				   modelAndView.addObject("permitirCapuy","0"); //permite que se observen los campos y los resultados de la tabla para capuy
				   modelAndView.addObject("empresa",request.getParameter("empresa")); //indica la empresa en donde voy a cargar el presupuesto comercial
				   modelAndView.addObject("usuario",request.getParameter("usuario")); //indica el usuario que esta en el sistema
		      }
        return modelAndView;
	}
	
	/**
	 * metodo que maneja lo relacionado con la parte de la modificacion del presupuesto comercial
	 * @param request parametros que recibe de la pantalla "modificarPresupuesto"
	 * @param response
	 * @return retorna los valores de la modificacion y se los envia a la pantalla "modificarPresupuesto"
	 */
	@RequestMapping(value = "/modificarpresupuesto.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modificarPresupuesto (HttpServletRequest request,HttpServletResponse response) {

		//request que recibe del POST
		String mes = request.getParameter("mes"); //valor que tiene el combo box mes
		String anio = request.getParameter("anio"); //valor que tiene el combo box año
		String warehouse = request.getParameter("almacen"); //ID del almacen que hayan seleccionado en el combo box
		String category = request.getParameter("categoria"); //ID de la categoria que hayan seleccionado en el combo box
		String department = request.getParameter("departamento"); //ID del departamento que hayan seleccionado en el combo box
		String line = request.getParameter("linea"); //ID de la linea que hayan seleccionado en el combo box
		String section = request.getParameter("seccion"); //ID de la seccion que hayan seleccionado en el combo box
		String registro = request.getParameter("registro"); //el combo box del tipo de registro
		String inventarioInicial = request.getParameter("inventarioInicialPresupuestada"); //numero del campo inventario inicial presupuestado
		String compras = request.getParameter("comprasPresupuestada"); // numero del campo compras presupuestadas
		String ventas = request.getParameter("ventasPresupuestadas"); //numero del campo ventas presupuestadas
		String rebajasTotal = request.getParameter("rebajasTotalPresupuestada"); //numero del campo rebajas total presupuestadas
		String rebajasDef = request.getParameter("rebajasDefPresupuestada"); // numero del campo rebajas definiticas presupuestadas
		String inventarioFinal = request.getParameter("inventarioFinalPresupuestado"); //numero del campo inventario final presupuestado
		String guardar = request.getParameter("guardar"); // si presiona el boton guardar cambios
		String descartar = request.getParameter("descartar"); // si presiona el boton guardar cambios
		String empresa = request.getParameter("empresa"); //la empresa en la que estoy haciendo los cambios, parametro que se pasa oculto
		String exportar = request.getParameter("exportar");
	
		Prld01DAOImpl presupuesto = new Prld01DAOImpl();
		
		if (mes != null && mes.length() == 1)// si colocan un solo digito, ejemplo: 2, 4, 7 para el mes
			   mes = "0"+mes; // se concatena el 0, para que el resultado sea 02 04 07
		
	 //si presiono el boton de guardar
		if (guardar != null){
			request.setAttribute("consulta", "consultar"); /*permite que la pagina recarge automaticamente la modificacion sin necesidad 
															 de presionar el boton de "Buscar"*/
		 //se guardan los cambios de la tabla
		  presupuesto.modificarPresupuesto(anio+mes,warehouse,category,department,line,section,registro,
				  inventarioInicial, compras, ventas, rebajasTotal, rebajasDef, inventarioFinal,empresa);
		}
		else if (descartar != null){
		 request.setAttribute("consulta", "consultar"); /*permite que la pagina recarge automaticamente la modificacion sin necesidad de presionar el boton de "Buscar"*/
		}
		else if (exportar != null){
		   presupuesto.exportarPresupuestoExcel(anio+mes,warehouse,category,department,line,section, empresa);
		   
		   response.setContentType("application/vnd.ms-excel");
           response.setHeader("Content-Disposition", "attachment; filename=presupuestoComercial.xls");
           try {
			InputStream is = new FileInputStream("C:\\Users\\ccastillo\\Desktop\\presupuestoComercial.xls");
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
			
			return null;
		} catch (FileNotFoundException e) {
			System.out.println("Error no se encontro el archivo");
		} catch (IOException e) {
			System.out.println("Error IO");
		}
	 }
		
		//retorna la consulta del presupuesto comercial incluyendo los cambios realizados sobre el
        return consultaPresupuesto (request,response,true);
	}

	
	/**
	 * metodo para manejar las excepciones que puedan ocurrir en este controlador
	 * @param e
	 * @param request
	 * @return retorna una vista sobre la pantalla de errores
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView Exception(Exception e , HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView ("home");
		modelAndView.addObject("error","Ups! Ha ocurrido un error. "+e);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/crearpresupuesto.*", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView crearPresupuesto(HttpServletRequest request,HttpServletResponse response) {
		
		//instancias
//		PresupuestoComercialDAOImpl presupuestoComercial = new PresupuestoComercialDAOImpl();
		WarehouseDAOImpl almacen = new WarehouseDAOImpl();
		CategoryDAOImpl categoria = new CategoryDAOImpl();
		DepartmentDAOImpl departamento = new DepartmentDAOImpl();
		LineDAOImpl linea = new LineDAOImpl();
		SectionDAOImpl seccion = new SectionDAOImpl();
		
		//request que recibe del POST
		String mes,anio,warehouse,category,department,line,section,consultar,registro,empresa,usuario,exportar,guardar;
		
		mes = anio = warehouse = category = department = line = section = consultar = registro = empresa = usuario = null;
		
		//se chequea primero si se le asignaron parametros nuevos al request (con el getAttribute) si tiene, se trabajan con estos valores, sino con los que obtuvo de la ventana
		mes = (String) (request.getAttribute("mes") == null ? request.getParameter("mes") : request.getAttribute("mes")); //valor que tiene el combo box mes
		anio = (String) (request.getAttribute("anio") == null ? request.getParameter("anio") : request.getAttribute("anio")); //valor que tiene el combo box año
		warehouse = (String) (request.getAttribute("almacen") == null ? request.getParameter("almacen") : request.getAttribute("almacen")); //ID del almacen que hayan seleccionado en el combo box
		category = (String) (request.getAttribute("categoria") == null ? request.getParameter("categoria") : request.getAttribute("categoria")); //ID de la categoria que hayan seleccionado en el combo box
		department = (String) (request.getAttribute("departamento") == null ? request.getParameter("departamento") : request.getAttribute("departamento")); //ID del departamento que hayan seleccionado en el combo box
		line = (String) (request.getAttribute("linea") == null ? request.getParameter("linea") : request.getAttribute("linea")); //ID de la linea que hayan seleccionado en el combo box
		section = (String) (request.getAttribute("seccion") == null ? request.getParameter("seccion") : request.getAttribute("seccion")); //ID de la seccion que hayan seleccionado en el combo box
		consultar = (String) (request.getAttribute("consulta") == null ? request.getParameter("consulta") : request.getAttribute("consulta")); //boton que permite la consulta (Boton Buscar)	
		registro = (String) (request.getAttribute("registro") == null ? request.getParameter("registro") : request.getAttribute("registro"));  //combo box para saber el tipo de regu
		empresa = (String) (request.getAttribute("empresa") == null ? request.getParameter("empresa") : request.getAttribute("empresa"));  //parametro que se pasa oculto, indica la empresa sobre la cual voy a trabajar
		usuario = (String) (request.getAttribute("usuario") == null ? request.getParameter("usuario") : request.getAttribute("usuario"));  //parametro que se pasa oculto, indica el nombre del usuario sobre la cual voy a trabajar
		exportar = (String) (request.getAttribute("exportar") == null ? request.getParameter("exportar") : request.getAttribute("exportar"));  //parametro que se pasa oculto, indica el nombre del usuario sobre la cual voy a trabajar
		guardar = (String) (request.getAttribute("guardar") == null ? request.getParameter("guardar") : request.getAttribute("guardar"));  //parametro que se pasa oculto, indica el nombre del usuario sobre la cual voy a trabajar
		
		
		//Variables
		ModelAndView modelAndView = null;
		int isCapuy = 0;
		
	  //chequear la empresa a la que pertenece el usuario
		if (empresa.equals("beco") || empresa.equals("amand")) // si es amand o beco
		  isCapuy = 0; //pongo en cero "isCapuy" permitiendo ver en la tabla de reportes campos extras
		else //si es capuy, abstracta o bcmuebles
		  isCapuy = 1; //pongo en uno "isCapuy" para quitar campos de la tabla que sale de la consulta del presupuesto
			
		 //se llama en el model and view la ventana de presupuesto comercial
		modelAndView = new ModelAndView ("crearPresupuesto");
	
		/*System.out.println("mes "+request.getParameter("mes")+"  año  "+request.getParameter("anio"));
		System.out.println("id almacen "+request.getParameter("almacen"));
		System.out.println("id categoria "+request.getParameter("categoria"));
		System.out.println("id departamento "+request.getParameter("departamento"));
		System.out.println("id line "+request.getParameter("linea"));
		System.out.println("id section "+request.getParameter("seccion"));
		System.out.println("value boton "+request.getParameter("consulta"));
		System.out.println("value mes anterior "+request.getParameter("anterior"));
		System.out.println("value mes proximo "+request.getParameter("proximo"));*/
	
		//setear la tabla, para que en la interfaz muestre o la tabla de rotacion o la de bolivares o piezas
		if (registro != null && registro.equals("rotacion"))
		  modelAndView.addObject("tablas","rotacion"); //mostrar en la interfaz la tabla de rotacion
		else
		  modelAndView.addObject("tablas","bspieza"); //mostrar en la interfaz la tabla de bolivares o piezas
		
		//setear el mes
		modelAndView.addObject("mes",mes);
		
		//setear el año
		 modelAndView.addObject("anio",anio);
		
		//cargar combo box con todos los almacenes
		modelAndView.addObject("almacenes",almacen.cargarAlmacenes(warehouse,empresa));
		
		//cargar combo box con las categorias
		modelAndView.addObject("categorias",categoria.cargarCategorias(category,empresa));
		
		//cargar combo box con los departamentos
		if (category != null && !category.equals("0")) //validar que hayan seleccionado alguna categoria
		  modelAndView.addObject("departamentos",departamento.cargarDepartamento(category, department,empresa));
			
		//cargar combo box con las lineas
		if (department != null && !department.equals("0")) //validar que hayan seleccionado algun departamento
		  modelAndView.addObject("lineas",linea.cargarLinea(department, line,empresa)); 
		
		//cargar combo box con las secciones
		 if (line != null && !line.equals("0") && (empresa.equals("beco") || empresa.equals("amand"))) //validar que hayan seleccionado alguna linea
		//solo cargar este combo para las empresas amand y beco
		   modelAndView.addObject("secciones",seccion.cargarSeccion(line, section,empresa)); 
		 
		 //setear el combo box de los tipo de registro
		 modelAndView.addObject("seleccionar", registro);
		
		//si escogen una nueva categoria, borrar las lineas y las secciones que aparecen en el combo box
		if (consultar==null && !departamento.buscarPosicionar()){
			modelAndView.addObject("lineas",linea.borrarListaLinea()); //borrar lineas
			modelAndView.addObject("secciones",seccion.borrarListaSeccion()); //borrar secciones
		}

		//si escogen un nuevo departamento, borrar las secciones que aparecen en el combo box
		if (consultar==null && !linea.buscarPosicionar()){
			modelAndView.addObject("secciones",seccion.borrarListaSeccion());
		}
	
		/*nombre que van a tener los texto al lado de cada combo box
		 * dependiendo de la empresa va a tener uno o otro combo
		 * para el caso de abstracta, capuy y bcmuebles el combo seccion no va a existir
		 */
	//para beco y amand
		if (empresa.equals("beco") || empresa.equals("amand")){
			modelAndView.addObject("combo1","Almacen"); //para el primer combo en la interfaz (arriba a la izquierda)
			modelAndView.addObject("combo2","Categoría"); //para el segundo combo en la interfaz (arriba a la derecha)
			modelAndView.addObject("combo3","Departamento"); //para el tercer combo en la interfaz (en el medio a la izquierda)
			modelAndView.addObject("combo4","Línea"); // para el cuarto combo en la interfaz (en el medio a la derecha)
			modelAndView.addObject("combo5","Sección"); // para el quinto combo (abajo a la izquierda)
		}
	  else{ //para abstracta, bcmuebles y capuy
		  modelAndView.addObject("combo1","Organización"); //para el primer combo en la interfaz (arriba a la izquierda)
		  modelAndView.addObject("combo2","Departamento");  //para el segundo combo en la interfaz (arriba a la derecha)
		  modelAndView.addObject("combo3","Sección"); //para el tercer combo en la interfaz (en el medio a la izquierda)
		  modelAndView.addObject("combo4","Tipo"); // para el cuarto combo en la interfaz (en el medio a la derecha)
		// para el quinto combo (abajo a la izquierda), se le pasa cero para que una validacion en la interfaz lo quite
		  modelAndView.addObject("combo5","0"); 
		}
		
		   if (mes != null && mes.length() == 1) // si colocan un solo digito, ejemplo: 2, 4, 7 para el mes
			   mes = "0"+mes; // se concatena el 0, para que el resultado sea 02 04 07
		
		Prld01DAOImpl prld01 = new Prld01DAOImpl();
		
		if (consultar != null && anio != null && mes != null){
		  modelAndView.addObject("consulta",prld01.sacarPresupuestoHistorico(anio+mes, empresa, warehouse, category, department, line, section, registro));
		
		  //permitir que se muestre la tabla con los valores
			modelAndView.addObject("borde","1"); //setea los bordes de la tabla en 1 (para visualizarlos)
			modelAndView.addObject("porcentaje","100%");  //setea el ancho de la tabla en un 60%
			modelAndView.addObject("permitir","1"); //permite que se observen los campos y los resultados de la tabla
			modelAndView.addObject("isCapuy",isCapuy); //permite que se observen los campos y los resultados (para amand y beco)
			modelAndView.addObject("mes",mes); //setea el mes desde el cual se saco el presupuesto comercial
			modelAndView.addObject("anio",anio); //setea el año desde el cual se saco el presupuesto comercial
			modelAndView.addObject("empresa",empresa); //indica la empresa en donde voy a cargar el presupuesto comercial
			modelAndView.addObject("usuario",usuario); //indica el usuario que esta en el sistema
		}
		else if (exportar != null){
			prld01.exportarExcelPresupuestoCreado(anio+mes, warehouse, category, department, line, section, empresa);
			
			   response.setContentType("application/vnd.ms-excel");
	           response.setHeader("Content-Disposition", "attachment; filename=presupuestoCreado.xls");
	           try {
				InputStream is = new FileInputStream("C:\\Users\\ccastillo\\Desktop\\presupuestoCreado.xls");
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
				
				return null;
			} catch (FileNotFoundException e) {
				System.out.println("Error no se encontro el archivo");
			} catch (IOException e) {
				System.out.println("Error IO");
			}
		}
		else if (guardar != null){
			
		}
		
		modelAndView.addObject("empresa",empresa);
		modelAndView.addObject("usuario",usuario);
		return modelAndView;
	}
	
}
