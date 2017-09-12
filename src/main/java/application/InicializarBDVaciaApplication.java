package application;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoCuentas.getInstance().migrarDesdeJSON();
		RepoEmpresas.getInstance().migrarDesdeJSON();
		RepoMetodologias.getInstance().cargarPredefinidas();
		RepoMetodologias.getInstance().migrarDesdeJSON();
		RepoIndicadores.getInstance().cargarPredefinidos();
		RepoIndicadores.getInstance().migrarDesdeJSON();
		PerThreadEntityManagers.closeEntityManager();
		System.out.println("Migración terminada (pero el programa sigue corriendo :( )");
	}

}
