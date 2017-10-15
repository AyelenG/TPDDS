package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Metodologia;
import model.Usuario;
import model.repositories.RepoMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AnalisisController {

	public static ModelAndView handleSeleccionarMetodologiaGet(Request req, Response res) {
		Usuario user = req.session().attribute("currentUser");
		Map<String, List<Metodologia>> model = new HashMap<>();
		List<Metodologia> metodologiasDeUser = RepoMetodologias.getInstance().findAllBy("user", user.getId());
		model.put("metodologias", metodologiasDeUser);
		return new ModelAndView(model, "analisis/seleccionar-metodologia.hbs");
	}
	
	public static String handleSeleccionarMetodologiaPost(Request req, Response res) {
		Long idMetodologiaSeleccionada = Long.parseLong(req.queryParams("metodologia"));
		Metodologia metodologiaSeleccionada = RepoMetodologias.getInstance()
												.get(idMetodologiaSeleccionada);
		
		return "Meto seleccionada" + metodologiaSeleccionada.getNombre();
	}
}
