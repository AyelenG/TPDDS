package Metodologias;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import application.precalculoindicadores.PrecalculoIndicadores;
import model.CuentaPeriodo;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.Periodo;
import model.Usuario;
import model.condiciones.Comparadores;
import model.condiciones.Condicion;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.TiposCondicionTaxativa;
import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoIndicadoresPeriodosSinValor;
import model.repositories.RepoIndicadoresPeriodosConValor;
import model.repositories.RepoUsuarios;

public class aplicarMetodologiaTest {

	private static Metodologia metodologia;
	private static RepoEmpresas empresas = RepoEmpresas.getInstance();
	private static RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private static RepoUsuarios usuarios = RepoUsuarios.getInstance();

	@BeforeClass
	public static void inicializarDatos() {
		Usuario testUser = new Usuario("test","test");
		usuarios.insertar(testUser);
		indicadores.insertar(new Indicador("TEST", "[EBITDA]",testUser));

		Empresa empresa;

		empresa = new Empresa("FCB", "Facebook");
		empresa.agregarCuenta(new Periodo(2016), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(98.1)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(91.9)));
		empresa.agregarCuenta(new Periodo(2013), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(11.05)));
		empresa.agregarCuenta(new Periodo(2014), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(44.3)));
		empresa.agregarCuenta(new Periodo(2012), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(23.8)));
		empresa.agregarCuenta(new Periodo(2004), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(15.8)));
		empresas.insertar(empresa);

		empresa = new Empresa("APL", "Apple");
		empresa.agregarCuenta(new Periodo(2012), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(30.9)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(30.1)));
		empresa.agregarCuenta(new Periodo(2016), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(31.4)));
		empresa.agregarCuenta(new Periodo(2014), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(19.8)));
		empresa.agregarCuenta(new Periodo(2013), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(25.8)));
		empresas.insertar(empresa);

		empresa = new Empresa("IBM", "IBM");
		empresa.agregarCuenta(new Periodo(2016), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(119)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(90.8)));
		empresa.agregarCuenta(new Periodo(2014), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(70.57)));
		empresa.agregarCuenta(new Periodo(2013), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(22.4)));
		empresa.agregarCuenta(new Periodo(2012), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(150.2)));
		empresa.agregarCuenta(new Periodo(1989), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(32.8)));
		empresas.insertar(empresa);

		List<Condicion> condiciones = new LinkedList<>();
		condiciones.add(new CondicionNoTaxativaConfigurable("Max.TEST - 3 años", 10, Comparadores.Mayor, "Test", 3));
		condiciones.add(new CondicionNoTaxativaConfigurable("Min.TEST - 4 años", 20, Comparadores.Menor, "Test", 4));
		condiciones.add(new CondicionTaxativaConfigurable("Promedio TEST - 5 años > 50", Comparadores.Mayor, TiposCondicionTaxativa.Promedio,
				"Test", 5, BigDecimal.valueOf(50)));
		condiciones.add(new CondicionTaxativaConfigurable("Simple TEST - 2 años < 120", Comparadores.Menor, TiposCondicionTaxativa.Simple,
				"Test", 2, BigDecimal.valueOf(120)));
		condiciones.add(new Longevidad());

		metodologia = new Metodologia("Prueba", condiciones, testUser);
		
		new PrecalculoIndicadores().precalcularTodos();

	}

	@Test
	public void verificarCondicionTaxativaSimple() {
		Condicion cond = metodologia.getCondiciones().get(3);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		assertTrue(cond.convieneInvertirEn(ibm,metodologia.getUser()));
	}

	@Test
	public void verificarCondicionTaxativaPromedio() {
		Condicion cond = metodologia.getCondiciones().get(2);
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertFalse(cond.convieneInvertirEn(apple,metodologia.getUser()));
	}

	@Test
	public void verificarCondicionTaxativaTendencia() {
		Condicion cond = new CondicionTaxativaConfigurable("Creciente TEST - 4 años", Comparadores.Mayor, TiposCondicionTaxativa.Tendencia,
				"Test", 4, null);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertTrue(cond.convieneInvertirEn(facebook,metodologia.getUser()));
	}

	@Test
	public void verificarCondicionTaxativaMediana() {
		Condicion cond = new CondicionTaxativaConfigurable("Mediana TEST - 5 años < 30.91", Comparadores.Menor, TiposCondicionTaxativa.Mediana,
				"Test", 5, BigDecimal.valueOf(30.91));
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertTrue(cond.convieneInvertirEn(apple,metodologia.getUser()));
	}

	@Test
	public void verificarCondicionNoTaxativa1() {
		Condicion cond = metodologia.getCondiciones().get(0);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(10, cond.comparar(ibm, facebook,metodologia.getUser()));
	}

	@Test
	public void verificarCondicionNoTaxativa2() {
		Condicion cond = metodologia.getCondiciones().get(1);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(-20, cond.comparar(ibm, facebook, metodologia.getUser()));
	}

	@Test
	public void verificarLongevidadFiltro() {
		Condicion cond = metodologia.getCondiciones().get(4);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertTrue(cond.convieneInvertirEn(facebook,metodologia.getUser()));
		assertFalse(cond.convieneInvertirEn(apple,metodologia.getUser()));
	}

	@Test
	public void verificarLongevidadOrden() {
		Condicion cond = metodologia.getCondiciones().get(4);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		assertEquals(15, cond.comparar(ibm, facebook,metodologia.getUser()));
	}

	@Test
	public void verificarFiltroDeEmpresas() {
		List<Empresa> empresasFiltradas = new LinkedList<>();
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		assertEquals(empresasFiltradas,
				metodologia.obtenerFiltradas(empresas.findAll()));
	}

	@Test
	public void verificarOrdenamientoDeEmpresas() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("APL", "Apple")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		assertEquals(empresasOrdenadas,
				metodologia.obtenerOrdenadas(empresas.findAll()));
	}

	@Test
	public void verificarInvalidas() {
		Empresa empresa = new Empresa("INV", "Invalida SA");
		empresa.agregarCuenta(new Periodo(2012), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(30.9)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(30.1)));
		empresa.agregarCuenta(new Periodo(2016), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(31.4)));
		empresa.agregarCuenta(new Periodo(2013), new CuentaPeriodo("EBITDA", BigDecimal.valueOf(25.8)));
		empresas.insertar(empresa);

		List<Empresa> empresasValidas = new LinkedList<>();
		empresasValidas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasValidas.add(empresas.buscarElemento(new Empresa("APL", "Apple")));
		empresasValidas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		assertEquals(empresasValidas,
				metodologia.obtenerValidas(empresas.findAll()));
		
		empresas.borrarElemento(empresa);
	}

	@Test
	public void verificarAplicacionMetodologia() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		assertEquals(empresasOrdenadas,
				metodologia.aplicar(metodologia.obtenerValidas(empresas.findAll())));

	}

	@AfterClass
	public static void limpiar() {
		RepoIndicadoresPeriodosConValor.getInstance().clean();
		RepoIndicadoresPeriodosSinValor.getInstance().clean();
		indicadores.clean();
		empresas.clean();
		RepoCuentas.getInstance().clean();
		usuarios.clean();
	}
}
