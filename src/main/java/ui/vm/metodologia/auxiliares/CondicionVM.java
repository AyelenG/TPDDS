package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.Condicion;


@Observable
public class CondicionVM {
	private String titulo;
	private Condicion condicion;

	public CondicionVM(String titulo, Condicion condicion) {
		this.titulo = titulo;
		this.condicion = condicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public Condicion getCondicion() {
		return condicion;
	}

}