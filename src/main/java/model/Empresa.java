package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombreEmpresa;
	private List<Periodo> periodos = new LinkedList<>();

	public Empresa(String nombre) {
		this.nombreEmpresa = nombre;
	}

	public Periodo buscarPeriodo(Integer año) {
		List<Periodo> pdos = periodos.stream().filter(p -> p.esIgual(año)).collect(Collectors.toList());
		if (pdos.isEmpty()) {
			Periodo nuevo = new Periodo(año);
			this.agregarPeriodo(nuevo);
			return nuevo;
		}
		return pdos.get(0);
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

}
