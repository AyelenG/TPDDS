package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa implements Comparable<Empresa> {
	private String nombreEmpresa;
	private List<Periodo> periodos = new LinkedList<>();

	public Empresa(String nombre) {
		this.nombreEmpresa = nombre;
	}

	public Periodo buscarPeriodo(Integer año) {
		return periodos.stream().filter(p -> p.esIgual(año)).findFirst().orElse(null);
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}

	public boolean esIgual(String nombre) {
		return this.nombreEmpresa.equals(nombre);
	}

	@Override
	public String toString() {
		return getNombreEmpresa();
	}

	@Override
	public int compareTo(Empresa o) {
		return this.nombreEmpresa.compareTo(o.nombreEmpresa);
	}

}
