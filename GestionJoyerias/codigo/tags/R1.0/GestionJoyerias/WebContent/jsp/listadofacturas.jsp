<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
    pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<title>Hist&oacute;rico de Facturas</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Gesti&oacute;n de Talleres</h1>
			<h2>Hist&oacute;rico de Facturas</h2>
		</div><!-- header -->

	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/ListadoJoyerias.do"/>
	</c:import> 

	
	
	
	<div id="main">
		
		<c:if test="${listado != null}">
		<table class= "normal" border="0">	
			
			<tr>			
				<td><b>Cod. Factura</b></td>
				<td><b>Fecha Factura</b></td>
				<td><center><b>Nombre Cliente</b></center></td>
				<td><b>Total</b></td>
				
			</tr>
			<c:forEach items="${listado}" var="factura">
		 	  <tr>
				<td><a href="/GestionJoyerias/ShowFactura.do?COD_FACTURA=${factura.codFactura}"> ${factura.codFactura}</a></td>
				<td> <fmt:formatDate value="${factura.fechaFactura}" type="date" pattern="dd/MM/yyyy" /></td>
				<td> ${factura.nombreCliente}</td>
				<td style="text-align:rigth"> ${factura.totalFactura} ¤</td>		
			  </tr>	
	 		
			</c:forEach>			
			
			
		</table>	
		
		</c:if>
		<c:if test="${listado == null}">
			<center><h3>No se han encontrado facturas</h3></center>
		</c:if>
		
		
	</div>
	<c:import url="/common/footer.jsp" />
		
	</div>
</body>
</html>