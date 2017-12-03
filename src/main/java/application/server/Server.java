package application.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	
	public static void main(String[] args) {
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

}
