package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public static ModelAndView handleLoginGet(Request request, Response response) {
    	Map<String, Object> viewModel = new HashMap<>();
    	viewModel.put("autenticado", true);
    	return new ModelAndView(viewModel, "login.hbs");
    }
    
    public static ModelAndView handleLoginPost(Request request, Response response) {
    	Usuario usuario = new Usuario(request.queryParams("nombre"), request.queryParams("pass"));
    	Map<String, Object> viewModel = new HashMap<>();
        if (UsuarioController.autenticar(usuario)) {
            viewModel.put("autenticado", true);
            viewModel.put("usuario", usuario);            
            request.session().attribute("currentUser", usuario.getNombre());
            return new ModelAndView(viewModel, "home.hbs");
        }
    	viewModel.put("autenticado", false);
    	return new ModelAndView(viewModel, "login.hbs");
    }
    
}
