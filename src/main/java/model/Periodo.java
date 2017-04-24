package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo implements Comparable<Periodo>{
	private Integer año;
	private List<Cuenta> cuentas = new LinkedList<>();

	public Periodo(Integer año) {
		this.año = año;
	}

	public Integer getAño() {
		return año;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void setAño(Integer año) {
		this.año = año;
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public boolean esIgual(Integer año) {
		return this.año.equals(año);
	}

	public Cuenta buscarCuenta(String nombreCuenta) {
		List<Cuenta> ctas = cuentas.stream().filter(c -> c.esIgual(nombreCuenta)).collect(Collectors.toList());
		if (ctas.isEmpty()) {
			Cuenta nueva = new Cuenta(nombreCuenta);
			this.agregarCuenta(nueva);
			return nueva;
		}
		return ctas.get(0);
	}

	@Override
	public String toString() {
		return getAño().toString();
	}

	@Override
	public int compareTo(Periodo o) {
		return this.año.compareTo(o.año);
	}

}
