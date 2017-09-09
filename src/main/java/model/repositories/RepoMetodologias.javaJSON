package model.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.Metodologia;
import model.condiciones.Condicion;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Tendencia;
import model.data.HandlerArchivoJSON;

public class RepoMetodologias extends RepoArchivo<Metodologia> {

	private static final RepoMetodologias instance = new RepoMetodologias();

	private final String RUTA = "data/Metodologias.json";

	private final List<Metodologia> metodologiasPredefinidas = new LinkedList<>();

	{
		List<Condicion> condiciones = new LinkedList<>(); 
		condiciones.add(new CondicionNoTaxativaConfigurable("Max. ROE - 10 años", 5, new Mayor(),
				"Retorno sobre capital total", 10));
		condiciones.add(new CondicionNoTaxativaConfigurable("Min. Nv.Deuda - 1 año", 5, new Menor(),
				"Nivel de deuda", 1));
		condiciones.add(new CondicionTaxativaConfigurable("Margen Creciente - 10 años > 50", new Mayor(),
				new Tendencia(),"Margen", 10, null));
		condiciones.add(new Longevidad());
		
		Metodologia warrenBuffet = new Metodologia("Warren-Buffet", condiciones);
		metodologiasPredefinidas.add(warrenBuffet);
	}

	private RepoMetodologias() {
		this.insertarVarios(metodologiasPredefinidas);
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
		this.insertarVarios(new HandlerArchivoJSON(RUTA).<Metodologia>load(Metodologia.class));
	}

	/* Guardado en archivo JSON */
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Metodologia>save(this.getMetodologiasDeUsuario());
	}

	public List<Metodologia> getIndicadoresPredefinidos() {
		return metodologiasPredefinidas;
	}

	public List<Metodologia> getMetodologiasDeUsuario() {
		return this.findAll().stream().filter(m -> !metodologiasPredefinidas.contains(m))
				.collect(Collectors.toList());
	}

	public void borrarMetodologiasDeUsuario() {
		this.findAll().removeIf(i -> !metodologiasPredefinidas.contains(i));
	}

}
