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

import model.data.HandlerArchivoJSON;

public abstract class RepoBD<T> implements Repositorio<T> {

	protected Class<T> entidad;

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
	
	public void migrarDesdeJSON() {
		this.insertarVarios(new HandlerArchivoJSON("data/"
					+ entidad.getSimpleName() + ".json").<T>load(entidad));
	}
	
	public T get(long id) {
		return entityManager.find(entidad, id);
	}

	public T buscarElemento(T elemento) {
		String campoDeBusqueda = this.campoDeBusqueda();
		String valorDeBusqueda = this.valorDeBusqueda(elemento);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entidad);
		Root<T> from = criteria.from(entidad);
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
		return this.entityManager.createQuery("from " + entidad.getSimpleName() + " e order by e.id asc").getResultList();
	}

	public void borrarElemento(T elemento) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		this.entityManager.remove(elemento);
		tx.commit();
	}
	
	public void clean() {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		this.findAll().forEach(_elemento -> entityManager.remove(_elemento));
		tx.commit();
	}

	protected String campoDeBusqueda() {
		return "nombre";
	}

	protected abstract String valorDeBusqueda(T elemento);

}
