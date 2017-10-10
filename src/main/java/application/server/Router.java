package application.server;

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
		
		get("/", LoginController::handleLoginGet, engine);
		post("/", LoginController::handleLoginPost, engine);
		get("/logout", LoginController.handleLogoutPost);
		get("/home", HomeController::showHome, engine);
		get("/analisis", AnalisisController::showResultado, engine);
		
	}

}
