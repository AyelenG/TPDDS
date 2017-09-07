package model.repositories;

import java.util.List;

import model.Metodologia;

public class RepoMetodologiasDB extends RepoBD<Metodologia> {
	
	private static final RepoMetodologiasDB instance = new RepoMetodologiasDB();
	
	private RepoMetodologiasDB(){}
	
	public static RepoMetodologiasDB getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Metodologia> findAll(){					
		return this.entityManager.createQuery("from Metodologia").getResultList();
	}
	
//	public void cargarBDDesdeArchivo(HandlerArchivo handler) {		
//		EntityTransaction tx = entityManager.getTransaction();
//		List<Metodologia> metodologias = handler.loadMetodologias();
//		tx.begin();
//		metodologias.forEach(metodologia -> entityManager.persist(metodologia));
//		tx.commit();
//	}

}
