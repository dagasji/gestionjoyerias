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
			<h2><%= (session.getAttribute("metodo")).equals("nueva")  ?  "Nueva Joyería" : "Modificar Joyería" %></h2>
		</div><!-- header -->
	
	
	<c:if test="${metodo != 'nueva'}">
	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/DetallesJoyeria.do?codigo=${joyeria.codJoyeria}"/>
	</c:import> 
	</c:if>
	

	<div id="main">
	<c:url value="/GuardarJoyeria.do" var="URLGuardarJoyeria"/>
	<form action="${URLGuardarJoyeria}" method="post">
		<table>
			<tr>
				<td>Código Joyería:</td>
				<td><input type="text" name="codigo" value = "${joyeria.codJoyeria}" readonly/></td>
			</tr>	
			<tr>
				<td>Nombre Joyería:</td>
				<td><input type="text" name="nombre" value= "${joyeria.nombreJoyeria}"></td>
			</tr>
			<tr>
				<td>Teléfono:</td>
				<td><input type="text" name="telefono" value= "${joyeria.telefono}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="boton" value="Guardar" style="background:#638cb5; border:1px;font-weight:bold;color:white;">
				<c:if test="${metodo == 'nueva'}">
					<input type="submit" name="boton" value="Volver" style="background:#638cb5; border:1px;font-weight:bold;color:white;">
				</c:if>
				
				</td>
			</tr>
		</table>
	</form>
	<%= (session.getAttribute("metodo")).equals("modificado")  ?  "Los datos de la joyería se guardaron correctamente." : ""%>
	
	</div>
	<c:import url="/common/footer.jsp" />
	
	</div>
	
</body>

</html>