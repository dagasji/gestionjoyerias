package es.aguamarina.gestionjoyeria.exception;

public class ParameterConfigException extends Exception {
	String descripcion;
	
	public ParameterConfigException()
	{
		descripcion = "Error al obtener los parametros de configuracion";
	}
	
	public ParameterConfigException(String nombre) {
		descripcion=  "Error al obtener el parametro de configuracion " + nombre;
	}

	public String toString(){
		return descripcion;
	}
		
}
