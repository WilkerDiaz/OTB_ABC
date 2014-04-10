<html>

<head>

    <title>Intranet Colaborativa</title>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta name="keywords" content="Dinámica, frameworks, J2EE, JEE, Java, Servlets, web, sql, Eclipse, plugins" />
	<meta name="description" content="Aplicación web construida con el framework Dinámica" />	
    <link rel="stylesheet" type="text/css" href="/resources/css/default.css" /> 

	<!--biblioteca generica JS/Dinamica-->
	<script type="text/javascript" src="action/script">
	</script>

	<script language="javascript">
	
		if (top.frames.length!=0) {
		    if (window.location.href.replace)
		        top.location.replace(self.location.href);
		    else
		        top.location.href=self.document.href;
		}
		
		onload = function()
		{ 
			setFocusOnForm("form1");
			displayLogin();
		} 
		
		//ejecutar login
		function login()
		{
			clearErrorMessages();
			document.getElementById("loginbutton").disabled=true;
			
			//llamada Ajax...
			return ajaxCall(	httpMethod="POST", 
								uri="/action/security/login", 
								divResponse="form_error", 
								divProgress="status", 
								formName="form1", 
								afterResponseFn=null, 
								onErrorFn=retryAddnewOrEdit);
	
		}
		
		//restaurar el formulario en caso de error
		function retryAddnewOrEdit() {
			document.getElementById("loginbutton").disabled=false;
			setFocusOnForm("form1");		
		}
		
		//mostrar div del login
		function displayLogin()
		{
			document.getElementById("form_login").style.display="";
			document.getElementById("form_forgotpass").style.display="none";
			if (document.getElementById("response")!=null)
				document.getElementById("response").style.display="none";	
			setFocusOnForm("form1");
		}
		
		//mostrar div del forgot pass
		function displayForgotPass()
		{
			document.getElementById("form_login").style.display="none";
			document.getElementById("form_forgotpass").style.display="";
			if (document.getElementById("form")!=null)
				document.getElementById("form").style.display="";
			clearForm("form2");
			displayForm();
		}
		
		//registrar forgot pass
		function insert()
		{		

			document.getElementById("grabar").disabled=true;
			clearErrorMessages();
				
			//llamada Ajax...
			return ajaxCall(httpMethod="POST", 
								uri="/action/security/forgotpass/generate", 
								divResponse="response", 
								divProgress="status2", 
								formName="form2", 
								afterResponseFn=hideForm, 
								onErrorFn=displayForm);	
		}

		function hideForm() {
			document.getElementById("form").style.display="none";
		}

		//muestra el formulario principal para editar
		function displayForm()
		{
			document.getElementById("grabar").disabled=false;
			document.getElementById("form").style.display="";
			document.getElementById("response").style.display="none";
			setFocusOnForm("form2");
		}
		
		//mostrar imagen y esconder su indicador de progreso de carga
		function displayCaptcha(captchaID)
		{
			document.getElementById(captchaID + "_wait").style.display='none';
			document.getElementById(captchaID).style.display='';
		}	

		function reloadCaptcha(captchaID) {
			document.getElementById(captchaID + "_wait").style.display='';
			document.getElementById(captchaID).src = "action/security/getcaptcha?dummy=" + Math.random();
		}

	</script>
	
	<style>
		body {margin:10px;}
	</style>

</head>

<body>
		
<form id="realizar-busqueda-form" action ="http://localhost:8080/autentificar.html" method="post">
		
<div id="form_login"/>		
	<!--logo-->
	<div style="float:left">
	
		
		<!--titulo-->
		
		<h1 style="margin-top:0px;margin-left:80px">Intranet Colaborativa</h1>
		
		<!--parrafo de presentacion-->
		<p style="width:450px;margin-left:80px" align="justify">
		<br><br>
			
				
		<br><br>
		
		
		<br><br>
		
		<!-- [<a href="javascript:alert('Por implementar')">Términos de uso y privacidad</a>]
		&nbsp;
		[<a href="javascript:alert('Por implementar')">Manual de Usuario</a>] -->
	
		</p>
	
	</div>
	
	<div style="float:left">
	
			<!--FORM-->
			<div style=" float:center; margin-top: 100px; width:200px">
			
			<form name="form1" onsubmit="return login()">
	
			<table class="formgrid" width="95%" align="center">
	
						
				<!--column layout-->
				<col width="40%">
				<col width="60%">
			
				<!--section header-->
				<tr>
					<th colspan="2">
						<span style="float: left">Identificación</span>
						<img id="status" style="float: right; margin-right: 10px; display: none" src="images/progress.gif">
					</th>
				</tr>
	
				<!--separator row-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
				
				<!--form fields-->
				<tr>
					<td  class="formlabel">
						Usuario:
					</td>
					<td>
						<input name="usuario" id="userlogin" type="text" size="20" value="">
					</td>
				</tr>
				<tr>
					<td class="formlabel">
						Contraseña:
					</td>
					<td>
						<input name="passwd" id="passwd" type="password" size="20">
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<div id="form_error" class="errormsg"></div>
					</td>
				</tr>
				
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
		
				<!--form buttons-->
				<tr>
					<td align="center" colspan="2">
						<input id="loginbutton" type="submit" value="Ingresar" class="button">
					</td>
				</tr>
				
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
		
			</table>
			
			<!--URI to navigate after sucessful login-->
			<input type="hidden" name="uri" value="/action/security/home">
			
			</form>
			
			</div>
			
			<!--END FORM-->
	
			<br>
	
			<!--<div style="width:300px">
			
				<table align="center" width="95%" class="formgrid">
					
					<tr>
						<td valign="top" width="60px">
							<img src="images/captcha.gif">
						</td>
						<td valign="top" style="padding-right:3px">
						<b>¿Olvidaste tu contraseña?</b><br>
						Haz click <a href="javascript:displayForgotPass()">AQUI</a> 
						para generar una nueva contraseña.
						</td>
					</tr>
					
				</table>
			
			</div>-->
	
	</div>

</div>

<!--response-->
<div id="form_forgotpass" style="display: none;">

	<div style="float:left">
	
		<div style="height:350px;float:left">
			<img src="images/newpassword.gif">
		</div>		
		
		<h1>Olvidé mi contraseña</h1>
		
		<p style="width:400px" align="justify">
			Le será enviada por email una nueva contraseña, a la dirección
			que indicó cuando se registró. Por favor ingrese los datos
			que se le solicitan para confirmar su identidad.
		</p>
		
	</div>
	
	<div style="float:right">
		
		<!--formulario-->
		<div id="form">		
		
		<form name="form2" onsubmit="return insert()">
		
			<div style="width:400px">
			<table class="formgrid" width="100%">
			
				<!--ancho de la columna de etiquetas-->
				<col width="40%">
				<col width="60%">
			
				<!--titulo-->
				<tr>
					<th colspan="2">
						<span style="float: left" id="formTitle">Datos mínimos para idenficación positiva</span>
						<img id="status2" style="float: right; margin-right: 10px; display: none" src="images/progress.gif">
					</th>
				</tr>
					
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
			
				<!--campos-->
				<tr>
					<td class="formlabel">
						Usuario
					</td>
					<td><input id="userlogin2" name="usuario" type="text" size="25" maxlength="25" ></td>
				</tr>
				<tr>
					<td class="formlabel">
						E-mail
					</td>
					<td><input id="email" name="email" type="text" size="30" maxlength="80" ></td>
				</tr>
			
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>			
			
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>		
			
				<!--seccion del captcha-->
				<tr>
					<td class="formlabel" valign="top">
					</td>
					<td align="left"  valign="top">
						<!--indicador de progreso de carga del captcha-->
						[<a href="javascript:reloadCaptcha('captchaimg')">Refrescar imagen</a>]
						<img id="captchaimg_wait" src="images/progress.gif">
						<img 
							id="captchaimg" 
							style="display:none" 
							src="action/security/getcaptcha" 
							border="1" 
							onload="displayCaptcha('captchaimg')">
						<br>
					</td>
				</tr>
				
				<tr>
					<td class="formlabel" valign="top">
						Protección Anti-Spam
					</td>
					<td>
						<input id="captcha" name="captcha" type="text" value="" maxlength="6" style="width:150px"><br>
						Favor tipear los caracteres que aparecen en la imagen de arriba.
					</td>
				</tr>
				
								
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
			
				<!--botones-->
				<tr>
					<td colspan="2" align="center">
						<input id="grabar" type="submit" value="Grabar" class="button">
						<input id="cancelar" type="button" value="Cancelar" onclick="displayLogin()" class="button">				
					</td>
				</tr>
				
				<!--separador-->
				<tr height="10px">
					<td colspan="2"></td>
				</tr>
		
			</table>
			</div>
		
		</form>
		</div>
	
		<!--panel de respuesta-->
		<div id="response" style="display:none;width:400px">
		</div>
		
		<!--indicador de progreso-->
		<br>
		<div id="status" class="alert" style="text-align:center; display:none;color:gray;width:400px;font-weight:bold;font-family:Arial">
			<img src="images/progress.gif"> Por favor espere...
		</div>
	
	</div>

</div>

<!--marca especial para que el codigo Ajax pueda reconocer un redirect a login-->
<span style="display:none">_ajax_VE8374yz_</span>
</form>
</body>
<div style=" position: absolute; height: 200px; right:0px; top: 100%; margin-top:-100px; display: block;  float:right ">
<table width="80%">

			<tr align=right>
			<td>
			</td>
		<td>
		<img src="images/logos-completos.png">
		</td>
		<!--<td>
		<img src="images/logocapuy.bmp" width= 240>
		</td><td>
		<img src="images/logoboconcept.bmp" width= 240>
		</td>
		<td>
		<img src="images/logoec.bmp" width= 100>
		</td>-->
			</tr>
			</table>
		</div>
</html>
