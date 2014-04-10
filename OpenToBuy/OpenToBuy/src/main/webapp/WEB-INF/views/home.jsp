<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/estiloOTB.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/navbar.css" /> 
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
            <a id="header" class="navbar-brand">Open To Buy</a>
          </div>
    </div><!--/.container-fluid -->
  </div>
</div>


<div class="centro">


<div class="row">
   <c:forEach items="${roles}" var="rol">
        <div class="col-lg-4">
        
        <c:if test="${rol.empresa == 'beco'}">
		          <img src="/resources/image/beco.png">
		        </c:if>
		        
		        <c:if test="${rol.empresa == 'amand'}">
		          <img src="/resources/image/amand.png">
		        </c:if>
		        
		        <c:if test="${rol.empresa == 'capuy'}">
		          <img src="/resources/image/capuy.png">
		        </c:if>
		        
		        <c:if test="${rol.empresa == 'abstracta'}">
		          <img src="/resources/image/abstracta.png">
		        </c:if>
		        
		        <c:if test="${rol.empresa == 'bcmuebles'}">
		          <img src="/resources/image/bcmuebles.png">
		        </c:if>
          
           <form id="regresar-libreta-form" action ="http://localhost:8080/presupuestocomercial.html" method="post" >
		       <input type="hidden" name="empresa" value="${rol.empresa}">
		       <input type="hidden" name="usuario" value="${usuario}">
		       <input class="btn btn-primary" type="submit" value="Ingresar">
		    </form> 
        </div>
   </c:forEach>
 </div>
</div>
</body>
</html>
