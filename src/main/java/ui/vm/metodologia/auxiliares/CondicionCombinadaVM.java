package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.combinadas.CondicionCombinada;

@Observable
public class CondicionCombinadaVM {
	private String titulo;
	private CondicionCombinada condicion;

	public CondicionCombinadaVM(String titulo, CondicionCombinada condicion) {
		this.titulo = titulo;
		this.condicion = condicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public CondicionCombinada getCondicion() {
		return condicion;
	}
}
