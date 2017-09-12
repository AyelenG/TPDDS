package model.repositories;

import java.util.Arrays;

import model.Indicador;

public class RepoIndicadores extends RepoBD<Indicador> {
	
	private static final RepoIndicadores instance = new RepoIndicadores();
	
	private RepoIndicadores() {
		this.entidad = Indicador.class;
	}
	
	public static RepoIndicadores getInstance() {
		return instance;
	}

	public void cargarPredefinidos(){
		this.insertarVarios(Arrays.asList(
				new Indicador("Ingreso Neto",
						"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]"),
				new Indicador("Retorno sobre capital total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]"),
				new Indicador("Nivel de deuda", "[FDS] * [FDS]"),
				new Indicador("Margen", "<PAPA> - [FDS]")));
	}
	@Override
	protected String valorDeBusqueda(Indicador elemento) {
		return elemento.getNombre();
	}

}
