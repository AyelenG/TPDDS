package ui.vm;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import exceptions.NoSePuedeEvaluarException;

import java.math.BigDecimal;

import model.CuentaEmpresa;
import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.repositories.RepoEmpresasBD;
import model.repositories.RepoIndicadores;

@Observable
public class AnalisisViewModel {
	private Empresa empresaSeleccionada;
	private List<Periodo> periodosSeleccionados = new LinkedList<>();
	private Periodo periodoSeleccionado;
	private List<CuentaEmpresa> cuentasSeleccionadas;

	private RepoIndicadores indiceIndicadores = RepoIndicadores.getInstance();
	private List<IndicadorVM> indicadoresConValor = new LinkedList<>();
	private List<IndicadorVM> indicadoresSinValor = new LinkedList<>();

	private boolean selectorPeriodo = false;
	private boolean botonConsultar = false;

	@Observable
	public class IndicadorVM {
		private String nombre;
		private BigDecimal valor;
		private String mensaje;

		public IndicadorVM(String nombre, BigDecimal valor) {
			this.nombre = nombre;
			this.valor = valor;
		}

		public IndicadorVM(String nombre, String mensaje) {
			this.nombre = nombre;
			this.mensaje = mensaje;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
	}

	public void consultar() {
		this.setCuentasSeleccionadas(periodoSeleccionado.getCuentas());
		this.cargarIndicadores();
	}

	public void cargarIndicadores() {
		this.indicadoresConValor.clear();
		this.indicadoresSinValor.clear();
		if (this.getPeriodoSeleccionado() != null) {
			this.agregarIndicadoresDePeriodo(indiceIndicadores.findAll());
			ObservableUtils.firePropertyChanged(this, "indicadoresConValor");
			ObservableUtils.firePropertyChanged(this, "indicadoresSinValor");
		}
	}

	private void agregarIndicadoresDePeriodo(List<Indicador> indicadores) {
		BigDecimal valor;
		IndicadorVM indicadorACargar;
		for (Indicador indicador : indicadores) {
			try {
				valor = indicador.evaluar(this.getPeriodoSeleccionado());
				indicadorACargar = new IndicadorVM(indicador.getNombre(), valor);
				indicadoresConValor.add(indicadorACargar);
			} catch (NoSePuedeEvaluarException e) {
				indicadorACargar = new IndicadorVM(indicador.getNombre(), e.getMensaje());
				indicadoresSinValor.add(indicadorACargar);
			}

		}
	}

	public List<Empresa> getEmpresas() {
		return RepoEmpresasBD.getInstance().findAll();
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

	public List<CuentaEmpresa> getCuentasSeleccionadas() {
		return this.cuentasSeleccionadas;
	}

	public void setCuentasSeleccionadas(List<CuentaEmpresa> cuentas) {
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

	public List<IndicadorVM> getIndicadoresConValor() {
		return indicadoresConValor;
	}

	public void setIndicadoresConValor(List<IndicadorVM> indicadores) {
		this.indicadoresConValor = indicadores;
	}

	public List<IndicadorVM> getIndicadoresSinValor() {
		return indicadoresSinValor;
	}

	public void setIndicadoresSinValor(List<IndicadorVM> indicadoresSinValor) {
		this.indicadoresSinValor = indicadoresSinValor;
	}

	public RepoIndicadores getIndiceIndicadores() {
		return indiceIndicadores;
	}

	public void setIndiceIndicadores(RepoIndicadores indiceIndicadores) {
		this.indiceIndicadores = indiceIndicadores;
	}

}
