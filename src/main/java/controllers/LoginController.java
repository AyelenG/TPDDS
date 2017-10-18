package controllers;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import model.repositories.RepoUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController {

    public static ModelAndView handleLoginGet(Request request, Response response) {
    	
    	/*Funcionamiento de sesiones:
    	 * La sesion se mantiene abierta para un mismo navegador,
    	 * incluso si se cierra la ventana, y es la misma para todas las pestañas 
    	 * Con otro navegador se crea otra sesion
    	 * Si se cierra el server se borran todas las sesiones
    	 */
    	
    	//si ya esta logueado el usuario en esta sesion voy a la home
    	Usuario currentUser = request.session().attribute("currentUser");
    	if( currentUser != null){
    		System.out.println(currentUser.getNombre());
    		response.redirect("/home");
    	}
    	//si tengo un parametro authError muestra la pantalla con el alert-message
    	Map<String, Object> model = new HashMap<>();
    	if(request.queryParams("authError") != null)
    		model.put("autentificacionFallida", true);
    	return new ModelAndView(model, "/login.hbs");
    }
    
    public static Object handleLoginPost(Request request, Response response) {
        Usuario usuario = RepoUsuarios.getInstance().autenticar
        		(request.queryParams("nombre"),request.queryParams("pass"));
    	if (usuario != null) {        
            request.session().attribute("currentUser", usuario);
	        response.redirect("/home");  
            return null;
        }
    	response.redirect("/login?authError");
    	return null;
    }
    
    public static Route handleLogoutPost = (Request request, Response response) -> {
    	request.session().removeAttribute("currentUser");
        response.redirect("/");
        return null;
    };
    
}
