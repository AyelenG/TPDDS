package ui.vm.metodologia;

import java.math.BigDecimal;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;


import model.condiciones.Mayor;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Simple;

@Observable
public class NuevaCondicionTaxativaViewModel {

	private CargaMetodologiaViewModel parentVM;
	
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
	
}
