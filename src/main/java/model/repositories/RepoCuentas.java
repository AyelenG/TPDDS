package model.repositories;

import model.Cuenta;
import model.data.HandlerArchivoJSON;

public class RepoCuentas extends RepoBD<Cuenta> {
	
	private static final RepoCuentas instance = new RepoCuentas();
	
	private RepoCuentas() {
		
	}
	
	public static RepoCuentas getInstance() {
		return instance;
	}

	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/CuentasPredeterminadas.json").<Cuenta>load(Cuenta.class));
	}

	@Override
	protected String valorDeBusqueda(Cuenta elemento) {
		return elemento.getNombre();
	}

	@Override
	protected String campoDeBusqueda() {
		return "nombre";
	}

	@Override
	protected Class<Cuenta> getEntityClass() {
		return Cuenta.class;
	}

	@Override
	protected String getEntityName() {
		return "Cuenta";
	}
}
