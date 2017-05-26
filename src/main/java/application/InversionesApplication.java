package application;

import org.uqbar.arena.Application;

import model.repositories.Repositorios;
import ui.windows.InicioWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {
		Repositorios.repoCuentas.cargar();
		Repositorios.repoIndicadores.cargar();
		new InversionesApplication().start();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
