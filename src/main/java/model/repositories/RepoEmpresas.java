package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

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

		for (Empresa empresa : empresas) {
			if (!existeElemento(empresa))
				this.insertar(empresa);
			else {
				EntityTransaction tx = entityManager.getTransaction();
				tx.begin();
				this.buscarElemento(empresa).agregarPeriodos(empresa.getPeriodos());
				tx.commit();
			}
		}
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
