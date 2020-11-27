package com.apicli.apicli.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicli.apicli.modelo.Modelo;
import com.apicli.apicli.repositorio.Repositorio;

@Service
public class Servicio implements ServicioInt {
	
	@Autowired
	private Repositorio repo;

	@Override
	public void insertarCliente(Modelo mod) {
		repo.insertarCliente(mod);
		System.out.println("se agrego un nuevo cliente");
	}

	@Override
	public void EliminarCliente(int id) {
		repo.eliminarCliente(id);
		System.out.println("se elimino un nuevo cliente");
	}

	@Override
	public List<Modelo> listarClientes() {
		return repo.listaClientes();
	}

	@Override
	public void editarCliente(int id, long cedula, long telefono, String direccion, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String empresa, String estatus) {
		repo.editarCliente(id, cedula, telefono, direccion, primerNombre, segundoNombre, primerApellido, segundoApellido, empresa, estatus);
		System.out.println("Se edito el cliente");
	}

}
