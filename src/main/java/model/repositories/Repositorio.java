package model.repositories;

import java.util.List;

//ninguna la implementa
//deberian implementarla RepoArchivo y RepoCuentas

public interface Repositorio<T>{
	
	void insertar(T elemento);
	void insertarVarios(List<?> elementos);
}
