package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Usuario;

public class RepoUsuarios extends RepoBD<Usuario> {
	
	private static final RepoUsuarios instance = new RepoUsuarios();
	
	private RepoUsuarios() {
		this.entidad = Usuario.class;
	}
	
	public static RepoUsuarios getInstance() {
		return instance;
	}

	@Override
	protected List<Object> valoresDeBusqueda(Usuario elemento) {
		return Arrays.asList(elemento.getNombre());
	}

	public Usuario getAdmin() {
		return this.buscarElemento(new Usuario("admin"));
	}

}