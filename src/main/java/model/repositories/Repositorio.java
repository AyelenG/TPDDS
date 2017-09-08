package model.repositories;

import java.util.List;

public interface Repositorio<T> {
	
	void insertar(T elemento);
	
	void insertarVarios(List<T> elementos);
	
	boolean existeElemento(T elemento);
	
	boolean sonIguales(T elemento1, T elemento2);
	
	List<T> findAll();
	
}
