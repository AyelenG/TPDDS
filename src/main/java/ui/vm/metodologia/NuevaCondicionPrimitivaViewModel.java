package ui.vm.metodologia;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.condiciones.Condicion;
import model.condiciones.primitivas.Longevidad;
import ui.vm.metodologia.auxiliares.CondicionVM;

@Observable
public class NuevaCondicionPrimitivaViewModel {

	private CargaMetodologiaViewModel parentVM;
	
	private List<Condicion> condicionesPrim = Arrays.asList(new Longevidad());
	private Condicion condicionPrimSeleccionada;
	
	public NuevaCondicionPrimitivaViewModel(CargaMetodologiaViewModel _parentVM) {
			this.parentVM = _parentVM;
	}
	
	public void cargarCondicionPrim() {
		if (condicionPrimSeleccionada != null) {
			parentVM.getCondicionesPrim().add(new CondicionVM(condicionPrimSeleccionada.toString(),condicionPrimSeleccionada));
//			ObservableUtils.firePropertyChanged(this.parentVM, "condicionesPrim");
		}
		else
			throw new UserException("Debe elegir una condicion primitiva de la lista");
	}
	

	public List<Condicion> getCondicionesPrim() {
		return condicionesPrim;
	}

	public void setCondicionesPrim(List<Condicion> condicionesPrim) {
		this.condicionesPrim = condicionesPrim;
	}

	public Condicion getCondicionPrimSeleccionada() {
		return condicionPrimSeleccionada;
	}

	public void setCondicionPrimSeleccionada(Condicion condicionPrimSeleccionada) {
		this.condicionPrimSeleccionada = condicionPrimSeleccionada;
	}

}
