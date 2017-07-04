package ui.vm.metodologia;

import java.math.BigDecimal;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Simple;

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

	public void nueva() {
		parentVM.getMetodologia().getCondicionesNT().add(new CondicionNoTaxativaConfigurable("Vacio",15,new Menor(),"Pepe",5 ));
		ObservableUtils.firePropertyChanged(this.parentVM, "condiciones");

	}
	
	
}
