package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Empresa;
import model.Periodo;
import model.repositories.RepoEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BuscadorController {
	
	public static ModelAndView periodos(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		String idEmpresaSeleccionada = req.queryParams("id");
		long id = Long.valueOf(idEmpresaSeleccionada).longValue();
		Empresa empresa = RepoEmpresas.getInstance().get(id);
		List<Periodo> periodos = empresa.getPeriodos();
		model.put("periodos", periodos);
		return new ModelAndView(model, "/periodos.hbs");
	}

}
