package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Periodo implements Comparable<Periodo> {
	
	@Id
	@GeneratedValue
	@Getter private long id;
	
	@Column(nullable=false)
	@Getter @Setter private Integer anio;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "peri_id", nullable = false)
	@Getter @Setter private List<CuentaPeriodo> cuentas = new LinkedList<>();

	public Periodo() {
		
	}
	
	public Periodo(Integer anio) {
		this.setAnio(anio);
	}

	public void agregarCuentas(List<CuentaPeriodo> cuentas) {
		for (CuentaPeriodo cuenta : cuentas)
			this.agregarCuenta(cuenta);
	}

	public void agregarCuenta(CuentaPeriodo cuentaEmpresa) {
		if (!existeCuenta(cuentaEmpresa.getCuenta()))
			cuentas.add(cuentaEmpresa);
		else
			this.buscarCuenta(cuentaEmpresa.getCuenta()).setValor(cuentaEmpresa.getValor());
	}

	public CuentaPeriodo buscarCuenta(Cuenta cuenta) {
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
}
