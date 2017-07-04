package ui.vm.metodologia;

import java.math.BigDecimal;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Simple;
import model.repositories.RepoIndicadores;

@Observable
public class NuevaCondicionNoTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionNoTaxativaConfigurable condNoTaxativa;
	private Integer peso;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	private List <String> comparadores;
	private String comparadorSeleccionado;
	private Integer anios;
	
	public NuevaCondicionNoTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		parentVM.getMetodologia().getCondicionesNT().add(new CondicionNoTaxativaConfigurable("Vacio",15,new Menor(),"Pepe",5 ));
		ObservableUtils.firePropertyChanged(this.parentVM, "condiciones");

	}

	public CondicionNoTaxativaConfigurable getCondNoTaxativa() {
		return condNoTaxativa;
	}

	public void setCondNoTaxativa(CondicionNoTaxativaConfigurable condNoTaxativa) {
		this.condNoTaxativa = condNoTaxativa;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
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

	public void setComparadores(List<String> comparadores) {
		this.comparadores = comparadores;
	}

	public String getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}

	public void setComparadorSeleccionado(String comparadorSeleccionado) {
		this.comparadorSeleccionado = comparadorSeleccionado;
	}

	public Integer getAnios() {
		return anios;
	}

	public void setAnios(Integer anios) {
		this.anios = anios;
	}
	
	
}
