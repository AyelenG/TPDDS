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
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000;
    }

}
