package application.server;

import static spark.Spark.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import controllers.AnalisisController;
import controllers.EmpresasController;
import controllers.HomeController;
import controllers.IndicadoresController;
import controllers.LoginController;
import controllers.BuscadorController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();
		
		staticFiles.location("/public");
		
		redirect.get("/", "/home"); 
		
		before((request, response) -> {
			if (request.session().attribute("currentUser") == null 
					&& isPublic(request.pathInfo()) == false) {
				response.redirect("/login");
			}
		});
		
		after((request, response) -> {
		    PerThreadEntityManagers.getEntityManager().clear();
		});
		
		get("/home", HomeController::showHome, engine);	
		
		get("/login", LoginController::handleLoginGet, engine);
		post("/login", LoginController::handleLoginPost);
		get("/logout", LoginController.handleLogout);
		
		get("/analisis/metodologias", AnalisisController::handleSeleccionarMetodologia, engine);
		get("/analisis/metodologias/metodologia/:id",AnalisisController::handleAnalisisMetodologia,engine);
		
		get("/analisis/indicadores", AnalisisController::handleSeleccionarEmpresaPeriodo,engine);
		get("/analisis/indicadores/:empresa/:periodo", AnalisisController::handleEvaluarIndicadores,engine);

		get("/empresas", EmpresasController::handleEmpresas, engine);
		get("/empresas/cuentas/:empresa", EmpresasController::handleSeleccionPeriodo, engine);
		get("/empresas/cuentas/:empresa/:periodo", EmpresasController::handleCuentas, engine);

		get("/indicadores", IndicadoresController::lista, engine);
		get("/indicadores/carga", IndicadoresController::carga, engine);
		post("/indicadores/carga", IndicadoresController::verificacion, engine);
		
		get("/periodos", BuscadorController::periodos,engine);

	}

	private static boolean isPublic(String pathInfo) {
		return pathInfo.equals("/") || pathInfo.equals("/home") || pathInfo.equals("/login");
	}

}
