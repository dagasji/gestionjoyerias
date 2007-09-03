<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Reparación</title>
</head>
<body>
	
	
<div id="container">
	<div id="header">
	<h1>Gestion de Talleres</h1>
	<h2>Listado Joyerias</h2>
	</div><!-- header -->
	
	<div id="main">
	<form action= "nuevaRepacion.do" name="formulario">
		<input type="hidden" name="codigo" value="111">
		<table>
						
			<tr> 
				<td>Código</td>
				<td><input type="text" name="codSobre" ></td>
			</tr>
			<tr> 
				<td>Artículo</td>
				<td><input type="text" name="descripcion"></td>
			</tr>
			<tr> 
				<td>Descripcion:</td>
				<td><input type="text" name="descripcion"></td>
			</tr>
			<tr> 
				<td>Precio:</td>
				<td><input type="text" name="descripcion"></td>
			</tr>
			<tr> 
				<td>Fecha Entrada:</td>
				<td><input type="text" name="descripcion"></td>
			</tr>
			<tr>
				<td>Entregado</td>
				<td><input type="checkbox" name="entregado"></td>
			</tr>
			<tr>
				<td>Facturado</td>
				<td><input type="checkbox" name="facturado"></td>
			</tr>
				
			
			<tr> 
				<td></td>
				<td><input type="submit" name="codSobre"  value="Aceptar"></td>
			</tr>	
			
		</table>
		
	</form>
	</div>
</div>
</body>
</html>