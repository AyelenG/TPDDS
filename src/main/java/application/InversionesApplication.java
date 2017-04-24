package application;

import org.uqbar.arena.Application;

import ui.windows.CargarArchivoYConsultarCuentasWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {
		new InversionesApplication().start();

	}

	@Override
	protected CargarArchivoYConsultarCuentasWindow createMainWindow() {
		return new CargarArchivoYConsultarCuentasWindow(this);
	}

}
