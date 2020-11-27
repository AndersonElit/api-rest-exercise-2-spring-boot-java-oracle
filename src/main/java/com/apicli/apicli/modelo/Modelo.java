package com.apicli.apicli.modelo;

public class Modelo implements ModeloInt {
	
	private int id_persona;
	private long cedula;
	private long telefono;
	private String direccion;
	private String primer_nombre;
	private String segundo_nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private String empresa;
	private String estatus;
	
	public void Model() {
		
	}
	
	public void Model(int id_persona, long cedula, long telefono, String direccion, String primer_nombre, String segundo_nombre,
			String primer_apellido, String segundo_apellido, String empresa, String estatus) {
		
		this.id_persona = id_persona;
		this.cedula = cedula;
		this.telefono = telefono;
		this.direccion = direccion;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.empresa = empresa;
		this.estatus = estatus;
		
	}
	
	public int getId() {
		return id_persona;
	}
	
	public void setId(int id) {
		this.id_persona = id;
	}
	
	public long getCedula() {
		return cedula;
	}
	
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	
	public long getTelefono() {
		return telefono;
	}
	
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getPrimerNombre() {
		return primer_nombre;
	}
	
	public void setPrimerNombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}
	
	public String getSegundoNombre() {
		return segundo_nombre;
	}
	
	public void setSegundoNombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}
	
	public String getPrimerApellido() {
		return primer_apellido;
	}
	
	public void setPrimerApellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	
	public String getSegundoApellido() {
		return segundo_apellido;
	}
	
	public void setSegundoApellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getEstatus() {
		return estatus;
	}
	
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
