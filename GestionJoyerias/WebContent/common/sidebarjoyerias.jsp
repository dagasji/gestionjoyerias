<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar">
		<table style="text-align: left">
			
			<tr>
				<td><a href="/GestionJoyerias/DatosJoyeria.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Datos</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/DatosJoyeria.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Lista de Reparaciones</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a  href="/GestionJoyerias/DatosJoyeria.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Reparados	</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a  href="/GestionJoyerias/DatosJoyeria.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Pendientes de Facturar</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/ListadoJoyerias.do"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Historico de Facturas</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<c:url value="${param.volver}" var="url" />
				<td><a href="${url}"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Volver</a></td>
			</tr>
		</table>
	</div>

	<h2></h2>
	
