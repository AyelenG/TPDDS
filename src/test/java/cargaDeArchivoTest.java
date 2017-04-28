import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//import model.DataLoader;
import model.Empresa;

import model.Periodo;
import model.repositories.Repositorios;

public class cargaDeArchivoTest {
	private Empresa empresa;
	private Periodo periodo;
	
	@Before 
	public void inicio(){
	//	Repositorios.empresas.agregarEmpresas(new ManipuladorArchivo("data/Cuentas.json").getEmpresas());
	}
	/*
	@Test
	public void verificarNombreDeLaPrimerEmpresa(){
		empresa = Repositorios.empresas.getEmpresas().get(0);
		assertTrue(empresa.esIgual("Facebook"));
	}
	@Test
	public void verificarPeriodoSegundaEmpresa() {
		empresa=Repositorios.empresas.getEmpresas().get(1);
		assertTrue(empresa.getPeriodos().get(0).esIgual(2000));
	}
	@Test
	public void verificarSegundaCuentaPrimeraEmpresaPrimerPeriodo() {
		
		empresa=Repositorios.empresas.getEmpresas().get(0);
		periodo=empresa.getPeriodos().get(0);
		assertTrue(periodo.getCuentas().get(1).esIgual("FDS"));
	}
*/
}
