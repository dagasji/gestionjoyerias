package es.aguamarina.gestionjoyeria.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import es.aguamarina.gestionjoyeria.config.Config;
import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.exception.ParameterConfigException;

public class HandlerGestionJoyerias {
	
	protected Connection conexion;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected final String URL;
	protected final String USER;
	protected final String PASSWORD;
	protected Logger log = null;
	protected DataSource ds;
	
	public HandlerGestionJoyerias() throws DataBaseException{
		log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		try {
			URL = Config.getPropiedad(Constants.CONFIG_BBDD_URL); //"jdbc:mysql://localhost/gestionjoyerias"
			USER = Config.getPropiedad(Constants.CONFIG_BBDD_USER); //"usuariojoyeria"
			PASSWORD = Config.getPropiedad(Constants.CONFIG_BBDD_PASSWORD); //"usuariojoyeria"
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error(e);
			throw new DataBaseException();
		} catch (ParameterConfigException e) {
			throw new DataBaseException();
		}
	}
	
	public DataSource getDs() {
		return ds;
	}



	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
