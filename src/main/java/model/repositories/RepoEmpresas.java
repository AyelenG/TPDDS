package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import model.Empresa;
import model.data.HandlerArchivoJSON;

public class RepoEmpresas extends RepoBD<Empresa> {
	
	private static final RepoEmpresas instance = new RepoEmpresas();
	
	private RepoEmpresas() {

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
	
	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/Empresas.json").<Empresa>load(Empresa.class));
	}

	@Override
	protected String valorDeBusqueda(Empresa elemento) {
		return elemento.getSymbol();
	}

	@Override
	protected String campoDeBusqueda() {
		return "symbol";
	}

	@Override
	protected Class<Empresa> getEntityClass() {
		return Empresa.class;
	}

	@Override
	protected String getEntityName() {
		return "Empresa";
	}

}
