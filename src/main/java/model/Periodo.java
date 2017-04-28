package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {
	private Integer anio;
	private List<Cuenta> cuentas = new LinkedList<>();

	public Periodo() {

	}

	public Periodo(Integer anio) {
		this.anio = anio;
	}

	public void buscarCuentaYAgregarOModificar(Cuenta cuenta) {
		Cuenta cuentaEncontrada = this.buscarCuenta(cuenta);
		if (cuentaEncontrada != null) {
			cuentaEncontrada.setValor(cuenta.getValor());
		} else {
			this.agregarCuenta(cuenta);
		}
	}

	public Cuenta buscarCuenta(Cuenta cuenta) {
		return cuentas.stream().filter(_cuenta -> _cuenta.esIgual(cuenta)).findFirst().orElse(null);
	}

	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Object cuentaObject : cuentas) {
			buscarCuentaYAgregarOModificar((Cuenta) cuentaObject); //para cambiarle el valor si la encuentra
		}
	}

	public boolean existeCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(_cuenta -> _cuenta.esIgual(cuenta));
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public boolean esIgual(Periodo periodo) {
		return this.getAnio().equals(periodo.getAnio());
	}

	@Override
	public String toString() {
		return getAnio().toString();
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
