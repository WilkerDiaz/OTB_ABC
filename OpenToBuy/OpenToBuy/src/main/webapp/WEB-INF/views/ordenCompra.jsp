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
       <!-- Primera fila: campo Creado por -->
	    <TR>  
	      <TD colspan="6">
	      <c:forEach items="${ordenes}" var="orden">  
	      <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span4" type="text" value="${orden.mensaje}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span4" type="text" value="${orden.mensaje}" disabled>  
          </c:if> 
	     </c:forEach>
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
      <c:forEach items="${ordenes}" var="orden"> 
      
           <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tarea_critica}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tarea_critica}" disabled>  
          </c:if> 
        </c:forEach>  
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
 		<c:forEach items="${ordenes}" var="orden">  
 		  <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.usuario}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.usuario}" disabled>  
          </c:if> 
        </c:forEach>
      </TD>  
   </TR>
   
   
   <!-- Cuarta Fila: Purchase Orden No y Tipo de Orden -->
   <TR>
      <TD>
		<p align="left">Purchase Orden No </p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">  
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input name="purchaseOrden" class = "span3" type="text" value="${orden.purchase_no}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input name="purchaseOrden" class = "span3" type="text" value="${orden.purchase_no}" disabled>  
          </c:if> 
        </c:forEach>
      </TD>
      
      <TD>
		<p align="left" >Tipo de Orden</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">  
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo}" disabled>  
          </c:if> 
        </c:forEach>
      </TD>    
    </TR>
    
    
    <!-- Quinta Fila: Estado de la OC -->
   <TR>
      <TD>
		<p align="left">Estado de la OC </p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.estado}">  
          </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.estado}" disabled>  
          </c:if> 
          
        </c:forEach>
      </TD>  
      
       <TD>
		<p align="left">Tipo de Orden de Compra</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
           <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo_orden_compra}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo_orden_compra}" disabled>  
          </c:if> 
          
        </c:forEach>
      </TD>  
    </TR>
    
   <!-- Sexta Fila: Departamento y Categoria -->
   <TR>
      <TD>
		<p align="left" >Categoría</p>
	  </TD>
		  
      <TD>
         <c:forEach items="${ordenes}" var="orden">
         
         <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.categoria}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.categoria}" disabled>  
          </c:if> 
          
        </c:forEach>
      </TD>  
      
      <TD>
		<p align="left">Departamento</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
          <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.departamento}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.departamento}" disabled>  
          </c:if> 
          
        </c:forEach>
      </TD>  
    </TR>
    
    
    <!-- Septima Fila: Fecha de creación -->
    <TR>
      <TD >
		<p align="left">Fecha de creación</p>
	  </TD>
		  
      <TD colspan="3">
        <c:forEach items="${ordenes}" var="orden">
        
          <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.fecha_creacion}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.fecha_creacion}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
    </TR>
    
     <!-- Octava Fila: Tema y Temporada -->
    <TR>
      <TD>
		<p align="left">Tema</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
          <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tema}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tema}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
      
      <TD>
		<p align="left">Temporada</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
           <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.temporada}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.temporada}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
    </TR>
    
     <!-- Novena Fila: Coleccion -->
    <TR>
      <TD>
		<p align="left">Colección</p>
	  </TD>
		  
      <TD>
         <c:forEach items="${ordenes}" var="orden">
         
         <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.coleccion}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.coleccion}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
      
      <TD>
		<p align="left">Paquete</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.paquete}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.paquete}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
      
    </TR>
    
 
    
     <!-- Decima Fila: Vendor y Vendor Contact -->
    <TR>
      <TD>
		<p align="left">Vendedor</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.vendedor}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.vendedor}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
      
      <TD>
		<p align="left">Contacto Vendedor</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.contacto_vendedor}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.contacto_vendedor}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
    </TR>
    
      <!-- Undecima Fila: Comprador -->
    <TR>
      <TD>
		<p align="left">Comprador</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.comprador}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.comprador}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
      
       <TD>
		<p align="left">País</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.pais}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.pais}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
    </TR>
    
     <!-- Decimo tercera Fila: Tipo de Despacho y Tienda -->
    <TR>
      <TD>
		<p align="left">Tipo de Despacho</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo_despacho}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tipo_despacho}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
      
       <TD>
		<p align="left">Tienda</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tienda}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.tienda}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
    </TR>
    
      <!-- Decimo cuarta Fila: Fecha de llegada y Fecha Estimada -->
    <TR>
      <TD>
		<p align="left">Fecha de llegada</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.fecha_llegada}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.fecha_llegada}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
      
       <TD>
		<p align="left">Fecha Estimada</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
         <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" name="fecha" type="text" value="${orden.fecha_estimada}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" name="fecha" type="text" value="${orden.fecha_estimada}" disabled>  
          </c:if>
          
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
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.cantidad_producto}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.cantidad_producto}" disabled>  
          </c:if>
          
        </c:forEach>
      </TD>
      
       <TD>
		<p align="left">Margen Estimado</p>
	  </TD>
		  
      <TD>
        <c:forEach items="${ordenes}" var="orden">
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.margen_estimado}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.margen_estimado}" disabled>  
          </c:if>

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
        
        <c:if test="${orden.estado == 'Proforma' }">
             <input class = "span3" type="text" value="${orden.total}">  
           </c:if> 
          
          <c:if test="${orden.estado != 'Proforma' }">
             <input class = "span3" type="text" value="${orden.total}" disabled>  
          </c:if>

        </c:forEach>
      </TD>
    </TR>
    
  </TABLE>
  
  </br>
  <table width="60%">
     <TR>	  
      <TD style="padding-bottom: 20px; padding-left: 50px">
      <c:forEach items="${ordenes}" var="orden">
        <c:if test="${orden.estado == 'Proforma' }">
          <input class="btn btn-default" type="submit"  name="aprobar" value="Aprobar">
        </c:if>
        
        <c:if test="${orden.estado != 'Proforma' }">
           <input class="btn btn-default disabled" type="button" disabled="disabled"  name="rechazar" value="Aprobar">
       	</c:if>
      </c:forEach>
     </TD>
      		  
     <TD style="padding-bottom: 20px">
      <c:forEach items="${ordenes}" var="orden">
        <c:if test="${orden.estado == 'Proforma' }">
         <input class="btn btn-default" type="submit"  name="rechazar" value="Rechazar Aprobación">
        </c:if>
        
        <c:if test="${orden.estado != 'Proforma' }">
            <input class="btn btn-default" type="submit" disabled="disabled"  name="rechazar" value="Rechazar Aprobación">
        </c:if>
      </c:forEach>
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