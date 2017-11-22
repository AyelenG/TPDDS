package application.server;

import java.util.Timer;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	
	//constantes de tiempo (en ms)
	private static int SEGUNDO = 1000;
	private static int MINUTO = 60 * SEGUNDO;
	private static long HORA = MINUTO * 60;
	@SuppressWarnings("unused")
	private static long DIA = HORA * 24;
	
	public static void main(String[] args) {
		
		agendarCargaCuentas(15 * MINUTO);
	    
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
    
    static void agendarCargaCuentas(long intervalo){
	    Timer t = new Timer();
	    TareaBatch tarea = new TareaBatch();
	    t.scheduleAtFixedRate(tarea, 0, intervalo);
    }

}
