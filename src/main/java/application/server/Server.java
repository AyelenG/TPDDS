package application.server;

import java.util.Timer;

import application.cargacuentas.CargaCuentasEmpresas;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	
	//constantes de tiempo (en ms)
	private static int SEGUNDO = 1000;
	private static int MINUTO = 60 * SEGUNDO;
	private static int HORA = MINUTO * 60;
	private static int DIA = HORA * 24;
	
	public static void main(String[] args) {
		
		agendarCargaCuentas(1 * MINUTO);
	    
		Spark.port(getHerokuAssignedPort());
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String puerto = processBuilder.environment().get("PORT");
        if (puerto != null) {
            return Integer.parseInt(puerto);
        }
        return 9000;
    }
    
    static void agendarCargaCuentas(int intervalo){
	    Timer t = new Timer();
	    CargaCuentasEmpresas tarea = new CargaCuentasEmpresas();
	    t.scheduleAtFixedRate(tarea, 0, intervalo);
    }

}
