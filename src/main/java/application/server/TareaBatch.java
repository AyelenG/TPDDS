package application.server;

import java.util.TimerTask;

import application.cargacuentas.CargaCuentasEmpresas;
import application.precalculoindicadores.PrecalculoIndicadores;

public class TareaBatch extends TimerTask {

	@Override
	public void run() {
		new CargaCuentasEmpresas().cargar();
		new PrecalculoIndicadores().precalcularTodos();
	}

}
