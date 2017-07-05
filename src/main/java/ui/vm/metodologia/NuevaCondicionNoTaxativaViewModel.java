package ui.vm.metodologia;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.repositories.RepoIndicadores;
import ui.vm.metodologia.auxiliares.ComparadorVM;
import ui.vm.metodologia.auxiliares.CondicionNoTaxativaVM;

@Observable
public class NuevaCondicionNoTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionNoTaxativaConfigurable nueva;
	private String peso;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	private List <ComparadorVM> comparadores = new LinkedList<ComparadorVM>();
	private ComparadorVM comparadorSeleccionado;
	private String anios;
	
	public NuevaCondicionNoTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
			comparadores.add(new ComparadorVM (new Mayor()));
			comparadores.add(new ComparadorVM (new Menor()));
	}
	
	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		CondicionNoTaxativaConfigurable nueva = new CondicionNoTaxativaConfigurable("Vacio",15,new Menor(),"Pepe",5 );
		parentVM.getCondicionesNT().add(new CondicionNoTaxativaVM(nueva.getNombre(),nueva));
//		ObservableUtils.firePropertyChanged(this.parentVM, "condicionesNT");

	}

	public CondicionNoTaxativaConfigurable getCondNoTaxativa() {
		return nueva;
	}

	public void setCondNoTaxativa(CondicionNoTaxativaConfigurable condNoTaxativa) {
		this.nueva = condNoTaxativa;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
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


	public String getAnios() {
		return anios;
	}

	public void setAnios(String anios) {
		this.anios = anios;
	}

	public List<ComparadorVM> getComparadores() {
		return comparadores;
	}

	public void setComparadores(List<ComparadorVM> comparadores) {
		this.comparadores = comparadores;
	}

	public ComparadorVM getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}

	public void setComparadorSeleccionado(ComparadorVM comparadorSeleccionado) {
		this.comparadorSeleccionado = comparadorSeleccionado;
	}
	
	
}
