package model;

import java.util.LinkedList;
import java.util.List;

public class Cuentas {

	private List<Cuenta> cuentas = new LinkedList<>();
	
	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Object cuentaObject : cuentas)
			this.agregarCuenta((Cuenta) cuentaObject);
	}	
	
	public void agregarCuenta(Cuenta cuenta) {
		if (!existeCuenta(cuenta))
			cuentas.add(new Cuenta(cuenta.getNombre(), null));
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
	
	public Cuenta get(int i){
		return cuentas.get(i);
	}	
	
	public int size() {
		return cuentas.size();
	}
}