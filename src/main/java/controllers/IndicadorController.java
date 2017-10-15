package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.repositories.RepoCuentas;
import model.repositories.RepoIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {
	
	@SuppressWarnings("rawtypes")
	public static ModelAndView cargaIndicador(Request req, Response res) {		
		Map<String, List> model = new HashMap<>();
		model.put("cuentas", RepoCuentas.getInstance().findAll());
		model.put("indicadores", RepoIndicadores.getInstance().findAll());
		return new ModelAndView(model, "/indicador/carga.hbs");
	}
}
