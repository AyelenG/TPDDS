package model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.uqbar.commons.utils.Observable;

import model.evaluador.Expresion;

@Observable
@JsonIgnoreProperties({ "changeSupport" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Indicador {

	private String nombre = "";
	private String formula = "";
	private Expresion expresion;

	public Indicador() {
	}

	public Indicador(String nombre) {
		this.setNombre(nombre);
	}

	public Indicador(String nombre, String formula, Expresion expresion) {
		this.setNombre(nombre);
		this.setFormula(formula);
		this.setExpresion(expresion);
	}

	public boolean esIgual(Indicador indicador) {
		return this.getNombre().equals(indicador.getNombre());
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
		//this.expresion = new ExpresionBuilder(formula);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public BigDecimal evaluar(Periodo periodo, Indicadores indiceIndicadores) {
		return expresion.getValor(periodo, indiceIndicadores);
	}

}
