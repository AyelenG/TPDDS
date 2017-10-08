package server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import controllers.AnalisisController;
import controllers.LoginController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();

		Spark.staticFiles.location("/public");

		Spark.get("/login", LoginController::showLogin, engine);
		Spark.get("/analisis", AnalisisController::showResultado, engine);
		
	}

}
