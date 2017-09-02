package model.repositories;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.Empresa;
import model.data.HandlerArchivoJSON;

public class RepoCuentasBD extends RepoBD<Cuenta>{
	
	private static final RepoCuentasBD instance = new RepoCuentasBD();
	
	private RepoCuentasBD(){}
	
	public static RepoCuentasBD getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> findAll(){					
		return this.entityManager.createQuery("from Cuenta").getResultList();
	}

	public void cargarBDDesdeArchivo(){		
		EntityTransaction tx = entityManager.getTransaction();
		List<Cuenta> cuentas = new HandlerArchivoJSON("data/CuentasPredeterminadas.json").<Cuenta>load(Cuenta.class);
		tx.begin();
		cuentas.forEach(cuenta -> entityManager.persist(cuenta));
		tx.commit();
	}
}
