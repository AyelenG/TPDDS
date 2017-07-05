package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.Comparador;

@Observable
public class ComparadorVM{
	private Comparador comparador;
	
	public ComparadorVM(Comparador comparador) {
		this.setComparador(comparador);
	}
	
	@Override
	public String toString() {
		return comparador.toString();
	}
	public Comparador getComparador() {
		return comparador;
	}
	public void setComparador(Comparador comparador){
		this.comparador = comparador;
	}
	
}
