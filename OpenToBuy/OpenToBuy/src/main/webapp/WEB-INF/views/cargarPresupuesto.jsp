<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
<title>Cargar Presupuesto</title>
<link rel="stylesheet" type="text/css" href="/resources/css/estiloOTB.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/navbar.css" /> 
<script language="javascript" src="/resources/js/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

</script>
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
 
      <li class="active" onclick="javascript:redireccionar('cargar-presupuesto-form');">
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
<form id="subir-adjunto-form" method="POST" action="" enctype="multipart/form-data">

	<TABLE cellpadding="1" style="margin-left:15%" width="50%">
	 	<TR>
	 	<input type="hidden" name="empresa" value="${empresa}">
	 	<input type="hidden" name="usuario" value="${usuario}">
	 	
          <TD>
            <input type="file" id="csvFileInput" name="fileCargar">
          </TD>
       
       	  <TD>
             <input type="button" id="btn-export" class="btn btn-default" value="Importar" 
	         onclick="javascript:cargar('subir-adjunto-form');" onmousedown="javascript:handleFiles()">
	      </TD> 
	 	</TR>
	 	
	 	<TR>
      	  <TD>
      	   </BR>
      	   
      	   <div id='div-barra' class="progress progress-striped active">
			  <div id='barra' class="progress-bar" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:0%">
			    <span id='text-barra' class="sr-only"></span>
			  </div>
			</div>

      	    <p id="mensaje" style="font-size: 14px !important;" align="left" >${mensaje}</p> 
    
      	  </TD> 
      	</TR>
      </TABLE>
      </form>

      
      </BR>
  	  </BR>
      
   <c:if test="${permitir == 1}"> 
   <TABLE class="table table-bordered table-hover" id="tabla-presupuesto-bolivares" border="${borde}" bordercolor="#999999"  width="${porcentaje}" style="color:#000000; background-color: #808080;">
  
  <span style="font-size: 14px; margin-left:7%">Presupuesto Comercial (Bolivares)</span>
  <!-- tabla que muestra el presupuesto comercial si marcaron la opcion "bolivares" o "piezas" en tipo registro -->	
		   	<TR>
		        <TD> Concepto</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	   <TD> ${presupuesto.mesNombre}-${presupuesto.anio}</TD>  
		        </c:forEach>
		   </TR>
		
		   <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Inicial Presupuestado</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_inicial_presupuestado}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Compras Presupuestadas</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_presupuestadas}"/></TD>  
		        </c:forEach>
		   </TR>
  
		  <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Ventas Presupuestadas</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.ventas_presupuestadas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Total Presupuestas</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_total_presupuestas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Promocionales Presupuestadas</TD>
		        <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_promocionales_presupuestadas}"/></TD>
		        </c:forEach>
		    </TR>
	
		  <c:if test="${permitirCapuy==1}">	 
		  	<TR> 	  	 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas FR Presupuestadas</TD>
		     
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	 <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_fr_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		 </c:if>
		  	 
		  	 <TR> 
		        <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Definitivas Presupuestadas</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_definitivas_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Presupuestado</TD>
		         <c:forEach items="${consultaBolivares}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_presupuestado}"/></TD>
		        </c:forEach>
		  	 </TR>
  		</TABLE>
   </c:if>
  
  
<c:if test="${permitir == 1}">
   <TABLE class="table table-bordered table-hover" id="tabla-presupuesto-pieza" border="${borde}" bordercolor="#999999"  width="${porcentaje}" style="color:#000000">
  
  <!-- tabla que muestra el presupuesto comercial si marcaron la opcion "bolivares" o "piezas" en tipo registro -->	
   <span style="font-size: 14px; margin-left:7%">Presupuesto Comercial (Piezas)</span>
		   	<TR>
		        <TD> Concepto</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	   <TD> ${presupuesto.mesNombre}-${presupuesto.anio}</TD>  
		        </c:forEach>
		   </TR>
		
		   <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Inicial Presupuestado</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_inicial_presupuestado}" /></TD>  
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Compras Presupuestadas</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.compras_presupuestadas}"/></TD>  
		        </c:forEach>
		   </TR>
  
		  <TR> 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Ventas Presupuestadas</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.ventas_presupuestadas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Total Presupuestas</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_total_presupuestas}"/></TD>
		        </c:forEach>
		   </TR>
		   
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Promocionales Presupuestadas</TD>
		        <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_promocionales_presupuestadas}"/></TD>
		        </c:forEach>
		    </TR>
	
		  <c:if test="${permitirCapuy==1}">	 
		  	<TR> 	  	 
		      <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas FR Presupuestadas</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	 <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_fr_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		 </c:if>
		  	 
		  	 <TR> 
		        <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Rebajas Definitivas Presupuestadas</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.rebajas_definitivas_presupuestadas}"/></TD>
		        </c:forEach>
		  	 </TR>
		
		    <TR> 
		       <TD class="gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> Inventario Final Presupuestado</TD>
		         <c:forEach items="${consultaPieza}" var="presupuesto">	
		     	  <TD class="alineacion-derecha gris-oscuro" style= "border-style:solid; border-width:thin; border-color: #999999"> <fmt:formatNumber pattern="###,##0.00" value="${presupuesto.inv_final_presupuestado}"/></TD>
		        </c:forEach>
		  	 </TR>
  </TABLE>
</c:if>
</div>
</body>
</html>