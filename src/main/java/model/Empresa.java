package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Empresa {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String symbol;
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "empr_id")
	private List<Periodo> periodos = new LinkedList<>();

	public Empresa() {
		this.setSymbol(new String());
		this.setNombre(new String());
	}

	public Empresa(String symbol, String nombre) {
		this.setSymbol(symbol);
		this.setNombre(nombre);
	}

	public Periodo buscarPeriodoYAgregar(Periodo periodo) {
		Periodo periodoEncontrado = this.buscarPeriodo(periodo);
		if (periodoEncontrado != null)
			return periodoEncontrado;
		this.agregarPeriodo(periodo);
		return periodo;
	}

	public void agregarPeriodos(List<Periodo> periodos) {
		for (Periodo periodo : periodos) {
			if (!existePeriodo(periodo))
				this.agregarPeriodo(periodo);
			else
				this.buscarPeriodo(periodo).agregarCuentas(periodo.getCuentas());
		}
	}

	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}

	public Periodo buscarPeriodo(Periodo periodo) {
		return periodos.stream().filter(_periodo -> _periodo.esIgual(periodo)).findFirst().orElse(null);
	}

	public boolean existePeriodo(Periodo periodo) {
		return periodos.stream().anyMatch(_periodo -> _periodo.esIgual(periodo));
	}

	public List<Periodo> getUltimosNAnios(int n) {
		return this.getPeriodos().stream()
					.filter(p -> p.getAnio() >= LocalDate.now().getYear() - n).sorted()
					.collect(Collectors.toList());
	}

	public int antiguedad(){
		Periodo primerPeriodo = this.getPeriodos().stream().sorted().collect(Collectors.toList()).get(0);
		return LocalDate.now().getYear() - primerPeriodo.getAnio();
	}
	/*
	 * Agrega una cuenta en el periodo correspondiente, si no existe el periodo
	 * en la empresa lo agrega
	 */
	public void agregarCuenta(Periodo periodo, CuentaEmpresa cuenta) {
		this.buscarPeriodoYAgregar(periodo).agregarCuenta(cuenta);
		
		
	}

	public boolean noEstaEn(List<Empresa> empresas){
		return !empresas.contains(this);
	}
	
	public String toString() {
		return getSymbol() + " - " + getNombre();
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol.toUpperCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	public long getId() {
		return id;
	}
}
