package ui.vm.metodologia;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Metodologia;

@Observable
public class CargaMetodologiaViewModel {
	
	private Metodologia metodologia = new Metodologia();

	private boolean habilitaCarga = true;
	
	public void nuevaMetodologia() {
		this.setMetodologia(new Metodologia());
		this.setHabilitaCarga(true);
	}
	
	public void cargarMetodologia() {
		if (metodologia.getNombre().isEmpty())
			throw new UserException("Complete el nombre de la Metodología.");
		this.setHabilitaCarga(false);
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
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
