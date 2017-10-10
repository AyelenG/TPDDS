package model.repositories;

import model.Cuenta;

public class RepoCuentas extends RepoBD<Cuenta> {
	
	private static final RepoCuentas instance = new RepoCuentas();
	
	private RepoCuentas() {
		this.entidad = Cuenta.class;
	}
	
	public static RepoCuentas getInstance() {
		return instance;
	}

	@Override
	protected String valorDeBusqueda(Cuenta elemento) {
		return elemento.getNombre();
	}

	public Cuenta buscarOInsertar(Cuenta cuenta) {
		Cuenta cuentaEncontrada = this.buscarElemento(cuenta);
		if(cuentaEncontrada == null){
			RepoCuentas.getInstance().insertar(cuenta);
			return cuenta;
		}
		else
			return cuentaEncontrada;		
	}

}
