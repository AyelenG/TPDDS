package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class CuentaEmpresa{

	@Id
	@GeneratedValue
	private long id;
	
	private Cuenta cuenta;
	private BigDecimal valor;

	public CuentaEmpresa() {
	}

	public CuentaEmpresa(String nombre) {
		this.setCuenta(new Cuenta(nombre));
	}

	public CuentaEmpresa(String nombre, BigDecimal valor) {
		this.setCuenta(new Cuenta(nombre));
		this.setValor(valor);
	}

	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());
	}
	
	public String getNombre(){
		return this.cuenta.getNombre();
	}
	
	public Cuenta getCuenta(){
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
