package application.server;

import static spark.Spark.*;

import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import controllers.AnalisisController;
import controllers.HomeController;
import controllers.IndicadorController;
import controllers.LoginController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();
		
		staticFiles.location("/public");
		
		redirect.get("/", "/login"); 
		
		get("/login", LoginController::handleLoginGet, engine);
		post("/login", LoginController::handleLoginPost);
		get("/home", HomeController::showHome, engine);	
		post("/logout", LoginController.handleLogoutPost);
		get("/analisis/metodologias", AnalisisController::handleSeleccionarMetodologiaGet, engine);
		post("/analisis/metodologias", AnalisisController::handleSeleccionarMetodologiaPost);
		get("/indicador/carga", IndicadorController::cargaIndicador, engine);
		
	}

}
