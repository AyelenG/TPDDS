package model.repositories;

import model.Cuenta;
import model.data.HandlerArchivoJSON;

public class RepoCuentasBD extends RepoBD<Cuenta>{
	
	private static final RepoCuentasBD instance = new RepoCuentasBD();
	
	private RepoCuentasBD(){}
	
	public static RepoCuentasBD getInstance() {
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
