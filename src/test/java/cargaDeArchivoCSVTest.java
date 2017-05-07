import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Empresa;
import model.Empresas;
import model.Periodo;
import model.data.LoaderArchivo;
import model.data.LoaderArchivoCSV;
import model.repositories.Repositorios;


public class cargaDeArchivoCSVTest {
	private Empresa empresa;
	private Empresa primerEmpresa = new Empresa("FB","Facebook");
	List<Empresa> empresas = new LinkedList<>();
	LoaderArchivo loader = new LoaderArchivoCSV("data/PruebaCuentas.csv");
	private BigDecimal valorCuenta;
	private Periodo periodo;
	@Before 
	public void inicio(){
		Repositorios.empresas.agregarEmpresas(loader.getEmpresas());
		empresas= Repositorios.empresas.getEmpresas();
	}
	@Test
	public void verificarPrimerEmpresa(){
		empresa = empresas.get(0);
		assertTrue(empresa.esIgual(primerEmpresa));
	}
	@Test
	public void verificarPeriodosDeLaSegundaEmpresa(){
		empresa = empresas.get(1);
		assertEquals(empresa.getPeriodos().size(), 1);
	}
	@Test
	public void verificarQueNoEstaApple(){
		empresa = new Empresa("AP","Apple");
		assertFalse(Repositorios.empresas.existeEmpresa(empresa));
	}
	@Test 
	public void siLaCuentaYaExisteNoLaDuplica(){
		empresa = empresas.get(0);
		empresa.getPeriodos().get(0).agregarCuenta(new Cuenta("EBITDA"));
		assertEquals(empresa.getPeriodos().get(0).getCuentas().size(),2);
	}
	@Test 
	public void siLaCuentaYaExisteEnUnPeriodoCambiaElValor(){
		empresa = empresas.get(0);
		empresa.getPeriodos().get(0).agregarCuenta(new Cuenta("EBITDA", valorCuenta));
		assertEquals(empresa.getPeriodos().get(0).getCuentas().get(0).getValor(),null);
	}
	@Test
	public void verificarQueAgreguePeriodoSiNoExiste(){
		empresa = empresas.get(0);
		periodo = new Periodo(2015);
		empresa.agregarPeriodo(periodo);
		assertEquals(empresa.getPeriodos().size(),3);	
	}
	
	
}
