package es.aguamarina.gestionjoyeria.exception;

public class ParameterException extends Exception {
	String descripcion;
	
	public ParameterException()
	{
		descripcion = "Parametros incorrectos";
	}
	
	public ParameterException(String parametro) {
		descripcion=  "Parametros incorrectos. " + parametro +" requerido";
	}

	public String toString(){
		return descripcion;
	}
		
}
