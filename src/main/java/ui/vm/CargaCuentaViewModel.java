package ui.vm;

import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

@Observable
public class CargaCuentaViewModel {	
	Cuenta cuenta = new Cuenta();
	private Empresa empresaSeleccionada;
	private List<Empresa> empresas = Repositorios.empresas.getEmpresas();
	private int anio;

	private boolean habilitaCarga = true;
	private boolean habilitaNueva = false;

	public void nuevaCuenta() {		
		this.setCuenta(new Cuenta());
		this.setHabilitaCarga(true);
		this.setHabilitaNueva(false);
	}
	
	public void cargarCuenta() {
		if (empresaSeleccionada == null)
			throw new UserException("Debe seleccionar una empresa.");
		if ( cuenta.getNombre() == null || cuenta.getValor() == null)
			throw new UserException("Complete los datos de la cuenta.");
		if ( anio < 1000 || anio > 3000)
			throw new UserException("Ingrese un período valido.");	
		empresaSeleccionada.buscarPeriodoYAgregar(new Periodo(anio)).agregarCuenta(cuenta);
		this.setHabilitaCarga(false);
		this.setHabilitaNueva(true);
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;		
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
	}
	
	public boolean isHabilitaNueva() {
		return habilitaNueva;
	}
	
	public void setHabilitaNueva(boolean habilitaNueva) {
		this.habilitaNueva = habilitaNueva;
	}
	
}
