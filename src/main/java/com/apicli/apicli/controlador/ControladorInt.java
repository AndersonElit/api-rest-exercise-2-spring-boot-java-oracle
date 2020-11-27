package com.apicli.apicli.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.apicli.apicli.modelo.Modelo;

public interface ControladorInt {
	
	public void insertarCli(@RequestBody Modelo mod);
	
	public void eliminarCli(@PathVariable int id);
	
	public List<Modelo> listarCli();
	
	public void editarCliente(@PathVariable int id, @PathVariable long cedula, @PathVariable long telefono, @PathVariable String direccion, @PathVariable String primerNombre, @PathVariable String segundoNombre, @PathVariable String primerApellido, @PathVariable String segundoApellido, @PathVariable String empresa, @PathVariable String estatus);

}
