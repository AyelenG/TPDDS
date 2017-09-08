package model.repositories;

import java.util.List;

import model.Empresa;
import model.data.HandlerArchivoJSON;

public class RepoEmpresasBD extends RepoBD<Empresa> {
	
	private static final RepoEmpresasBD instance = new RepoEmpresasBD();
	
	private RepoEmpresasBD() {

	}
	
	public static RepoEmpresasBD getInstance() {
		return instance;
	}
	
	@Override
	public boolean sonIguales(Empresa e1, Empresa e2) {
		return e1.getNombre().equals(e2.getSymbol());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findAll(){					
		return this.entityManager.createQuery("from Empresa").getResultList();
	}
	
	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/Empresas.json").<Empresa>load(Empresa.class));
	}

}
