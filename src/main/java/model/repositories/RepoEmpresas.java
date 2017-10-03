package model.repositories;

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
	protected String valorDeBusqueda(Empresa elemento) {
		return elemento.getSymbol();
	}

	@Override
	protected String campoDeBusqueda() {
		return "symbol";
	}
	
}
