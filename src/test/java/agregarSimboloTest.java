import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import ui.vm.CargaIndicadoresViewModel;

public class agregarSimboloTest {
	
	private CargaIndicadoresViewModel viewModel = new CargaIndicadoresViewModel();
	
	
	@Test
	public void verificarAgregarSimboloPrimeraVez(){
		viewModel.setIngresado("");
		viewModel.agregarSimbolo("50");
		assertEquals(viewModel.getIngresado(),"50");
	}
	
	@Test
	public void verificarAgregarSimbolo(){
		viewModel.setIngresado("ABC +");
		viewModel.agregarSimbolo("50");
		assertEquals(viewModel.getIngresado(),"ABC + 50");
	}
	
}
