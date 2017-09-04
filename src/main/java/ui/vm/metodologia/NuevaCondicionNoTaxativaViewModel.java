package ui.vm.metodologia;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.repositories.RepoIndicadores;
import ui.vm.metodologia.auxiliares.ComparadorVM;
import ui.vm.metodologia.auxiliares.CondicionVM;

@Observable
public class NuevaCondicionNoTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionNoTaxativaConfigurable nueva = new CondicionNoTaxativaConfigurable();
	private String peso = "";
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	private List<ComparadorVM> comparadores = Arrays.asList(new ComparadorVM(new Mayor()),
			new ComparadorVM(new Menor()));
	private ComparadorVM comparadorSeleccionado;
	private String anios = "";
	private boolean habilitaCarga = true;

	public NuevaCondicionNoTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
		this.parentVM = _parentVM;
	}
	
	public void nuevaCondicion() {
		this.limpiarTodo();
		this.setHabilitaCarga(true);
	}
	
	public void limpiarTodo() {
		this.setNueva(new CondicionNoTaxativaConfigurable());
		this.setAnios("");
		this.setIndicadorSeleccionado(null);
		this.setComparadorSeleccionado(null);
		this.setPeso("");
	}

	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void agregar() {
		Integer anios = null;
		Integer peso = null;
		if (this.getNueva().getNombre().isEmpty()) {
			throw new UserException("Ingrese un nombre de condicion");
		}
		if (this.getIndicadorSeleccionado() == null) {
			throw new UserException("Ingrese un indicador");
		}
		if (this.getComparadorSeleccionado() == null) {
			throw new UserException("Ingrese un comparador");
		}
		try {
			anios = Integer.valueOf(this.getAnios());
		} catch (NumberFormatException e) {
			throw new UserException("Ingrese una cantidad de años valida");
		}
		if(!(anios > 0)){
			throw new UserException("La cantidad de años debe ser mayor a 0");
		}
		try {
			peso = Integer.valueOf(this.getPeso());
		} catch (NumberFormatException e) {
			throw new UserException("Ingrese un peso valido");
		}
		if(!(peso > 0)){
			throw new UserException("El peso debe ser mayor a 0");
		}
		nueva.setComparador(this.getComparadorSeleccionado().getComparador());
		nueva.setNombreIndicador(this.getIndicadorSeleccionado().getNombre());
		nueva.setCantidadAnios(anios);
		nueva.setPeso(peso);

		parentVM.getCondicionesNT().add(new CondicionVM(nueva.getNombre(), nueva));
		this.setHabilitaCarga(false);
		// ObservableUtils.firePropertyChanged(this.parentVM, "condicionesNT");

	}

	public CondicionNoTaxativaConfigurable getNueva() {
		return nueva;
	}

	public void setNueva(CondicionNoTaxativaConfigurable nueva) {
		this.nueva = nueva;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public List<Indicador> getIndicadores() {
		return indicadores.findAll();
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

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
		ObservableUtils.firePropertyChanged(this, "habilitaNueva");
	}

	public boolean isHabilitaNueva() {
		return !habilitaCarga;
	}
}
