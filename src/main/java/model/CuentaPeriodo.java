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

import lombok.Getter;
import lombok.Setter;
import model.repositories.RepoCuentas;

@Entity
@JsonIgnoreProperties({ "changeSupport" })
public class CuentaPeriodo {

	@Id
	@GeneratedValue
	@Getter private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cta_id", nullable = false)
	@Getter private Cuenta cuenta;
	
	@Column(nullable=false)
	@Getter @Setter private BigDecimal valor;

	public CuentaPeriodo() {

	}

	public CuentaPeriodo(String nombre) {
		this.setCuenta(new Cuenta(nombre));
	}

	public CuentaPeriodo(String nombre, BigDecimal valor) {
		this.setCuenta(new Cuenta(nombre));
		this.setValor(valor);
	}

	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());
	}

	public String getNombre(){
		return this.cuenta.getNombre();
	}

	public void setCuenta(Cuenta cuenta) {
		RepoCuentas repoCuentas = RepoCuentas.getInstance();
		this.cuenta = repoCuentas.buscarOInsertar(cuenta);
	}

}
