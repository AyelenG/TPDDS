package ui.vm.metodologia;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class NuevaCondicionNoTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	
	public NuevaCondicionNoTaxativaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}
	
}
