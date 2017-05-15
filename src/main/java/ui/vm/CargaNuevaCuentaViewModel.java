package ui.vm;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.data.CuentasPredeterminadas;
import model.repositories.Repositorios;

@Observable
public class CargaNuevaCuentaViewModel {

	Cuenta cuenta = new Cuenta();
	private boolean habilitaCarga = true;

	public void nuevaCuenta() {		
		this.setCuenta(new Cuenta());
		this.setHabilitaCarga(true);
	}

	public void cargarCuenta() {
		if (Repositorios.cuentasPredeterminadas.existeCuenta(cuenta))
			throw new UserException("La cuenta ingresada ya existe.");
		if(cuenta.getNombre().isEmpty() || cuenta.getNombre() == null){
			throw new UserException("Complete el nombre de la Cuenta.");
		}
		Repositorios.cuentasPredeterminadas.agregarCuenta(cuenta);
		CuentasPredeterminadas.actualizarJSON();
		this.setHabilitaCarga(false);
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
		ObservableUtils.firePropertyChanged(this, "habilitaNueva");
	}

	public boolean isHabilitaNueva() {
		return !habilitaCarga;
	}

}