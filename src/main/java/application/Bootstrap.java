package application;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Indicador;
import model.Metodologia;
import model.condiciones.Condicion;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Tendencia;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoBD;
import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;

public class Bootstrap {

	public void init() {
		this.initPredefinidos();
		this.initFromJSON(RepoCuentas.getInstance());
		this.initFromJSON(RepoEmpresas.getInstance());
		this.initFromJSON(RepoIndicadores.getInstance());
		this.initFromJSON(RepoMetodologias.getInstance());
	}

	private <T> void initFromJSON(RepoBD<T> repo) {
		Class<T> entidad = repo.getEntidad();
		repo.insertarVarios(new HandlerArchivoJSON("data/" + entidad.getSimpleName() + ".json").<T>load(entidad));
	}

	private void initPredefinidos() {
		initIndicadoresP();
		initMetodologiasP();
	}

	private void initMetodologiasP() {
		RepoBD<Metodologia> repo = RepoMetodologias.getInstance();
		List<Condicion> condiciones = new LinkedList<>();
		condiciones.add(new CondicionNoTaxativaConfigurable("Max. ROE - 10 años", 5, new Mayor(),
				"Retorno sobre capital total", 10));
		condiciones
				.add(new CondicionNoTaxativaConfigurable("Min. Nv.Deuda - 1 año", 5, new Menor(), "Nivel de deuda", 1));
		condiciones.add(new CondicionTaxativaConfigurable("Margen Creciente - 10 años > 50", new Mayor(),
				new Tendencia(), "Margen", 10, null));
		condiciones.add(new Longevidad());
		repo.insertar(new Metodologia("Warren-Buffet", condiciones));

	}

	private void initIndicadoresP() {
		RepoIndicadores.getInstance().insertarVarios(Arrays.asList(
				new Indicador("Ingreso Neto",
						"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]"),
				new Indicador("Retorno sobre capital total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]"),
				new Indicador("Nivel de deuda", "[FDS] * [FDS]"), new Indicador("Margen", "<PAPA> - [FDS]")));
	}
}
