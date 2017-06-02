import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.Empresas;
import model.Periodo;
import model.data.HandlerArchivo;
import model.data.HandlerArchivoCSV;

public class CargaDeArchivoCSVTest {
	private Empresa empresa;
	Empresas empresas = new Empresas();
	HandlerArchivo loader = new HandlerArchivoCSV("data/PruebaCuentas.csv");
	private BigDecimal valorCuenta = new BigDecimal(100);
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
	public void verificarPeriodosDeLaSegundaEmpresa() {
		empresa = empresas.get(1);
		assertEquals(empresa.getPeriodos().size(), 1);
	}

	@Test
	public void verificarQueNoEstaApple() {
		empresa = new Empresa("AP", "Apple");
		assertFalse(empresas.existeElemento(empresa));
	}

	@Test
	public void siLaCuentaYaExisteNoLaDuplica() {
		empresa = empresas.get(0);
		empresa.getPeriodos().get(0).agregarCuenta(new Cuenta("EBITDA"));
		assertEquals(empresa.getPeriodos().get(0).getCuentas().size(), 2);
	}

	@Test
	public void siLaCuentaYaExisteEnUnPeriodoCambiaElValor() {
		empresa = empresas.get(0);
		empresa.getPeriodos().get(0).agregarCuenta(new Cuenta("EBITDA", valorCuenta));
		assertEquals(empresa.getPeriodos().get(0).getCuentas().get(0).getValor(), valorCuenta);
	}

	@Test
	public void verificarQueAgreguePeriodoSiNoExiste() {
		empresa = empresas.get(0);
		periodo = new Periodo(2015);
		empresa.agregarPeriodo(periodo);
		assertEquals(empresa.getPeriodos().size(), 3);
	}

}
