package es.aguamarina.gestionjoyeria.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.structura.Joyeria;
import es.aguamarina.gestionjoyeria.structura.ReparacionVO;



public class HandlerReparaciones {
	private Connection conexion;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String URL = "jdbc:mysql://localhost/gestionjoyerias";
	private final String USER = "usuariojoyeria";
	private final String PASSWORD = "123";
	private Logger log;
	
	
	public HandlerReparaciones() throws DataBaseException
	{
		log = Logger.getLogger("Default");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DataBaseException();
		}
	}
	
	
	
	
	
	public void guardarJoyeria(Joyeria joyeria) throws DataBaseException
	{

		try {
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "Insert into joyerias(COD_JOYERIA, DESCRIPCION, TELEFONO) values(?, ?, ?)";
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, joyeria.getCodJoyeria());
			ps.setString(2, joyeria.getNombreJoyeria());
			ps.setString(3, joyeria.getTelefono());		
			ps.execute();
			


		}catch (SQLException e) {
			throw new DataBaseException("Error al insertar los datos de la nueva joyeria");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	

	/**
	 * Metodo que devuelve el listado de reparaciones para una joyeria determinada.
	 * @param codJoyeria 
	 * @return List Listado de Reparaciones
	 * @throws DataBaseException
	 */
	public List listadoReparaciones(Integer codJoyeria) throws DataBaseException
	{

		try {
			log.info("entrando");
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT COD_REPARACION, COD_SOBRE, REPARACION, PRECIO, NOMBRE_CLIENTE, FECHA_ENTRADA, FECHA_SALIDA, REPARADO, FACTURADO, ARTICULO FROM reparaciones";
			ps = conexion.prepareStatement(sql);
			
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast())
					return null;
				else
				{
					List<ReparacionVO> listaReparaciones = new ArrayList<ReparacionVO>();
					while(rs.next()){
						ReparacionVO reparacion = new ReparacionVO();
						reparacion.setCodReparacion(rs.getInt("COD_REPARACION"));
						reparacion.setCodSobre(rs.getString("COD_SOBRE"));
						reparacion.setReparacion(rs.getString("REPARACION"));
						reparacion.setPrecio(rs.getFloat("PRECIO"));
						reparacion.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
						reparacion.setFechaEntrada(rs.getDate("FECHA_ENTRADA"));
						reparacion.setFechaSalida(rs.getDate("FECHA_SALIDA"));
						reparacion.setReparado(rs.getBoolean("REPARADO"));
						reparacion.setFacturado(rs.getBoolean("FACTURADO"));
						reparacion.setArticulo(rs.getString("ARTICULO"));
						
						listaReparaciones.add(reparacion);		
					}
					return listaReparaciones;
				}
					
			}else{
				return null;
			}
			
		}catch (SQLException e) {
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	
	public void modificarJoyeria(Joyeria joyeria) throws DataBaseException
	{

		try {
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "Update joyerias set DESCRIPCION = ? , TELEFONO = ? where COD_JOYERIA = ?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, joyeria.getNombreJoyeria());
			ps.setString(2, joyeria.getTelefono());
			ps.setInt(3, joyeria.getCodJoyeria());
			ps.executeUpdate();
			


		}catch (SQLException e) {
			e.printStackTrace();
			//throw new DataBaseException("Error al modificar los datos de la joyeria");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	
	
	public Joyeria detallesJoyeria(Integer codJoyeria) throws DataBaseException
	{

		try {
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT COD_JOYERIA, DESCRIPCION, TELEFONO FROM joyerias where COD_JOYERIA = ?";
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, codJoyeria);
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast()){
					return null;
				}
				else
				{
					if(rs.next()){
						Joyeria joyeria = new Joyeria();
						joyeria.setCodJoyeria(rs.getInt("COD_JOYERIA"));
						joyeria.setNombreJoyeria(rs.getString("DESCRIPCION"));
						joyeria.setTelefono(rs.getString("TELEFONO"));
						return joyeria;
					}
				}
					
			}
				return null;
			
			
		}catch (SQLException e) {
			throw new DataBaseException("Error al acceder a los datos de la joyeria");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	
	public Integer ultimoCodigo() throws DataBaseException{
		try {
			Integer codigoMax;
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "Select max(COD_JOYERIA) as codigo from joyerias";
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next())
				codigoMax = rs.getInt("codigo") + 1;
			else
				codigoMax = 1;
			return codigoMax; 
			
		} catch (SQLException e) {
			throw new DataBaseException("Error al acceder a los datos de las joyerias");
		}
		
		finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		
	}
	
	public HashMap listadoNombresJoyerias() throws DataBaseException
	{

		try {
			conexion =	(Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT COD_JOYERIA, DESCRIPCION FROM joyerias";
			ps = conexion.prepareStatement(sql);
			rs=	ps.executeQuery();
			
			
			if(rs != null)
			{
				if(rs.isFirst() && rs.isLast())
					return null;
				else
				{
					HashMap listaJoyerias = new HashMap();
					while(rs.next()){
						listaJoyerias.put(rs.getInt("COD_JOYERIA"), rs.getString("DESCRIPCION"));						
					}
					return listaJoyerias;
				}
					
			}else{
				return null;
			}
			
		}catch (SQLException e) {
			throw new DataBaseException("Error al acceder a los datos de las joyerias");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
	}
	
}
