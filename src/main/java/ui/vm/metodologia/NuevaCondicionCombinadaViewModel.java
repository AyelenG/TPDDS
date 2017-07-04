package ui.vm.metodologia;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class NuevaCondicionCombinadaViewModel {

	private CargaMetodologiaViewModel parentVM;
	
	public NuevaCondicionCombinadaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicion() {
		if (parentVM != null) {
			ObservableUtils.firePropertyChanged(this.parentVM, "metodologia");
		}
	}
	
}
