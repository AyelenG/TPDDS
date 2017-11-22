package application.precalculoindicadores;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import model.precalculo.PrecalculoIndicadores;


public class Precalculo {
	
	public static void main(String[] args) {
		PrecalculoIndicadores precalculo = new PrecalculoIndicadores();
		precalculo.precalcularIndicadores();	
		PerThreadEntityManagers.closeEntityManager();
		System.out.println("Precalculo terminado (usar boton de stop de Eclipse)");
	}

	
}
