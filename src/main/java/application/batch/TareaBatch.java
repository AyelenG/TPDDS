package application.batch;

import java.util.Timer;
import java.util.TimerTask;

import application.cargacuentas.CargaCuentasEmpresas;
import application.precalculoindicadores.PrecalculoIndicadores;

public class TareaBatch extends TimerTask {

	//constantes de tiempo (en ms)
	private static int SEGUNDO = 1000;
	private static int MINUTO = 60 * SEGUNDO;
	private static long HORA = MINUTO * 60;
	@SuppressWarnings("unused")
	private static long DIA = HORA * 24;
	
	public static void main(String[] args) {
		agendarTarea(15 * MINUTO);
	}

	@Override
	public void run() {
		new CargaCuentasEmpresas().cargar();
		new PrecalculoIndicadores().precalcularTodos();
	}

    static void agendarTarea(long intervalo) {
	    Timer timer = new Timer();
	    TareaBatch tarea = new TareaBatch();
	    timer.scheduleAtFixedRate(tarea, 0, intervalo);
    }

}
