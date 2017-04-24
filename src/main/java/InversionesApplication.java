import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import ui.windows.InversionesWindow;

/**
 * Diseño de Sistemas
 *       2017
 *  Jueves Mañana
 *     Grupo 12
 * 
 * 		TP Anual
 * 	Primera Iteración
 * 
 * Funciones:
 * 	- Carga de cuentas desde archivo 
 * 	- Consulta de cuentas (por empresa y periodo)
 * 
 */

public class InversionesApplication extends Application{

	public static void main(String[] args) {
		new InversionesApplication().start();
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new InversionesWindow(this);
	}
}
