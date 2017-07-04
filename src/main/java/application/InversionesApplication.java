package application;

import org.uqbar.arena.Application;

import model.repositories.RepoCuentas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;
import ui.windows.InicioWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {
		RepoCuentas.getInstance().cargar();
		RepoIndicadores.getInstance().cargar();
		RepoMetodologias.getInstance().cargar();
		new InversionesApplication().start();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
