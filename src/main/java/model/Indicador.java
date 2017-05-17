package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Indicador {
	//
	private String nombre;
	private String formula;
	//...
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public boolean esIgual(Indicador indicador) {
		return this.getNombre().equals(indicador.getNombre());
	}
	
	@Override
	public String toString(){
		return this.nombre.toUpperCase();
	}
	
	
	
}
