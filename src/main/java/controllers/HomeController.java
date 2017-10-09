package controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

public static ModelAndView showHome(Request req, Response res) {
		
		Map<String,Object> model = new HashMap<>();
		return new ModelAndView(model, "home.hbs");
	}
}
