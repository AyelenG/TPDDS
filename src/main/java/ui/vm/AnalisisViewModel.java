package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

@Observable
public class AnalisisViewModel {
	private Empresa empresaSeleccionada;
	private List<Periodo> periodosSeleccionados;
	private Periodo periodoSeleccionado;
	private List<Cuenta> cuentasSeleccionadas;
	
	private boolean selectorPeriodo = false;
	private boolean botonConsultarCuentas = false;


	public void consultarCuentas() {
		this.setCuentasSeleccionadas(periodoSeleccionado.getCuentas());
	}

	public List<Empresa> getEmpresas() {
		return Repositorios.empresas.getEmpresas();
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.setPeriodosSeleccionados(empresaSeleccionada.getPeriodos());
		this.setSelectorPeriodo(true);
		this.setBotonConsultarCuentas(false);
	}

	public List<Periodo> getPeriodosSeleccionados() {
		return periodosSeleccionados;
	}

	public void setPeriodosSeleccionados(List<Periodo> periodos) {
		this.periodosSeleccionados = periodos;
	}

	public Periodo getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		this.setBotonConsultarCuentas(true);
	}

	public List<Cuenta> getCuentasSeleccionadas() {
		return this.cuentasSeleccionadas;
	}

	public void setCuentasSeleccionadas(List<Cuenta> cuentas) {
		this.cuentasSeleccionadas = cuentas;
	}

	public boolean isSelectorPeriodo() {
		return selectorPeriodo;
	}

	public void setSelectorPeriodo(boolean selectorPeriodo) {
		this.selectorPeriodo = selectorPeriodo;
	}

	public boolean isBotonConsultarCuentas() {
		return botonConsultarCuentas;
	}

	public void setBotonConsultarCuentas(boolean botonConsultarCuentas) {
		this.botonConsultarCuentas = botonConsultarCuentas;
	}

}
