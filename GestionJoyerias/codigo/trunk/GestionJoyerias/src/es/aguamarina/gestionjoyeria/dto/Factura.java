package es.aguamarina.gestionjoyeria.dto;

import java.util.Date;

/**
 * DTO que encapsula los datos de una factura.
 * @author Daniel Gaspar Jimenez
 *
 */
public class Factura {
	
	private Integer codFactura;
	private Date fechaFactura;
	private String nombreCliente;
	private Float totalFactura;

	public Float getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Float totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Integer getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(Integer codFactura) {
		this.codFactura = codFactura;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
}
