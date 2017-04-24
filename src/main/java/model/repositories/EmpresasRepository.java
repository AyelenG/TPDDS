package model.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;

public class EmpresasRepository {
	private List<Empresa> empresas = new LinkedList<>();

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void agregarEmpresa(Empresa empresa) {
		empresas.add(empresa);
	}

	public Empresa buscarEmpresa(String nombreEmpresa) {
		List<Empresa> emps = empresas.stream().filter(emp -> emp.esIgual(nombreEmpresa)).collect(Collectors.toList());
		if (emps.isEmpty()) {
			Empresa nueva = new Empresa(nombreEmpresa);
			this.agregarEmpresa(nueva);
			return nueva;
		}
		return emps.get(0);
	}

}
