package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class RepoBD<T> implements Repositorio<T> {

	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public void insertar(T elemento) {
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
	
	public T get(long id){
		return entityManager.find(this.getEntityClass(), id);
	}

	public T buscarElemento(T elemento) {
		Class<T> entityClass = this.getEntityClass();
		String campoDeBusqueda = this.campoDeBusqueda();
		String valorDeBusqueda = this.valorDeBusqueda(elemento);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> from = criteria.from(entityClass);
		criteria.select(from);
		criteria.where(builder.equal(from.get(campoDeBusqueda), valorDeBusqueda));
		TypedQuery<T> typed = entityManager.createQuery(criteria);
		try {
			return typed.getSingleResult();
		} catch (final NoResultException nre) {
			return null;
		}

	}

	public boolean existeElemento(T elemento) {
		return this.buscarElemento(elemento) != null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.getEntityName() + " e order by e.id asc").getResultList();
	}

	protected abstract Class<T> getEntityClass();
	
	protected abstract String getEntityName();
	
	protected abstract String valorDeBusqueda(T elemento);

	protected abstract String campoDeBusqueda();

}
