package controllers;

import model.Usuario;
import model.repositories.RepoUsuarios;

public class UsuarioController {
    
	public static Usuario usuarioActual;
	
	public static boolean autenticar(Usuario usuario) {
        if (usuario.getNombre().isEmpty() || usuario.getPass().isEmpty())
            return false;
        usuarioActual = RepoUsuarios.getInstance().buscarElemento(usuario);
        if (usuarioActual == null)
        	return false;
        if (usuario.getPass().equals(usuarioActual.getPass()))
        	return true;
        usuarioActual = null;
    	return false;
    }

}
