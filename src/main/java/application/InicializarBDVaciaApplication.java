package application;

import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoCuentas.getInstance().migrarDesdeJSON();
		RepoEmpresas.getInstance().migrarDesdeJSON();
		RepoMetodologias.getInstance().cargarWarrenBuffet();
		RepoMetodologias.getInstance().migrarDesdeJSON();
		RepoIndicadores.getInstance().migrarDesdeJSON();
	}

}
