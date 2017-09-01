package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Empresa;
import model.data.HandlerArchivoJSON;

public class RepoEmpresasBD extends RepoBD<Empresa> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findAll(){					
		return this.entityManager.createQuery("from Empresa").getResultList();
	}
	
	public void cargarBDDesdeArchivo() {		
		EntityTransaction tx = entityManager.getTransaction();
		List<Empresa> empresas = new HandlerArchivoJSON("data/CuentasPrueba.json").<Empresa>load(Empresa.class);
		tx.begin();
		empresas.forEach(empresa -> entityManager.persist(empresa));
		tx.commit();
		
	}

}
