package db;

import static org.junit.Assert.assertEquals;


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import model.Cuenta;
import model.Empresa;
import model.Metodologia;
import model.Periodo;
import model.condiciones.Condicion;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Promedio;


public class PersistenciaHSQLDBTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	private EntityManager entityManager; 
	
	private Empresa empresa;
	private Metodologia metodologia;

	
	
	@Before
	public void init(){
		entityManager = PerThreadEntityManagers.getEntityManager();
		empresa = new Empresa();
		
		beginTransaction();
		
		
	}
	@Test
	public void persistirEmpresa(){
		empresa.setNombre("Google");
		empresa.setSymbol("GOOGL");
		entityManager.persist(empresa);
		Empresa empresaBuscada = (Empresa) entityManager.createQuery("from Empresa").getSingleResult();
		assertEquals("Google",empresaBuscada.getNombre());
	}
	
	@Test
	public void persistirEmpresaConDosPeriodos(){
		empresa.setNombre("Google");
		empresa.setSymbol("GOOGL");
		List<Periodo> periodos = new LinkedList<Periodo>();
		Periodo p2012= new Periodo(2012);
		Periodo p2013= new Periodo(2013);
		periodos.add(p2012);
		periodos.add(p2013);
		empresa.agregarPeriodos(periodos);
		entityManager.persist(empresa);
		List<Periodo> perEncontrados = (List<Periodo>) entityManager.createQuery("FROM Periodo").getResultList();
		assertEquals(perEncontrados.size(),2);
	}
	
	/*@Test
	public void persistirEmpresaConCuentaEnUnPeriodo(){
		Periodo p2012= new Periodo(2012);
		CuentaEmpresa cuenta = new CuentaEmpresa("Ingresos", new BigDecimal(90272));
		empresa.agregarPeriodo(p2012);
		p2012.agregarCuenta(cuenta);
		entityManager.persist(empresa);
		CuentaEmpresa cuentaBuscada = (CuentaEmpresa) entityManager.createQuery("FROM CuentaEmpresa").getSingleResult();
		//CuentaEmpresa cuentaBuscada = entityManager.find(CuentaEmpresa.class, 1l);
		assertEquals(cuentaBuscada.getCuenta().getNombre(),"Ingresos");
		
	}*/
	@Test
	public void persistirCuentas(){
		Cuenta cuenta = new Cuenta("Ingresos");
		persist(cuenta);
		Cuenta cuentaBuscada = (Cuenta) entityManager.createQuery("from Cuenta").getSingleResult();
		assertEquals(cuentaBuscada.getNombre(), cuenta.getNombre());
	}
	@Test
	public void persistirMetodologia(){
		List<Condicion> condiciones = new LinkedList<>();
		condiciones.add(new CondicionNoTaxativaConfigurable("Min.TEST - 4 años", 20, new Menor(), "Test", 4));
		condiciones.add(new CondicionTaxativaConfigurable("Promedio TEST - 5 años > 50", new Mayor(), new Promedio(),
				"Test", 5, BigDecimal.valueOf(50)));
		metodologia=new Metodologia("Prueba", condiciones);
		persist(metodologia);
		
		Metodologia metodologiaBuscada = entityManager.find(Metodologia.class, 1l);
		assertEquals(metodologiaBuscada.getCondiciones().size(), 2);
		
	}
	
	
	
	@After
	public void after(){
		rollbackTransaction();
		
	}
	
	
}