<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
    pageEncoding="ISO-8859-15"%>
<%@ page import="es.aguamarina.gestionjoyeria.dto.Reparacion" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="es.aguamarina.gestionjoyeria.config.Constants"%>
<html>
<head>
  <!-- Libreria con javascript generales -->
  <script type="text/javascript" src="./js/general.js"></script>
  
  <!-- Hoja de estilos del calendario -->
  <link rel="stylesheet" type="text/css" media="all" href="./js/calendar-tas.css" title="win2k-cold-1" />

  <!-- librería principal del calendario -->
 <script type="text/javascript" src="./js/calendar.js"></script>

 <!-- librería para cargar el lenguaje deseado -->
  <script type="text/javascript" src="./js/lang/calendar-es.js"></script>

  <!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
  <script type="text/javascript" src="./js/calendar-setup.js"></script> 


<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Reparaci&oacute;</title>


<script type="text/javascript">

function validarPrecio(){
	
	document.formulario.preciovisualizar.value = round(document.formulario.preciovisualizar.value, 2);
	document.formulario.precio.value = document.formulario.preciovisualizar.value 
	
   	my_str = new String( document.formulario.preciovisualizar.value );
	i=my_str.split(".", 2);
	if(i[1]==undefined){
	   my_str =  + my_str + '.00' + ' ¤';
	}else{
	   if(i[1].length==1){
         my_str = my_str + "0" + ' ¤';
      }else{
		my_str = my_str + ' ¤';
      }      
   }
   document.formulario.preciovisualizar.value = my_str;
   
   
}

function modificarPrecio(){

	document.formulario.preciovisualizar.value = document.formulario.precio.value;

}

function validarCosto(){
	
	document.formulario.costovisualizar.value = round(document.formulario.costovisualizar.value, 2);
	document.formulario.costo.value = document.formulario.costovisualizar.value 
	
   	my_str = new String( document.formulario.costovisualizar.value );
	i=my_str.split(".", 2);
	if(i[1]==undefined){
	   my_str =  + my_str + '.00' + ' ¤';
	}else{
	   if(i[1].length==1){
         my_str = my_str + "0" + ' ¤';
      }else{
		my_str = my_str + ' ¤';
      }      
   }
   document.formulario.costovisualizar.value = my_str;
   
   
}

function modificarCosto(){

	document.formulario.costovisualizar.value = document.formulario.costo.value;

}

function validarSiNumero(object)
{
	numero = object.value;
	if (!/^([0-9])*$/.test(numero))
	object.value = numero.substring(0,numero.length-1);
}

function round(number,X) {
	X = (!X ? 2 : X);
	return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
} 



function comprobarChecks(){
	
	if (document.formulario.presupuesto.checked){
		document.formulario.reparado.checked =false;
		document.formulario.reparado.disabled =true;
	}else{
		document.formulario.reparado.disabled =false;
	}
	if (!document.formulario.reparado.checked){
		document.formulario.entregado.disabled = true;
		document.formulario.entregado.checked = false;		
	}else{
		document.formulario.entregado.disabled = false;
	}
	
	if (!document.formulario.entregado.checked){
		document.formulario.facturado.disabled = true;
		document.formulario.facturado.checked = false;		
		document.formulario.fechaSalida.value = "";
		document.formulario.fechaSalida.disabled = true;
		document.formulario.fechaSalidaLanzador.disabled =true;
	}else{
		document.formulario.facturado.disabled = false;
		document.formulario.fechaSalida.disabled = false;
		document.formulario.fechaSalidaLanzador.disabled =false;
		//Establecemos la fecha actual por defecto
		if(document.formulario.fechaSalida.value == ''){
			document.formulario.fechaSalida.value = '<fmt:formatDate value="${now}" type="date" pattern="dd/MM/yyyy" />';
		}
		
	}
	
	
	if (!document.formulario.facturado.checked){
		document.formulario.fechaFactura.value = "";
		document.formulario.fechaFactura.disabled = true;
		//document.formulario.fechaFacturaLanzador.disabled =true;
	}else{
		document.formulario.fechaFactura.disabled = false;
		//document.formulario.fechaFacturaLanzador.disabled =false;
	}
	
	
}


function validar(){
	if (document.formulario.codSobre.value == ''){
		alert ('Debe indicar un código de sobre.');
		document.formulario.codSobre.focus();
		return false;
	}
	if (document.formulario.nombreCliente.value == ''){
		alert ('Debe indicar un nombre de cliente.');
		document.formulario.nombreCliente.focus();
		return false;
	}
	if (document.formulario.articulo.value == ''){
		alert ('Debe indicar un artículo.');
		document.formulario.articulo.focus();
		return false;
	}
		
	return true;
	
}

</script>

</head>
<body>
	
<div id="container">
	<div id="header">
	<h1>Gestion de Talleres</h1>
	<h2><%= (request.getAttribute("metodo")).equals("nueva")  ?  "Nueva Reparación" : "Modificar Reparación" %></h2>
	</div><!-- header -->
	<c:import url="/common/sidebarjoyerias.jsp">
    	 <c:param name="volver" value= "/DetallesJoyeria.do?codigo=${joyeria.codJoyeria}"/>
	</c:import> 

	<div id="main">
	<c:url value="/GuardarReparacion.do" var="URLGuardarReparacion"/>
	<form action= "${URLGuardarReparacion}" name="formulario" onSubmit="return validar()">
		<table>
			<tr> 
				<td><input type="hidden" name="<%=Constants.PARAMETER_CODIGO_JOYERIA %>" value="${joyeria.codJoyeria}"></td>
				<td><input type="hidden" name="<%=Constants.PARAMETER_METODO %>" value="${metodo}"></td>
			</tr>
			<tr> 
				<td>C&oacute;digo</td>
				<td><input type="text" name="codReparacion" value="${reparacion.codReparacion}" readonly  style="width:60px;text-align:right"></td>
			</tr>
			<tr> 
				<td>C&oacute;digo Sobre</td>
				<td><input type="text" name="codSobre" value="${reparacion.codSobre}" style="width:60px;text-align:right"></td>
			</tr>
			<tr> 
				<td>Nombre del Cliente</td>
				<td><input type="text" name="nombreCliente" value="${reparacion.nombreCliente}" style="width:240px"></td>
			</tr>
			<tr> 
				<td>Art&iacute;culo</td>
				<td><input type="text" name="articulo" value="${reparacion.articulo}" style="width:240px"></td>
			</tr>
			<tr> 
				<td>Descripci&oacute;n:</td>
				<td><textarea name="descripcion" rows=3 cols=28>${reparacion.reparacion}</textarea></td>
			</tr>
			<tr> 
				<td>Costo:</td>
				<td><input type="text" name="costovisualizar"  value="${reparacion.costo}"  onfocus="javascript:modificarCosto()" onchange="javascript:validarCosto()" style="width:60px;text-align:right"></td>
				<td><input type="hidden" name="costo" value="${reparacion.costo}"></td>
			</tr>
			<tr> 
				<td>Precio:</td>
				<td><input type="text" name="preciovisualizar"  value="${reparacion.precio}"  onfocus="javascript:modificarPrecio()" onchange="javascript:validarPrecio()" style="width:60px;text-align:right"></td>
				<td><input type="hidden" name="precio" value="${reparacion.precio}"></td>
			</tr>
			<tr> 
				<td>Tipo reparaci&oacute;n</td>
				<td>
					<select name="tipoReparacion">
						<option value="1" <c:if test="${reparacion.tipoReparacion == 1}">selected="selected"</c:if>>Compostura Relojeria</option>
						<option value="2" <c:if test="${reparacion.tipoReparacion == 2}">selected="selected"</c:if>>Compostura Joyer&iacute;a</option>						
					</select>
				</td>
			</tr>
			<tr> 
				<td>Fecha Entrada:</td>
				<td>
					<input type="text" value="<fmt:formatDate value="${reparacion.fechaEntrada}" type="date" pattern="dd/MM/yyyy" />" name="fechaEntrada" id="fechaEntrada">
					<input type="button" id="fechaEntradaLanzador" value="..." />
				</td>

			</tr>

			<c:if test="${metodo != 'nueva'}">
			<tr>
				<td>Presupuesto</td>
				<td><input type="checkbox" name="presupuesto" <c:if test="${reparacion.presupuesto}">checked</c:if> onclick="javascript:comprobarChecks()"></td>
			</tr>				
			<tr>
				<td>Reparado</td>
				<td><input type="checkbox" name="reparado" <c:if test="${reparacion.reparado}">checked</c:if> onclick="javascript:comprobarChecks()"></td>
			</tr>				
			<tr>
				<td>Entregado</td>
				<td><input type="checkbox" name="entregado" <c:if test="${reparacion.entregado}">checked</c:if> onclick="javascript:comprobarChecks()"></td>
			</tr>
			<tr> 
				<td>Fecha Salida:</td>
				<td><input type="text" id="fechaSalida" name="fechaSalida" value="<fmt:formatDate value="${reparacion.fechaSalida}" type="date" pattern="dd/MM/yyyy" />">
				<input type="button" id="fechaSalidaLanzador" value="..." />
				</td>
			</tr>
			<tr>
				<td>Facturado</td>
				<td><input type="checkbox" name="facturado" <c:if test="${reparacion.facturado}">checked</c:if> onclick="javascript:comprobarChecks()"></td>
				
			</tr>
			<c:if test="${reparacion.facturado}">
				<tr> 
					<td>Fecha Factura:</td>
					<td>
						<input type="text" name="fechaFactura" id="fechaFactura" value="<fmt:formatDate value="${reparacion.factura.fechaFactura}" type="date" pattern="dd/MM/yyyy"/>" readonly>
						<!-- <input type="button" id="fechaFacturaLanzador" value="..." /> -->
					</td>
				</tr>
			</c:if>
			 
			<input type="hidden" name="codFactura" value="${reparacion.factura.codFactura}">
			
			<script type="text/javascript">
			Calendar.setup({
		    	inputField     :    "fechaSalida",     // id del campo de texto
		    	ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
		    	button     :    "fechaSalidaLanzador"     // el id del botón que lanzará el calendario
			});
			
						
			/*
			Calendar.setup({
		    	inputField     :    "fechaFactura",     // id del campo de texto
		     	ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
		     	button     :    "fechaFacturaLanzador"     // el id del botón que lanzará el calendario
			});			
			*/
			
			</script>
			</c:if>
			
			
			<tr> 
				<td></td>
				<td>
				<input type="submit" name="boton" class="boton" value="Aceptar">
				<c:if test="${reparacion.facturado == true}">
				<input type="button" name="imprimir" class="boton" value="Imprimir Factura" onclick="printFactura(${reparacion.factura.codFactura})">
				</c:if>
				</td>
			</tr>	
		</table>
		<%= (request.getAttribute("metodo") != null && request.getAttribute("metodo").equals("modificado"))  ?  "Los datos de la reparación se guardaron correctamente." : ""%>
		
	</form>
	</div>

	<c:import url="/common/footer.jsp" />
	<c:if test="${metodo != 'nueva'}">
		<script type="text/javascript">comprobarChecks();</script>
	</c:if>
</div>

<script type="text/javascript">
Calendar.setup({
    inputField     :    "fechaEntrada",     // id del campo de texto
    ifFormat     :     "%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto
	button     :    "fechaEntradaLanzador"     // el id del botón que lanzará el calendario
});

</script>

</body>
</html>