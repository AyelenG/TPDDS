package application;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class InicializadorBD {

	public static void main(String[] args) {
		new Bootstrap().init();
		PerThreadEntityManagers.closeEntityManager();
		System.out.println("Migración terminada (usar boton de stop de Eclipse)");
	}

}
