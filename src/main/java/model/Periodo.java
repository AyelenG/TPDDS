package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {
	private Integer anio;
	private List<Cuenta> cuentas = new LinkedList<>();
	
	public Periodo(){
		
	}
	
	public Periodo(Integer anio) {
		super();
		this.anio = anio;
	}

	public Cuenta buscarCuentaYAgregar(Cuenta cuenta) {
		Optional<Cuenta> cuentaEncontrada = cuentas.stream().filter(_cuenta -> _cuenta.esIgual(cuenta)).findFirst();		
		if (cuentaEncontrada.isPresent())
			return cuentaEncontrada.get();
		Cuenta cuentaNueva = new Cuenta(cuenta.getNombre(), cuenta.getValor());
		this.agregarCuenta(cuentaNueva);
		return cuentaNueva;
	}
	
	public boolean esIgual(Periodo periodo) {
		return this.getAnio().equals(periodo.getAnio());
	}
	
	public boolean existeCuenta(Cuenta cuenta){
		return cuentas.stream().anyMatch(_cuenta -> _cuenta.esIgual(cuenta));		
	}
	
	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Object cuentaObject : cuentas) {			
			if (!existeCuenta((Cuenta) cuentaObject))
				this.agregarCuenta((Cuenta) cuentaObject);
		}					
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

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	
}
