<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.aguamarina.gestionjoyeria.structura.Joyeria"
		 import="java.util.ArrayList"
		 import="java.util.Iterator"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Joyerías</title>
</head>
<body>
	<div id="container">
		<div id="header">
		<h1>Gestion de Talleres</h1>
		<h2>Listado Joyerias</h2>
		</div><!-- header -->
	
	<div id="sidebar">
		<a href="/GestionJoyerias/NuevaJoyeria.do">Nueva Joyeria</a>
	
	</div>
		
	
	<% ArrayList<Joyeria> listado = (ArrayList)request.getAttribute("listadoJoyerias");
	   	Joyeria joyeria;
		Iterator<Joyeria> it = listado.iterator();
	
	%>
	<div id="main">
	<table>
		<tr>
			
			<th><b><u>Código</u></b></th>
			<th>&nbsp;&nbsp;&nbsp;</th>
			<th><b><u>Nombre Joyería</u></b></th>
			<th>&nbsp;&nbsp;&nbsp;</th>
			<th><b><u>Teléfono</u></b></th>
			

		</tr>	
	<%while(it.hasNext()){
		joyeria = it.next();%>	
		<tr>
			<c:url value="/DetallesMostrar.do" var="URLdetalles"/>
				
			<td><a href="/GestionJoyerias/DetallesJoyeria.do?codigo=<%=joyeria.getCodJoyeria()%>"><%=joyeria.getCodJoyeria()%></a></td>
						<td></td>
			<td><%=joyeria.getNombreJoyeria()%></td>
						<td></td>
			<td><%=joyeria.getTelefono()%></td>
		</tr>
	
	<%} %>
	</table>
	
	<br>
	<br>
	</div>
	<div id="footer">
		Joyeria Relojería Aguamarina
	</div>
	</div>
	
</body>
</html>