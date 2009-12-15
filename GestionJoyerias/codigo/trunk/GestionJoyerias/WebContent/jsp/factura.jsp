<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
    pageEncoding="ISO-8859-15"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />

  <!-- Libreria con javascript generales -->
  <script type="text/javascript" src="./js/general.js"></script>
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
		<form name="formulario" action="/GestionJoyerias/Facturar.do" method="post">		
			<table class= "normal" border="0">					
				<tr>			
					<td><b>C&oacute;digo de sobre</b></td>
					<td><b>Art&iacute;culo</b></td>					
					<td><b>Fecha de Entrada</b></td>
					<td><b>Fecha de Salida</b></td>
					<td><center><b>Precio</b></center></td>
					
				</tr>
				<c:forEach items="${listadoReparaciones}" var="reparacion">
			 	  <tr>
			 	  	<td><a href="/GestionJoyerias/DetallesReparacion.do?codReparacion=${reparacion.codReparacion}"> ${reparacion.codSobre}</a></td>
					<td> ${reparacion.articulo}</td>				
					<td> <fmt:formatDate value="${reparacion.fechaEntrada}" type="date" pattern="dd/MM/yyyy" /></td>
					<td> <fmt:formatDate value="${reparacion.fechaSalida}" type="date" pattern="dd/MM/yyyy" /></td>
					<td style="text-align:rigth"> ${reparacion.precio} ¤</td>
					<td><input type="hidden" name="listado" value="${reparacion.codReparacion}"/></td>	
					<c:set var="total" value='${total + reparacion.precio}'/>
				  </tr>			
				</c:forEach>				
	
				<tr>
					<td colspan="5"><hr></td>
				</tr>			
				
				<tr>	
					<td></td>
					<td></td>
					<td></td>
					<td><b>Total</b></td>
					<td style="text-align:rigth"> ${total} ¤</td>
				</tr>	
				
				<tr>						
				</tr>
				
				<c:if test="${factura == null}">
					<tr style="text-align:left">
						<td colspan=2 >Nombre de Cliente:</td>
						<td colspan=2>
							<input type="text" 
							       name="<%=es.aguamarina.gestionjoyeria.config.Constants.PARAMETER_NOMBRE_CLIENTE %>" 
								   value="${factura.nombreCliente}">
						</td>
					</tr>
				</c:if>
				<tr>
				</tr>			
				
				<tr>
					<td>
						<input type="hidden" name="confirmar" value="ok">
					</td>

					<c:if test="${factura == null}">					
						<td colspan="3" style="text-align:left"><input type="submit" class="boton" value="Aceptar"></td>
					</c:if>
					<c:if test="${factura != null}">					
						<td colspan="3"style="text-align:left"><input type="button" class="boton" value="Imprimir Factura" onclick="javascript:printFactura(${factura.codFactura})"></td>
					</c:if>
				</tr>

			</table>	
		</form>
		
		
		
		</c:if>
		<c:if test="${listadoReparaciones == null}">
			<center><h3>No se han encontrado reparaciones</h3></center>
		</c:if>
		
		
	</div>
	<c:import url="/common/footer.jsp" />
		
	</div>
	
</body>

</html>