package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Empresa;

public class RepoEmpresas extends RepoBD<Empresa> {
	
	private static final RepoEmpresas instance = new RepoEmpresas();
	
	private RepoEmpresas() {
		this.entidad = Empresa.class;
	}
	
	public static RepoEmpresas getInstance() {
		return instance;
	}
	
	@Override
	public void insertarVarios(List<Empresa> empresas) {
		this.withTransaction(() -> {
				for (Empresa empresa : empresas) {
					if (!existeElemento(empresa))
						entityManager().persist(empresa);
					else {
						this.buscarElemento(empresa).agregarPeriodos(empresa.getPeriodos());
					}
				}
									});
		
	}
	
	@Override
	protected List<String> camposDeBusqueda() {
		return Arrays.asList("symbol");
	}
	
	@Override
	protected List<Object> valoresDeBusqueda(Empresa emp) {
		return Arrays.asList(emp.getSymbol());
	}
}
