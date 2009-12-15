<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<html>
<head>
<link href="/GestionJoyerias/css/estilo.css" rel="stylesheet" type="text/css" />
<SCRIPT LANGUAGE="JavaScript">
<!--
function volver(){
	history.go(-1);
}
// -->
</script>

<title>Gesti&oacute;n de Talleres</title>
</head>
<body>

<div id="container">
	<div id="header">
		<h1>Gesti&oacute;n de Talleres</h1>
		<h2>Error</h2>
	</div><!-- header -->
	<div id="main" style="margin-left: 0px">
		<center>
			<h3>${error}</h3>
			<A HREF="javascript:volver()">Volver</A>
		</center>	
	
	</div>
	
	<c:import url="/common/footer.jsp" />
</div>

<html:errors/>

</body>
</html>