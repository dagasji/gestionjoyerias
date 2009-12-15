<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="es.aguamarina.gestionjoyeria.config.Constants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<title>Nueva Joyer&iacute;a</title>
<script type="text/javascript">

function validar(){
	if (document.formulario.nombre.value == '' || document.formulario.nombre.value.length < 1){
		alert('Debe indicar un nombre para la joyeria');
		return false;
	}
	if (document.formulario.telefono.value.length > 9){
		alert('El tamaño máximos del teléfono es de 9 digitos.');
		return false;			
	}	
}

</script>

</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Gesti&oacute;n de Talleres</h1>
			<h2><%= (request.getAttribute(Constants.PARAMETER_METODO) != null 
					&& request.getAttribute(Constants.PARAMETER_METODO).equals(Constants.PARAMETER_NUEVA))  
					?  "Nueva Joyería" : "Modificar Joyería" %></h2>
		</div><!-- header -->
	
	
	<c:if test="${metodo != 'nueva'}">
	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/DetallesJoyeria.do?codigo=${joyeria.codJoyeria}"/>
	</c:import> 
	</c:if>
	

	<div id="main">
	<c:url value="/GuardarJoyeria.do" var="URLGuardarJoyeria"/>
	<form action="${URLGuardarJoyeria}" method="post" name="formulario" onSubmit="return validar()">
		<input type="hidden" name="metodo" value="${metodo}">
		<table>
			<tr>
				<td>C&oacute;digo Joyer&iacute;a:</td>
				<td><input type="text" name="codigo" value = "${joyeria.codJoyeria}" readonly/></td>
			</tr>	
			<tr>
				<td>Nombre Joyer&iacute;a:</td>
				<td><input type="text" name="nombre" value= "${joyeria.nombreJoyeria}"></td>
			</tr>
			<tr>
				<td>Tel&eacute;fono:</td>
				<td><input type="text" name="telefono" value= "${joyeria.telefono}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="boton" value="Guardar" style="background:#638cb5; border:1px;font-weight:bold;color:white;">
				<c:if test="${metodo == 'nueva'}">
					<input type="button" name="boton" value="Volver"  style="background:#638cb5; border:1px;font-weight:bold;color:white;" onclick ="javascript:history.back(1)">
				</c:if>				
				</td>
			</tr>
		</table>
	</form>
	<%= (request.getAttribute(Constants.PARAMETER_METODO)).equals(Constants.PARAMETER_MODIFICADO_OK)  
		?  "Los datos de la joyer&iacute;a se guardaron correctamente." : ""%>
	
	</div>
	<c:import url="/common/footer.jsp" />
	
	</div>
	
</body>

</html>