package model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.uqbar.commons.utils.Observable;

@Observable
@JsonIgnoreProperties({"changeSupport"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Indicador {

	private String nombre = "";
	private String formula = "";
	private BigDecimal valor;

	public Indicador() {
	}
	public Indicador(String nombre) {
		this.setNombre(nombre);
	}
	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.formula = formula;
	}
	public Indicador(String nombre, String formula, BigDecimal nuevoValor){
		this.setNombre(nombre);
		this.formula = formula;
		this.valor=nuevoValor;
	}
	public Indicador(String nombre, BigDecimal nuevoValor){
		this.setNombre(nombre);
		
		this.valor=nuevoValor;
	}

	public boolean esIgual(Indicador indicador) {
		return this.getNombre().equals(indicador.getNombre());
	}
	
	@Override
	public String toString(){
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
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
