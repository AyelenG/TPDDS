package application;

import org.uqbar.arena.Application;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ui.windows.InicioWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {

		new InversionesApplication().start();
		PerThreadEntityManagers.closeEntityManager();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
