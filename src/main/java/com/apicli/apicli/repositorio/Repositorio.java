package com.apicli.apicli.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.apicli.apicli.modelo.Modelo;

@Repository
public class Repositorio implements RepositorioInt {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertarCliente(Modelo mod) {
		String procedure = "CALL procedimientos_clientes.insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				mod.getCedula(), mod.getTelefono(), mod.getDireccion(), mod.getPrimerNombre(), mod.getSegundoNombre(), mod.getPrimerApellido(), mod.getSegundoApellido(), mod.getEmpresa(), mod.getEstatus()				
		});	
	}

	@Override
	public void eliminarCliente(int id) {
		String procedure = "CALL procedimientos_clientes.eliminar_cliente(?)";
		jdbcTemplate.update(procedure, id);
	}

	@Override
	public List<Modelo> listaClientes() {
		
		String sql = "select * from clientes";
		
		List<Modelo> listaCli = jdbcTemplate.query(sql, new ResultSetExtractor<List<Modelo>>() {
			@Override
			public List<Modelo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Modelo> list = new ArrayList<Modelo>();
				while (rs.next()) {
					Modelo cliente = new Modelo();
					cliente.setId(rs.getInt("ID_PERSONA"));
					cliente.setCedula(rs.getLong("CEDULA"));
					cliente.setTelefono(rs.getLong("TELEFONO"));
					cliente.setDireccion(rs.getString("DIRECCION"));
					cliente.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
					cliente.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
					cliente.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
					cliente.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
					cliente.setEmpresa(rs.getString("EMPRESA"));
					cliente.setEstatus(rs.getString("ESTATUS"));
					list.add(cliente);
				}
				return list;
			}
		});
		return listaCli;		
	}

	@Override
	public void editarCliente(int id, long cedula, long telefono, String direccion, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String empresa, String estatus) {
		String procedure = "call procedimientos_clientes.editar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(procedure, id, cedula, telefono, direccion, primerNombre, segundoNombre, primerApellido, segundoApellido, empresa, estatus);		
	}

}
