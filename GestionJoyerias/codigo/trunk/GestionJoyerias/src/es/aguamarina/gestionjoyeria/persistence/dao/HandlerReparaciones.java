package es.aguamarina.gestionjoyeria.persistence.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Factura;
import es.aguamarina.gestionjoyeria.dto.Reparacion;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.criteria.ReparacionCriteria;



public class HandlerReparaciones extends HandlerGestionJoyerias{
		
	
	private final String SQL_ULTIMO_CODIGO = "Select max(COD_REPARACION) as codigo from reparaciones";
	
	private final String SQL_ELIMINAR_REPARACION = "delete from reparaciones where COD_REPARACION = ?";
	
	public HandlerReparaciones() throws DataBaseException
	{
		super();
	}
	
	/**
	 * Metodo que devuelve el listado de reparaciones para una joyeria determinada.
	 * @param codJoyeria 
	 * @return List Listado de Reparaciones
	 * @throws DataBaseException
	 */
	public List listadoReparaciones(Integer codJoyeria, String estado) throws DataBaseException
	{

		log.debug("Entrada método listadoReparaciones()");
		
		try {
			conexion =	ds.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COD_REPARACION, COD_SOBRE, REPARACION, PRESUPUESTO, PRECIO, NOMBRE_CLIENTE, FECHA_ENTRADA, FECHA_SALIDA, REPARADO, FACTURADO, ARTICULO, facturas.FECHA_FACTURACION, facturas.COD_FACTURA FROM reparaciones left join facturas on reparaciones.COD_FACTURA_FK = facturas.COD_FACTURA WHERE COD_JOYERIA_FK = ?");
			
			//Obtenemos las reparaciones en estado Presupuesto
			if(estado.equals(Constants.ESTADO_PRESUPUESTO))
				sql.append(" AND PRESUPUESTO = 1");
				
			//Obtenemos las reparaciones que esten sin reparar
			if(estado.equals(Constants.ESTADO_SINREPARAR))
				sql.append(" AND (REPARADO = 0 OR REPARADO is null) AND (PRESUPUESTO = 0 OR PRESUPUESTO is null)");
			
			//Obtenemos las reparaciones en que esten reparadas pero no est�n entregadas
			else if(estado.equals(Constants.ESTADO_SINENTREGAR))
				sql.append(" AND REPARADO = 1 AND (ENTREGADO =0 OR ENTREGADO is null)");
			
			//Obtenemos las reparaciones que esten entregadas pero no facturadas
			else if(estado.equals(Constants.ESTADO_SINFACTURAR))
				sql.append(" AND ENTREGADO = 1 AND (FACTURADO =0 OR FACTURADO is null)");
			
			//Obtenemos las reparaciones que esten facturadas
			else if(estado.equals(Constants.ESTADO_FACTURADOS))
				sql.append(" AND FACTURADO = 1");
				
				
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codJoyeria);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
				
				//Recorremos el listado de reparaciones
				while(rs.next()){
					Reparacion reparacion = new Reparacion();
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
					reparacion.setPresupuesto(rs.getBoolean("PRESUPUESTO"));
					
					Factura factura = new Factura();
					factura.setCodFactura(rs.getInt("COD_FACTURA"));					
					factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					reparacion.setFactura(factura);				
					
					listaReparaciones.add(reparacion);
					
				}
				if(listaReparaciones.size() == 0)
					//No existe ninguna reparacion con ese estado.
					return null;
				else
					//Devolvemos el listado de reparaciones.
					return listaReparaciones;
				
					
			}else{
				//No existe ninguna reparacion con ese estado.
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");

		}finally{
			try {
				log.debug("Salida metodo listadoReparaciones()");
				//Cerramos la conexion a bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}	
	}
	
	
	/**
	 * Metodo que devuelve el primer codigo disponible de reparacion.
	 * @return Integer Primer codigo disponible.
	 * @throws DataBaseException
	 */
	public Integer ultimoCodigo() throws DataBaseException{
	
		log.debug("Entrada metodo ultimoCodigo()");
		
		try {
			Integer codigoMax;
			conexion =	ds.getConnection();
			ps = conexion.prepareStatement(this.SQL_ULTIMO_CODIGO);
			rs = ps.executeQuery();
			
			if(rs.next())
				//Existe alguna reparacion devolvemos el siguiente codigo
				codigoMax = rs.getInt("codigo") + 1;
			else
				//No existe todavia ninguna reparacion
				codigoMax = 1;
			
			//Devolvemos la reparacion					
			return codigoMax; 
			
		} catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");
		}
		
		finally{
			try {
				log.debug("Salida metodo ultimoCodigo()");
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
				
	}
	
	
	/**
	 * Metodo que inserta una nueva reparacion en base de datos.
	 * @param reparacion
	 * @throws DataBaseException
	 */
	public void insertarReparacion(Reparacion reparacion) throws DataBaseException
	{
		
		log.debug("Entrada metodo insertarReparacion()");
		
		try {
			conexion =	ds.getConnection();
						
			StringBuffer sql = new StringBuffer();
				
			//Montamos la sentencia sql
			sql.append("insert into reparaciones (");
			sql.append("COD_REPARACION,COD_SOBRE, COD_JOYERIA_FK");
			
			int i = 3;
			
			if (reparacion.getReparacion() != null){
				sql.append(",REPARACION");
				i++;
			}
			if (reparacion.getArticulo() != null){
				sql.append(",ARTICULO");
				i++;
			}
			if (reparacion.getFechaEntrada() != null){
				sql.append(",FECHA_ENTRADA");
				i++;
			}
			if (reparacion.getFechaSalida() != null){
				sql.append(",FECHA_SALIDA");
				i++;
			}
			if (reparacion.getNombreCliente() != null){
				sql.append(",NOMBRE_CLIENTE");
				i++;
			}
			if (reparacion.getPrecio() != null){
				sql.append(",PRECIO");
				i++;
			}
			if (reparacion.getCosto() != null){
				sql.append(",COSTO");
				i++;
			}
			if (reparacion.isReparado() != null){
				sql.append(",REPARADO");
				i++;
			}
			if (reparacion.isFacturado() != null){
				sql.append(",FACTURADO");
				i++;
			}
			if (reparacion.isEntregado() != null){
				sql.append(",ENTREGADO");
				i++;
			}
			if (reparacion.getTipoReparacion() != null){
				sql.append(",TIPO");
				i++;
			}
						
			sql.append(") values (?");
			
			for(int x=1; x<i; x++)
				sql.append(",?");
			
			sql.append(")");
			
			
			ps = conexion.prepareStatement(sql.toString());
			
			ps.setInt(1, reparacion.getCodReparacion());
			ps.setString(2, reparacion.getCodSobre());
			ps.setInt(3, reparacion.getJoyeria().getCodJoyeria());
			
			i = 3;
			
			if (reparacion.getReparacion() != null)
				ps.setString(++i, reparacion.getReparacion());
			if (reparacion.getArticulo() != null)
				ps.setString(++i, reparacion.getArticulo());
			if (reparacion.getFechaEntrada() != null)
				ps.setDate(++i, new Date(reparacion.getFechaEntrada().getTime()));
			if (reparacion.getFechaSalida() != null)
				ps.setDate(++i, new Date(reparacion.getFechaSalida().getTime()));
			if (reparacion.getNombreCliente() != null)
				ps.setString(++i, reparacion.getNombreCliente());
			if (reparacion.getPrecio() != null)
				ps.setFloat(++i, reparacion.getPrecio());
			if (reparacion.getCosto() != null)
				ps.setFloat(++i, reparacion.getCosto());
			if (reparacion.isReparado() != null)
				ps.setBoolean(++i, reparacion.isReparado());
			if (reparacion.isFacturado() != null)
				ps.setBoolean(++i, reparacion.isFacturado());
			if (reparacion.isEntregado() != null)
				ps.setBoolean(++i, reparacion.isEntregado());
			if (reparacion.getTipoReparacion() != null)
				ps.setInt(++i, reparacion.getTipoReparacion());
					
			//Ejecutamos la insercion	
			ps.execute();
			
			

		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al insertar los datos de la nueva reparacion");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		log.debug("Salida metodo insertarReparacion()");
		
	}
	
	
	/**
	 * Metodo que elimina una reparacion
	 * @param Integer codigo de la reparacion a eliminar
	 * @throws DataBaseException
	 */
	public void eliminarReparacion(Integer codReparacion) throws DataBaseException{
	
		log.debug("Entrada metodo eliminarReparacion()");
	
		try {
			conexion =	ds.getConnection();
			ps = conexion.prepareStatement(this.SQL_ELIMINAR_REPARACION);
			ps.setInt(1, codReparacion);
			ps.execute();
			
		} catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");
		}
		
		finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		log.debug("Salida metodo eliminarReparacion()");				
	}
	
	
	/**
	 * Metodo que devuelve un objeto Reparacion con los datos de una reparacion
	 * @param Integer codigo de la reparacion
	 * @return Reparacion. Datos de la reparacion
	 * @throws DataBaseException
	 */
	public Reparacion getReparacion(Integer codReparacion) throws DataBaseException
	{

		log.debug("Entrada metodo getReparacion()");				

		try {
			conexion =	ds.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COD_REPARACION, COD_SOBRE, REPARACION, PRECIO,PRESUPUESTO, NOMBRE_CLIENTE, FECHA_ENTRADA, FECHA_SALIDA, REPARADO, ENTREGADO, facturas.FECHA_FACTURACION, facturas.COD_FACTURA, FACTURADO, ARTICULO, COSTO, TIPO FROM reparaciones left join facturas on reparaciones.COD_FACTURA_FK = facturas.COD_FACTURA WHERE COD_REPARACION = ?");
			
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codReparacion);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				Reparacion reparacion = null;
				if(rs.next()){
					reparacion = new Reparacion();
					reparacion.setCodReparacion(rs.getInt("COD_REPARACION"));
					reparacion.setCodSobre(rs.getString("COD_SOBRE"));
					reparacion.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
					reparacion.setReparacion(rs.getString("REPARACION"));
					reparacion.setArticulo(rs.getString("ARTICULO"));
					reparacion.setPrecio(rs.getFloat("PRECIO"));
					reparacion.setFechaEntrada(rs.getDate("FECHA_ENTRADA"));
					reparacion.setReparado(rs.getBoolean("REPARADO"));
					reparacion.setEntregado(rs.getBoolean("ENTREGADO"));
					reparacion.setFechaSalida(rs.getDate("FECHA_SALIDA"));
					//reparacion.setFechaFactura(rs.getDate("FECHA_FACTURACION")); esto es antiguo de cuando la fecha factura estaba en la tabla de reparaciones
					reparacion.setFacturado(rs.getBoolean("FACTURADO"));								
					reparacion.setPresupuesto(rs.getBoolean("PRESUPUESTO"));					
					reparacion.setCosto(rs.getFloat("COSTO"));
					reparacion.setTipoReparacion(rs.getInt("TIPO"));
					
					Factura factura = new Factura();
					factura.setCodFactura(rs.getInt("COD_FACTURA"));					
					factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					reparacion.setFactura(factura);			
				}
				
				if(reparacion == null)
					//No se encuentra la reparacion
					return null;
				else
					//Devolvemos la reparacion
					return reparacion;
				
					
			}else{
				//No se encuentra la reparacion
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");
			

		}finally{
			try {
				log.debug("Salida metodo getReparacion()");
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		
		
	}
	
	
	/**
	 * Metodo que actualiza los datos de una reparacion
	 * @param Reparacion. Datos de la reparacion a actualizar.
	 * @throws DataBaseException
	 */
	public void updateReparacion(Reparacion reparacion) throws DataBaseException
	{
		
		log.debug("Entrada metodo updateReparacion()");
		
		try {
									
			StringBuffer sql = new StringBuffer();
			
			int x = 0;
			boolean separador = false;
			
			//Formamos la sql
			sql.append("update reparaciones set "); 
			
			
			if (reparacion.getReparacion() != null){
				sql.append(" REPARACION='").append(reparacion.getReparacion()).append("'");
				separador = true;
			}
			if (reparacion.getCodSobre() != null){
				sql.append((separador) ? " , " : "").append(" COD_SOBRE= '").append(reparacion.getCodSobre()).append("'");
				separador = true;
			}
			if (reparacion.getArticulo() != null){
				sql.append((separador) ? " , " : "").append(" ARTICULO= '").append(reparacion.getArticulo()).append("'");
				separador = true;
			}
					
			if (reparacion.getNombreCliente() != null){
				sql.append((separador) ? " , " : "").append(" NOMBRE_CLIENTE= '").append(reparacion.getNombreCliente()).append("'");
				separador = true;
			}
			if (reparacion.getPrecio() != null){
				sql.append((separador) ? " , " : "").append(" PRECIO= ").append(reparacion.getPrecio());
				separador = true;
			}
			if (reparacion.getCosto() != null){
				sql.append((separador) ? " , " : "").append(" COSTO= ").append(reparacion.getCosto());
				separador = true;
			}
			if (reparacion.getTipoReparacion() != null){
				sql.append((separador) ? " , " : "").append(" TIPO= ").append(reparacion.getTipoReparacion());
				separador = true;
			}			
			if (reparacion.isReparado() != null){
				sql.append((separador) ? " , " : "").append(" REPARADO= ").append(reparacion.getReparado());
				separador = true;
			}
			if (reparacion.isFacturado() != null){
				sql.append((separador) ? " , " : "").append(" FACTURADO= ").append(reparacion.getFacturado());
				separador = true;
				
			}
			if (reparacion.isPresupuestado() != null){
				sql.append((separador) ? " , " : "").append(" PRESUPUESTO= ").append(reparacion.getPresupuesto());
				separador = true;
			}			
			if (reparacion.isEntregado() != null){
				sql.append((separador) ? " , " : "").append(" ENTREGADO= ").append(reparacion.getEntregado());
				separador = true;
			}
			
			if (reparacion.getPresupuesto() != null){
				sql.append((separador) ? " , " : "").append(" PRESUPUESTO=").append(reparacion.getPresupuesto());
				separador = true;
			}
			
			log.info("Comprobamos si la reparacion esta facturada.");
			if (reparacion.isFacturado() != null && reparacion.isFacturado()){
				log.info("La reparacion esta facturada");
				FacturasDao hf = new FacturasDao();
				hf.setDs(this.getDs());
				Integer codFactura = null;
				if(reparacion.getFactura().getCodFactura() == null || reparacion.getFactura().getCodFactura().equals(""))
					codFactura = hf.insertFactura(reparacion.getFactura());
				else{
					hf.updateFactura(reparacion.getFactura());
					codFactura = reparacion.getFactura().getCodFactura();
				}
				sql.append((separador) ? " , " : "").append(" COD_FACTURA_FK = ").append(codFactura);
			}else{
				log.info("La reparacion no esta facturada");
				sql.append((separador) ? " , " : "").append(" COD_FACTURA_FK = null");
			}
			
			sql.append((separador) ? " , " : "").append(" FECHA_ENTRADA= ?");
			x++;
			separador = true;
			
			sql.append((separador) ? " , " : "").append(" FECHA_SALIDA= ?");
			x++;
			separador = true;
		
			sql.append( " WHERE COD_REPARACION=").append(reparacion.getCodReparacion());
			
			conexion =	ds.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			
			int i=0;
			
			if (reparacion.getFechaEntrada() != null)
				ps.setDate(++i, new Date(reparacion.getFechaEntrada().getTime()));
			else
				ps.setDate(++i, null);
				
			if (reparacion.getFechaSalida() != null)
				ps.setDate(++i, new Date(reparacion.getFechaSalida().getTime()));
			else
				ps.setDate(++i, null);
			
			
			//Ejecutamos la actualizacion
			ps.executeUpdate();
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al insertar los datos de la nueva reparacion");

		}finally{
			try {
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		log.debug("Salida metodo updateReparacion()");		
	}
	
	
	/**
	 * Metodo de busqueda de reparaciones por criterio
	 * @param ReparacionCriteria. Criterio de busqueda
	 * @return ArrayList Listado con las reparaciones encontradas.
	 * @throws DataBaseException
	 */
	public ArrayList searchReparacionByCriteria(ReparacionCriteria criterio) throws DataBaseException
	{
		
		log.debug("Entrada metodo searchReparacionByCriteria()");		
		
		try {
			conexion =	ds.getConnection();
						
			StringBuffer sql = new StringBuffer();
						
			sql.append("SELECT COD_REPARACION, COD_SOBRE, REPARACION, PRESUPUESTO, PRECIO, NOMBRE_CLIENTE, ").
				append("FECHA_ENTRADA, FECHA_SALIDA, REPARADO, FACTURADO, ARTICULO, facturas.FECHA_FACTURACION, facturas.COD_FACTURA ").
				append("FROM reparaciones left join facturas on reparaciones.COD_FACTURA_FK = facturas.COD_FACTURA WHERE COD_JOYERIA_FK = ? ");
			
			if (criterio.getArticulo() != null){
				sql.append(" AND ARTICULO like ? ");
								
			}
			if (criterio.getCodSobr() != null){
				sql.append(" AND COD_SOBRE = ?");
							
			}
			if (criterio.getEntregado() != null){
				sql.append(" AND ENTREGADO = ?");
								
			}
			if (criterio.getFacturado() != null){
				sql.append(" AND FACTURADO = ?");
								
			}
			if (criterio.getFechaEntrada() != null){
				sql.append(" AND FECHA_ENTRADA > ?");
								
			}
			if (criterio.getFechaFactura() != null){
				sql.append(" AND FECHA_FACTURA > ?");
								
			}
			if (criterio.getFechaSalida() != null){
				sql.append(" AND FECHA_SALIDA > ?");
								
			}
			if (criterio.getNombreCliente() != null){
				sql.append(" AND NOMBRE_CLIENTE like ? ");
								
			}
			if (criterio.getPrecio() != null){
				sql.append(" AND PRECIO > ?");
								
			}
			if (criterio.getPresupuesto() != null){
				sql.append(" AND PRESUPUESTO = ?");
								
			}
			if (criterio.getReparado() != null){
				sql.append(" AND REPARADO = ?");
								
			}
			if (criterio.getReparacion() != null){
				sql.append(" AND REPARACION like ? ");
								
			}
			
			ps = conexion.prepareStatement(sql.toString());
			int i = 1;	
			
			ps.setInt(i, criterio.getCodJoyeria());
			i++;
			if (criterio.getArticulo() != null){
				ps.setString(i, "%" + criterio.getArticulo() + "%");
				i++;
			}
			if (criterio.getCodSobr() != null){
				ps.setString(i, criterio.getCodSobr());
				i++;				
			}
			if (criterio.getEntregado() != null){
				ps.setBoolean(i, criterio.getEntregado());
				i++;				
			}
			if (criterio.getFacturado() != null){
				ps.setBoolean(i, criterio.getFacturado());
				i++;				
			}
			if (criterio.getFechaEntrada() != null){
				ps.setDate(i, new Date(criterio.getFechaEntrada().getTime()));
				i++;				
			}
			if (criterio.getFechaFactura() != null){
				ps.setDate(i, new Date(criterio.getFechaFactura().getTime()));
				i++;				
			}
			if (criterio.getFechaSalida() != null){
				ps.setDate(i, new Date(criterio.getFechaSalida().getTime()));
				i++;				
			}
			if (criterio.getNombreCliente() != null){
				ps.setString(i, "%" +  criterio.getNombreCliente() + "%");
				i++;				
			}
			if (criterio.getPrecio() != null){
				ps.setFloat(i, criterio.getPrecio());
				i++;				
			}
			if (criterio.getPresupuesto() != null){
				ps.setBoolean(i, criterio.getPresupuesto());
				i++;				
			}
			if (criterio.getReparado() != null){
				ps.setBoolean(i, criterio.getReparado());
				i++;				
			}
			if (criterio.getReparacion() != null){
				ps.setString(i, "%" + criterio.getReparacion() + "%");
				i++;				
			}
			
			log.info(sql);
			
			rs = ps.executeQuery();
			
			if(rs != null)
			{
				ArrayList<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
				while(rs.next()){
					Reparacion reparacion = new Reparacion();
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
					//reparacion.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					reparacion.setPresupuesto(rs.getBoolean("PRESUPUESTO"));
					
					Factura factura = new Factura();
					factura.setCodFactura(rs.getInt("COD_FACTURA"));					
					factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					reparacion.setFactura(factura);		
					
					listaReparaciones.add(reparacion);
					
				}
				if(listaReparaciones.size() == 0)
					return null;
				else
					return listaReparaciones;
				
					
			}else{
				return null;
			}
			
				
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al realizar la busqueda");

		}finally{
			try {
				log.debug("Salida metodo searchReparacionByCriteria()");
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		
		
	}
	
	
	/**
	 * Metodo que devuelve el listado de reparaciones pasandole como parametro un array 
	 * de codigos de reparaciones
	 * @param Integer[] array con los codigos de las reparaciones
	 * @return List Listado de Reparaciones
	 * @throws DataBaseException
	 */
	public List listadoReparaciones(Integer[] codReparacion) throws DataBaseException
	{
		
		log.debug("Entrada metodo listadoReparaciones()");

		try {
			conexion =	ds.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COD_REPARACION, COD_SOBRE, REPARACION, PRESUPUESTO, PRECIO, NOMBRE_CLIENTE, FECHA_ENTRADA, FECHA_SALIDA, REPARADO, FACTURADO, ARTICULO FROM reparaciones "). 
				append(" WHERE ");
			
			boolean separador = false;
			
			//Recorremos el array de codigos para formar la sql
			for (int i= 0; i < codReparacion.length; i++){
				if (separador)
					sql.append(" OR ");
				sql.append("COD_REPARACION = ?");
				separador = true;
				
			}				
			
			ps = conexion.prepareStatement(sql.toString());
			
			log.info(sql.toString());
			
			//Recorremos el array de codigos para formar la sql
			for (int i= 0; i < codReparacion.length; i++){
				ps.setInt(i+1, codReparacion[i]);
				
			}
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
				while(rs.next()){
					Reparacion reparacion = new Reparacion();
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
					reparacion.setPresupuesto(rs.getBoolean("PRESUPUESTO"));
					
					/*Factura factura = new Factura();
					factura.setCodFactura(rs.getInt("COD_FACTURA"));					
					factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					reparacion.setFactura(factura);*/					
					
					listaReparaciones.add(reparacion);
					
				}
				if(listaReparaciones.size() == 0)
					//No se ha encontrado ninguna reparacion				
					return null;
				else
					//Devolvemos el listado de reparaciones.
					return listaReparaciones;
				
					
			}else{
				//No se ha encontrado ninguna reparacion				
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");

		}finally{
			try {
				log.debug("Salida metodo listadoReparaciones()");
				conexion.close();
			}catch (SQLException e) {

			}
		}
		
		
		
	}
	
	public List listadoReparacionesByFactura(Integer codFactura) throws DataBaseException
	{

		log.debug("Entrada método listadoReparacionesByFactura()");
		
		try {
			conexion =	ds.getConnection();
			String sql = "SELECT * FROM reparaciones r where COD_FACTURA_FK =?";
			
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codFactura);
			
			//Ejecutamos la consulta
			rs=	ps.executeQuery();
						
			if(rs != null)
			{
				List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
				
				//Recorremos el listado de reparaciones
				while(rs.next()){
					Reparacion reparacion = new Reparacion();
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
					reparacion.setPresupuesto(rs.getBoolean("PRESUPUESTO"));
					
					//Factura factura = new Factura();
					//factura.setCodFactura(rs.getInt("COD_FACTURA_FK"));					
					//factura.setFechaFactura(rs.getDate("FECHA_FACTURACION"));
					//reparacion.setFactura(factura);				
					
					listaReparaciones.add(reparacion);
					
				}
				if(listaReparaciones.size() == 0)
					//No existe ninguna reparacion con ese estado.
					return null;
				else
					//Devolvemos el listado de reparaciones.
					return listaReparaciones;
				
					
			}else{
				//No existe ninguna reparacion en esa factura.
				return null;
			}
			
		}catch (SQLException e) {
			log.error(e);
			throw new DataBaseException("Error al acceder a los datos de las reparaciones");

		}finally{
			try {
				log.debug("Salida metodo listadoReparaciones()");
				//Cerramos la conexion a bbdd
				conexion.close();
			}catch (SQLException e) {

			}
		}	
	}

	
}
