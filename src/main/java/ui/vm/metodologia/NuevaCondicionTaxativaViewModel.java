package ui.vm.metodologia;

import java.math.BigDecimal;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Mayor;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Simple;
import model.repositories.RepoIndicadores;

@Observable
public class NuevaCondicionTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionTaxativaConfigurable condTaxativa;
	private List <String> tipos;
	private String tipoSeleccionado;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	private List <String> comparadores;
	private String comparadorSeleccionado;
	private BigDecimal valorRef;
	private Integer anios;
	
	
	
	public NuevaCondicionTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		parentVM.getMetodologia().getCondicionesT().add(new CondicionTaxativaConfigurable("Chorizo", new Mayor(),
				new Simple(), "Reinosa", 3, BigDecimal.valueOf(20)));
		ObservableUtils.firePropertyChanged(this.parentVM, "condiciones");

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
	}
	
}
