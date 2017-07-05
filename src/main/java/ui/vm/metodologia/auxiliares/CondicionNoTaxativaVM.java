package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.notaxativas.CondicionNoTaxativa;

@Observable
public class CondicionNoTaxativaVM {
	private String titulo;
	private CondicionNoTaxativa condicion;

	public CondicionNoTaxativaVM(String titulo, CondicionNoTaxativa condicion) {
		this.titulo = titulo;
		this.condicion = condicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public CondicionNoTaxativa getCondicion() {
		return condicion;
	}

}