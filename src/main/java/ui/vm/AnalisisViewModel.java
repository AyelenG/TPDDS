package ui.vm;

import java.util.LinkedList;
import java.util.List;

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
	private List<Periodo> periodosSeleccionados;
	private Periodo periodoSeleccionado;
	private List<Cuenta> cuentasSeleccionadas;
	private List<Indicador> indicadoresConValor= new LinkedList<>();
	private List<Indicador> cargaDeIndicadores= new LinkedList<>();
	private BigDecimal valor;
	private Indicadores indicadoresP=Repositorios.repoIndicadores;
	private boolean selectorPeriodo = false;
	private boolean botonConsultarCuentas = false;
	private boolean botonConsultarIndicadores = false;
	private Indicador indicadorACargar;
	
	public void consultarCuentas() {
		
		this.setCuentasSeleccionadas(periodoSeleccionado.getCuentas());
		
	}
	public void consultarIndicadores() {
		this.indicadoresConValor.clear();
		this.cargaDeIndicadores.clear();
		this.agregarIndicadoresConValor(Repositorios.repoIndicadores.getIndicadores());
		this.setIndicadoresConValor(this.getCargaDeIndicadores());
	}
	
	public void agregarIndicadoresConValor(List<Indicador> indicadores){
		for(Indicador indicador: indicadores){
			this.setValor(Evaluador.evaluar(indicador, getPeriodoSeleccionado(), indicadoresP));
			if(this.valor!=null){
				indicadorACargar = new Indicador(indicador.getNombre(), indicador.getFormula(), this.valor);
				this.agregarIndicador(indicadorACargar);
			}
		}
	}
	
	public void agregarIndicador(Indicador indicador) {
		this.cargaDeIndicadores.add(indicador);
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
		this.setBotonConsultarCuentas(false);
		this.setBotonConsultarIndicadores(false);
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
		this.setBotonConsultarIndicadores(true);
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

	public List<Indicador> getIndicadoresConValor() {
		return indicadoresConValor;
	}
	public boolean isBotonConsultarIndicadores() {
		return botonConsultarIndicadores;
	}

	public void setBotonConsultarIndicadores(boolean botonConsultarIndicadores) {
		this.botonConsultarIndicadores = botonConsultarIndicadores;
	}
	public void setValor(BigDecimal nuevoValor){
		this.valor= nuevoValor;
	}
	public List<Indicador> getCargaDeIndicadores() {
		return cargaDeIndicadores;
	}
	public void setCargaDeIndicadores(List<Indicador> indicadores) {
		this.cargaDeIndicadores = indicadores;
	}
	public void setIndicadoresConValor(List <Indicador> indicadores){
		this.indicadoresConValor = indicadores;
	}
	

}
