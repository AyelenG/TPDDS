package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.taxativas.CondicionTaxativa;

@Observable
public class CondicionTaxativaVM {
	private String titulo;
	private CondicionTaxativa condicion;

	public CondicionTaxativaVM(String titulo, CondicionTaxativa condicion) {
		this.titulo = titulo;
		this.condicion = condicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public CondicionTaxativa getCondicion() {
		return condicion;
	}

}