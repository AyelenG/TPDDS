package ui.vm.metodologia;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Mediana;
import model.condiciones.taxativas.Promedio;
import model.condiciones.taxativas.Simple;
import model.condiciones.taxativas.Sumatoria;
import model.condiciones.taxativas.Tendencia;
import model.repositories.RepoIndicadores;
import ui.vm.metodologia.auxiliares.ComparadorVM;
import ui.vm.metodologia.auxiliares.CondicionTaxativaVM;
import ui.vm.metodologia.auxiliares.TipoCondicionVM;

@Observable
public class NuevaCondicionTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionTaxativaConfigurable nueva = new CondicionTaxativaConfigurable();
	private List<TipoCondicionVM> tipos = Arrays.asList(new TipoCondicionVM(new Promedio()),
			new TipoCondicionVM(new Simple()), new TipoCondicionVM(new Sumatoria()), new TipoCondicionVM(new Mediana()),
			new TipoCondicionVM(new Tendencia()));
	private TipoCondicionVM tipoSeleccionado;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;

	private List<ComparadorVM> comparadores = Arrays.asList(new ComparadorVM(new Mayor()),
			new ComparadorVM(new Menor()));
	private ComparadorVM comparadorSeleccionado;

	private String valorRef = "";
	private String anios = "";

	public NuevaCondicionTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
		this.parentVM = _parentVM;
	}

	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		Integer anios = null;
		BigDecimal valorDeRef = null;
		if(this.getNueva().getNombre().isEmpty()){
			throw new UserException("Ingrese un nombre de condicion");
		}
		if(this.getTipoSeleccionado() == null){
			throw new UserException("Ingrese un tipo de condicion");
		}
		if(this.getIndicadorSeleccionado() == null){
			throw new UserException("Ingrese un indicador");
		}
		if(this.getComparadorSeleccionado() == null){
			throw new UserException("Ingrese un comparador");
		}
		try {
			anios = Integer.valueOf(this.getAnios());
		} catch (NumberFormatException e) {
			throw new UserException("Ingrese una cantidad de años valida");
		}
		if (this.isNotTendencia()) {
			try {
				valorDeRef = new BigDecimal(this.getValorRef());
			} catch (NumberFormatException e) {
				throw new UserException("Ingrese un valor de referencia valido");
			}
		}
		nueva.setTipo(this.getTipoSeleccionado().getTipoCondicionTaxativa());
		nueva.setComparador(this.getComparadorSeleccionado().getComparador());
		nueva.setNombreIndicador(this.getIndicadorSeleccionado().getNombre());
		nueva.setCantidadAnios(anios);
		nueva.setValorDeReferencia(valorDeRef);
		
		parentVM.getCondicionesT().add(new CondicionTaxativaVM(nueva.getNombre(), nueva));
		// ObservableUtils.firePropertyChanged(this.parentVM, "condicionesT");
	}

	public CargaMetodologiaViewModel getParentVM() {
		return parentVM;
	}

	public void setParentVM(CargaMetodologiaViewModel parentVM) {
		this.parentVM = parentVM;
	}

	public CondicionTaxativaConfigurable getNueva() {
		return nueva;
	}

	public void setNueva(CondicionTaxativaConfigurable nueva) {
		this.nueva = nueva;
	}

	public List<TipoCondicionVM> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoCondicionVM> tipos) {
		this.tipos = tipos;
	}

	public TipoCondicionVM getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(TipoCondicionVM tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
		ObservableUtils.firePropertyChanged(this, "notTendencia");

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

	public List<ComparadorVM> getComparadores() {
		return comparadores;
	}

	public void setComparadores(List<ComparadorVM> operadores) {
		this.comparadores = operadores;
	}

	public String getValorRef() {
		return valorRef;
	}

	public void setValorRef(String valorRef) {
		this.valorRef = valorRef;
	}

	public String getAnios() {
		return anios;
	}

	public void setAnios(String anios) {
		this.anios = anios;
	}

	public ComparadorVM getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}

	public void setComparadorSeleccionado(ComparadorVM operadorSeleccionado) {
		this.comparadorSeleccionado = operadorSeleccionado;
	}

	public boolean isNotTendencia() {
		return tipoSeleccionado.toString() != "Tendencia";
	}

}
