<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configuración Base de datos</title>
<link rel="stylesheet" type="text/css" href="/resources/css/estiloOTB.css" />
<script language="javascript" src="/resources/js/main.js"></script>
<meta http-equiv="content-type" content="text/plain; charset=UTF-8"/>
</head>
<body>

<h1 class="sidebar1"> Configuración</h1>
<form id="configuracion-form" action ="http://localhost:8080/guardarconfiguracion.html" method="post" >
 <TABLE class="table table-bordered table-hover" cellpadding="1">
 <TR>
   <TD>
    <h1 class="centrar">Empresa</h1>
   </TD>
   
   <TD>
    <h2>ojdbc</h2>
   </TD>
   
   <TD>
    <h2>url</h2>
   </TD>
   
   <TD>
    <h2>usuario</h2>
   </TD>
   
   <TD>
    <h2>password</h2>
   </TD>
 </TR>
 
 <TR>
 
 <!-- configuracion base de datos beco -->
 <TD>
    <h2>Beco</h2>
   </TD>
   
   <TD>
     <input name="ojdbcBeco" class="span3 centrar" type ="text" value="${ojdbcBeco}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
      <input name="urlBeco" class="span centrar" type ="text" value="${urlBeco}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="usuarioBeco" class="span2 centrar" type ="text" value="${usuarioBeco}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="passwordBeco" class="span2 centrar" type ="text" value="${passwordBeco}" onkeyup="javascript:repintar();"></input>
   </TD>
 </TR>
 

 <!-- configuracion base de datos amand -->
 <TR>
 <TD>
    <h2 class="centrar">Amand</h2>
   </TD>
   
   <TD>
     <input name="ojdbcAmand" class="span3 centrar" type ="text" value="${ojdbcAmand}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
      <input name="urlAmand" class="span centrar" type ="text" value="${urlAmand}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="usuarioAmand" class="span2 centrar" type ="text" value="${usuarioAmand}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="passwordAmand" class="span2 centrar" type ="text" value="${passwordAmand}" onkeyup="javascript:repintar();"></input>
   </TD>
 </TR>
 
 
 <!-- configuracion base de datos abstracta --> 
<TR>
 <TD>
    <h2 class="centrar">Abstracta</h2>
   </TD>
   
   <TD>
     <input name="ojdbcAbstracta" class="span3 centrar" type ="text" value="${ojdbcAbstracta}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
      <input name="urlAbstracta" class="span centrar" type ="text" value="${urlAbstracta}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="usuarioAbstracta" class="span2 centrar" type ="text" value="${usuarioAbstracta}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="passwordAbstracta" class="span2 centrar" type ="text" value="${passwordAbstracta}" onkeyup="javascript:repintar();"></input>
   </TD>
 </TR>
 

 <!-- configuracion base de datos bc muebles --> 
<TR>
 <TD>
    <h2 class="centrar">Bc Muebles</h2>
   </TD>
   
   <TD>
     <input name="ojdbcBcMuebles" class="span3 centrar" type ="text" value="${ojdbcBcMuebles}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
      <input name="urlBcMuebles" class="span centrar" type ="text" value="${urlBcMuebles}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="usuarioBcMuebles" class="span2 centrar" type ="text" value="${usuarioBcMuebles}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="passwordBcMuebles" class="span2 centrar" type ="text" value="${passwordBcMuebles}" onkeyup="javascript:repintar();"></input>
   </TD>
 </TR>


 <!-- configuracion base de datos capuy --> 
<TR>
 <TD>
    <h2 class="centrar">Capuy</h2>
   </TD>
   
   <TD>
     <input name="ojdbcCapuy" class="span3 centrar" type ="text" value="${ojdbcCapuy}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
      <input name="urlCapuy" class="span centrar" type ="text" value="${urlCapuy}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="usuarioCapuy" class="span2 centrar" type ="text" value="${usuarioCapuy}" onkeyup="javascript:repintar();"></input>
   </TD>
   
   <TD>
     <input name="passwordCapuy" class="span2 centrar" type ="text" value="${passwordCapuy}" onkeyup="javascript:repintar();"></input>
   </TD>
 </TR>
 </TABLE>
 
 <input id="btn-guardar" class="btn btn-success" style="margin-left:47%" type="submit" name="guardar" value="Guardar">
 
</form>

<form id="configuracion-form" action ="http://localhost:8080/" method="post" >
	</br>
	</br>
	</br>
   <input id="btn-salir" class="btn btn-default" style="margin-left:48%" type="submit" name="salir" value="Salir">
</form>


</body>
</html>