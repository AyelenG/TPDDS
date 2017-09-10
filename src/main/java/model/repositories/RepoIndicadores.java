package model.repositories;

import model.Indicador;

public class RepoIndicadores extends RepoBD<Indicador> {
	
	private static final RepoIndicadores instance = new RepoIndicadores();
	
	private RepoIndicadores() {
		this.entidad = Indicador.class;
	}
	
	public static RepoIndicadores getInstance() {
		return instance;
	}

	@Override
	protected String valorDeBusqueda(Indicador elemento) {
		return elemento.getNombre();
	}

}
