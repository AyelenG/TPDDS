package EmpresasYCuentas;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Empresa;
import model.Periodo;
import model.data.HandlerArchivo;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoEmpresas;

public class CargaDeArchivoJSONTest {
	private Empresa empresa;
	private RepoEmpresas empresas = RepoEmpresas.getInstance();
	private HandlerArchivo loader = new HandlerArchivoJSON("data/CuentasPrueba.json");
	private Periodo periodo;

	@Before
	public void inicio() {
		empresas.agregarElementos(loader.loadEmpresas());
	}

	@Test
	public void verificarPrimerEmpresa() {
		empresa = empresas.get(0);
		assertEquals("FB",empresa.getSymbol());
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
		assertFalse(empresas.existeElemento(empresa));
	}
	
	@After
	public void limpiar() {
		empresas.borrarElementos();
	}
}
