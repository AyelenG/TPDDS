package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class CuentaEmpresa extends Cuenta {

	private BigDecimal valor;

	public CuentaEmpresa() {
		super();
	}

	public CuentaEmpresa(String nombre) {
		super(nombre);
	}

	public CuentaEmpresa(String nombre, BigDecimal valor) {
		super(nombre);
		this.setValor(valor);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
