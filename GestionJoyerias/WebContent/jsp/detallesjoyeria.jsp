<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<title>Nueva Joyería</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Gestion de Talleres</h1>
			<h2>Detalles de Joyería</h2>
		</div><!-- header -->

	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/ListadoJoyerias.do"/>
	</c:import> 
	
	
	
	
	<div id="main">
		
		<table  class="normal" style="text-align: left">
			
			<tr>
				<td>Nombre:</td>
				<td>${joyeria.nombreJoyeria}</td>
			</tr>
		</table>
		
			
		<table class= "normal" border="0">	
			<tr>			
				<td><b>Código de sobre</b></td>
				<td><b>Artículo</b></td>
				<td><b>Nombre Cliente</b></td>
				<td><b>Fecha de Entrada</b></td>
				<td><b>Fecha de Salida</b></td>
				
			</tr>
			<c:forEach items="${listadoReparaciones}" var="reparacion">
		 	  <tr>	
				<td> ${reparacion.codSobre}</td>
				<td> ${reparacion.articulo}</td>
				<td> ${reparacion.nombreCliente}</td>
				<td> ${reparacion.fechaEntrada}</td>
				<td> ${reparacion.fechaSalida}</td>
			  </tr>	
	 		
			</c:forEach>
			
		</table>	
	
	</div>
	<c:import url="/common/footer.jsp" />
		
	</div>
	
</body>

</html>