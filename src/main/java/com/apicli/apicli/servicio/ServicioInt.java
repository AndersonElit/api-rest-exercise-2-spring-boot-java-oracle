package com.apicli.apicli.servicio;

import java.util.List;

import com.apicli.apicli.modelo.Modelo;

public interface ServicioInt {
	
	public void insertarCliente(Modelo mod);
	
	public void EliminarCliente(int id);
	
	public List<Modelo> listarClientes();
	
	public void editarCliente(int id, long cedula, long telefono, String direccion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String empresa, String estatus);

}
