package model.repositories;

import java.util.List;

import model.Cuenta;
import model.data.HandlerArchivoJSON;

public class RepoCuentasBD extends RepoBD<Cuenta>{
	
	private static final RepoCuentasBD instance = new RepoCuentasBD();
	
	private RepoCuentasBD(){}
	
	public static RepoCuentasBD getInstance() {
		return instance;
	}
	
	@Override
	public boolean sonIguales(Cuenta c1, Cuenta c2) {
		return c1.getNombre().equals(c2.getNombre());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> findAll(){					
		return this.entityManager.createQuery("from Cuenta").getResultList();
	}

	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/CuentasPredeterminadas.json").<Cuenta>load(Cuenta.class));
	}
}
