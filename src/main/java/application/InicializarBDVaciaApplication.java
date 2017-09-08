package application;

import model.repositories.RepoCuentasBD;
import model.repositories.RepoEmpresasBD;
import model.repositories.RepoMetodologiasBD;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoCuentasBD.getInstance().cargarBDDesdeArchivo();
		RepoEmpresasBD.getInstance().cargarBDDesdeArchivo();
		RepoMetodologiasBD.getInstance().cargarWarrenBuffet();
		RepoMetodologiasBD.getInstance().cargarBDDesdeArchivo();
//		RepoIndicadoresBD.getInstance().cargarBDDesdeArchivo();
	}

}
