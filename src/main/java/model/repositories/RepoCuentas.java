package model.repositories;

import java.util.Arrays;
import java.util.List;

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
	protected List<Object> valoresDeBusqueda(Cuenta cuenta) {
		return Arrays.asList(cuenta.getNombre());
	}

	public Cuenta buscarOInsertar(Cuenta cuenta) {
		Cuenta cuentaEncontrada = this.buscarElemento(cuenta);
		if(cuentaEncontrada == null){
			this.insertar(cuenta);
			return cuenta;
		}
		else
			return cuentaEncontrada;		
	}

}
