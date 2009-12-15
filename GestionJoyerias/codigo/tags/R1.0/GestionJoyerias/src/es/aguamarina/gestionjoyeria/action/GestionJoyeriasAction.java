package es.aguamarina.gestionjoyeria.action;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ActionSupport;




/**
 * Clase Action de la que deben heredar todos los action de la aplicacion p
 * ara asi tener acceso al contexto
 * @author Daniel Gaspar Jiménez
 * 
 */
public abstract class GestionJoyeriasAction extends ActionSupport{
	
	protected WebApplicationContext cxt; 
	
	
	/**
	 * Recuperamos el contexto
	 */
	protected void onInit(){
		
		cxt = getWebApplicationContext();
		
	}
	
		
}