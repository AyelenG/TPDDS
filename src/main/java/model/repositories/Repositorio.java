package model.repositories;

import java.util.List;

public interface Repositorio<T> {
	
	void insertar(T elemento);
	
	void insertarVarios(List<T> elementos);
	
	T buscarElemento(T elemento);
	
	boolean existeElemento(T elemento);
		
	List<T> findAll();
	
	void borrarElemento(T elemento);
	
	void clean();
	
}
