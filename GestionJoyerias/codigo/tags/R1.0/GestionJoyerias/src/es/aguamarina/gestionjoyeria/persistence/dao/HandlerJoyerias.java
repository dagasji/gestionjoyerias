package es.aguamarina.gestionjoyeria.persistence.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.aguamarina.gestionjoyeria.dto.Joyeria;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;



public class HandlerJoyerias extends HandlerGestionJoyerias {
	
	
	
	
	public HandlerJoyerias() throws DataBaseException
	{
		super();
	}
	
	
	
	/**
	 * Metodo que almacena en base de dato una nueva joyeria.
	 * @param Joyeria. Datos de la joyeria a almacenar
	 * @throws DataBaseException
	 */
	public void guardarJoyeria(Joyeria joyeria) throws DataBaseException
	{

		log.debug("Entrada metodo guardarJoyeria()");
		
		try {
			conexion =	ds.getConnection();
			
			//sql de insercion de una nueva joyeria
			String sql = "Insert into joyerias(COD_JOYERIA, DESCRIPCION, TELEFONO) values(?, ?, ?)";
			
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, joyeria.getCodJoyeria());
			ps.setString(2, joyeria.getNombreJoyeria());
			ps.setString(3, joyeria.getTelefono());		
			
			//Ejecutamos la insercion
			ps.execute();


		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al insertar los datos de la nueva joyeria");

		}finally{
			try {
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		log.debug("Salida metodo guardarJoyeria()");		
	}
	
	
	/**
	 * Metodo que modifica los datos de una joyeria en base de datos.
	 * @param Joyeria. Datos de la joyeria a modificar
	 * @throws DataBaseException
	 */
	public void modificarJoyeria(Joyeria joyeria) throws DataBaseException
	{
		
		log.debug("Entrada metodo modificarJoyeria()");		
		
		try {
			conexion =	ds.getConnection();
			
			//sql de modificacion de la joyeria
			String sql = "Update joyerias set DESCRIPCION = ? , TELEFONO = ? where COD_JOYERIA = ?";
			
			ps = conexion.prepareStatement(sql);
			
			ps.setString(1, joyeria.getNombreJoyeria());
			ps.setString(2, joyeria.getTelefono());
			ps.setInt(3, joyeria.getCodJoyeria());
			
			//Ejecutamos la actualizacion
			ps.executeUpdate();
			


		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al modificar los datos de la joyeria");

		}finally{
			try {
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		log.debug("Salida metodo modificarJoyeria()");		
		
	}
	
	
	/**
	 * Metodo que devuelve el listado de joyerias existentes.
	 * @return List. Listado de joyerias
	 * @throws DataBaseException
	 */
	public List listadoJoyerias() throws DataBaseException
	{
		
		log.debug("Entrada metodo listadoJoyerias()");		

		try {
			conexion =	ds.getConnection();
			
			//Consulta para obtener un listado de joyerias
			String sql = "SELECT COD_JOYERIA, DESCRIPCION, TELEFONO FROM joyerias";
			ps = conexion.prepareStatement(sql);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
			
			
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast())
					//No se ha encontrado ninguna joyeria
					return null;
				else
				{					
					List<Joyeria> listaJoyerias = new ArrayList<Joyeria>();
					
					//Recorremos el ResultSet con los resultados de la consulta
					while(rs.next()){
						Joyeria joyeria = new Joyeria();
						joyeria.setCodJoyeria(rs.getInt("COD_JOYERIA"));
						joyeria.setNombreJoyeria(rs.getString("DESCRIPCION"));
						joyeria.setTelefono(rs.getString("TELEFONO"));
						listaJoyerias.add(joyeria);		
					}
					
					//Devolvemos el listado de joyerias
					return listaJoyerias;
				}
					
			}else{
				//No se ha encontrado ninguna joyeria
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las joyerias");

		}finally{
			try {
				log.debug("Salida metodo listadoJoyerias()");
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}	
	}
	
	
	/**
	 * Metodo que devuelve los detalles de una joyeria.
	 * @param Integer. Codigo de la joyeria
	 * @return Joyeria. VO con los datos de la joyeria.
	 * @throws DataBaseException
	 */
	public Joyeria detallesJoyeria(Integer codJoyeria) throws DataBaseException
	{
				
		log.debug("Entrada metodo detallesJoyeria()");		

		try {
			conexion =	ds.getConnection();
			
			//Consulta para la obtencion de los datos de una joyeria
			String sql = "SELECT COD_JOYERIA, DESCRIPCION, TELEFONO FROM joyerias where COD_JOYERIA = ?";
			
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, codJoyeria);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast()){
					//No se ha encontrado ninguna joyeria con ese codigo
					return null;
				}
				else
				{
					//Obtenemos los datos de la joyeria
					if(rs.next()){
						Joyeria joyeria = new Joyeria();
						joyeria.setCodJoyeria(rs.getInt("COD_JOYERIA"));
						joyeria.setNombreJoyeria(rs.getString("DESCRIPCION"));
						joyeria.setTelefono(rs.getString("TELEFONO"));
						
						//Devolvemos los datos de la joyeria
						return joyeria;
					}
				}
					
			}
			
			//No existe ninguna joyeria con el codigo indicado
			return null;
			
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de la joyeria");

		}finally{
			try {
				log.debug("Salida metodo detallesJoyeria()");
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}			
		
	}
	
	
	/**
	 * Metodo que devuelve el primer codigo de joyeria libre.
	 * @return Integer. Codigo de la joyeria
	 * @throws DataBaseException
	 */
	public Integer ultimoCodigo() throws DataBaseException{
	
		log.debug("Entrada metodo ultimoCodigo()");	
	
		try {
			Integer codigoMax;
			conexion =	ds.getConnection();
			
			//Consulta para la obtencion del ultimo codigo de joyeria insertado en bbdd
			String sql = "Select max(COD_JOYERIA) as codigo from joyerias";
			
			ps = conexion.prepareStatement(sql);
			
			//Ejecutamos la consulta
			rs = ps.executeQuery();
			
			if(rs.next())
				//Existen joyerias, devolvemos el siguiente codigo disponible
				codigoMax = rs.getInt("codigo") + 1;
			else
				//No existen joyerias, devolvemos el codigo 1
				codigoMax = 1;
				
			//Devolvemos el codigo de la joyeria
			return codigoMax; 
			
		} catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las joyerias");
		}
		
		finally{
			try {
				log.debug("Salida metodo ultimoCodigo()");
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}
	}
	
	
	/**
	 * Metodo que devuelve un listado con los nombres de la joyerias.
	 * @return HashMap. listado con los nombres de las joyerias.
	 * @throws DataBaseException
	 */
	public HashMap listadoNombresJoyerias() throws DataBaseException
	{

		try {
			conexion =	ds.getConnection();
			
			//Consulta sql para obtener un listado con el codigo y nombre de las joyerias
			String sql = "SELECT COD_JOYERIA, DESCRIPCION FROM joyerias";
			
			
			ps = conexion.prepareStatement(sql);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
			
			
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast())
					//No se ha encontrado ninguna joyeria
					return null;
				else
				{
					HashMap listaJoyerias = new HashMap();
					
					//Recorremos el ResultSet con el resultado de la consulta.
					while(rs.next()){
						listaJoyerias.put(rs.getInt("COD_JOYERIA"), rs.getString("DESCRIPCION"));						
					}
					
					//Devolvemos el listado de joyerias
					return listaJoyerias;
				}
					
			}else{
				//No se ha encontrado ninguna joyeria
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las joyerias");

		}finally{
			try {
				//Cerramos la conexion con la bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	
	
}
