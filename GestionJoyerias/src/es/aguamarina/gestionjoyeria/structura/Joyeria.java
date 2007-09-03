package es.aguamarina.gestionjoyeria.structura;

import java.util.List;

public class Joyeria {

	private Integer codJoyeria;
	private String nombreJoyeria;
	private String telefono;
	private List reparaciones;
	
	public Integer getCodJoyeria() {
		return codJoyeria;
	}
	
	public void setCodJoyeria(Integer codJoyeria) {
		this.codJoyeria = codJoyeria;
	}
	public String getNombreJoyeria() {
		return nombreJoyeria;
	}
	public void setNombreJoyeria(String nombreJoyeria) {
		this.nombreJoyeria = nombreJoyeria;
	}
	public List getReparaciones() {
		return reparaciones;
	}
	public void setReparaciones(List reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
