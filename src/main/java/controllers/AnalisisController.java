package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NoSePuedeAplicarException;
import model.Empresa;
import model.Metodologia;
import model.Periodo;
import model.Usuario;
import model.precalculo.IndicadorPeriodoConValor;
import model.precalculo.IndicadorPeriodoSinValor;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadoresPeriodosConValor;
import model.repositories.RepoIndicadoresPeriodosSinValor;
import model.repositories.RepoMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AnalisisController {

	public static ModelAndView handleSeleccionarMetodologia(Request req, Response res) {
		if(req.queryParams("metodologia") != null){
			String idMetodologiaSeleccionada = req.queryParams("metodologia");		
			res.redirect("/analisis/metodologias/" + idMetodologiaSeleccionada);
		}
		Usuario user = req.session().attribute("currentUser");
		Map<String, List<Metodologia>> model = new HashMap<>();
		List<Metodologia> metodologiasDeUser = RepoMetodologias.getInstance().findAllBy("user", user.getId());
		model.put("metodologias", metodologiasDeUser);
		return new ModelAndView(model, "analisis/seleccionar-metodologia.hbs");
	}
	
	public static ModelAndView handleAnalisisMetodologia(Request req, Response res) {
		Metodologia metodologia = RepoMetodologias.getInstance()
				.get(Long.parseLong(req.params("id")));
		Map<String, Object> model = new HashMap<>();
		List<Empresa> empresas = RepoEmpresas.getInstance().findAll();	
		List<Empresa> validas = null;
		List<Empresa> invalidas = null;
		List<Empresa> deseables = null;
		List<Empresa> noDeseables = null;
		try{
			validas = metodologia.obtenerValidas(empresas);
			invalidas = metodologia.obtenerInvalidas(empresas);
			deseables = metodologia.aplicar(validas);
			noDeseables = metodologia.obtenerNoDeseables(validas);
			if(!deseables.isEmpty()){
				Empresa mejor = deseables.get(0);
				model.put("mejor", mejor);
				deseables.remove(mejor);
			}
			model.put("deseables", deseables);
			model.put("indeseables", noDeseables);
			model.put("invalidas", invalidas);
		}
		catch(NoSePuedeAplicarException e){
			model.put("error", e.getMessage());
		}
		model.put("metodologia", metodologia);
		return new ModelAndView(model, "analisis/analizar-metodologia.hbs");
	}
	
	public static ModelAndView handleEvaluarIndicadores(Request req, Response res){
		Usuario user = req.session().attribute("currentUser");
		
		long idEmpresa = Long.valueOf(req.params("empresa")).longValue();
		Integer anio = Integer.valueOf(req.params("periodo"));
		Empresa empresa = RepoEmpresas.getInstance().get(idEmpresa);
		Periodo periodo = empresa.buscarPeriodo(new Periodo(anio));

		List<IndicadorPeriodoConValor> indicadoresConValor = RepoIndicadoresPeriodosConValor.getInstance().findAllByUserYPeriodo(periodo, user);
		List<IndicadorPeriodoSinValor> indicadoresSinValor = RepoIndicadoresPeriodosSinValor.getInstance().findAllByUserYPeriodo(periodo, user);
		
		Map<String, Object> model = new HashMap<>();
		model.put("empresa",empresa);
		model.put("periodo", periodo);
		model.put("indicadoresOK", indicadoresConValor);
		model.put("indicadoresError", indicadoresSinValor);
		return new ModelAndView(model,"analisis/evaluar-indicadores.hbs");
	}
	
	public static ModelAndView handleSeleccionarEmpresaPeriodo(Request req, Response res) {
		String empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		if(empresa != null && periodo != null){
			res.redirect("/analisis/indicadores/" + empresa +"/" + periodo);
		}
		List<Empresa> empresas = RepoEmpresas.getInstance().findAll();		
		Map<String, Object> model = new HashMap<>();		 	
		model.put("empresas", empresas);
		
		return new ModelAndView(model, "analisis/seleccionar-empresa-periodo.hbs");
	}
	
}
