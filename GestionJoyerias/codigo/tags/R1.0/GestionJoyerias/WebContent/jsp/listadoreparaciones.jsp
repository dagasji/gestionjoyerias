<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		
		<table  class="normal" style="text-align: left">
			
			<tr>
				<td>Nombre:</td>
				<td>${joyeria.nombreJoyeria}</td>
			</tr>
		</table>
		
		
		<c:if test="${listadoReparaciones != null}">
			
		<table class= "normal" border="0" cellspacing="5">	
			<tr>			
				<td><b>C&oacute;d. sobre</b></td>
				<td><b>Art&iacute;culo</b></td>
				<td><center><b>Nombre Cliente</b></center></td>
				<td><b>Fecha de Entrada</b></td>
				<td><b>Fecha de Salida</b></td>
				
			</tr>
			<c:forEach items="${listadoReparaciones}" var="reparacion">
		 	  <tr>	
				<td><a href="/GestionJoyerias/DetallesReparacion.do?codReparacion=${reparacion.codReparacion}"> ${reparacion.codSobre}</a></td>
				<td> ${reparacion.articulo}</td>
				<td style="text-align:left"> ${reparacion.nombreCliente}</td>
				<td> <fmt:formatDate value="${reparacion.fechaEntrada}" type="date" pattern="dd/MM/yyyy" /></td>
				<td> <fmt:formatDate value="${reparacion.fechaSalida}" type="date" pattern="dd/MM/yyyy" /></td>
				<td><td><a href="/GestionJoyerias/EliminarReparacion.do?codReparacion=${reparacion.codReparacion}"><img src="/GestionJoyerias/imagenes/btn_eliminar.JPG" alt="" border="0"/></a></td>				
			  </tr>	
	 		
			</c:forEach>
			
		</table>	
		</c:if>
		<c:if test="${listadoReparaciones == null}">
			<center><h3>No se han encontrado reparaciones</h3></center>
		</c:if>
		
		
	</div>
	<c:import url="/common/footer.jsp" />
		
	</div>
	
</body>

</html>