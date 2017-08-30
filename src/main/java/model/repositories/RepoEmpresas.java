package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.Periodo;

public class RepoEmpresas extends Repositorio<Empresa> {

	private static final RepoEmpresas instance = new RepoEmpresas();

	private RepoEmpresas() {

	}

	public static RepoEmpresas getInstance() {
		return instance;
	}
	
	public void insertarEnBD (Empresa empresa){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		entityManager.persist(empresa);
		tx.commit();
	}
	@SuppressWarnings("unchecked")
	public void findAllBD(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 				
		List<Empresa> empresas = entityManager.createQuery("from Empresa").getResultList();		
		this.setElementos(empresas);
		
	}
	
	@Override
	public boolean sonIguales(Empresa e1, Empresa e2) {
		return e1.getSymbol().equals(e2.getSymbol());
	}

	@Override
	public void agregarElementos(List<Empresa> empresas) {
		for (Empresa empresa : empresas) {
			if (!existeElemento(empresa))
				this.agregarElemento(empresa);
			else
				this.buscarElemento(empresa).agregarPeriodos(empresa.getPeriodos());
		}
	}

	public void agregarPeriodoABD(Periodo periodo, Empresa empresa) {
		empresa = new Empresa("ABC", "hola");
		System.out.println("PASO");
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 	
		System.out.println("PASO 2");
		entityManager.persist(periodo);
		//entityManager.createNativeQuery(
			//	"insert into Periodo values empr_id=" + empresa.getId() + ", anio=" + periodo.getAnio()).;
		
	}

}
