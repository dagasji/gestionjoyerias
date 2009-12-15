package es.aguamarina.gestionjoyeria.config;



public class Constants {
	
	
	//Distintos estados de una reparacion
	public static final String ESTADO_SINREPARAR ="sinreparar";
	public static final String ESTADO_PRESUPUESTO ="presupuesto";
	public static final String ESTADO_SINENTREGAR ="sinentregar";
	public static final String ESTADO_SINFACTURAR ="sinfacturar";
	public static final String ESTADO_FACTURADOS ="facturados";
	
	
	//Parametros para pasar por la request
	public static final String PARAMETER_JOYERIA = "joyeria";
	public static final String PARAMETER_ERROR = "error";
	public static final String PARAMETER_CODIGO_JOYERIA = "codigo";
	public static final String PARAMETER_NOMBRE_JOYERIA = "nombre";
	public static final String PARAMETER_TELEFONO_JOYERIA = "telefono";
	public static final String PARAMETER_ESTADO = "estado";
	public static final String PARAMETER_LISTADO_REPARACIONES = "listadoReparaciones";
	public static final String PARAMETER_NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
	public static final String PARAMETER_FECHA_FACTURA = "FECHA_FACTURA";
	public static final String PARAMETER_COD_FACTURA = "COD_FACTURA";
	public static final String PARAMETER_METODO = "metodo";
	public static final String PARAMETER_MODIFICAR = "modificar";
	public static final String PARAMETER_MODIFICADO_OK = "modificado";
	public static final String PARAMETER_NUEVA = "nueva";
	public static final String PARAMETER_COD_REPARACION = "codReparacion";
	public static final String PARAMETER_REPARACION = "reparacion";
	public static final String PARAMETER_NOW = "now";
	
	
	
	//Mapeos
	public static final String MAPPING_OK = "ok";
	public static final String MAPPING_KO = "ko";
	public static final String MAPPING_SIN_FACTURAR = "ok_sinfacturar";
	public static final String MAPPING_VOLVER = "volver";
	
	
	//Logger
	public static final String LOGGER_GESTION_JOYERIAS = "gestion";
	

	/*En un futuro recargar configuracion desde un xml*/	
	//public static final String RUTA_PLANTILLA_JR = "c:\\tmp\\factura.jrxml";
	//public static final String RUTA_LOGO_FACTURA = "C:\\Documents and Settings\\Dani\\Mis documentos\\Java\\workspace\\GestionJoyerias\\WebContent\\imagenes\\logo_aguamarina.jpg";
	
	//Datos configurables
	public static final String CONFIG_RUTA_PLANTILLA_JR = "RUTA_PLANTILLA_JR";
	public static final String CONFIG_RUTA_LOGO_FACTURA = "LOGO_URL";
	public static final String CONFIG_BBDD_URL = "BBDD_URL";
	public static final String CONFIG_BBDD_USER = "BBDD_USER";
	public static final String CONFIG_BBDD_PASSWORD = "BBDD_PASSWORD";
	public static final String CONFIG_BBDD_DRIVER = "RUTA_PLANTILLA_JR";
	
	
}


