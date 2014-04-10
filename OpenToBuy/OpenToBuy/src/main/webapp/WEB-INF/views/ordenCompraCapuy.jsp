<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
<title>Orden de Compra</title>
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
      
      <li class="active" onclick="javascript:redireccionar('ordenes-compra-form');">
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

<div class="izquierda">
	<ul class="nav nav-pills nav-stacked" style="max-width: 180px;">

      <div class="blog-masthead">
        <nav class="blog-nav">
          <a class="blog-nav-item active">
             Ordenes de Compra
          </a>
        </nav>
    </div>
    
    <div class="">
        <nav class="" onclick="javascript:redireccionar('productos-ordenes-form');">
          <a style="padding-left:72px"  class="blog-nav-item active">
           <form id="productos-ordenes-form" action ="http://localhost:8080/ordendecompra.html" method="post">
          		Productos
          	 <input type="hidden" name="empresa" value="${empresa}">
          	 <input type="hidden" name="usuario" value="${usuario}">
          	 <input type="hidden" name="productos" value="productos">
          	 <c:forEach items="${ordenes}" var="orden"> 
			    <input type="hidden" name="idOrder" value="${orden.id}">
			  </c:forEach>
        	</form>
          </a>
        </nav>
    </div>  
    </ul>
</div>


<div id="menu" class="centroOrdenes">
<form id="realizar-busqueda-form" action ="http://localhost:8080/ordendecompra.html" method="post" onsubmit="return validateSolicitude(this);">
  <input type="hidden" name="usuario" value="${usuario}">
  <input type="hidden" name="empresa" value="${empresa}">
  <input type="hidden" name="cantidad" value="${cantidad}">
  <input type="hidden" name="ordenes" value="ordenes">
  <c:forEach items="${ordenes}" var="orden"> 
    <input type="hidden" name="idOrder" value="${orden.id}">
  </c:forEach>
  
  <TABLE cellpadding="1" width="95%">
  
  <c:forEach items="${ordenes}" var="orden"> 
   <c:if test="${orden.mensaje != '0'}">
       <!-- Primera fila: campo creado por -->
	    <TR>  
	      <TD colspan="6">
	        <input class = "span4" type="text" value="${orden.mensaje}">
	      </TD>  
	   </TR>
    </c:if>
  </c:forEach>
   
  <c:forEach items="${ordenes}" var="orden"> 
  <c:if test="${orden.tarea_critica != '0'}">
   <!-- Segunda fila: campo creado por -->
    <TR>
 
      <TD>
		<p align="left">En espera de aprobación</p>
	  </TD>
		  
      <TD colspan="3">
        <input class = "span3" type="text" value="${orden.tarea_critica}">  
      </TD>  
   </TR>
   </c:if>
  </c:forEach>
  
   
   <!-- Tercera fila: campo creado por -->
    <TR>
 
      <TD>
		<p align="left" >Creado Por</p>
	  </TD>
		  
      <TD colspan="3">
        <input class = "span3" type="text" value="${usuario}">   
      </TD>  
   </TR>
   
   
   <!-- Cuarta Fila: Purchase Orden No y Tipo de Orden -->
   <TR>
      <TD>
		<p align="left">Purchase Orden No </p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
          <input class = "span3" type="text" value="${orden.purchase_no}">
        </c:forEach>
      </TD>
      
      <TD>
		<p align="left" >Organización</p>
	  </TD>
		  
      <TD>   
        <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.tienda}">
        </c:forEach>   
      </TD>    
    </TR>
    
    
    <!-- Quinta Fila: Estado de la OC -->
   <TR>
      <TD>
		<p align="left">Descripción </p>
	  </TD>
		  
      <TD colspan="6">
        <c:forEach items="${ordenes}" var="orden">
          <input style="width: 565px" type="text" value="${orden.descripcion}">
        </c:forEach>
      </TD>  
    </TR>
    
   <!-- Sexta Fila: Departamento y Categoria -->
   <TR>
      <TD>
		<p align="left">Tipo de documento</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
          <input class = "span3" type="text" value="${orden.tipo}">
        </c:forEach>
      </TD>   
      
      <TD>
		<p align="left">Proveedor</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.proveedor}">
        </c:forEach>
      </TD>
    </TR>
    
    
    <!-- Septima Fila: Fecha de creación -->
    <TR>
      <TD >
		<p align="left">Fecha Ordenada</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.fecha_creacion}">
        </c:forEach>
      </TD>
      
       <TD >
		<p align="left">Fecha Prometida</p>
	  </TD>
		  
      <TD>
       <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.fecha_llegada}">
       </c:forEach>
      </TD>
      
    </TR>
    
     <!-- Septima Fila: Fecha de creación -->
    <TR>
      <TD >
		<p align="left">Dirección Proveedor</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.direccion_proveedor}">
       </c:forEach>
      </TD>
      
       <TD >
		<p align="left">Dirección Factura</p>
	  </TD>
		  
      <TD>
       <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.direccion_proveedor}">
        </c:forEach>
      </TD>
      
    </TR>
    
      <!-- Decimo quinta Fila: Cantidad de Producto -->
    <TR>
      <TD>
		<p align="left">Cantidad de producto</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
         <input class = "span3" type="text" value="${orden.cantidad_producto}">
        </c:forEach>
      </TD>
      
       <TD>
		<p align="left">IVA</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
          <input class = "span3" type="text" value="${orden.iva}">
        </c:forEach>
      </TD>
    </TR>
   
      <!-- Decimo sexta Fila: TotalPVP y PVP Total + IVA -->
    <TR>
      <TD>
		<p align="left">TotalPVP</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
          <input class = "span3" type="text" value="${orden.total}">
        </c:forEach> 
      </TD>
      
      <TD>
		<p align="left">PVP Total + IVA</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
          <input class = "span3" type="text" value="${orden.total_iva}">
        </c:forEach>
      </TD>
    </TR>
    
  </TABLE>
  
  </br>
  <table width="60%">
     <TR>	  
      <TD style="padding-bottom: 20px;padding-left: 50px">
        <input class="btn btn-default" type="submit"  name="aprobar" value="Aprobar">
      </TD>
      		  
      <TD style="padding-bottom: 20px">
       <input class="btn btn-default" type="submit"  name="rechazar" value="Rechazar Aprobación">
      </TD>
      
    <c:forEach items="${ordenes}" var="orden">	  
	      <TD style="padding-bottom: 20px;padding-left: 250px"> 
	      <c:if test="${(cantidad) > 1}">
	        <input class="btn btn-default"  type="submit" name="anterior" value="Anterior">
	        </c:if>
	      </TD>
	   
	</c:forEach>
	  
	<c:forEach items="${ordenes}" var="orden">
	  <c:if test="${orden.limite > (cantidad)}">
	    <TD style="padding-bottom: 20px">
	     <input class="btn btn-default" type="submit" name="proximo" value="Proximo">
	    </TD>
	  </c:if>
 	</c:forEach>
    </TR>
  </table>
</form>
</div>
</body>
</html>