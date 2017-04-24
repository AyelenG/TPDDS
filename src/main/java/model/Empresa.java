package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombre;
	private List<Cuenta> cuentas = new LinkedList<>();

	public Empresa(String nombre) {
		this.nombre = nombre;
	}

	public boolean es(String nombreEmpresa) {
		return nombre.equals(nombreEmpresa);
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public Cuenta buscarCuenta(String nombreCuenta, Periodo periodo) {
		List<Cuenta> matches = cuentas.stream().filter(cuenta -> cuenta.es(nombreCuenta, periodo))
				.collect(Collectors.toList());
		return matches.isEmpty() ? null : matches.get(0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public List<Cuenta> getCuentasPorPeriodo(Periodo periodo) {
		return cuentas.stream().filter(cuenta -> cuenta.esDePeriodo(periodo)).collect(Collectors.toList());
	}

	public List<Periodo> getPeriodos() {
		return cuentas.stream().
		map(cuenta -> cuenta.getPeriodo()).distinct().sorted()
		.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return nombre;
	}

}
