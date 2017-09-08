package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class RepoBD<T> implements Repositorio<T> {
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
	
	public void insertar (T elemento) {		
		EntityTransaction tx = entityManager.getTransaction();		
		tx.begin();
		entityManager.persist(elemento);
		tx.commit();
	}
	
	public void insertarVarios(List<T> elementos) {	 
		EntityTransaction tx = entityManager.getTransaction();		
		tx.begin();
		elementos.forEach(_elemento -> entityManager.persist(_elemento));
		tx.commit();
	}
	
	public boolean existeElemento(T elemento) {
		return this.findAll().stream().anyMatch(_elemento -> this.sonIguales(_elemento, elemento));
	}
	
	public abstract boolean sonIguales(T elemento1, T elemento2);
	
	public abstract List<T> findAll();

}
