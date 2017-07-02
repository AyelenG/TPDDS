package Metodologias;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.Periodo;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;
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
		empresa = new Empresa("IBM", "IBM");
		empresa.agregarCuenta(new Periodo(2017), new Cuenta("EBITDA", BigDecimal.valueOf(119)));
		empresa.agregarCuenta(new Periodo(2016), new Cuenta("EBITDA", BigDecimal.valueOf(60.8)));
		empresa.agregarCuenta(new Periodo(2015), new Cuenta("EBITDA", BigDecimal.valueOf(70.57)));
		empresa.agregarCuenta(new Periodo(2014), new Cuenta("EBITDA", BigDecimal.valueOf(22.4)));
		empresa.agregarCuenta(new Periodo(2013), new Cuenta("EBITDA", BigDecimal.valueOf(150.2)));
		empresas.agregarElemento(empresa);

		empresa = new Empresa("FCB", "Facebook");
		empresa.agregarCuenta(new Periodo(2017), new Cuenta("EBITDA", BigDecimal.valueOf(98.1)));
		empresa.agregarCuenta(new Periodo(2016), new Cuenta("EBITDA", BigDecimal.valueOf(91.9)));
		empresa.agregarCuenta(new Periodo(2014), new Cuenta("EBITDA", BigDecimal.valueOf(11.05)));
		empresa.agregarCuenta(new Periodo(2015), new Cuenta("EBITDA", BigDecimal.valueOf(44.3)));
		empresas.agregarElemento(empresa);

		empresa = new Empresa("APL", "Apple");
		empresa.agregarCuenta(new Periodo(2013), new Cuenta("EBITDA", BigDecimal.valueOf(30.9)));
		empresa.agregarCuenta(new Periodo(2016), new Cuenta("EBITDA", BigDecimal.valueOf(30.1)));
		empresa.agregarCuenta(new Periodo(2017), new Cuenta("EBITDA", BigDecimal.valueOf(31.4)));
		empresa.agregarCuenta(new Periodo(2015), new Cuenta("EBITDA", BigDecimal.valueOf(19.8)));
		empresas.agregarElemento(empresa);

		List<CondicionNoTaxativa> condicionesNT = new LinkedList<>();
		condicionesNT.add(new CondicionNoTaxativa("Max.TEST - 3 años", 10, new Mayor(),
				indicadores.buscarElemento(new Indicador("Test")), 3));
		condicionesNT.add(new CondicionNoTaxativa("Min.TEST - 4 años", 20, new Menor(),
				indicadores.buscarElemento(new Indicador("Test")), 4));
		List<CondicionTaxativa> condicionesT = new LinkedList<>();
		condicionesT.add(new CondicionTaxativa("Promedio TEST - 5 años > 50", new Mayor(), new Promedio(),
				indicadores.buscarElemento(new Indicador("Test")), 5, BigDecimal.valueOf(50)));
		condicionesT.add(new CondicionTaxativa("Simple TEST - 2 años < 120", new Menor(), new Simple(),
				indicadores.buscarElemento(new Indicador("Test")), 2, BigDecimal.valueOf(120)));

		metodologias.agregarElemento(new Metodologia("Prueba", condicionesNT, condicionesT, null));
	}

	@Test
	public void verificarCondicionTaxativaSimple() {
		CondicionTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba"))
											.getCondicionesT().get(1);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		assertTrue(cond.convieneInvertirEn(ibm));
	}

	@Test
	public void verificarCondicionTaxativaPromedio() {
		CondicionTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba"))
											.getCondicionesT().get(0);
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertFalse(cond.convieneInvertirEn(apple));
	}
	
	@Test
	public void verificarCondicionTaxativaTendencia() {
		CondicionTaxativa cond = new CondicionTaxativa("Creciente TEST - 4 años", new Mayor(), new Tendencia(),
				indicadores.buscarElemento(new Indicador("Test")), 4, null);
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertTrue(cond.convieneInvertirEn(facebook));
	}
	
	@Test
	public void verificarCondicionTaxativaMediana() {
		CondicionTaxativa cond = new CondicionTaxativa("Mediana TEST - 5 años < 30.91", new Menor(), new Mediana(),
				indicadores.buscarElemento(new Indicador("Test")), 5, BigDecimal.valueOf(30.91));
		Empresa apple = empresas.buscarElemento(new Empresa("APL", "Apple"));
		assertTrue(cond.convieneInvertirEn(apple));
	}


	@Test
	public void verificarCondicionNoTaxativa1() {
		CondicionNoTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba"))
												.getCondicionesNT().get(0);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(10,cond.comparar(ibm, facebook));
	}
	
	@Test
	public void verificarCondicionNoTaxativa2() {
		CondicionNoTaxativa cond = metodologias.buscarElemento(new Metodologia("Prueba"))
												.getCondicionesNT().get(1);
		Empresa ibm = empresas.buscarElemento(new Empresa("IBM", "IBM"));
		Empresa facebook = empresas.buscarElemento(new Empresa("FCB", "Facebook"));
		assertEquals(-20,cond.comparar(ibm, facebook));
	}

	@Test
	public void verificarFiltroDeEmpresas() {
		List<Empresa> empresasFiltradas = new LinkedList<>();
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasFiltradas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		assertEquals(empresasFiltradas, metodologias.buscarElemento(new Metodologia("Prueba"))
													.obtenerFiltradas(empresas.getElementos()));
	}
	
	@Test
	public void verificarOrdenamientoDeEmpresas() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("APL", "Apple")));
		assertEquals(empresasOrdenadas, metodologias.buscarElemento(new Metodologia("Prueba"))
													.obtenerOrdenadas(empresas.getElementos()));
	}
	
	@Test
	public void verificarAplicacionMetodologia() {
		List<Empresa> empresasOrdenadas = new LinkedList<>();
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("FCB", "Facebook")));
		empresasOrdenadas.add(empresas.buscarElemento(new Empresa("IBM", "IBM")));
		assertEquals(empresasOrdenadas, metodologias.buscarElemento(new Metodologia("Prueba"))
													.aplicar(empresas.getElementos()));
	}

	@After
	public void limpiar() {
		metodologias.borrarMetodologiasDeUsuario();
		empresas.borrarElementos();
		indicadores.borrarIndicadoresDeUsuario();
	}
}
