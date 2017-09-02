package ui.vm;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import model.Cuenta;
import model.repositories.RepoCuentas;
import model.repositories.RepoCuentasBD;

@Observable
public class CargaNuevaCuentaViewModel {

	Cuenta cuenta = new Cuenta();
	private boolean habilitaCarga = true;

	private CargaCuentaEmpresaViewModel parentVM;

	public CargaNuevaCuentaViewModel(CargaCuentaEmpresaViewModel parentVM) {
		if (parentVM != null) {
			this.parentVM = parentVM;
		}
	}

	public void nuevaCuenta() {
		this.setCuenta(new Cuenta());
		this.setHabilitaCarga(true);
	}

	public void cargarCuenta() {
		RepoCuentas repositorioCuentas = RepoCuentas.getInstance();
		if (repositorioCuentas.existeElemento(cuenta))
			throw new UserException("La cuenta ingresada ya existe.");
		if (cuenta.getNombre().isEmpty()) {
			throw new UserException("Complete el nombre de la Cuenta.");
		}
		repositorioCuentas.insertar(cuenta);
		repositorioCuentas.guardar();
		RepoCuentas.getInstance().insertar(cuenta);
		this.setHabilitaCarga(false);
		if (parentVM != null)
			ObservableUtils.firePropertyChanged(this.parentVM, "cuentas");
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