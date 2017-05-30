package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo implements Comparable<Periodo>{
	private Integer anio;
	private List<Cuenta> cuentas = new LinkedList<>();

	public Periodo(){
		
	}
	public Periodo(Integer anio) {
		this.setAnio(anio);
	}

	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Cuenta cuenta : cuentas)
			this.agregarCuenta(cuenta);
	}

	public void agregarCuenta(Cuenta cuenta) {
		if (!existeCuenta(cuenta))
			cuentas.add(cuenta);
		else
			this.buscarCuenta(cuenta).setValor(cuenta.getValor());
	}

	public Cuenta buscarCuenta(Cuenta cuenta) {
		return cuentas.stream().filter(_cuenta -> _cuenta.esIgual(cuenta)).findFirst().orElse(null);
	}

	public boolean existeCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(_cuenta -> _cuenta.esIgual(cuenta));
	}

	public boolean esIgual(Periodo periodo) {
		return this.getAnio().equals(periodo.getAnio());
	}

	@Override
	public String toString() {
		return getAnio().toString();
	}
	
	@Override
	public int compareTo(Periodo o) {
		return this.getAnio() - o.getAnio();
	}

	public Integer getAnio() {
		return anio;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}
