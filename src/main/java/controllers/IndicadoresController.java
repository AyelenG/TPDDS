package controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import application.precalculoindicadores.PrecalculoIndicadores;
import exceptions.FormulaIndicadorIncorrectaException;
import model.Indicador;
import model.Usuario;
import model.parser.ExpresionBuilder;
import model.repositories.RepoCuentas;
import model.repositories.RepoIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	
	public static ModelAndView carga(Request request, Response response) {
    	Usuario currentUser = request.session().attribute("currentUser");
		Map<String, Object> model = new HashMap<>();
		model.put("cuentas", RepoCuentas.getInstance().findAll());
		model.put("indicadores", RepoIndicadores.getInstance().findAllBy("user", currentUser.getId()));
		return new ModelAndView(model, "/indicadores/carga.hbs");
	}
	
	public static ModelAndView verificacion(Request request, Response response) {
    	Usuario currentUser = request.session().attribute("currentUser");
    	Indicador indicador = new Indicador(request.queryParams("nombreIndicador"));
    	Map<String, Object> model = new HashMap<>();
    	String formula = StringEscapeUtils.unescapeHtml(request.queryParams("formula"));
    	try {
    		new ExpresionBuilder(formula).build();
    	}
    	catch (FormulaIndicadorIncorrectaException e) {
    		model.put("cargaFallida", true);
    		return new ModelAndView(model, "/indicadores/carga.hbs");
    	}
		indicador.setFormula(formula);
		indicador.setUser(currentUser);
		RepoIndicadores.getInstance().insertar(indicador);
		
		PrecalculoIndicadores precalculo = new PrecalculoIndicadores();
		precalculo.precalcularNuevo(indicador);
		
		model.put("cargaExitosa", true);
		return new ModelAndView(model, "/indicadores/carga.hbs");
	}
	
	public static ModelAndView lista(Request request, Response response) {
    	Usuario currentUser = request.session().attribute("currentUser");
		Map<String, Object> model = new HashMap<>();
		model.put("indicadores", RepoIndicadores.getInstance().findAllBy("user", currentUser.getId()));
		return new ModelAndView(model, "/indicadores/lista.hbs");
	}

}
