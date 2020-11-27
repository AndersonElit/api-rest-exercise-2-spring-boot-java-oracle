package com.apicli.apicli.modelo;

public interface ModeloInt {
	
	public void Model();
	
	public void Model(int id_persona, long cedula, long telefono, String direccion, String primer_nombre, String segundo_nombre,
			String primer_apellido, String segundo_apellido, String empresa, String estatus);
	
	public int getId();
	
	public void setId(int id);
	
	public long getCedula();
	
	public void setCedula(long cedula);
	
	public long getTelefono();
	
	public void setTelefono(long telefono);
	
	public String getDireccion();
	
	public void setDireccion(String direccion);
	
	public String getPrimerNombre();
	
	public void setPrimerNombre(String primer_nombre);
	
	public String getSegundoNombre();
	
	public void setSegundoNombre(String segundo_nombre);
	
	public String getPrimerApellido();
	
	public void setPrimerApellido(String primer_apellido);
	
	public String getSegundoApellido();
	
	public void setSegundoApellido(String segundo_apellido);
	
	public String getEmpresa();
	
	public void setEmpresa(String empresa);
	
	public String getEstatus();
	
	public void setEstatus(String estatus);

}
