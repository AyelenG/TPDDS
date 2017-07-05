package ui.vm.metodologia;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.combinadas.Longevidad;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionCombinadaVM;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionNoTaxativaVM;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionTaxativaVM;

@Observable
public class NuevaCondicionPrimitivaViewModel {

	private CargaMetodologiaViewModel parentVM;
	
	private List<CondicionCombinada> condicionesComb = Arrays.asList(new Longevidad());
	private List<CondicionTaxativa> condicionesT = Arrays.asList();
	private List<CondicionNoTaxativa> condicionesNT = Arrays.asList();
	private CondicionCombinada condicionCombSeleccionada;
	private CondicionTaxativa condicionTSeleccionada;
	private CondicionNoTaxativa condicionNTSeleccionada;
	
	public NuevaCondicionPrimitivaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicionComb() {
		if (condicionCombSeleccionada != null) {
			parentVM.getCondicionesComb().add(new CondicionCombinadaVM(condicionCombSeleccionada.toString(),condicionCombSeleccionada));
//			ObservableUtils.firePropertyChanged(this.parentVM, "condicionesComb");
		}
		else
			throw new UserException("Debe elegir una condicion combinada de la lista");
	}
	
	public void cargarCondicionT() {
		if (condicionTSeleccionada != null) {
			parentVM.getCondicionesT().add(new CondicionTaxativaVM(condicionTSeleccionada.toString(),condicionTSeleccionada));
//			ObservableUtils.firePropertyChanged(this.parentVM, "condicionesT");
		}
		else
			throw new UserException("Debe elegir una condicion taxativa de la lista");
	}
	
	public void cargarCondicionNT() {
		if (condicionNTSeleccionada != null) {
			parentVM.getCondicionesNT().add(new CondicionNoTaxativaVM(condicionNTSeleccionada.toString(),condicionNTSeleccionada));
//			ObservableUtils.firePropertyChanged(this.parentVM, "condicionesNT");
		}
		else
			throw new UserException("Debe elegir una condicion no taxativa de la lista");
	}

	public List<CondicionCombinada> getCondicionesComb() {
		return condicionesComb;
	}

	public void setCondicionesComb(List<CondicionCombinada> condicionesComb) {
		this.condicionesComb = condicionesComb;
	}

	public List<CondicionTaxativa> getCondicionesT() {
		return condicionesT;
	}

	public void setCondicionesT(List<CondicionTaxativa> condicionesT) {
		this.condicionesT = condicionesT;
	}

	public List<CondicionNoTaxativa> getCondicionesNT() {
		return condicionesNT;
	}

	public void setCondicionesNT(List<CondicionNoTaxativa> condicionesNT) {
		this.condicionesNT = condicionesNT;
	}

	public CondicionCombinada getCondicionCombSeleccionada() {
		return condicionCombSeleccionada;
	}

	public void setCondicionCombSeleccionada(CondicionCombinada condicionCombSeleccionada) {
		this.condicionCombSeleccionada = condicionCombSeleccionada;
	}

	public CondicionTaxativa getCondicionTSeleccionada() {
		return condicionTSeleccionada;
	}

	public void setCondicionTSeleccionada(CondicionTaxativa condicionTSeleccionada) {
		this.condicionTSeleccionada = condicionTSeleccionada;
	}

	public CondicionNoTaxativa getCondicionNTSeleccionada() {
		return condicionNTSeleccionada;
	}

	public void setCondicionNTSeleccionada(CondicionNoTaxativa condicionNTSeleccionada) {
		this.condicionNTSeleccionada = condicionNTSeleccionada;
	}
	
}
