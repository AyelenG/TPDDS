package server;

import static spark.Spark.*;

import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import controllers.AnalisisController;
import controllers.HomeController;
import controllers.LoginController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();
		
		staticFiles.location("/public");
		
		//redirect.get("/","/home"); si no esta index.html, uso /home como index
		
		get("/home",HomeController::showHome,engine);
		get("/login", LoginController::showLogin, engine);
		get("/analisis", AnalisisController::showResultado, engine);
		
	}

}
