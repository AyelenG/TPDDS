import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Empresa;
import model.Empresas;
import model.Periodo;
import model.data.LoaderArchivo;
import model.data.LoaderArchivoJSON;

public class CargaDeArchivoJSONTest {
	private Empresa empresa;
	private Empresa primerEmpresa = new Empresa("FB", "Facebook");
	private Empresas empresas = new Empresas();
	LoaderArchivo loader = new LoaderArchivoJSON("data/CuentasPrueba.json");
	private Periodo periodo;

	@Before
	public void inicio() {
		empresas.agregarEmpresas(loader.getEmpresas());
	}

	@Test
	public void verificarPrimerEmpresa() {
		empresa = empresas.get(0);
		assertTrue(empresa.esIgual(primerEmpresa));
	}

	@Test
	public void verificarQueAgreguePeriodoSiNoExiste() {
		empresa = empresas.get(0);
		periodo = new Periodo(2015);
		empresa.agregarPeriodo(periodo);
		assertEquals(empresa.getPeriodos().size(), 3);
	}

	@Test
	public void verificarQueNoEstaApple() {
		empresa = new Empresa("AP", "Apple");
		assertFalse(empresas.existeEmpresa(empresa));
	}
}
