package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

	public static ModelAndView showLogin(Request req, Response res) {
		Usuario usuario = new Usuario(req.queryParams("nombre"), req.queryParams("pass"));
		Map<String, Object> viewModel = new HashMap<String, Object>();
		viewModel.put("usuario", usuario);
		return new ModelAndView(viewModel, "showLogin.hbs");
	}
}
