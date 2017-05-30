package ui.vm;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import java.math.BigDecimal;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.Indicadores;
import model.Periodo;
import model.parser.evaluador.Evaluador;
import model.repositories.Repositorios;

@Observable
public class AnalisisViewModel {
	private Empresa empresaSeleccionada;
	private List<Periodo> periodosSeleccionados = new LinkedList<>();
	private Periodo periodoSeleccionado;
	private List<Cuenta> cuentasSeleccionadas;

	private Indicadores indiceIndicadores = Repositorios.repoIndicadores;
	private List<Indicador> indicadoresConValor = new LinkedList<>();

	private boolean selectorPeriodo = false;
	private boolean botonConsultar = false;

	public void consultar() {
		this.setCuentasSeleccionadas(periodoSeleccionado.getCuentas());
		this.cargarIndicadores();
	}

	public void cargarIndicadores() {
		this.indicadoresConValor.clear();
		this.agregarIndicadoresConValor(indiceIndicadores.getIndicadores());
		ObservableUtils.firePropertyChanged(this, "indicadoresConValor");
	}

	private void agregarIndicadoresConValor(List<Indicador> indicadores) {
		BigDecimal valor;
		Indicador indicadorACargar;
		for (Indicador indicador : indicadores) {
			valor = Evaluador.evaluar(indicador, getPeriodoSeleccionado(), indiceIndicadores);
			if (valor != null) {
				indicadorACargar = new Indicador(indicador.getNombre(), indicador.getFormula(), valor);
				this.agregarIndicador(indicadorACargar);
			}
		}
	}

	private void agregarIndicador(Indicador indicador) {
		this.indicadoresConValor.add(indicador);
	}

	public List<Empresa> getEmpresas() {
		return Repositorios.repoEmpresas.getEmpresas();
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.setPeriodosSeleccionados(empresaSeleccionada.getPeriodos());
		this.setSelectorPeriodo(true);
		this.setBotonConsultar(false);
	}

	public List<Periodo> getPeriodosSeleccionados() {
		return periodosSeleccionados.stream().sorted().collect(Collectors.toList());
	}

	public void setPeriodosSeleccionados(List<Periodo> periodosSeleccionados) {
		this.periodosSeleccionados = periodosSeleccionados;
	}

	public Periodo getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		this.setBotonConsultar(true);
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

	public boolean isBotonConsultar() {
		return botonConsultar;
	}

	public void setBotonConsultar(boolean botonConsultarCuentas) {
		this.botonConsultar = botonConsultarCuentas;
	}

	public List<Indicador> getIndicadoresConValor() {
		return indicadoresConValor;
	}

	public void setIndicadoresConValor(List<Indicador> indicadores) {
		this.indicadoresConValor = indicadores;
	}

	public Indicadores getIndiceIndicadores() {
		return indiceIndicadores;
	}

	public void setIndiceIndicadores(Indicadores indiceIndicadores) {
		this.indiceIndicadores = indiceIndicadores;
	}

}
