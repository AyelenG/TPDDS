package application;


import model.data.HandlerArchivoJSON;
import model.repositories.RepoCuentasBD;
import model.repositories.RepoEmpresasBD;

public class InicializarBDVaciaApplication {

	public static void main(String[] args) {
		RepoCuentasBD.getInstance().cargarBDDesdeArchivo();
//		RepoEmpresasBD.getInstance().cargarBDDesdeArchivo(new HandlerArchivoJSON("data/CuentasPrueba.json"));
		/*RepoIndicadores.getInstance().cargar();
		RepoMetodologias.getInstance().cargar();
		RepoEmpresas.getInstance().findAllBD();
		RepoCuentas.getInstance().findAllBD();*/
	}
	

}
