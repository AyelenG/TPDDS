package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String symbol;
	private String nombre;
	private List<Periodo> periodos = new LinkedList<>();
	
	public Empresa() {

	}

	public Empresa(String symbol, String nombrenombre) {
		super();
		this.symbol = symbol;
		this.nombre = nombrenombre;
	}

	public Periodo buscarPeriodoYAgregar(Periodo periodo) {
		Optional<Periodo> periodoEncontrado = periodos.stream().filter(_periodo -> _periodo.esIgual(periodo)).findFirst();		
		if (periodoEncontrado.isPresent())
			return periodoEncontrado.get();
		Periodo periodoNuevo = new Periodo(periodo.getAnio());
		this.agregarPeriodo(periodoNuevo);
		return periodoNuevo;
	}

	public boolean existePeriodo(Periodo periodo){
		return periodos.stream().anyMatch(_periodo -> _periodo.esIgual(periodo));		
	}
	
	public void agregarPeriodos(List<Periodo> periodos) {
		for (Object peridodoObject : periodos) {			
			Periodo periodo = (Periodo) peridodoObject;
			if (!existePeriodo(periodo)) {
				this.agregarPeriodo(periodo);
			}
			else {
				this.buscarPeriodo(periodo).agregarCuentas(periodo.getCuentas());
			}
		}
	}	
	
	public Periodo buscarPeriodo(Periodo periodo){
		Optional<Periodo> periodoEncontrado = periodos.stream().filter(_periodo -> _periodo.esIgual(periodo)).findFirst();
		return periodoEncontrado.isPresent() ? periodoEncontrado.get() : null;
	}
	
	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}

	public boolean esIgual(Empresa empresa) {
		return this.getSymbol().equals(empresa.getSymbol());
	}

	@Override
	public String toString() {
		return getSymbol() + " " + getNombre();
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

}
