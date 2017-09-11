package model.repositories;

import java.util.LinkedList;
import java.util.List;

public abstract class RepoArchivo<T> implements Repositorio<T> {
	
	//final Class<T> claseDelTipoGenerico;
	private List<T> elementos = new LinkedList<>();
	
	/*public Repositorio(Class<T> claseDelTipoGenerico) {
        this.claseDelTipoGenerico = claseDelTipoGenerico;
    }*/
	
	public List<T> findAll() {
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
	
	public void insertarVarios(List<T> elementos) {
		for (T e : elementos)
			if (!existeElemento(e))
				this.insertar(e);
	}

	public void insertar(T e) {
		this.elementos.add(e);		
	}	
	
	public T buscarElemento(T e) {
		return this.elementos.stream().filter(_e -> this.sonIguales(_e,e)).findFirst().orElse(null);
	}

	public boolean existeElemento(T e) {
		return this.elementos.stream().anyMatch(_e -> this.sonIguales(_e,e));
	}
	
	public void clean(){
		this.elementos.clear();
	}
	
	protected abstract boolean sonIguales(T elemento1, T elemento2);

}
