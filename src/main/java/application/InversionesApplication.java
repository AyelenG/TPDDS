package application;

import java.util.Date;
import java.util.GregorianCalendar;

import org.uqbar.arena.Application;

import com.ibm.icu.util.Calendar;

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
