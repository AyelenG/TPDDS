package model.repositories;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


public abstract class RepoBD<T> implements Repositorio<T>, WithGlobalEntityManager, TransactionalOps{

	protected Class<T> entidad;
	
	public Class<T> getEntidad(){
		return entidad;
	}
	
	public void insertar(T elemento) {
		this.withTransaction(() -> entityManager().persist(elemento));
	}

	public void insertarVarios(List<T> elementos) {
		this.withTransaction(
				() -> elementos
						.forEach(_elemento -> entityManager().persist(_elemento))
				);
	}
	
	public T get(long id) {
		return entityManager().find(entidad, id);
	}

	public T buscarElemento(T elemento) {
		String campoDeBusqueda = this.campoDeBusqueda();
		String valorDeBusqueda = this.valorDeBusqueda(elemento);
		CriteriaBuilder builder = entityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(entidad);
		Root<T> from = criteria.from(entidad);
		criteria.select(from);
		criteria.where(builder.equal(from.get(campoDeBusqueda), valorDeBusqueda));
		TypedQuery<T> typed = entityManager().createQuery(criteria);
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
		return this.entityManager().createQuery("from " + entidad.getSimpleName() + " e order by e.id asc").getResultList();
	}

	public void borrarElemento(T elemento) {
		this.withTransaction(() -> entityManager().remove(elemento));
	}
	
	public void clean() {
		this.withTransaction(
							() -> this.findAll()
							.forEach(_elemento -> entityManager().remove(_elemento))
							);
	}

	protected String campoDeBusqueda() {
		return "nombre";
	}

	protected abstract String valorDeBusqueda(T elemento);

}
