package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

public static ModelAndView showHome(Request req, Response res) {
		Map<String,Object> model = new HashMap<>();
		Usuario currentUser = req.session().attribute("currentUser");
		model.put("usuario", currentUser);
		return new ModelAndView(model, "home.hbs");
	}
}
