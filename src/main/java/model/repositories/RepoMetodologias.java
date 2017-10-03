package model.repositories;

import model.Metodologia;

public class RepoMetodologias extends RepoBD<Metodologia> {
	
	private static final RepoMetodologias instance = new RepoMetodologias();

	private RepoMetodologias() {
		this.entidad = Metodologia.class;
	}
	
	public static RepoMetodologias getInstance() {
		return instance;
	}
	
	@Override
	protected String valorDeBusqueda(Metodologia elemento) {
		return elemento.getNombre();
	}

}
