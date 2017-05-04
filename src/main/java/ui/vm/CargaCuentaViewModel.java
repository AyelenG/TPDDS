package ui.vm;

import java.math.BigDecimal;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

@Observable
public class CargaCuentaViewModel {

	private Empresa empresaSeleccionada;
	private String nombre = "";
	private String valor = "";
	private int anio;

	private boolean habilitaCarga = true;
	private boolean habilitaNueva = false;

	public CargaCuentaViewModel(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public void nuevaCuenta() {
		this.setNombre("");
		this.setValor("");
		this.setHabilitaCarga(true);
		this.setHabilitaNueva(false);
	}

	public void cargarCuenta() {
		if (empresaSeleccionada == null)
			throw new UserException("Debe seleccionar una empresa.");
		if (this.getNombre() == "" || this.getValor() == "")
			throw new UserException("Complete los datos de la cuenta.");
		if (anio < 1000 || anio > 3000)
			throw new UserException("Ingrese un período valido.");
		BigDecimal valor;
		try {
			 valor = new BigDecimal(this.getValor());
		} catch (NumberFormatException e) {
			throw new UserException("Debe ingresar un valor válido.");
		}
		empresaSeleccionada.buscarPeriodoYAgregar(new Periodo(anio)).agregarCuenta(new Cuenta(this.getNombre(), valor));
		this.setHabilitaCarga(false);
		this.setHabilitaNueva(true);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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
		return Repositorios.empresas.getEmpresas();
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
