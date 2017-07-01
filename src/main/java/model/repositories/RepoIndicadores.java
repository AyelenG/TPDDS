package model.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Indicador;
import model.data.HandlerArchivoJSON;

public class RepoIndicadores extends Repositorio<Indicador> {

	private static final RepoIndicadores instance = new RepoIndicadores();

	private final String RUTA = "data/Indicadores.json";

	private final List<Indicador> indicadoresPredefinidos = Arrays.asList(
			new Indicador("Ingreso Neto",
					"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]"),
			new Indicador("Retorno sobre capital total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]"));

	private RepoIndicadores() {
		this.agregarElementos(indicadoresPredefinidos);
	}

	public static RepoIndicadores getInstance() {
		return instance;
	}

	@Override
	public boolean sonIguales(Indicador i1, Indicador i2) {
		return i1.getNombre().equals(i2.getNombre());
	}

	/*
	 * public void eliminarIndicadores(List<Indicador> indicadores) {
	 * indicadores.forEach(i -> this.eliminarIndicador(i)); }
	 * 
	 * public void eliminarIndicador(Indicador indicador) {
	 * this.indicadores.remove(indicador); }
	 */

	/* Carga desde archivo JSON */
	public void cargar() {
		this.agregarElementos(new HandlerArchivoJSON(RUTA).<Indicador>load(Indicador.class));
	}

	/* Guardado en archivo JSON */
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Indicador>save(this.getIndicadoresDeUsuario());
	}

	public List<Indicador> getIndicadoresPredefinidos() {
		return indicadoresPredefinidos;
	}

	public List<Indicador> getIndicadoresDeUsuario() {
		return this.getElementos().stream().filter(i -> !indicadoresPredefinidos.contains(i))
				.collect(Collectors.toList());
	}

}
