<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="es.aguamarina.gestionjoyeria.config.Constants" %>
<div id="sidebar">
		<table style="text-align: left">

			<tr>
				<td><a href="/GestionJoyerias/DatosJoyeria.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Datos de la Joyer&iacute;a</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/NuevaReparacion.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Nueva Reparaci&oacute;n</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/BuscarReparacion.do?codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Buscar</a></td>
			</tr>
			
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/DetallesJoyeria.do?estado=<%=Constants.ESTADO_SINREPARAR %>&codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Sin Reparar</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a  href="/GestionJoyerias/DetallesJoyeria.do?estado=<%=Constants.ESTADO_PRESUPUESTO %>&codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Presupuestos</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a  href="/GestionJoyerias/DetallesJoyeria.do?estado=<%=Constants.ESTADO_SINENTREGAR %>&codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Reparados</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a  href="/GestionJoyerias/DetallesJoyeria.do?estado=<%=Constants.ESTADO_SINFACTURAR %>&codigo=${joyeria.codJoyeria }"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Pendientes de Facturar</a></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><a href="/GestionJoyerias/ListaFacturas.do?codigo=${joyeria.codJoyeria}"><img src="/GestionJoyerias/imagenes/enlace.GIF" alt="" border="0"/>Historico de Facturas</a></td>
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
	
