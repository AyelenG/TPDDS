package db;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.CuentaEmpresa;
import model.Empresa;
import model.Periodo;
import model.data.HandlerArchivo;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoEmpresas;

public class PersistenciaTest {
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
	private EntityTransaction tx;
	
	private Empresa empresa;
	private Periodo periodo;
	private CuentaEmpresa cuentaEmpresa;
	private Cuenta cuenta;
	
	private RepoEmpresas empresas = RepoEmpresas.getInstance();
	private HandlerArchivo loader = new HandlerArchivoJSON("data/CuentasPrueba.json");

	@Before
	public void inicio() {
		//entityManager.createStoredProcedureQuery("limpiarTablas").execute();
		empresas.insertarVarios(loader.loadEmpresas());
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
	
	@Test
	public void persistirTodasLasEmpresas() {
		tx = entityManager.getTransaction();
		tx.begin();
		empresas.findAll().forEach(empresa -> entityManager.persist(empresa));
		tx.commit();
		
		System.out.println(entityManager.find(Empresa.class, new Long(1)));
	}

//	@Test
//	public void obtenerUnaEmpresa() {
//		System.out.println(entityManager.find(Empresa.class, new Long(1)));
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
		entityManager.clear();
		empresas.borrarElementos();
	}
}
