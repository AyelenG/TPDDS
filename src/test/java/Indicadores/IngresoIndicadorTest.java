package Indicadores;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Cuenta;
import model.Indicador;
import ui.vm.CargaIndicadorViewModel;

public class IngresoIndicadorTest {
	
	private CargaIndicadorViewModel viewModel = new CargaIndicadorViewModel();
	
	
	@Test
	public void verificarAgregarSimboloPrimeraVez(){
		viewModel.setIngresado("");
		viewModel.agregarToken("50");
		assertEquals(viewModel.getIngresado(),"50");
	}
	
	@Test
	public void verificarAgregarSimbolo(){
		viewModel.setIngresado("ABC +");
		viewModel.agregarToken("50");
		assertEquals(viewModel.getIngresado(),"ABC + 50");
	}
	
	@Test
	public void verificarFormateoDeIndicador(){
		Indicador indicador = new Indicador();
		indicador.setNombre("Hector");
		viewModel.setIndicadorSeleccionado(indicador);
		viewModel.ingresarIndicador();
		assertEquals(viewModel.getIngresado(), "<HECTOR>");
	} 
	
	@Test
	public void verificarFormateoDeCuenta(){
		Cuenta cuenta = new Cuenta();
		cuenta.setNombre("PBI");
		viewModel.setCuentaSeleccionada(cuenta);
		viewModel.ingresarCuenta();
		assertEquals(viewModel.getIngresado(), "[PBI]");
	} 
	
	@Test
	public void verificarBorrarUltimo(){
		viewModel.agregarToken("[ABC]");
		viewModel.agregarToken("/");
		viewModel.agregarToken("45.15");
		viewModel.borrarUltimo();
		assertEquals("[ABC] /" , viewModel.getIngresado());
	}
	
}
