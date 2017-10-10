package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import lombok.Getter;
import lombok.Setter;
import model.evaluador.Expresion;

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
	@Getter private String nombre = "";
	@Column(nullable=false)
	@Getter @Setter private String formula = "";
	
	@Transient
	@Getter @Setter private Expresion expresion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true)
	@Getter @Setter private Usuario user;
	
	public Indicador() {
	}

	public Indicador(String nombre) {
		this.setNombre(nombre);
	}

	public Indicador(String nombre, String formula) {
		this.setNombre(nombre);
		this.setFormula(formula);
	}

	public Indicador(String nombre, String formula, Usuario user) {
		this.setNombre(nombre);
		this.setFormula(formula);
		this.setUser(user);
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public BigDecimal evaluar(Periodo periodo) {
		if (this.getExpresion() == null)
			this.setExpresion(new ExpresionBuilder(this.getFormula()).build());
		return this.getExpresion().getValor(periodo, RepoIndicadores.getInstance());
	}
}
