package application;

import model.repositories.RepoCuentasBD;
import model.repositories.RepoEmpresasBD;
import model.repositories.RepoMetodologiasBD;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoEmpresasBD.getInstance().cargarBDDesdeArchivo();
		RepoCuentasBD.getInstance().cargarBDDesdeArchivo();
		RepoMetodologiasBD.getInstance().cargarBDDesdeArchivo();
		RepoMetodologiasBD.getInstance().cargarWarrenBuffet();
//		RepoIndicadoresBD.getInstance().cargarBDDesdeArchivo();
	}

}
