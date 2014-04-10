//enviar por POST el formulario
 function redireccionar(formulario){
   f=document.getElementById(formulario);
	 if(f){
	   f.submit();
	 }
  }
  
  //cambiar el texto del boton de buscar, para que aparezca Buscando cuando este haciendo la consulta
 function cambiar_texto(id,mensaje){
	   document.getElementById(id).value = mensaje;
	   
	  }
 
  //funcion para validor que solo coloquen numeros en los campos numericos (mes, año)
 function acceptNum(evt){	
		var nav4 = window.Event ? true : false;
		// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57	
		var key = nav4 ? evt.which : evt.keyCode;	
		return ((key <= 13) || (key >= 48 && key <= 57));
	}
 
//funcion para que exporta la tabla HTML a excel cuando presionen el ENTER
function importarExcel(evt,table, name){
	var nav4 = window.Event ? true : false;
	
	var key = nav4 ? evt.which : evt.keyCode;
	
	//Enter = 13
	
	//si presiona el enter llamar al metodo que se encarga exportar la tabla
	 if (key == 13){
		 tableToExcel (table, name);
		 return false;
	 }
  return true;
}
 
  //funcion para validar el formulario
 function validateSolicitude(form){
	 msg = "";
	 
	 //si no introducen un mes
	 if(document.getElementById('text-mes').value == ''){
		 msg += "Introducir el mes.\n";
	 }
	 //o el mes es menor a 1 y mayor a 12, manda un mensaje de error
	 else if (document.getElementById('text-mes').value < 1
			 || document.getElementById('text-mes').value > 12){
		 msg += "Introducir un mes valido.\n";
 	}
	 
	 //si no se introduce ningun año
	 if(document.getElementById('text-anio').value == '')
		 msg += "Introducir el año.\n";
	 //o la longitud del año es distinta de cuatro, manda un mensaje de error
	 else if (document.getElementById('text-anio').value.length != 4)
		 msg += "Introducir un año valido.\n";
	 
	 //si ocurrio algun error
	   if(msg != "") {
		   //envia un mensaje de error, con los errores ocurridos
			alert("Se han producido los siguientes errores:\n\n" + msg);
			return false;
		}
 }
 
 //transformar una tabla de html a un excel
 var tableToExcel = (function () {
	    var uri = 'data:application/vnd.ms-excel;base64,',
	        template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	        base64 = function (s) {
	            return window.btoa(unescape(encodeURIComponent(s)));
	        }, format = function (s, c) {
	            return s.replace(/{(\w+)}/g, function (m, p) {
	                return c[p];
	            });
	        };
	    
	      //funcion para poder obtener la tabla de la interfaz e importarlo a un excel
	      return function (table, name) {
	    	 var msg = "";
	    	 //si no coloco el nombre del archivo cuando quizo importar la tabla
	    	 if (document.getElementById('text-export').value == '')
	    	   msg = "Introduzca el nombre del archivo."; //manda un mensaje de error
	    	 else {
	    		 if (!table.nodeType) 
	            	table = document.getElementById(table);
	    		
	    		//obtener la tabla de la interfaz
	    		 var html = table.outerHTML;
	            
	    		 //cambiar los caracteres especiales por su forma en el formato UTF-8
	            while (html.indexOf('á') != -1) html = html.replace('á', '&aacute;');
	            while (html.indexOf('é') != -1) html = html.replace('é', '&eacute;');
	            while (html.indexOf('í') != -1) html = html.replace('í', '&iacute;');
	            while (html.indexOf('ó') != -1) html = html.replace('ó', '&oacute;');
	            while (html.indexOf('ú') != -1) html = html.replace('ú', '&uacute;');
	            while (html.indexOf('º') != -1) html = html.replace('º', '&ordm;');
	            
	            var ctx = { worksheet: name || 'Worksheet', table : html};
	            
	            document.getElementById("dlink").href = uri + base64(format(template, ctx));
	            //agarrar el nombre del text field de la interfaz y asignarlo como nombre
	            document.getElementById("dlink").download = document.getElementById('text-export').value+".xls";
	            document.getElementById("dlink").click();
	      }
	      
	    //si hubo un error, manda un mensaje de advertencia
	      if (msg != "")
	    	alert("Se han producido los siguientes errores:\n\n" + msg);
			return false;
	     };

	})(); 
 
/*funcion para repintar el boton de guardado en la pantalla de configuracion
 * le setea las clases "btn" y "btn-danger" para poder pintar el boton
 * de color rojo cuando se presione una tecla
 */
function repintar(){
    document.getElementById('btn-guardar').className = "btn btn-danger";
}


 // metodo para aumentar el porcentaje que lleva la barra de cargando
function aumentar(cantidad,aumento){	
	//sacar lo que lleva ya cargado la barra y se le suma el progreso (aumento)
	aumento = parseFloat(aumento) + parseFloat(cantidad);

	//se le coloca a la barra este nuevo aumento de progreso
	document.getElementById('barra').style.width = aumento+"%";

	//si todavia la barra no llega al 99%
	 if (aumento <= 99){
		 //se pinta el progreso que ya lleva
		 document.getElementById("text-barra").innerHTML = aumento.toFixed()+"%";
		 //y se vuelve a llamar al metodo recursivamente cada segundo
       return setTimeout("aumentar("+cantidad+","+aumento+")",1000);
	 }
	 //cuando ya supero el 99%
	 else{
		 //se deja a la barra en el 99% hasta que el proceso que este haciendo termine
		 document.getElementById("text-barra").innerHTML = "99%";
	   return 0;
	 }
}

//metodo para sacar el tiempo que va a tardar la barra en recorrer el 100%
function processData(csv) {
	//se sacan todas las filas (registros) que estan en el csv
    var allTextLines = csv.split(/\r\n|\n/);
    /*se saca el aumento que va a tener la barra cada segundo
     * 
     * La formula sale en base a un aproximacion. Aproximadamente el programa tarda
     * 150 segundos entre borrar los registro viejos, insertar registros y realizar la consulta 
     * del nuevo presupuesto comercial (esto fue probado con un archivo que contenia 49000 registros
     * dentro del archivo csv)
     * 
     * El numero de la formula indica el porcentaje que va a aumentar la barra cada segundo
     */
    var cantidad = (100/((150*allTextLines.length)/49000));
    //se castea a solo 2 decimales
    cantidad = cantidad.toFixed(2);
    //y se llama al metodo para aumentar la barra de cargando
    aumentar (cantidad,0);
}

//metodo para obtener el archivo csv que selecciono el usuario
function loadHandler(event) {
	//obtener el archivo .csv
    var csv = event.target.result;
    //llamar al metodo para sacar el tiempo estimado de carga del presupuesto
    processData(csv);
  }


function getAsText(fileToRead) {
    var reader = new FileReader();
    // Read file into memory as UTF-8      
    reader.readAsText(fileToRead);
    // Handle errors load
    reader.onload = loadHandler;
  }
  
//metodo para validar si el archivo que me estan pasando es un .csv
function handleFiles() {
	//borrar el mensaje de error anterior (si existiera)
	 document.getElementById("mensaje").innerHTML = " ";
	 //obtener el archivo que esta pasando el usuario
	files = document.getElementById('csvFileInput').files;
	//obtener el nombre del archivo con su extension (lo que se muestra en la interfaz al seleccionar un archivo)
	archivo = document.getElementById('csvFileInput').value;
	//obtener la extension del archivo
	extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
    // Check for the various File API support.
    if (window.FileReader) {
        // FileReader are supported.
       if (extension == '.csv') //si el archivo es un .csv
        getAsText(files[0]); // se llama al metodo para cargar el archivo
    } else {
        alert('La carga de archivo no esta soportada en este explorador.');
    }
  }

//metodo que se utiliza para la carga del presupuesto comercial
function cargar(formulario){
	//obtener el archivo que se quiere cargar 
	files = document.getElementById('csvFileInput').value;
	//obtener la extension del archivo
	extension = (files.substring(files.lastIndexOf("."))).toLowerCase();
	
 //si es un archivo .csv
  if (extension == '.csv'){
	 //al formulario que pasaron por parametro lo coloco a apuntar al controlador de /cargarpresupuesto.*
	document.getElementById(formulario).action = "http://localhost:8080/cargarpresupuesto.html";
	//le asigno el multipart/form-data para que el archivo sea capaz de ser manipulado por spring
	document.getElementById(formulario).enctype = "multipart/form-data";
	//activo la barra de cargando
	document.getElementById('div-barra').className="progress progress-striped active";
	//obtengo el formulario
	   f=document.getElementById(formulario);
		 if(f){
			//le hago submit con todos los nuevos cambios hechos
		   f.submit();
		 }
  }
  else{ //si el archivo no es .csv
    alert ("Solo se permiten archivos .csv"); //manda un mensaje de error
	return 0;
  }
}
  
//metodo que se utiliza cuando se modifica el presupuesto comercial y se presiona el boton de "Guardar Cambios"
 function guardarCambios(elm){
	 //se le asigna al boton como "name" el mismo nombre que tiene el ID, esto sirve para que spring pueda saber cuando
	 //estan realizando una operacion de guardado de datos o cuando quiere descartar los cambios
	 document.getElementById(elm.id).name = elm.id;
	 //se le setea al boton un nuevo mensaje
	 document.getElementById(elm.id).value = "Guardando...";
	 //se le asgina al formulario el controlador al cual va dirigido
	 document.getElementById('realizar-busqueda-form').action = "http://localhost:8080/modificarpresupuesto.html";
	 //se obtiene el formulario (con todo los cambios que se le acaban de hacer)
	 f=document.getElementById('realizar-busqueda-form');
	  if(f){
		  //se le hace submit al formulario
		f.submit();
	 }
  }
