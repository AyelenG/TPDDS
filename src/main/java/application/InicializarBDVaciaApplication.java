package application;

import org.uqbar.arena.Application;

import model.repositories.RepoCuentas;
import ui.windows.InicioWindow;

public class InicializarBDVaciaApplication extends Application {

	public static void main(String[] args) {
		RepoCuentas.getInstance().cargarBDDesdeArchivo();
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
