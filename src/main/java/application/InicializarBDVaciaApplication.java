package application;

import org.uqbar.arena.Application;

import model.repositories.RepoCuentas;
import model.repositories.RepoCuentasBD;
import model.repositories.RepoEmpresas;
import model.repositories.RepoEmpresasBD;
import ui.windows.InicioWindow;

public class InicializarBDVaciaApplication extends Application {

	public static void main(String[] args) {
		RepoCuentasBD.getInstance().cargarBDDesdeArchivo();
		RepoEmpresasBD.getInstance().cargarBDDesdeArchivo();
		/*RepoIndicadores.getInstance().cargar();
		RepoMetodologias.getInstance().cargar();
		RepoEmpresas.getInstance().findAllBD();
		RepoCuentas.getInstance().findAllBD();*/
		//new InversionesApplication().start();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
