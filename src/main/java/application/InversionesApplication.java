package application;

import org.uqbar.arena.Application;

import ui.windows.InicioWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {
		new InversionesApplication().start();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
