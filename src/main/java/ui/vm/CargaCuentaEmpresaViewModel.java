package ui.vm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;


import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

@Observable
public class CargaCuentaEmpresaViewModel {

	private Empresa empresaSeleccionada;
	private Cuenta cuentaSeleccionada;
	private String valor = "";
	private int anio;
	private boolean habilitaCarga = true;
	private AnalisisViewModel parentVM;

	public CargaCuentaEmpresaViewModel(AnalisisViewModel parentVM) {
		if (parentVM != null) {
			this.parentVM = parentVM;
			this.empresaSeleccionada = parentVM.getEmpresaSeleccionada();
			Periodo periodoSeleccionado = parentVM.getPeriodoSeleccionado();
			this.anio = periodoSeleccionado != null? periodoSeleccionado.getAnio().intValue() : 0;
		}
	}

	public void nuevaCuenta() {
		this.setCuentaSeleccionada(null);
		this.setValor("");
		this.setHabilitaCarga(true);
	}
	
	private boolean esAnioValido(int anio){
		LocalDate fecha = LocalDate.now();
		return anio >= 1000 && anio <= fecha.getYear();
	}

	public void cargarCuenta() {
		if (empresaSeleccionada == null)
			throw new UserException("Debe seleccionar una empresa.");
		if (this.getCuentaSeleccionada() == null || this.getValor().isEmpty())
			throw new UserException("Complete los datos de la cuenta.");
		if (!esAnioValido(anio))
			throw new UserException("Ingrese un período valido.");
		BigDecimal valor;
		try {
			valor = new BigDecimal(this.getValor());
		} catch (NumberFormatException e) {
			throw new UserException("Debe ingresar un valor válido.");
		}
		empresaSeleccionada.agregarCuenta(new Periodo(anio), new Cuenta(cuentaSeleccionada.getNombre(), valor));
		this.setHabilitaCarga(false);
		
		/**
		 * Con esto impacta los cambios en la ventana de analisis
		 * al momento de cargar la nueva cuenta
		 */
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "periodosSeleccionados");
			ObservableUtils.firePropertyChanged(this.parentVM, "cuentasSeleccionadas");
			parentVM.cargarIndicadores();
		}
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
		return Repositorios.repoEmpresas.getEmpresas();
	}

	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public List<Cuenta> getCuentas() {
		return Repositorios.repoCuentas.getCuentas();
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
