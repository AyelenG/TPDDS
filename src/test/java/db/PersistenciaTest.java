package db;

import static org.junit.Assert.assertEquals;


import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.data.HandlerArchivo;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoEmpresas;

public class PersistenciaTest{
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
	
	private RepoEmpresas repoEmpresas = RepoEmpresas.getInstance();
	private HandlerArchivo loader = new HandlerArchivoJSON("data/CuentasPrueba.json");

	@Before
	public void inicio() {
		repoEmpresas.insertarVarios(loader.loadEmpresas());
				
	}

//	@Test
//	public void persistirUnaEmpresa() {
//		empresa = empresas.buscarElemento(new Empresa("FB","-")); //FB
//		tx = entityManager.getTransaction();
//		tx.begin();
//		entityManager.persist(empresa);
//		tx.commit();
//		
//		empresa = new Empresa("IBM","IbeEme");
//		empresa.agregarCuenta(new Periodo(2010), new CuentaEmpresa("EBITDA",BigDecimal.valueOf(55.55)));
//		
//		tx = entityManager.getTransaction();
//		tx.begin();
//		entityManager.persist(empresa);
//		tx.commit();
//		
//		System.out.println(entityManager.find(Empresa.class, new Long(1)));
//	}
	
//	@Test
//	public void segundaEmpresaPersistidaGoogle() {
//	
//		assertEquals("Google", entityManager.find(Empresa.class, 2l).getNombre());
//	}
//	
//	@Test
//	public void buscarFacebook() {
//		Empresa facebook = entityManager.find(Empresa.class, 1l);
//		assertEquals(facebook, repoEmpresas.buscarElemento(new Empresa("FB","Facebook")));
//	}
	
	@Test
	public void buscarIBMDevuelveNull() {
		assertEquals(null, repoEmpresas.buscarElemento(new Empresa("IBM","IBM")));
	}
	
	@Test
	public void obtenerTodasLasEmpresas() {
		assertEquals(2, repoEmpresas.findAll().size());
		assertEquals("Facebook", repoEmpresas.findAll().get(0).getNombre());
		assertEquals("GOOGL", repoEmpresas.findAll().get(1).getSymbol());
	}
//	@Test
//	public void obtenerUnaEmpresaYModificarla() {
//		tx = entityManager.getTransaction();
//		Empresa empresa = entityManager.find(Empresa.class, new Long(1));
//		System.out.println(empresa);
//		tx.begin();
//		empresa.agregarCuenta(new Periodo(2016), new CuentaEmpresa("Fulanito", BigDecimal.valueOf(10.5)));
//		tx.commit();
//	}

	
//	@Test
//	public void persistirUnPeriodo() {
//		empresa = empresas.buscarElemento(new Empresa("FB","-")); //FB
//		periodo = empresa.buscarPeriodo(new Periodo(2010));
//		
//		tx = entityManager.getTransaction();
//		tx.begin();
//		entityManager.persist(periodo);
//		tx.commit();
//	}
//	
//	@Test
//	public void persistirUnaCuentaEmpresa() {
//		empresa = empresas.buscarElemento(new Empresa("FB","-")); //FB
//		periodo = empresa.buscarPeriodo(new Periodo(2010));
//		cuentaEmpresa = periodo.buscarCuenta(new Cuenta("EBITDA"));
//		
//		tx = entityManager.getTransaction();
//		tx.begin();
//		entityManager.persist(cuentaEmpresa);
//		tx.commit();
//	}
	
	@After
	public void limpiar() {
		repoEmpresas.clean();
	}
}
