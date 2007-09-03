package es.aguamarina.gestionjoyeria.exception;

public class DataBaseException extends Exception {
	String descripcion;
	
	public DataBaseException()
	{
		descripcion = "Error en la consulta ha base de datos";
	}
	
	public DataBaseException(String mensaje) {
		descripcion=  mensaje;
	}

	public String toString(){
		return descripcion;
	}
		
}
