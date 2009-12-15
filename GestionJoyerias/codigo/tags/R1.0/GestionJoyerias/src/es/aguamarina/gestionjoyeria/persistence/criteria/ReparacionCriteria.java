package es.aguamarina.gestionjoyeria.persistence.criteria;

import java.util.Date;

import es.aguamarina.gestionjoyeria.dto.Joyeria;

public class ReparacionCriteria {
	private Integer codJoyeria = null;
	private String codSobr = null;
	private String reparacion = null;
	private String articulo = null;
	private Float precio = null;
	private String nombreCliente = null;
	private Joyeria joyeria = null;
	private Boolean reparado = null;
	private Boolean entregado =  null;
	private Boolean facturado = null;
	private Boolean presupuesto = null;
	private Date fechaEntrada = null;
	private Date fechaSalida = null;
	private Date fechaFactura = null;
	
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getCodSobr() {
		return codSobr;
	}
	public void setCodSobr(String codSobr) {
		this.codSobr = codSobr;
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
	public void setFacturado(Boolean facturado) {
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
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Boolean getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Boolean presupuesto) {
		this.presupuesto = presupuesto;
	}
	public String getReparacion() {
		return reparacion;
	}
	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}
	public Boolean getReparado() {
		return reparado;
	}
	public void setReparado(Boolean reparado) {
		this.reparado = reparado;
	}
	public Integer getCodJoyeria() {
		return codJoyeria;
	}
	public void setCodJoyeria(Integer codJoyeria) {
		this.codJoyeria = codJoyeria;
	}
	
		
	
}
