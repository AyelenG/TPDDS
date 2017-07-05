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
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Mediana;
import model.condiciones.taxativas.Promedio;
import model.condiciones.taxativas.Simple;
import model.condiciones.taxativas.Sumatoria;
import model.condiciones.taxativas.Tendencia;
import model.condiciones.taxativas.TipoCondicionTaxativa;
import model.repositories.RepoIndicadores;


@Observable
public class NuevaCondicionTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	private CondicionTaxativaVM condTaxativa;
	private List<TipoVM> tipos = new LinkedList<TipoVM>();
	private TipoVM tipoSeleccionado;
	private RepoIndicadores indicadores = RepoIndicadores.getInstance();
	private Indicador indicadorSeleccionado;
	
	private List<Comparador> comparadores = new LinkedList<Comparador>();
	private Comparador comparadorSeleccionado;
	
	private String valorRef;
	private String anios;
	private boolean tendencia = true;



	public NuevaCondicionTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
		this.parentVM = _parentVM;
		tipos.add(new TipoVM(new Mediana()));
		tipos.add(new TipoVM(new Promedio()));
		tipos.add(new TipoVM(new Simple()));
		tipos.add(new TipoVM(new Sumatoria()));
		tipos.add(new TipoVM(new Tendencia()));		
		comparadores.add(new Mayor());
		comparadores.add(new Menor());
	}
	
	public static class CondicionTaxativaVM{
		private String nombre;
		private CondicionTaxativaConfigurable condicionTaxativa = new CondicionTaxativaConfigurable();

		public CondicionTaxativaConfigurable getCondicionTaxativa() {
			return condicionTaxativa;
		}

		public void setCondicionTaxativa(CondicionTaxativaConfigurable condicionTaxativa) {
			this.condicionTaxativa = condicionTaxativa;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
	}
	
	public static class ComparadorVM{
		private String nombre;
		private Comparador comparador;
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Comparador getComparador() {
			return comparador;
		}
		public void setComparador(Comparador comparador) {
			this.comparador = comparador;
		}
		
	}
	
	public static class TipoVM{
		
		private TipoCondicionTaxativa tipoCondicionTaxativa;
		
		public TipoVM(TipoCondicionTaxativa tipo)
		{
			this.setTipoCondicionTaxativa(tipo);
		}
		
		public void setTipoCondicionTaxativa(TipoCondicionTaxativa tipo){
			tipoCondicionTaxativa = tipo;
		}

		public TipoCondicionTaxativa getTipoCondicionTaxativa() {
			return tipoCondicionTaxativa;
		}
		
		@Override
		public String toString(){
			return tipoCondicionTaxativa.toString();
		}
		
	}

	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}

	public void nueva() {
		CondicionTaxativaConfigurable nueva = new CondicionTaxativaConfigurable("Chorizo", new Mayor(), new Simple(),
				"Reinosa", 3, BigDecimal.valueOf(20));
		//parentVM.getCondicionesT().add(new CondicionTaxativaVM());
//		ObservableUtils.firePropertyChanged(this.parentVM, "condicionesT");

	}

	public CargaMetodologiaViewModel getParentVM() {
		return parentVM;
	}

	public void setParentVM(CargaMetodologiaViewModel parentVM) {
		this.parentVM = parentVM;
	}

	public CondicionTaxativaVM getCondTaxativa() {
		return condTaxativa;
	}

	public void setCondTaxativa(CondicionTaxativaVM condTaxativa) {
		this.condTaxativa = condTaxativa;
	}

	public List<TipoVM> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoVM> tipos) {
		this.tipos = tipos;
	}

	public TipoVM getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(TipoVM tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
		if (tipoSeleccionado.toString() == "Tendencia")
			this.setTendencia(false);
		else
			this.setTendencia(true);

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

	public List<Comparador> getComparadores() {
		return comparadores;
	}

	public void setComparadores(List<Comparador> operadores) {
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

	public Comparador getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}

	public void setComparadorSeleccionado(Comparador operadorSeleccionado) {
		this.comparadorSeleccionado = operadorSeleccionado;
	}

	public boolean isTendencia() {
		return tendencia;
	}

	public void setTendencia(boolean tendencia) {
		this.tendencia = tendencia;
	}

}
