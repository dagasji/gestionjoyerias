function printFactura(codFactura){
	window.open("/GestionJoyerias/PrintFactura.pdf?COD_FACTURA=" + codFactura,"","location=no,directories=no,status=no,scrollbars=yes,toolbar=no,menubar=no,resizable=yes");
}


function isEmpty(cadena){
	if(cadena.length > 0){
		return false;
	}else{
		return true; 
	}
}