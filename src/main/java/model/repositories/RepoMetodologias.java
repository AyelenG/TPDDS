package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Metodologia;
import model.Usuario;

public class RepoMetodologias extends RepoBD<Metodologia> {
	
	private static final RepoMetodologias instance = new RepoMetodologias();

	private RepoMetodologias() {
		this.entidad = Metodologia.class;
	}
	
	public static RepoMetodologias getInstance() {
		return instance;
	}
	
	@Override
	protected List<String> camposDeBusqueda() {
		return Arrays.asList("nombre","user");
	}
	
	@Override
	protected List<Object> valoresDeBusqueda(Metodologia met) {
		Usuario user = met.getUser();
		if(user == null)
			user = RepoUsuarios.getInstance().getAdmin();
		return Arrays.asList(met.getNombre(),user);
	}
}
