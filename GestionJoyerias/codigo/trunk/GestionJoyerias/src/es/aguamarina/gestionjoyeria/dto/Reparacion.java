
package es.aguamarina.gestionjoyeria.dto;

import java.util.Date;

/**
 * @author Daniel Gaspar Jimenez
 *	
 */
public class Reparacion {
	
	private Integer codReparacion;
	private String codSobre;
	private String reparacion;
	private String articulo;
	private Float precio;
	private String nombreCliente;
	private Joyeria joyeria;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Boolean reparado;
	private Boolean entregado;
	private Boolean facturado;
	private Boolean presupuesto;
	private Factura factura;
	private Float costo;
	private Integer tipoReparacion;
	//private Date fechaFactura;
	
	
	/**
	 * Constructor por defecto
	 *
	 */
	public Reparacion()
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


	public Boolean isFacturado() {
		return facturado;
	}


	public void setFacturado(Boolean facturado) {
		this.facturado = facturado;
	}


	public Date getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

/*
	public Date getFechaFactura() {
		return fechaFactura;
	}


	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
*/

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


	public Float getPrecio() {
		return precio;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}


	public Boolean isReparado() {
		return reparado;
	}

	public Boolean isEntregado() {
		return entregado;
	}

	public void setReparado(Boolean reparado) {
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


	public Boolean getEntregado() {
		return entregado;
	}


	public void setEntregado(Boolean entregado) {
		this.entregado = entregado;
	}


	public Boolean getFacturado() {
		return facturado;
	}


	public Boolean getReparado() {
		return reparado;
	}


	public Boolean getPresupuesto() {
		return presupuesto;
	}
	public Boolean isPresupuestado() {
		return presupuesto;
	}

	public void setPresupuesto(Boolean presupuesto) {
		this.presupuesto = presupuesto;
	}


	public Float getCosto() {
		return costo;
	}


	public void setCosto(Float costo) {
		this.costo = costo;
	}


	public Integer getTipoReparacion() {
		return tipoReparacion;
	}


	public void setTipoReparacion(Integer tipoReparacion) {
		this.tipoReparacion = tipoReparacion;
	}
	
	
	
	
}
