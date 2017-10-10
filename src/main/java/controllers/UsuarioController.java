package controllers;

import model.Usuario;
import model.repositories.RepoUsuarios;

public class UsuarioController {
    
	public static boolean autenticar(Usuario usuario) {
        if (usuario.getNombre().isEmpty() || usuario.getPass().isEmpty())
            return false;
        Usuario usuarioEncontrado = RepoUsuarios.getInstance().buscarElemento(usuario);
        if (usuarioEncontrado == null)
        	return false;
        if (usuario.getPass().equals(usuarioEncontrado.getPass()))
        	return true;
    	return false;
    }

}
