<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
<title>Presupuesto Comercial</title>
<link rel="stylesheet" type="text/css" href="/resources/css/estiloOTB.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/navbar.css" /> 
<script language="javascript" src="/resources/js/main.js"></script>
<script src="/resources/js/jquery-2.1.0.js"></script>
<meta http-equiv="content-type" content="text/plain; charset=UTF-8"/>
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
            
             <li onclick="javascript:redireccionar('crear-presupuesto-from');">
		       <a>
		        <form id="crear-presupuesto-from" action ="http://localhost:8080/crearpresupuesto.html" method="post">
		          Crear Presupuesto
		          <input type="hidden" name="empresa" value="${empresa}">
		          <input type="hidden" name="usuario" value="${usuario}">
		        </form>
		       </a>
		     </li>
       
		       <li class="active" onclick="javascript:redireccionar('consultar-presupuesto-from');">
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
<form id="realizar-busqueda-form" action ="http://localhost:8080/presupuestocomercial.html" method="post" onsubmit="return validateSolicitude(this);">
		
		
    <TABLE cellpadding="1" width="95%">
		
		 <!-- primera fila, lleva el mes y el año-->
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
		<input id="btn-consulta" class="btn btn-default" style="margin-left:43%" type="submit"  name="consulta" value="Buscar" onclick="javascript:cambiar_texto('btn-consulta','Buscando...')">

  <TABLE id="tabla-presupuesto" border="${borde}" bordercolor="#999999"  width="${porcentaje}" style="padding-right: 50px">
  
  <!-- tabla que muestra el presupuesto comercial si marcaron la opcion "bolivares" o "piezas" en tipo registro -->
   <c:if test="${tablas == 'bspieza' and (permitir == 1)}"> 	
		   	<TR>
		        <TD> Concepto</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	   <TD> ${presupuesto.mesNombre}-${presupuesto.anio}</TD>  
		        </c:forEach>
		   </TR>
		
		   <TR> 
		      <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Inventario Inicial Presupuestado</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha" onmouseup=""> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_inicial_presupuestado}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		   <TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Inventario Inicial Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_inicial_real}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		   
		   <TR> 
		      <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-style:outset; border-color: #999999"> Compras Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_presupuestadas}"/></TD>  
		        </c:forEach>
		   </TR>

		   <TR> 
		      <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999"> Compras Colocadas Total</TD>
		        <c:forEach items="${consulta}" var="presupuesto">	
		     	   <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_colocadas_total}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   <TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Colocadas Nacionales</TD>
		        <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_colocadas_nacionales}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		   <TR>
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Colocadas Importadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_colocadas_importadas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		   <TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Colocadas Meses Anteriores</TD>
		        <c:forEach items="${consulta}" var="presupuesto">	
		     	 <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_colocadas_mes_anteriores}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		   <TR> 
		      <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999"> Compras Recibidas Total</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_recibidas_total}"/></TD>
		        </c:forEach>
		   </TR>
		   	  
		 <c:if test="${isCapuy !=1}">  
		    <TR>     
		      <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999"> Compras Chequeadas Total</TD>
		     
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_chequeadas_total}"/></TD>
		        </c:forEach>
		    </TR>
		  </c:if>
		   
		 <c:if test="${isCapuy !=1}">   
		   <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Chequeadas Nacional</TD>
		     
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_chequeadas_nacional}"/></TD>
		        </c:forEach>
		   </TR>
		 </c:if>
		   
		  <c:if test="${isCapuy !=1}">  
		    <TR> 
		        <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Chequeadas Importadas</TD>
		        <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_chequeadas_importadas}"/></TD>
		        </c:forEach>
		    </TR>
		  </c:if>
		   
		 <c:if test="${isCapuy !=1}">   
		    <TR>   
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Compras Chequedas Meses Anterior</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_chequedas_mes_anterior}"/></TD>
		        </c:forEach>
		    </TR>
		 </c:if>
		  
		  
		  <TR> 
		      <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Ventas Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.ventas_presupuestadas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		   <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Ventas Reales</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.ventas_reales}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		   <TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Traspasos Enviados</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.traspasos_enviados}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Traspasos Recibidos</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.traspasos_recibidos}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Devoluciones</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.devoluciones}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		    <TR> 
		       <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Total Presupuestas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_total_presupuestas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		   
		    <TR> 
		      <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Total Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #FFC800; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_total_real}"/></TD>
		        </c:forEach>
		    </TR>
		    
		    
		    <TR> 
		       <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Promocionales Presupuestadas</TD>
		        <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_promocionales_presupuestadas}"/></TD>
		        </c:forEach>
		    </TR>
		    
		    
		    <TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Promocionales Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_promocionales_real}"/></TD>
		        </c:forEach>
		  	 </TR>
		  	 
		  <c:if test="${isCapuy !=1}">	 
		  	<TR> 	  	 
		      <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Rebajas FR Presupuestadas</TD>
		     
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	 <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_fr_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		 </c:if>
		  	
		  <c:if test="${isCapuy !=1}"> 
		  	<TR> 	  	 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Rebajas FR Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_fr_real}"/></TD>
		        </c:forEach>
		  	 </TR>
		   </c:if>
		  	 
		  	 
		  	 <TR> 
		        <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Definitivas Presupuestadas</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_definitivas_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		  	 
		  	 <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Rebajas Definitivas Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_definitivas_real}"/></TD>
		        </c:forEach>
		  	 </TR>
		
		
		    <TR> 
		       <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Presupuestado</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #808080; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_presupuestado}"/></TD>
		        </c:forEach>
		  	 </TR>
		  	 
		  	 <TR> 
		       <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_real}"/></TD>
		        </c:forEach>
		  	 </TR>
		  	 
		  	<TR> 
		      <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Proyectado</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #C0C0C0; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_proyectado}"/></TD>
		        </c:forEach>
		  	 </TR>

		  	 <TR class="amarillo-claro"> 
		  	  <c:if test="${permitir == 1}">
		         <TD style= "background-color: #FFFF00; border-style:solid; border-width:thin; border-color: #999999"> Limite De Compras</TD>
		       </c:if>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD style= "background-color: #FFFF00; border-style:solid; border-width:thin; border-color: #999999" class="alineacion-derecha"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.limite_de_compras}"/></TD>
		        </c:forEach>
		  	 </TR>
    </c:if>
    
   <!-- tabla para mostrar la rotacion -->
   <c:if test="${tablas == 'rotacion' and permitir ==1}">
      	  <TR>
		        <TD> Concepto</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	   <TD> ${presupuesto.mesNombre}-${presupuesto.anio}</TD>  
		        </c:forEach>
		  </TR>
		   
		   
		   <TR class="gris-claro"> 
		      <TD> Rotación Presupuestada</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD> <fmt:formatNumber pattern="###,###.##" value="${presupuesto.rotacion_presupuestada}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		   <TR class="gris-claro"> 
		      <TD> Rotación Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD> <fmt:formatNumber pattern="###,###.##" value="${presupuesto.rotacion_real}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		   
		   <TR class="gris-claro"> 
		      <TD> Cobertura Presupuestada</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD> <fmt:formatNumber pattern="###,###.##" value="${presupuesto.cobertura_presupuestada}"/></TD>  
		        </c:forEach>
		   </TR>
		   
		   <TR class="gris-claro"> 
		      <TD> Cobertura Real</TD>
		         <c:forEach items="${consulta}" var="presupuesto">	
		     	  <TD> <fmt:formatNumber pattern="###,###.##" value="${presupuesto.cobertura_real}"/></TD>  
		        </c:forEach>
		   </TR>
		   
      </c:if>
  </TABLE>

 <TABLE style="margin-left:10%" width="30%">
  <TR>
  <!-- mostrar los botonesde mes anteior y mes proximo cuando se haga una consulta del presupuesto comercial -->
   <c:if test="${permitir == 1}"> 
	 <TD> <input class="btn btn-default"  type="submit" name="anterior" value="Mes Anterior"></TD>
	 <TD><input class="btn btn-default" type="submit" name="proximo" value="Mes Próximo"></TD>
	<a id="dlink"  style="display:none;"></a>
	
	<TD><input id='text-export' type="text" class ="span" style="margin-left:75%; margin-top:5px" placeholder="Introduzca el nombre del archivo"
	onkeypress="return importarExcel(event,'tabla-presupuesto', 'Presupuesto Comercial');"></TD>
	
	<TD><input id="btn-export" class="btn btn-default" style="margin-left:240%" type=button value="Exportar" 
	            onclick="javascript:tableToExcel('tabla-presupuesto', 'Presupuesto Comercial');"></TD>
	</c:if>
  </TR>
 </TABLE>
 </form> 
 </div>

</body>
</html>