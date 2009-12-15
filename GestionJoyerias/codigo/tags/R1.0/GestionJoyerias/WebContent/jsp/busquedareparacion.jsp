<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<title>Nueva Joyer&iacute;a</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Gesti&oacute;n de Talleres</h1>
			<h2>Detalles de Joyer&iacute;a</h2>
		</div><!-- header -->

	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/ListadoJoyerias.do"/>
	</c:import> 
	
	
	
	<div id="main">
		<c:url value="/ResultadoBusqueda.do" var="URL"/>
		<form action= "${URL}" name="formulario">
			<table>
				<tr>
					<td><input type="hidden" name="codJoyeria" value="${joyeria.codJoyeria}"></td>
				</tr>
				<tr>
					<td>C&oacute;digo Sobre</td>
					<td><input type="text" name="codSobre"></td>
				</tr>
				<tr>
					<td>Descripci&oacute;n Reparaci&oacute;n</td>
					<td><input type="text" name="descripcion"></td>
				</tr>
				<tr>
					<td>Art&iacute;culo</td>
					<td><input type="text" name="articulo"></td>
				</tr>
				<tr>
					<td>Nombre Cliente</td>
					<td><input type="text" name="nombreCliente"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Buscar" class="boton"></td>
				</tr>
			</table>
		</form>
		
		
	</div>
	<c:import url="/common/footer.jsp" />
		
	</div>
	
</body>

</html>