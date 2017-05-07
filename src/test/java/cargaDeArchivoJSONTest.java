import static org.junit.Assert.*;


import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Empresa;
import model.Periodo;
import model.data.LoaderArchivo;
import model.data.LoaderArchivoJSON;
import model.repositories.Repositorios;


public class cargaDeArchivoJSONTest {
	private Empresa empresa;
	private Empresa primerEmpresa = new Empresa("FB","Facebook");
	List<Empresa> empresas = new LinkedList<>();
	LoaderArchivo loader = new LoaderArchivoJSON("data/CuentasPrueba.json");
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
	public void verificarQueAgreguePeriodoSiNoExiste(){
		empresa = empresas.get(0);
		periodo = new Periodo(2015);
		empresa.agregarPeriodo(periodo);
		assertEquals(empresa.getPeriodos().size(),3);	
	}
	@Test
	public void verificarQueNoEstaApple(){
		empresa = new Empresa("AP","Apple");
		assertFalse(Repositorios.empresas.existeEmpresa(empresa));
	}
}

