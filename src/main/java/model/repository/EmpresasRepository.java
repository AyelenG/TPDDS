package model.repository;

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

	public boolean existeEmpresa(String nombreEmpresa) {
		return empresas.stream().anyMatch(empresa -> empresa.es(nombreEmpresa));
	}

	public Empresa buscarEmpresa(String nombreEmpresa) {
		List<Empresa> matches = empresas.stream()
			.filter(empresa->empresa.es(nombreEmpresa))
			.collect(Collectors.toList());
		return matches.isEmpty()?null:matches.get(0);
	}
}
