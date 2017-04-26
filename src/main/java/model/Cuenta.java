package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta implements Comparable<Cuenta> {
	private String nombreCuenta;
	private BigDecimal valor;

	public Cuenta(String nombre) {
		this.nombreCuenta = nombre;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean esIgual(String nombre) {
		return this.nombreCuenta.equals(nombre);
	}

	public String toString() {
		return this.nombreCuenta;
	}

	@Override
	public int compareTo(Cuenta o) {
		return this.nombreCuenta.compareTo(o.nombreCuenta);
	}
}
