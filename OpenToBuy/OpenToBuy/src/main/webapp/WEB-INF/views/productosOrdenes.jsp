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
      
      <li class ="active" onclick="javascript:redireccionar('ordenes-compra-form');">
       <a>
        <form id="ordenes-compra-form" action ="http://localhost:8080/ordendecompra.html" method="post">
          Ordenes de Compra
          <input type="hidden" name="empresa" value="${empresa}">
          <input type="hidden" name="usuario" value="${usuario}">
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

      <div class="">
        <nav class="" onclick="javascript:redireccionar('ordenes-form');">
          <a class="blog-nav-item active">
           <form id="ordenes-form" action ="http://localhost:8080/ordendecompra.html" method="post">
          		Ordenes de Compra
          	 <input type="hidden" name="empresa" value="${empresa}">
          	 <input type="hidden" name="usuario" value="${usuario}">
          	 <input type="hidden" name="ordenes" value="ordenes">
        	</form>
          </a>
        </nav>
    </div>
    
    <div class="blog-masthead">
        <nav class="blog-nav">
          <a style="padding-left:72px" class="blog-nav-item active">Productos</a>
        </nav>
      </div>  
    </ul>
    
   
</div>
<div id="menu" class="centroOrdenes">
<form id="realizar-busqueda-form" action ="http://localhost:8080/ordendecompra.html" method="post" onsubmit="return validateSolicitude(this);">
  <input type="hidden" name="usuario" value="${usuario}">
  <input type="hidden" name="empresa" value="${empresa}">
  <input type="hidden" name="cantidadProducto" value="${cantidadProducto}">
  <input type="hidden" name="idOrder" value="${idOrder}">
  <input type="hidden" name="productos" value="productos">
  
  <TABLE cellpadding="1" width="95%">

  <!-- Primera Fila: Linea y Seccion -->
   <TR>
      <TD>
		<p align="left">Línea </p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">  
           <input class = "span3" type="text" value="${producto.linea}">  
        </c:forEach>
      </TD>
      
      <TD>
		<p style="padding-left:20px" align="left" >Sección</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">  
             <input class = "span3" type="text" value="${producto.seccion}">  
        </c:forEach>
      </TD>    
    </TR>
    
    
    <!-- Segunda Fila: Marca y caracteristica -->
   <TR>
      <TD>
		<p align="left">Marca</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.marca}">  
        </c:forEach>
      </TD>  
      
       <TD>
		<p style="padding-left:20px" align="left">Característica Principal</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">
             <input  class = "span3" type="text" value="${producto.caracteristica}">  
        </c:forEach>
      </TD>  
    </TR>
    
   <!-- Tercera Fila: Concepto y cantidad-->
   <TR>
      <TD>
		<p align="left" >Concepto de Valor</p>
	  </TD>
		  
      <TD>
         <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.concepto}">  
        </c:forEach>
      </TD>  
      
      <TD>
		<p style="padding-left:20px" align="left">Cantidad</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.cantidad}">  
        </c:forEach>
      </TD>  
    </TR>
    
    
    <!-- Cuarta Fila: Precio e impuesto -->
    <TR>
      <TD >
		<p align="left">Precio de Venta</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.precio}">  
        </c:forEach>
      </TD>
      
      <TD>
		<p style="padding-left:20px" align="left">Monto del Impuesto</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.iva}">  
        </c:forEach>
      </TD>
    </TR>

    
     <!-- Quinto Fila: Precio + IVA -->
    <TR>
      <TD>
		<p align="left">Precio de Venta con impuesto</p>
	  </TD>
		  
      <TD>
         <c:forEach items="${productos}" var="producto">
             <input class = "span3" type="text" value="${producto.precioIva}">  
        </c:forEach>
      </TD>
    </TR>    
  </TABLE>
  
  </br>
  <table width="60%">
     <TR>	       
        <c:forEach items="${productos}" var="producto">
	      <TD style="padding-bottom: 20px;padding-left: 250px"> 
	        <c:if test="${(cantidadProducto) > 1}">
	          <input class="btn btn-default"  type="submit" name="anteriorProducto" value="Anterior">
	        </c:if>
	      </TD>
	    </c:forEach>
	  
	  <c:forEach items="${productos}" var="producto">
	   <c:if test="${producto.limite > (cantidadProducto)}">
	     <TD style="padding-bottom: 20px">
	       <input class="btn btn-default" type="submit" name="proximoProducto" value="Proximo">
	  	 </TD>
	   </c:if>
 	  </c:forEach>
    </TR>
  </table>
</form>
</div>
</body>
</html>