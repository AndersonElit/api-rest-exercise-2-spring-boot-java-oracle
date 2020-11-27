package com.apicli.apicli.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apicli.apicli.modelo.Modelo;
import com.apicli.apicli.servicio.Servicio;

@RestController
public class Controlador implements ControladorInt {
	
	@Autowired
	private Servicio serv;

	@Override
	@RequestMapping(value="/insertarCli", method = RequestMethod.POST)
	public void insertarCli(Modelo mod) {
		serv.insertarCliente(mod);
	}

	@Override
	@RequestMapping(value="/eliminarCli/{id}", method = RequestMethod.DELETE)
	public void eliminarCli(@PathVariable int id) {
		serv.EliminarCliente(id);
	}

	@Override
	@RequestMapping(value="/listaClientes", method = RequestMethod.GET)
	public List<Modelo> listarCli() {
		return serv.listarClientes();
	}

	@Override
	@RequestMapping(value="/editarCli/{id}/{cedula}/{telefono}/{direccion}/{primerNombre}/{segundoNombre}/{primerApellido}/{segundoApellido}/{empresa}/{estatus}", method = RequestMethod.PUT)
	public void editarCliente(@PathVariable int id, @PathVariable long cedula, @PathVariable long telefono, @PathVariable String direccion, @PathVariable String primerNombre,
			@PathVariable String segundoNombre, @PathVariable String primerApellido, @PathVariable String segundoApellido, @PathVariable String empresa, @PathVariable String estatus) {
		serv.editarCliente(id, cedula, telefono, direccion, primerNombre, segundoNombre, primerApellido, segundoApellido, empresa, estatus);		
	}

}
