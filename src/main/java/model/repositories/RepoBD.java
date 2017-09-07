package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class RepoBD<T> implements Repositorio<T> {
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
	
	public void insertar (T elemento){
		
		EntityTransaction tx = entityManager.getTransaction();		
		tx.begin();
		entityManager.persist(elemento);
		tx.commit();
	}
	
	public void insertarVarios(List<T> elementos){
	 
		EntityTransaction tx = entityManager.getTransaction();		
		tx.begin();
		elementos.forEach(elemento -> entityManager.persist(elemento));
		tx.commit();
	}
	
	public abstract List<T> findAll();

}
