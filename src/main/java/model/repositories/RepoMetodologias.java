package model.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Metodologia;
import model.data.HandlerArchivoJSON;

public class RepoMetodologias extends Repositorio<Metodologia> {

	private static final RepoMetodologias instance = new RepoMetodologias();

	private final String RUTA = "data/Metodologias.json";

	private final List<Metodologia> metodologiasPredefinidas = Arrays.asList(
			new Metodologia("Warren-Buffet",
					null,null,null) //falta crear la lista de condiciones NT,T,y combinadas
			);

	private RepoMetodologias() {
		this.agregarElementos(metodologiasPredefinidas);
	}

	public static RepoMetodologias getInstance() {
		return instance;
	}

	@Override
	public boolean sonIguales(Metodologia m1, Metodologia m2) {
		return m1.getNombre().equals(m2.getNombre());
	}

	/* Carga desde archivo JSON */
	public void cargar() {
		this.agregarElementos(new HandlerArchivoJSON(RUTA).<Metodologia>load(Metodologia.class));
	}

	/* Guardado en archivo JSON */
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Metodologia>save(this.getMetodologiasDeUsuario());
	}

	public List<Metodologia> getIndicadoresPredefinidos() {
		return metodologiasPredefinidas;
	}

	public List<Metodologia> getMetodologiasDeUsuario() {
		return this.getElementos().stream().filter(m -> !metodologiasPredefinidas.contains(m))
				.collect(Collectors.toList());
	}
	
	public void borrarMetodologiasDeUsuario() {
		this.getElementos().removeIf(i-> !metodologiasPredefinidas.contains(i));
	}

}
