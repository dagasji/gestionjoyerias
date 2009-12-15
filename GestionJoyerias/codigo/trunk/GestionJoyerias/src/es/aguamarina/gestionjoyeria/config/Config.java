package es.aguamarina.gestionjoyeria.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

import es.aguamarina.gestionjoyeria.exception.ParameterConfigException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class Config {
	
	public static String ficheroConfiguracion;
		
	public static HashMap<String, String> propiedades;
	
	private static boolean CONTEXT_ACTIVE = true; 
	
	static {
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
				
		try {
			
			ficheroConfiguracion = getFileConfig();
			
			//Cargamos el fichero con la propiedades
			InputStream is=new FileInputStream(ficheroConfiguracion);			
			
			//Cargamos las propiedades en el Properties			
			Properties prop = new Properties();
			prop.load(is);
			
			//Cerramos el InputStream 
			is.close();
			
			//Asignamos la propiedades al nuevo HashMap
			propiedades = new HashMap(prop);			
			
		}  catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (NamingException e) {
			log.error(e);
		}
	}
	
	
	/**
	 * Metodo para obtener parametros de configuracion
	 * @param nombre
	 * @return
	 */
	public static String getPropiedad(String nombre) throws ParameterConfigException{

		String valor = propiedades.get(nombre);

		if (valor == null)
			throw new ParameterConfigException(nombre);

		return valor;
	}
	
	
	/**
	 * Metodo para obtener parametros de configuracion
	 * @param nombre
	 * @return
	 * @throws NamingException 
	 */
	private static String getFileConfig() throws NamingException{

		try {
			if (CONTEXT_ACTIVE){
				//Recuperamos el contexto inicial
				Context contextoInicial;
				contextoInicial = new InitialContext();

				//Obtenemos el contexto de la aplicacion
				Context contexto = (Context) contextoInicial.lookup("java:comp/env");

				//Recuperamos la propiedad que contiene el nombre del fichero de configuracion
				return (String) contexto.lookup("ficheroconfiguracion");

			}else{				
				return "c:/tmp/configuracion.properties";
			}

		} catch (NamingException e) {
			throw e;			
		}
	}
	
}
