package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private Periodo periodo;
	private BigDecimal valor;

	public Cuenta(String nombre, Periodo periodo, BigDecimal valor) {
		this.nombre = nombre;
		this.periodo = periodo;
		this.valor = valor;
	}

	public boolean es(String nombre, Periodo periodo) {
		return this.nombre.equals(nombre) && this.periodo.equals(periodo);
	}

	public boolean esDePeriodo(Periodo periodo) {
		return this.periodo.equals(periodo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Cuenta [nombre=" + nombre + ", periodo=" + periodo + ", valor=" + valor + "]";
	}

}
