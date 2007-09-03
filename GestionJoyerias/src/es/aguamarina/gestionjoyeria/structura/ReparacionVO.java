
package es.aguamarina.gestionjoyeria.structura;

import java.util.Date;

/**
 * @author Dani
 *	
 */
public class ReparacionVO {
	
	private Integer codReparacion;
	private String codSobre;
	private String reparacion;
	private String articulo;
	private float precio;
	private String nombreCliente;
	private Joyeria joyeria;
	private Date fechaEntrada;
	private Date fechaSalida;
	private boolean reparado;
	private boolean facturado;
	private Factura factura;
	private Date fechaFactura;
	
	
	/**
	 * Constructor por defecto
	 *
	 */
	public ReparacionVO()
	{
		
	}


	public String getCodSobre() {
		return codSobre;
	}


	public void setCodSobre(String codSobre) {
		this.codSobre = codSobre;
	}



	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	public boolean isFacturado() {
		return facturado;
	}


	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}


	public Date getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public Date getFechaFactura() {
		return fechaFactura;
	}


	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}


	public Date getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}


	public Joyeria getJoyeria() {
		return joyeria;
	}


	public void setJoyeria(Joyeria joyeria) {
		this.joyeria = joyeria;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public boolean isReparado() {
		return reparado;
	}


	public void setReparado(boolean reparado) {
		this.reparado = reparado;
	}


	public String getArticulo() {
		return articulo;
	}


	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}


	public String getReparacion() {
		return reparacion;
	}


	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}


	public Integer getCodReparacion() {
		return codReparacion;
	}


	public void setCodReparacion(Integer codReparacion) {
		this.codReparacion = codReparacion;
	}
	
	
	
	
}
