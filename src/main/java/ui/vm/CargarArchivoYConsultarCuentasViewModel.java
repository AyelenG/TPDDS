package ui.vm;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.DataLoader;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

@Observable
public class CargarArchivoYConsultarCuentasViewModel {
	private String ruta = "";
	private boolean botonCargarDatos = true;
	private boolean botonConsultarCuentas = false;
	private boolean selectorPeriodo = false;
	private List<Empresa> empresas = Repositorios.empresasRepo.getEmpresas();
	private Empresa empresaSeleccionada;
	private List<Periodo> periodosSeleccionados;
	private Periodo periodoSeleccionado;
	private List<Cuenta> cuentasSeleccionadas;

	public void cargarCuentas() {
		// ManipuladorArchivo manipulador = new ManipuladorArchivo(this.ruta);
		// List<Empresa> empresas = manipulador.deArchivoAEmpresas();
		DataLoader.cargarDatosDesdeArchivo(ruta);
		this.setEmpresas(Repositorios.empresasRepo.getEmpresas()
				.stream().sorted().collect(Collectors.toList()));
		this.setBotonCargarDatos(false);
	}

	public void consultarCuentas() {
		this.setCuentasSeleccionadas(periodoSeleccionado.getCuentas());
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<Cuenta> getCuentas() {
		return cuentasSeleccionadas;
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
		this.setPeriodosSeleccionados(empresaSeleccionada.getPeriodos()
				.stream().sorted().collect(Collectors.toList()));
		this.setSelectorPeriodo(true);
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

	public boolean isSelectorPeriodo() {
		return selectorPeriodo;
	}

	public void setSelectorPeriodo(boolean selectorPeriodo) {
		this.selectorPeriodo = selectorPeriodo;
	}

}
