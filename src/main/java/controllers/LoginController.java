package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController {

    public static ModelAndView handleLoginGet(Request request, Response response) {
    	Map<String, Object> viewModel = new HashMap<>();
    	return new ModelAndView(viewModel, "login.hbs");
    }
    
    public static ModelAndView handleLoginPost(Request request, Response response) {
    	Usuario usuario = new Usuario(request.queryParams("nombre"), request.queryParams("pass"));
    	Map<String, Object> viewModel = new HashMap<>();
        if (UsuarioController.autenticar(usuario)) {
            viewModel.put("autentificacionSatisfactoria", true);
            viewModel.put("usuario", usuario);            
            request.session().attribute("currentUser", usuario.getNombre());
	          if (request.queryParams("loginRedirect") != null)
	        	  response.redirect(request.queryParams("loginRedirect"));
            return new ModelAndView(viewModel, "home.hbs");
        }
    	viewModel.put("autentificacionFallida", true);
    	return new ModelAndView(viewModel, "login.hbs");
    }
    
    public static Route handleLogoutPost = (Request request, Response response) -> {
        UsuarioController.usuarioActual = null;
    	request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect("/");
        return null;
    };

    // El origen del request (request.pathInfo()) se salva en la sesión
    // el usuario es redirigido despues del login
    public static void chequearUsuarioLogueado(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect("/");
        }
    };
    
}
