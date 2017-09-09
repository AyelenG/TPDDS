package model.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Indicador;
import model.data.HandlerArchivoJSON;

public class RepoIndicadores extends RepoArchivo<Indicador> {

	private static final RepoIndicadores instance = new RepoIndicadores();

	private final String RUTA = "data/Indicadores.json";

	private final List<Indicador> indicadoresPredefinidos = Arrays.asList(
			new Indicador("Ingreso Neto",
					"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]"),
			new Indicador("Retorno sobre capital total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]"),
			new Indicador("Nivel de deuda", "[FDS] * [FDS]"),
			new Indicador("Margen", "<PAPA> - [FDS]"));

	private RepoIndicadores() {
		this.insertarVarios(indicadoresPredefinidos);
	}

	public static RepoIndicadores getInstance() {
		return instance;
	}

	@Override
	public boolean sonIguales(Indicador i1, Indicador i2) {
		return i1.getNombre().equals(i2.getNombre());
	}

	/* Carga desde archivo JSON */
	public void cargar() {
		this.insertarVarios(new HandlerArchivoJSON(RUTA).<Indicador>load(Indicador.class));
	}

	/* Guardado en archivo JSON */
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Indicador>save(this.getIndicadoresDeUsuario());
	}

	public List<Indicador> getIndicadoresPredefinidos() {
		return indicadoresPredefinidos;
	}

	public List<Indicador> getIndicadoresDeUsuario() {
		return this.findAll().stream().filter(i -> !indicadoresPredefinidos.contains(i))
				.collect(Collectors.toList());
	}

	public void borrarIndicadoresDeUsuario() {
		this.findAll().removeIf(i -> !indicadoresPredefinidos.contains(i));
	}

}
