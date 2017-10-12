package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Indicador;
import model.Usuario;

public class RepoIndicadores extends RepoBD<Indicador> {
	
	private static final RepoIndicadores instance = new RepoIndicadores();
	
	private RepoIndicadores() {
		this.entidad = Indicador.class;
	}
	
	public static RepoIndicadores getInstance() {
		return instance;
	}

	@Override
	protected List<String> camposDeBusqueda() {
		return Arrays.asList("nombre","user");
	}
	
	@Override
	protected List<Object> valoresDeBusqueda(Indicador ind) {
		Usuario user = ind.getUser();
		if(user == null)
			user = RepoUsuarios.getInstance().getAdmin();
		return Arrays.asList(ind.getNombre(),user);
	}
}
