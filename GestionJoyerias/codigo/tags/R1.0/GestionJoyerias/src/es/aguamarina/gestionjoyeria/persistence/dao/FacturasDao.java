package es.aguamarina.gestionjoyeria.persistence.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.aguamarina.gestionjoyeria.dto.Factura;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;

/**
 * Handler encargado de manejar los datos de una factura
 * @author Daniel Gaspar Jiménez
 *
 */
public class FacturasDao extends HandlerGestionJoyerias{
	
	
	
	public FacturasDao() throws DataBaseException{
		super();		
	}
	
	
	
	/**
	 * Metodo que crea una nueva factura y devuelve el codigo de la factura creada
	 * @param Factura. Datos de creacion de la factura 
	 * @return Integer Código de la factura creada.
	 * @throws DataBaseException
	 */
	public Integer insertFactura(Factura factura) throws DataBaseException{
	
		log.debug("Entrada método insertFactura()");
		
		try {			
			
			StringBuffer sql = new StringBuffer();
			
			//sql de insercion de una nueva factura.
			sql.append("INSERT INTO  facturas (COD_FACTURA, FECHA_FACTURACION, FACT_DS_NOMBRE_CLIENTE) values(?, ?, ?)"); 
			log.debug("Consulta a ejecutar: " + sql.toString());
			
			//Obtenemos el ultimo codigo de factura disponible.
			factura.setCodFactura(this.ultimoCodigoFactura());
			
			conexion =	ds.getConnection();
			
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, factura.getCodFactura());
			ps.setDate(2, new Date(factura.getFechaFactura().getTime()));
			ps.setString(3, factura.getNombreCliente());
			
			log.info("Ultimo codigo de factura: " + factura.getCodFactura());
			
			//Ejecutamos la insercion.
			ps.execute();						
			
			
		}catch (SQLException e) {
			log.error("insertFactura(): Error al crear la nueva factura", e);
			throw new DataBaseException(e, "insertFactura(): Error crear la nueva factura");

		} finally{
			try {
				conexion.close();
			} catch (SQLException e) {
				log.error("Error al cerrar la conexion a la base de datos", e);				
			}
		}
		
		log.debug("Salida método insertFactura()");
		return factura.getCodFactura();

	}
	
	
	public void updateFactura(Factura factura){
		
	}
	
	
	/**
	 * Metodo que devuelve el siguiente codigo de factura disponible.
	 * @return Integer Código de factura disponible.
	 * @throws DataBaseException
	 */
	public Integer ultimoCodigoFactura() throws DataBaseException{
	
		log.debug("Entrada metodo ultimoCodigoFactura()");
		
		try {
			Integer codigoMax;
			conexion =	ds.getConnection();
			
			//Obtenemos el ultimo codigo de factura insertado en bbdd.
			ps = conexion.prepareStatement("Select max(COD_FACTURA) as codigo from facturas");
			rs = ps.executeQuery();			
			
			if(rs.next())
				//En el caso que exista algun codigo de factura se devuelve el "ultimo + 1"
				codigoMax = rs.getInt("codigo") + 1;
			else
				//Si no existe ningun resultado devolvemos "1" como resultado.
				codigoMax = 1;
				
			//Devolvemos el codigo obtenido.
			return codigoMax; 
			
		} catch (SQLException e) {
			
			log.error("Error al acceder a los datos de las facturas", e);
			throw new DataBaseException(e, "Error al acceder a los datos de las facturas");
			
		}finally{			
			try {				
				conexion.close();
				log.debug("Salida método ultimoCodigoFactura()");
			} catch (SQLException e) {
				log.error(e);				
			}
		}		
	}
	
	
	/**
	 * Metodo encargado de crear una factura para las reparaciones
	 * indicadas como parametro.
	 * @param codReparaciones Array de Integer con los codigos
	 * de las reparaciones.
	 * @param factura Objeto que encapsula los datos de la factura.
	 * @return Nueva factura creada
	 * @throws DataBaseException Si se produce una excepción en la
	 * capa de acceso a datos.
	 */
	public Factura facturar(Integer[] codReparaciones, Factura factura) throws DataBaseException{
		log.debug("Entrada metodo facturar()");

		try {
			
			this.insertFactura(factura);
			
			conexion =	ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE reparaciones set FACTURADO = 1, COD_FACTURA_FK = ?"). 
				append(" WHERE ");
			
			boolean separador = false;
			
			//Recorremos el array de codigos para formar la sql
			for (int i= 0; i < codReparaciones.length; i++){
				if (separador)
					sql.append(" OR ");
				sql.append("COD_REPARACION = ?");
				separador = true;
				
			}				
			
			ps = conexion.prepareStatement(sql.toString());
			
			log.info(sql.toString());
			
			ps.setInt(1, factura.getCodFactura());
			
			//Recorremos el array de codigos para formar la sql
			for (int i=0; i < codReparaciones.length; i++){
				ps.setInt(i+2, codReparaciones[i]);
				
			}		
			
			//Ejecutamos la consulta
			ps.executeUpdate();
			
			return factura;
			
		}catch (SQLException e) {
			
			log.error("Error al crear la nueva factura.", e);
			throw new DataBaseException(e, "Error al crear la nueva factura.");

		}finally{
			try {
				log.debug("Salida metodo facturar()");
				conexion.close();
			}catch (SQLException e) {

			}
		}	
	
	}
	
	/**
	 * Metodo que devuelve los datos de una factura
	 * @param codFactura. Integer Codigo de la factura.
	 * @throws DataBaseException
	 */
	public Factura getFactura(Integer codFactura) throws DataBaseException{
	
		log.debug("Entrada metodo getFactura()");
		
		try {
			
			conexion =	ds.getConnection();
			
			ps = conexion.prepareStatement("Select * from facturas where COD_FACTURA = ?");
			ps.setInt(1, codFactura);
			rs = ps.executeQuery();			
			
			Factura factura = new Factura();
			if(rs.next()){
				factura.setCodFactura(rs.getInt("COD_FACTURA"));
				factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
				factura.setNombreCliente(rs.getString("FACT_DS_NOMBRE_CLIENTE"));
				}
							
			//Devolvemos el codigo obtenido.
			return factura; 
			
		} catch (SQLException e) {
			
			log.error("Error al acceder a los datos de las facturas", e);
			throw new DataBaseException(e, "Error al obtener los datos de la factura");
			
		}finally{
			log.debug("Salida método getFactura()");
			
		}		
	}
	
	
	
	
	/**
	 * Metodo que devuelve un listados con los datos de las facturas
	 * de una joyeria
	 * @throws DataBaseException
	 */
	public List getListFacturasByJoyeria(Integer codJoyeria) throws DataBaseException{
	
		log.debug("Entrada metodo getListFacturas()");
		
		try {
			conexion =	ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT f.COD_FACTURA, f.FECHA_FACTURACION, FACT_DS_NOMBRE_CLIENTE, sum(r.PRECIO) as TOTAL_FACTURA ");
			sql.append("FROM facturas f join reparaciones r on f.COD_FACTURA = r.COD_FACTURA_FK ");
			sql.append("WHERE r.COD_JOYERIA_FK = ? ");
			sql.append("GROUP BY f.COD_FACTURA ");
			sql.append("ORDER BY f.COD_FACTURA");
			
			ps = conexion.prepareStatement(sql.toString());
			
			ps.setInt(1, codJoyeria);
			
			rs = ps.executeQuery();			
			
			Factura factura;
			List<Factura> lista = new ArrayList<Factura>();
			while(rs.next()){
				
				factura = new Factura();
				factura.setCodFactura(rs.getInt("COD_FACTURA"));
				factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
				factura.setNombreCliente(rs.getString("FACT_DS_NOMBRE_CLIENTE"));
				factura.setTotalFactura(rs.getFloat("TOTAL_FACTURA"));
				lista.add(factura);
				}
			
						
			return lista; 
			
		} catch (SQLException e) {
			
			log.error("Error al acceder a los datos de las facturas de la joyeria: " + codJoyeria, e);
			throw new DataBaseException(e, "Error al acceder a los datos de las facturas de la joyeria");
			
		}finally{			
			try {
				log.debug("Salida método getListFacturas()");
				conexion.close();
			}catch (SQLException e) {

			}
		}		
	}
	
	
}
