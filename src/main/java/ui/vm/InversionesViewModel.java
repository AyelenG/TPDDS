package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.DataLoader;
import model.Empresa;
import model.Periodo;
import model.repository.Repositorios;

@Observable
public class InversionesViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada = null;
	private boolean selectorPeriodo = false;
	private List<Periodo> periodos = null;
	private Periodo periodoSeleccionado = null;
	private List<Cuenta> cuentasSeleccionadas = null;
	private String rutaArchivoDatos = "";
	private boolean botonCargarDatos = true;
	private boolean botonConsultarCuentas = false;
	
	public InversionesViewModel(){
		this.setEmpresas(Repositorios.empresasRepo.getEmpresas());
	}

	public void cargarCuentas() {
		DataLoader.cargarDatosDesdeArchivo(rutaArchivoDatos);
		this.setEmpresas(null);
		this.setEmpresas(Repositorios.empresasRepo.getEmpresas());
		this.setBotonCargarDatos(false);
	}
	
	public void consultarCuentas() {
		this.setCuentasSeleccionadas(empresaSeleccionada.getCuentasPorPeriodo(periodoSeleccionado));		
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.setSelectorPeriodo(true);
		this.setPeriodos(empresaSeleccionada.getPeriodos());
	}

	public boolean isSelectorPeriodo() {
		return selectorPeriodo;
	}

	public void setSelectorPeriodo(boolean selectorPeriodo) {
		this.selectorPeriodo = selectorPeriodo;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	public Periodo getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		this.setBotonConsultarCuentas(true);
	}

	public List<Cuenta> getCuentasSeleccionadas() {
		return cuentasSeleccionadas;
	}

	public void setCuentasSeleccionadas(List<Cuenta> cuentasSeleccionadas) {
		this.cuentasSeleccionadas = cuentasSeleccionadas;
	}

	public String getRutaArchivoDatos() {
		return rutaArchivoDatos;
	}

	public void setRutaArchivoDatos(String rutaArchivoDatos) {
		this.rutaArchivoDatos = rutaArchivoDatos;
	}

	public boolean isBotonCargarDatos() {
		return botonCargarDatos;
	}

	public void setBotonCargarDatos(boolean botonCargarDatos) {
		this.botonCargarDatos = botonCargarDatos;
	}

	public boolean isBotonConsultarCuentas() {
		return botonConsultarCuentas;
	}

	public void setBotonConsultarCuentas(boolean botonConsultarCuentas) {
		this.botonConsultarCuentas = botonConsultarCuentas;
	}
	
}
