package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.exceptions.CuentaVaciaException;
import model.repositories.Repositorios;

@Observable
public class CargaCuentaViewModel {	
	Cuenta cuenta = new Cuenta();
	private Empresa empresaSeleccionada;
	private List<Empresa> empresas = Repositorios.empresas.getEmpresas();
	private Integer anio;
	private boolean habilitaCarga = false;
	private String mensajeExito = "";
	
	public void cargarCuenta() {
		if(anio == null || cuenta.getNombre() == null || cuenta.getValor() == null){
			throw new CuentaVaciaException();
		}
		empresaSeleccionada.buscarPeriodoYAgregar(new Periodo(anio)).buscarCuentaYAgregarOModificar(cuenta);
		this.setMensajeExito("Carga realizada Exitosamente"); //si esta la cuenta le cambia el valor, si no la agrega
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getMensajeExito() {
		return mensajeExito;
	}

	public void setMensajeExito(String mensajeExito) {
		this.mensajeExito = mensajeExito;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.setHabilitaCarga(true);
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

}
