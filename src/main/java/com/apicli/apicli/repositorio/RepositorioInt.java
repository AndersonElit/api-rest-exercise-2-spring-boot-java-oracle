package com.apicli.apicli.repositorio;

import java.util.List;

import com.apicli.apicli.modelo.Modelo;

public interface RepositorioInt {
	
	public void insertarCliente(Modelo mod);
	
	public void eliminarCliente(int id);
	
	public List<Modelo> listaClientes();
	
	public void editarCliente(int id, long cedula, long telefono, String direccion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String empresa, String estatus);

}
