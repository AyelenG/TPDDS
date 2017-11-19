package model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


public abstract class RepoBD<T> implements Repositorio<T>, WithGlobalEntityManager, TransactionalOps {

	protected Class<T> entidad;
	
	public Class<T> getEntidad() {
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
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<String> campos = this.camposDeBusqueda();
		List<Object> valores = this.valoresDeBusqueda(elemento);
		
		CriteriaBuilder builder = entityManager().getCriteriaBuilder();
		//creo una query que devuelve un elemento del entidad del repo
		CriteriaQuery<T> criteria = builder.createQuery(entidad);
		//determino el FROM de la query como la tabla asociada a la entidad del repo
		Root<T> from = criteria.from(entidad);
		criteria.select(from);
		//agrego el WHERE a partir de la lista de campos de busqueda asociadas a la entidad
		//y los valores de estos campos en el elemento que me pasaron
		for(String field : campos){
			int i = campos.indexOf(field);
			predicates.add(builder.equal(from.get(field), valores.get(i)));
		}
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		//creo la query real y obtengo un solo resultado, o null si no hay resultado
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

	@SuppressWarnings("unchecked")
	public <K> List<T> findAllBy(String fieldName, K fieldValue) {
		return this.entityManager().createQuery
				("from " + entidad.getSimpleName() + " e "
						 + "where e." + fieldName + " = " + fieldValue 
						 + " order by e.id asc").getResultList();
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

	protected List<String> camposDeBusqueda() {
		return Arrays.asList("nombre");
	}

	protected abstract List<Object> valoresDeBusqueda(T elemento);

}
