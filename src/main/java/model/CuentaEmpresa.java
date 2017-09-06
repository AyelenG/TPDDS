package model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import model.repositories.RepoCuentas;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class CuentaEmpresa{

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cta_id", nullable = false)
	private Cuenta cuenta;
	
	@Column(nullable=false)
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
		//TODO  CAMBIAR ESTO A BASE DE DATOS
		Cuenta cuentaEncontrada = RepoCuentas.getInstance().buscarElemento(cuenta);
		if(cuentaEncontrada == null){
			RepoCuentas.getInstance().insertar(cuenta);
			RepoCuentas.getInstance().guardar();
			this.cuenta = cuenta;
		}
		else
			this.cuenta = cuentaEncontrada;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
