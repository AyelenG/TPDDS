package model.repositories;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.Empresa;
import model.data.HandlerArchivoJSON;

public abstract class Repositorio<T> {
	
	//final Class<T> claseDelTipoGenerico;
	private List<T> elementos = new LinkedList<>();
	
	/*public Repositorio(Class<T> claseDelTipoGenerico) {
        this.claseDelTipoGenerico = claseDelTipoGenerico;
    }*/
	
	public List<T> getElementos() {
		return elementos;
	}

	public void setElementos(List<T> elementos) {
		this.elementos = elementos;
	}

	public T get(int i) {
		return this.elementos.get(i);
	}

	public int indexOf(T e) {
		return this.elementos.indexOf(e);
	}

	public int size() {
		return this.elementos.size();
	}
	
	public void agregarElementos(List<T> elementos) {
		for (T e : elementos)
			if (!existeElemento(e))
				this.agregarElemento(e);
	}

	public void agregarElemento(T e) {
		this.elementos.add(e);		
	}	
	
	public T buscarElemento(T e) {
		return this.elementos.stream().filter(_e -> this.sonIguales(_e,e)).findFirst().orElse(null);
	}

	public boolean existeElemento(T e) {
		return this.elementos.stream().anyMatch(_e -> this.sonIguales(_e,e));
	}
	
	public void borrarElementos(){
		this.elementos.clear();
	}
	
	public void insertarEnBD (T elemento){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		entityManager.persist(elemento);
		tx.commit();
	}
	
	public void insertarVariosEnBD(List<T> elementos){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager(); 
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		elementos.forEach(elemento -> entityManager.persist(elemento));
		tx.commit();
	}
	
	/*
	public void cargarBDDesdeArchivo(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<T> elementos = new HandlerArchivoJSON(RUTA).<T>load(claseDelTipoGenerico);
		tx.begin();
		elementos.forEach(elemento -> em.persist(elemento));
		tx.commit();
	}*/
	
	public abstract boolean sonIguales(T e1, T e2);
}
