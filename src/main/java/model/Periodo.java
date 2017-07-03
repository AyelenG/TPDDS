package model;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Periodo implements Comparable<Periodo>{
	private Integer anio;
	private List<CuentaEmpresa> cuentas = new LinkedList<>();

	public Periodo(){
		
	}
	public Periodo(Integer anio) {
		this.setAnio(anio);
	}

	public void agregarCuentas(List<CuentaEmpresa> cuentas) {
		for (CuentaEmpresa cuenta : cuentas)
			this.agregarCuenta(cuenta);
	}

	public void agregarCuenta(CuentaEmpresa cuenta) {
		if (!existeCuenta(cuenta))
			cuentas.add(cuenta);
		else
			this.buscarCuenta(cuenta).setValor(cuenta.getValor());
	}

	public CuentaEmpresa buscarCuenta(Cuenta cuenta) {
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

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	public List<CuentaEmpresa> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaEmpresa> cuentas) {
		this.cuentas = cuentas;
	}

}
