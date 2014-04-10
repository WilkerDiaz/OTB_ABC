<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
<title>Crear Presupuesto</title>
<link rel="stylesheet" type="text/css" href="/resources/css/estiloOTB.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/navbar.css" /> 
<script language="javascript" src="/resources/js/main.js"></script>
<script src="/resources/js/jquery-2.1.0.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>
	<div class="container">
      <div id="menu" class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a id="header" class="navbar-brand" onclick="javascript:redireccionar('consultar-empresa-from');">
             <form id="consultar-empresa-from" action ="http://localhost:8080/autentificar.html" method="post">
             <input type="hidden" name="usuario" value="${usuario}">
              Open To Buy
            </form>
            </a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            
            <li class="active" onclick="javascript:redireccionar('crear-presupuesto-from');">
		       <a>
		        <form id="crear-presupuesto-from" action ="http://localhost:8080/crearcomercial.html" method="post">
		          Crear Presupuesto
		          <input type="hidden" name="empresa" value="${empresa}">
		          <input type="hidden" name="usuario" value="${usuario}">
		        </form>
		       </a>
		     </li>
		     
              <li onclick="javascript:redireccionar('consultar-presupuesto-from');">
		       <a>
		        <form id="consultar-presupuesto-from" action ="http://localhost:8080/presupuestocomercial.html" method="post">
		          Consultar Presupuesto 
		          <input type="hidden" name="empresa" value="${empresa}">
		          <input type="hidden" name="usuario" value="${usuario}">
		        </form>
		       </a>
		     </li>
 
      <li onclick="javascript:redireccionar('cargar-presupuesto-form');">
       <a>
        <form id="cargar-presupuesto-form" action ="http://localhost:8080/cargarpresupuesto.html" method="post">
          Cargar Presupuesto
          <input type="hidden" name="empresa" value="${empresa}">
          <input type="hidden" name="usuario" value="${usuario}">
        </form>
       </a>   
      </li>
      
      <li onclick="javascript:redireccionar('modificar-presupuesto-form');">
       <a>
        <form id="modificar-presupuesto-form" action ="http://localhost:8080/modificarpresupuesto.html" method="post">
          Modificar Presupuesto
          <input type="hidden" name="empresa" value="${empresa}">
          <input type="hidden" name="usuario" value="${usuario}">
        </form>
       </a>   
      </li>
      
      <li onclick="javascript:redireccionar('ordenes-compra-form');">
       <a>
        <form id="ordenes-compra-form" action ="http://localhost:8080/ordendecompra.html" method="post">
          Ordenes de Compra
          <input type="hidden" name="empresa" value="${empresa}">
          <input type="hidden" name="usuario" value="${usuario}">
          <input type="hidden" name="ordenes" value="ordenes">
        </form>
       </a>   
      </li>

      </ul>
       <ul class="nav navbar-nav navbar-right">
         <li style="font-size:18px;text-transform: uppercase;padding-top:15px;color: #fff">${empresa}</li>
        </ul>
      </div><!--/.nav-collapse -->
    </div><!--/.container-fluid -->
  </div>
</div>

<div class="centro">
<form id="realizar-busqueda-form" action ="http://localhost:8080/crearpresupuesto.html" method="post" onsubmit="return validateSolicitude(this);">
    <TABLE cellpadding="1" width=95%>
		
		 <!-- segunda fila, lleva el almacen y la categoria-->
		 <TR>
		  
		  <td>
			<p align="left">Mes</p>
		 </td>
		   
		   <TD>
		    <input id='text-mes' class = "span" placeholder="Introduzca un mes" type="text" name="mes" value="${mes}" onkeypress="return acceptNum(event, window.Event, 0);">
		   </TD>
		   
		    <TD>
		     <p align="left" >Año</p>
		    </TD>
		    
		    <TD>
		     <input id='text-anio' class= "span" placeholder="Introduzca un año" type="text" name="anio" value="${anio}" onkeypress="return acceptNum(event, window.Event, 0);">
		    </TD>
		  </TR>  
		
		 <!-- segunda fila, lleva el almacen y la categoria-->
		 <TR>
		  
		   <TD>
		     <p align="left">${combo1}</p>
		    </TD>
		   <TD> 
		   <!-- cargar el combo box con los almacenes -->
			  <select class="span form-control" name="almacen" onchange="javascript:redireccionar('realizar-busqueda-form');"> 
			    <c:forEach items="${almacenes}" var="warehouse">
			     
			     <!-- Si no fue el que se selecciona. no se posiciona sobre el -->
			     <c:if test="${warehouse.posicionar == 0}">
			       <option value="${warehouse.m_warehouse_id}">${warehouse.name}</option> 
			      </c:if>
			      
			     <!-- Si fue el que se selecciona, se posiciona sobre el -->
			      <c:if test="${warehouse.posicionar == 1}">
			         <option value="${warehouse.m_warehouse_id}" selected="selected">${warehouse.name}</option> 
			      </c:if>
			    </c:forEach>
			  </select> 
			</TD>
			
			 <TD>
		     <p align="left">${combo2}</p>
		    </TD>
		    
			<TD> 
			<!-- cargar el combo box con las categorias -->
			  <select class="span form-control" onchange="javascript:redireccionar('realizar-busqueda-form');" name="categoria"> 
			    <c:forEach items="${categorias}" var="category">
			    
			    <!-- Si no fue el que se selecciona. no se posiciona sobre el -->
			     <c:if test="${category.posicionar == 0}">
			      <option value ="${category.xx_vmr_category_id}" >${category.name}</option> 
			     </c:if>
			     
			     <!-- Si fue el que se selecciona, se posiciona sobre el -->
			     <c:if test="${category.posicionar == 1}">
			      <option value ="${category.xx_vmr_category_id}" selected="selected">${category.name}</option> 
			     </c:if>
	     
			    </c:forEach>
			  </select>
			</TD> 
		  </TR>
		  
		 <!-- tercera fila, lleva el departamento y la linea-->
		 <TR>
		  <TD>
		    <p align="left">${combo3}</p>
		    </TD>
		    
		   <TD>
		   <!-- cargar el combo box con los departamentos -->
			  <select class="span form-control" onchange="javascript:redireccionar('realizar-busqueda-form');" name="departamento"> 
			   <c:forEach items="${departamentos}" var="department">
			   
			     <!-- Si no fue el que se selecciona. no se posiciona sobre el -->
			     <c:if test="${department.posicionar == 0}">
			      <option value ="${department.xx_vmr_department_id}" >${department.name}</option> 
			     </c:if>
			     
			     <!-- Si fue el que se selecciona, se posiciona sobre el -->
			     <c:if test="${department.posicionar == 1}">
			      <option value ="${department.xx_vmr_department_id}" selected="selected">${department.name}</option> 
			     </c:if>
			     
			    </c:forEach>
			  </select> 
			</TD>
			
			 <TD>
		     <p align="left">${combo4}</p>
		    </TD>
		    
			<TD>
			<!-- cargar el combo box con las lineas -->
			  <select class="span form-control" onchange="javascript:redireccionar('realizar-busqueda-form');" name="linea"> 
			   <c:forEach items="${lineas}" var="line">
			   
			     <!-- Si no fue el que se selecciona. no se posiciona sobre el -->
			     <c:if test="${line.posicionar == 0}">
			      <option value ="${line.xx_vmr_line_id}" >${line.name}</option> 
			     </c:if>
			     
			     <!-- Si fue el que se selecciona, se posiciona sobre el -->
			     <c:if test="${line.posicionar == 1}">
			      <option value ="${line.xx_vmr_line_id}" selected="selected">${line.name}</option> 
			     </c:if>
			     
			    </c:forEach>
			  </select> 
			 </TD> 
		  </TR>
			  
		<!-- cuarta fila, lleva la seccion y el tipo de registro-->
		 <TR>
		 <c:if test="${combo5 != '0'}">
		  <TD>
		     <p align="left">${combo5}</p>
		    </TD>
		    
		   <TD>
		   <!-- cargar el combo box con las secciones -->
			  <select class="span form-control" onchange="javascript:redireccionar('realizar-busqueda-form');" name="seccion"> 
			   <c:forEach items="${secciones}" var="section">
			   
			     <!-- Si no fue el que se selecciona. no se posiciona sobre el -->
			     <c:if test="${section.posicionar == 0}">
			      <option value ="${section.xx_vmr_section_id}" >${section.name}</option> 
			     </c:if>
			     
			     <!-- Si fue el que se selecciona, se posiciona sobre el -->
			     <c:if test="${section.posicionar == 1}">
			      <option value ="${section.xx_vmr_section_id}" selected="selected">${section.name}</option> 
			     </c:if>
			     
			    </c:forEach>
			  </select> 
			</TD>
		  </c:if>
		  
		   <TD>
		     <p align="left">Tipo de Registro</p>
		    </TD>
		    
		    <TD>
		    <!-- cargar el combo box con los tipo de registro -->
			  <select class="span form-control" name="registro" onchange="javascript:redireccionar('realizar-busqueda-form');"> 
			  
			  <!-- Si no fue selcciona el combo box con bolivares -->
			     <c:if test="${seleccionar != 'bolivares'}">
			       <option value ="bolivares" >Bolívares</option> 
			     </c:if>
			     
			   <!-- Si fue selcciona el combo box con bolivares -->
			     <c:if test="${seleccionar == 'bolivares'}">
			       <option value ="bolivares" selected="selected">Bolívares</option> 
			     </c:if>
			    
			    <!-- Si no fue selcciona el combo box con piezas -->
			     <c:if test="${seleccionar != 'piezas'}">
			  	   <option value="piezas" >Piezas</option>
			  	 </c:if>
			  	 
			  	 <!-- Si fue selcciona el combo box con piezas -->
			     <c:if test="${seleccionar == 'piezas'}">
			       <option value ="piezas" selected="selected">Piezas</option> 
			     </c:if>
			     
			     <!-- Si no fue selcciona el combo box con rotacion -->
			     <c:if test="${seleccionar != 'rotacion'}">
			  	   <option value="rotacion" >Rotacaión/Cobertura</option>
			  	 </c:if>
			  	 
			  	 <!-- Si fue selcciona el combo box con rotacion -->
			     <c:if test="${seleccionar == 'rotacion'}">
			       <option value ="rotacion" selected="selected">Rotacaión/Cobertura</option> 
			     </c:if>
			  </select> 
			 </TD>
		  </TR>
		  
		</TABLE>
		
		<input type="hidden" name="empresa" value="${empresa}">
		<input type="hidden" name="usuario" value="${usuario}">
		<input id="btn-consulta" class="btn btn-default" style="margin-left:43%" type="submit" name="consulta" value="Buscar" onclick="javascript:cambiar_texto('btn-consulta','Buscando...')">
 
   <c:if test="${permitir == 1}"> 
   <TABLE class="table-bordered table-hover" id="tabla-presupuesto" border="${borde}" bordercolor="#999999"  width="${porcentaje}" style="color:#000000; background-color: #808080;">
  
  <!-- tabla que muestra el presupuesto comercial si marcaron la opcion "bolivares" o "piezas" en tipo registro -->	
		   	<TR>
		        <TD> Concepto</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	   <TD> ${presupuesto.mesNombre}-${presupuesto.anio}</TD>  
		        </c:forEach>
		   </TR>
		
		   <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Inicial Presupuestado</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="inventarioInicial" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_inicial_presupuestado}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		   <TR> 
		      <TD class="gris-oscuro"  style= "border-style:solid; border-width:thin; border-color: #999999"> Compras Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="compras" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_presupuestadas}"/></TD>  
		        </c:forEach>
		   </TR>
  
		  <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Ventas Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="ventas" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.ventas_presupuestadas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Total Presupuestas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="rebajasTotal" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_total_presupuestas}"/></TD>
		        </c:forEach>
		   </TR>
	  	 
		  	 <TR> 
		        <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Definitivas Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="rebajasDef" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_definitivas_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Presupuestado</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD name="inventarioFinal" class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_presupuestado}"/></TD>
		        </c:forEach>
		  	 </TR>
  		</TABLE>
	 
	<TABLE width="20%">
	  <TD><input class="btn btn-default" id="exportar" name="exportar" value="Exportar" type="submit"></TD>
	  <TD><input style="margin-left:115%" type="file" id="csvFileInput" name="fileModificar"></TD>
      <TD><input style="margin-left:450%" type="button" id="btn-export" class="btn btn-default" value="Importar" 
	         onclick="javascript:cargar('realizar-busqueda-form');" onmousedown="javascript:handleFiles()">
	      </TD> 
	      <p id="mensaje" style="font-size: 14px !important;" align="left" >${mensaje}</p>
			
	</TABLE>
	</BR>
	</BR>
	<div  id='div-barra' style="margin-left:30%; margin-right: 30%" class="">
	  <div id='barra' class="progress-bar" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:0%">
	       <span id='text-barra' class="sr-only"></span>
	   </div>
	</div>
	
	<!-- campos ocultos que se modifica el value, para saber cual valor se modifico en la interfaz -->
	 <input id="inventarioInicial" name="inventarioInicialPresupuestada" type="hidden" value="">
	 <input id="compras" name="comprasPresupuestada" type="hidden" value="">
	 <input id="ventas" name="ventasPresupuestadas" type="hidden" value="">
	 <input id="rebajasTotal" name="rebajasTotalPresupuestada" type="hidden" value="">
	 <input id="rebajasDef" name="rebajasDefPresupuestada" type="hidden" value="">
	 <input id="inventarioFinal" name="inventarioFinalPresupuestado" type="hidden" value="">
   </c:if>
   
    
  </form> 
  </div>
</body>
</html>