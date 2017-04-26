package model.repositories;

import java.util.LinkedList;
import java.util.List;

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
		return empresas.stream().filter(emp -> emp.esIgual(nombreEmpresa)).findFirst().orElse(null);
	}

}
