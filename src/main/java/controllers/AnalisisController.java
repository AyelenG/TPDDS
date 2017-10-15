package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Empresa;
import model.Metodologia;
import model.Usuario;
import model.repositories.RepoEmpresas;
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
	
	public static Object handleSeleccionarMetodologiaPost(Request req, Response res) {
		String idMetodologiaSeleccionada = req.queryParams("metodologia");		
		res.redirect("/analisis/metodologias/metodologia/" + idMetodologiaSeleccionada);
		return null;
	}
	
	public static ModelAndView handleAnalisisMetodologia(Request req, Response res) {
		Metodologia metodologia = RepoMetodologias.getInstance()
				.get(Long.parseLong(req.params("id")));
		List<Empresa> empresas = RepoEmpresas.getInstance().findAll();
		
		List<Empresa> validas = metodologia.obtenerValidas(empresas);
		List<Empresa> invalidas = metodologia.obtenerInvalidas(empresas);
		List<Empresa> deseables = metodologia.aplicar(validas);
		List<Empresa> noDeseables = metodologia.obtenerNoDeseables(validas);

		Map<String, Object> model = new HashMap<>();
		model.put("metodologia", metodologia);
		if(!deseables.isEmpty()){
			Empresa mejor = deseables.get(0);
			model.put("mejor", mejor);
			deseables.remove(mejor);
		}
		model.put("deseables", deseables);
		model.put("indeseables", noDeseables);
		model.put("invalidas", invalidas);
		return new ModelAndView(model, "analisis/analizar-metodologia.hbs");
	}
	
}
