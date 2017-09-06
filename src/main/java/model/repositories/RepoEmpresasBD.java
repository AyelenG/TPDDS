package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.data.HandlerArchivo;
import model.data.HandlerArchivoJSON;

public class RepoEmpresasBD extends RepoBD<Empresa> {
	
	private static final RepoEmpresasBD instance = new RepoEmpresasBD();
	
	private RepoEmpresasBD(){}
	
	public static RepoEmpresasBD getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findAll(){					
		return this.entityManager.createQuery("from Empresa").getResultList();
	}
	
	public void cargarBDDesdeArchivo(HandlerArchivo handler) {		
		EntityTransaction tx = entityManager.getTransaction();
		List<Empresa> empresas = handler.loadEmpresas();
		tx.begin();
		empresas.forEach(empresa -> entityManager.persist(empresa));
		tx.commit();
		
	}

}
