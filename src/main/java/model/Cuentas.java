package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
@Observable
public class Cuentas {

	private List<Cuenta> cuentas = new LinkedList<>();

	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Cuenta cuenta : cuentas)
			if (!existeCuenta(cuenta))
				this.agregarCuenta(cuenta);
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public Cuenta buscarCuenta(Cuenta cuenta) {
		return cuentas.stream().filter(_cuenta -> _cuenta.esIgual(cuenta)).findFirst().orElse(null);
	}

	public boolean existeCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(_cuenta -> _cuenta.esIgual(cuenta));
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public Cuenta get(int i) {
		return cuentas.get(i);
	}

	public int indexOf(Cuenta cuenta) {
		return cuentas.indexOf(cuenta);
	}

	public int size() {
		return cuentas.size();
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

}
