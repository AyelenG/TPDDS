package application;

import org.uqbar.arena.Application;

import model.Indicadores;
import model.data.CuentasPredeterminadas;
import ui.windows.InicioWindow;

public class InversionesApplication extends Application {

	public static void main(String[] args) {
		CuentasPredeterminadas.cargar();
		Indicadores.cargar();
		new InversionesApplication().start();
	}
	
	@Override
	protected InicioWindow createMainWindow() {
		return new InicioWindow(this);
	}

}
