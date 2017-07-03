package Metodologias;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.CuentaEmpresa;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.Periodo;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.combinadas.Longevidad;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativa;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Mediana;
import model.condiciones.taxativas.Promedio;
import model.condiciones.taxativas.Simple;
import model.condiciones.taxativas.Tendencia;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoMetodologias;

public class aplicarMetodologiaTest {

	RepoMetodologias metodologias = RepoMetodologias.getInstance();
	RepoEmpresas empresas = RepoEmpresas.getInstance();
	RepoIndicadores indicadores = RepoIndicadores.getInstance();

	@Before
	public void inicializarDatos() {
		indicadores.agregarElemento(new Indicador("TEST", "[EBITDA]"));

		Empresa empresa;

		empresa = new Empresa("FCB", "Facebook");
		empresa.agregarCuenta(new Periodo(2017), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(98.1)));
		empresa.agregarCuenta(new Periodo(2016), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(91.9)));
		empresa.agregarCuenta(new Periodo(2014), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(11.05)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(44.3)));
		empresa.agregarCuenta(new Periodo(2005), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(15.8)));
		empresas.agregarElemento(empresa);

		empresa = new Empresa("APL", "Apple");
		empresa.agregarCuenta(new Periodo(2013), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(30.9)));
		empresa.agregarCuenta(new Periodo(2016), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(30.1)));
		empresa.agregarCuenta(new Periodo(2017), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(31.4)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(19.8)));
		empresa.agregarCuenta(new Periodo(2010), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(25.8)));
		empresas.agregarElemento(empresa);

		empresa = new Empresa("IBM", "IBM");
		empresa.agregarCuenta(new Periodo(2017), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(119)));
		empresa.agregarCuenta(new Periodo(2016), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(90.8)));
		empresa.agregarCuenta(new Periodo(2015), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(70.57)));
		empresa.agregarCuenta(new Periodo(2014), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(22.4)));
		empresa.agregarCuenta(new Periodo(2013), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(150.2)));
		empresa.agregarCuenta(new Periodo(1990), new CuentaEmpresa("EBITDA", BigDecimal.valueOf(32.8)));
		empresas.agregarElemento(empresa);

		List<CondicionNoTaxativa> condicionesNT = new LinkedList<>();
		condicionesNT.add(new CondicionNoTaxativaConfigurable("Max.TEST - 3 años", 10, new Mayor(), "Test", 3));
		condicionesNT.add(new CondicionNoTaxativaConfigurable("Min.TEST - 4 años", 20, new Menor(), "Test", 4));
		List<CondicionTaxativa> condicionesT = new LinkedList<>();
		condicionesT.add(new CondicionTaxativaConfigurable("Promedio TEST - 5 años > 50", new Mayor(), new Promedio(),
				"Test", 5, BigDecimal.valueOf(50)));
		condicionesT.add(new CondicionTaxativaConfigurable("Simple TEST - 2 años < 120", new Menor(), new Simple(),
				"Test", 2, BigDecimal.valueOf(120)));

		List<CondicionCombinada> condicionesComb = Arrays.asList(new Longevidad());
		
		metodologias.agregarElemento(new Metodologia("Prueba", condicionesNT, condicionesT, condicionesComb));
	}

	@Test
	public void verificarCondicionTaxativaSimple() {
		CondicionTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesT().get(1);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		assertTrue(cond.convieneInvertirEn(ibm));
	}

	@Test
	public void verificarCondicionTaxativaPromedio() {
		CondicionTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesT().get(0);
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertFalse(cond.convieneInvertirEn(apple));
	}

	@Test
	public void verificarCondicionTaxativaTendencia() {
		CondicionTaxativa cond = new CondicionTaxativaConfigurable("Creciente TEST - 4 años", new Mayor(),
				new Tendencia(), "Test", 4, null);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertTrue(cond.convieneInvertirEn(facebook));
	}

	@Test
	public void verificarCondicionTaxativaMediana() {
		CondicionTaxativa cond = new CondicionTaxativaConfigurable("Mediana TEST - 5 años < 30.91", new Menor(),
				new Mediana(), "Test", 5, BigDecimal.valueOf(30.91));
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertTrue(cond.convieneInvertirEn(apple));
	}

	@Test
	public void verificarCondicionNoTaxativa1() {
		CondicionNoTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesNT().get(0);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(10, cond.comparar(ibm, facebook));
	}

	@Test
	public void verificarCondicionNoTaxativa2() {
		CondicionNoTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesNT().get(1);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(-20, cond.comparar(ibm, facebook));
	}

	@Test
	public void verificarLongevidadFiltro() {
		CondicionCombinada cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesComb().get(0);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertTrue(cond.convieneInvertirEn(facebook));
		assertFalse(cond.convieneInvertirEn(apple));
	}

	@Test
	public void verificarLongevidadOrden() {
		CondicionCombinada cond = metodologias.buscarElemento(new Metodologia("Prueba")).getCondicionesComb().get(0);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		assertEquals(15, cond.comparar(ibm, facebook));
	}

	@Test
	public void verificarFiltroDeEmpresas() {
		List<Empresa> empresasFiltradas = new LinkedList<>();
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		assertEquals(empresasFiltradas,
				metodologias.buscarElemento(new Metodologia("Prueba")).obtenerFiltradas(empresas.getElementos()));
	}

	@Test
	public void verificarOrdenamientoDeEmpresas() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("APL", "Apple")));
		assertEquals(empresasOrdenadas,
				metodologias.buscarElemento(new Metodologia("Prueba")).obtenerOrdenadas(empresas.getElementos()));
	}

	@Test
	public void verificarAplicacionMetodologia() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		assertEquals(empresasOrdenadas,
				metodologias.buscarElemento(new Metodologia("Prueba")).aplicar(empresas.getElementos()));
	}

	@After
	public void limpiar() {
		metodologias.borrarMetodologiasDeUsuario();
		empresas.borrarElementos();
		indicadores.borrarIndicadoresDeUsuario();
	}
}
