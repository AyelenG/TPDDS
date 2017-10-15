package controllers;

import model.Usuario;
import model.repositories.RepoUsuarios;

public class UsuarioController {
    
	public static Usuario autenticar(String nombre, String pass) {
        if (nombre.isEmpty() || pass.isEmpty())
            return null;
        Usuario usuarioEncontrado = RepoUsuarios.getInstance().buscarElemento(new Usuario(nombre));
        if (usuarioEncontrado == null)
        	return null;
        if (pass.equals(usuarioEncontrado.getPass()))
        	return usuarioEncontrado;
    	return null;
    }

}
