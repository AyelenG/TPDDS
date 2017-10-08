package model.repositories;

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
	protected String valorDeBusqueda(Usuario elemento) {
		return elemento.getNombre();
	}

}