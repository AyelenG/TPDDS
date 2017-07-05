package ui.vm.metodologia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Comparador;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Mediana;
import model.condiciones.taxativas.Promedio;
import model.condiciones.taxativas.Simple;
import model.condiciones.taxativas.Sumatoria;
import model.condiciones.taxativas.Tendencia;
import model.condiciones.taxativas.TipoCondicionTaxativa;
import model.repositories.RepoIndicadores;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionTaxativaVM;

@Observable
public class NuevaCondicionTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionTaxativaConfigurable condTaxativa;
	private List<String> tipos = new LinkedList<String>();
	private String tipoSeleccionado;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	private List<String> comparadores = new LinkedList<String>();
	private String comparadorSeleccionado;
	private BigDecimal valorRef;
	private Integer anios;
	private boolean tendencia = true;

	// por no poder poner observable en la interfaz
	private Comparador comparadorObjetoso;
	private TipoCondicionTaxativa tipoObjetoso;

	public NuevaCondicionTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
		this.parentVM = _parentVM;
		tipos.add("Mediana");
		tipos.add("Promedio");
		tipos.add("Simple");
		tipos.add("Sumatoria");
		tipos.add("Tendencia");
		comparadores.add("Mayor");
		comparadores.add("Menor");
	}

	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		CondicionTaxativaConfigurable nueva = new CondicionTaxativaConfigurable("Chorizo", new Mayor(), new Simple(),
				"Reinosa", 3, BigDecimal.valueOf(20));
		parentVM.getCondicionesT().add(new CondicionTaxativaVM(nueva.getTitulo(), nueva));
//		ObservableUtils.firePropertyChanged(this.parentVM, "condicionesT");

	}

	public CargaMetodologiaViewModel getParentVM() {
		return parentVM;
	}

	public void setParentVM(CargaMetodologiaViewModel parentVM) {
		this.parentVM = parentVM;
	}

	public CondicionTaxativaConfigurable getCondTaxativa() {
		return condTaxativa;
	}

	public void setCondTaxativa(CondicionTaxativaConfigurable condTaxativa) {
		this.condTaxativa = condTaxativa;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
		if (tipoSeleccionado == "Tendencia")
			this.setTendencia(false);
		else
			this.setTendencia(true);

		switch (tipoSeleccionado) {
		case "Mediana":
			this.tipoObjetoso = new Mediana();
		case "Promedio":
			this.tipoObjetoso = new Promedio();
		case "Simple":
			this.tipoObjetoso = new Simple();
		case "Sumatoria":
			this.tipoObjetoso = new Sumatoria();
		case "Tendencia":
			this.tipoObjetoso = new Tendencia();
		}
	}

	public List<Indicador> getIndicadores() {
		return indicadores.getElementos();
	}

	public void setIndicadores(RepoIndicadores indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public List<String> getComparadores() {
		return comparadores;
	}

	public void setComparadores(List<String> operadores) {
		this.comparadores = operadores;
	}

	public BigDecimal getValorRef() {
		return valorRef;
	}

	public void setValorRef(BigDecimal valorRef) {
		this.valorRef = valorRef;
	}

	public Integer getAnios() {
		return anios;
	}

	public void setAnios(Integer anios) {
		this.anios = anios;
	}

	public String getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}

	public void setComparadorSeleccionado(String operadorSeleccionado) {
		this.comparadorSeleccionado = operadorSeleccionado;
		if (comparadorSeleccionado == "Mayor")
			this.comparadorObjetoso = new Mayor();
		else
			this.comparadorObjetoso = new Menor();
	}

	public boolean isTendencia() {
		return tendencia;
	}

	public void setTendencia(boolean tendencia) {
		this.tendencia = tendencia;
	}

}
