package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import model.evaluador.Expresion;
import model.evaluador.terminales.*;
import model.evaluador.operaciones.*;

import model.parser.ExpresionBuilder;
import model.repositories.RepoIndicadores;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport", "expresion" })
public class Indicador {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 50, unique = true, nullable=false)
	private String nombre = "";
	@Column(nullable=false)
	private String formula = "";
	
	@Transient
	private Expresion expresion;

	public Indicador() {
		
	}

	public Indicador(String nombre) {
		this.setNombre(nombre);
	}

	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
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
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public BigDecimal evaluar(Periodo periodo) {
		if (this.getExpresion() == null)
			this.setExpresion(new ExpresionBuilder(this.getFormula()).build());
		return this.getExpresion().getValor(periodo, RepoIndicadores.getInstance());
	}

	public String generarFormula(){
		return this.generarArbol(this.getExpresion());
	}
	
	private String generarArbol(Expresion expresion){
		String s = "";
		String op = "";
		
		if(expresion instanceof TerminalLiteral){
			s += ((TerminalLiteral) expresion).getLiteral();
		}
		else if(expresion instanceof TerminalCuenta){
			s += ((TerminalCuenta) expresion).getNombreCuenta();
		}
		else if(expresion instanceof TerminalIndicador){
			s += ((TerminalIndicador) expresion).getNombreIndicador();
		}
		else{
			if(expresion instanceof Suma){
				op = " + ";
			}
			else if(expresion instanceof Resta){
				op = " - ";
			}
			else if(expresion instanceof Multiplicacion){
				op = " * ";
			}
			else if(expresion instanceof Division){
				op = " / ";
			}
			
			s += "(" + this.generarArbol(((Operacion) expresion).getOpIzq()) 
					+ op +
					this.generarArbol(((Operacion) expresion).getOpDer()) + ")";
		}
		return s;
	}	

}
