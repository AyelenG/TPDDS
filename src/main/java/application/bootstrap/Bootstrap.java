package application.bootstrap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import model.Indicador;
import model.Metodologia;
import model.Usuario;
import model.condiciones.Comparadores;
import model.condiciones.Condicion;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.TiposCondicionTaxativa;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoBD;
import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;
import model.repositories.RepoUsuarios;

public class Bootstrap {

	public void init() {
		this.initPredefinidos();
		Usuario admin = RepoUsuarios.getInstance().getAdmin(); 
		this.initFromJSON(RepoCuentas.getInstance(),null);
		this.initFromJSON(RepoEmpresas.getInstance(),null);
		this.initFromJSON(RepoIndicadores.getInstance(),(ind) -> ind.setUser(admin));
		this.initFromJSON(RepoMetodologias.getInstance(),(met) -> met.setUser(admin));
	}

	private <T> List<T> getFromJSON(Class<T> entidad) {
		return new HandlerArchivoJSON("data/" + entidad.getSimpleName() + ".json").<T>load(entidad);	
	}

	private <T> void initFromList(RepoBD<T> repo,List<T> elements){
		repo.insertarVarios(elements);
	}
	
	private <T> void initFromJSON(RepoBD<T> repo, Consumer<T> modif) {
		Class<T> entidad = repo.getEntidad();
		List<T> list = getFromJSON(entidad);
		if(modif != null)
			list.forEach(modif);
		this.initFromList(repo,list);
	}

	private void initPredefinidos() {
		initUsuariosP();
		initIndicadoresP();
		initMetodologiasP();
	}

	private void initMetodologiasP() {
		Usuario admin = RepoUsuarios.getInstance().getAdmin();
		RepoBD<Metodologia> repo = RepoMetodologias.getInstance();
		List<Condicion> condiciones = new LinkedList<>();
		condiciones.add(new CondicionNoTaxativaConfigurable("Max. ROE - 10 años", 5, Comparadores.Mayor,
				"Retorno sobre capital total", 10));
		condiciones
				.add(new CondicionNoTaxativaConfigurable("Min. Nv.Deuda - 1 año", 5, Comparadores.Menor, "Nivel de deuda", 1));
		condiciones.add(new CondicionTaxativaConfigurable("Margen Creciente - 10 años > 50", Comparadores.Mayor,
				TiposCondicionTaxativa.Tendencia, "Margen", 10, null));
		condiciones.add(new Longevidad());
		repo.insertar(new Metodologia("Warren-Buffet", condiciones, admin));

	}

	private void initIndicadoresP() {
		Usuario admin = RepoUsuarios.getInstance().getAdmin();
		RepoIndicadores.getInstance().insertarVarios(Arrays.asList(
				new Indicador("Ingreso Neto",
						"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]",
						 admin),
				new Indicador("Retorno sobre capital total",
							"(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]",
							admin),
				new Indicador("Nivel de deuda",
							"[FDS] * [FDS]",
							admin),
				new Indicador("Margen", 
							"<PAPA> - [FDS]",
							admin)));
	}
	
	private void initUsuariosP() {
		RepoUsuarios.getInstance().insertarVarios(Arrays.asList(
				new Usuario("admin", "admin"),
				new Usuario("juan", "juan"),
				new Usuario("maria", "maria")));
	}

}
