package model.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.Metodologia;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.combinadas.Longevidad;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativa;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Tendencia;
import model.data.HandlerArchivoJSON;

public class RepoMetodologias extends Repositorio<Metodologia> {

	private static final RepoMetodologias instance = new RepoMetodologias();

	private final String RUTA = "data/Metodologias.json";

	private final List<Metodologia> metodologiasPredefinidas = new LinkedList<>();

	{
		List<CondicionNoTaxativa> condicionesNT = new LinkedList<>(); //peso 5 ambas condiciones
		condicionesNT.add(new CondicionNoTaxativaConfigurable("Max. ROE - 10 años", 5, new Mayor(),
				"Retorno sobre capital total", 10));
		condicionesNT.add(new CondicionNoTaxativaConfigurable("Min. Nv.Deuda - 1 año", 5, new Menor(),
				"Nivel de deuda", 1));

		List<CondicionTaxativa> condicionesT = new LinkedList<>();
		condicionesT.add(new CondicionTaxativaConfigurable("Margen Creciente - 10 años > 50", new Mayor(),
				new Tendencia(),"Margen", 10, null));

		List<CondicionCombinada> condicionesComb = new LinkedList<>();
		condicionesComb.add(new Longevidad());
		
		Metodologia warrenBuffet = new Metodologia("Warren-Buffet", condicionesNT, condicionesT, condicionesComb);
		metodologiasPredefinidas.add(warrenBuffet);
	}

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
		this.getElementos().removeIf(i -> !metodologiasPredefinidas.contains(i));
	}

}
