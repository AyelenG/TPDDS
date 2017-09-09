package application;

import model.repositories.RepoCuentasBD;
import model.repositories.RepoEmpresasBD;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoCuentasBD.getInstance().cargarBDDesdeArchivo();
		RepoEmpresasBD.getInstance().cargarBDDesdeArchivo();
		RepoMetodologias.getInstance().cargarWarrenBuffet();
		RepoMetodologias.getInstance().cargarBDDesdeArchivo();
		RepoIndicadores.getInstance().cargarBDDesdeArchivo();
	}

}
