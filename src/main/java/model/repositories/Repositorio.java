package model.repositories;

import java.util.LinkedList;
import java.util.List;

public abstract class Repositorio<T> {
	
	private List<T> elementos = new LinkedList<>();
	
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
	
	public abstract boolean sonIguales(T e1, T e2);
}
