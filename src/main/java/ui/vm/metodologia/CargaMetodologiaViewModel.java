package ui.vm.metodologia;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Metodologia;
import model.repositories.RepoMetodologias;
import ui.vm.metodologia.auxiliares.CondicionCombinadaVM;
import ui.vm.metodologia.auxiliares.CondicionNoTaxativaVM;
import ui.vm.metodologia.auxiliares.CondicionTaxativaVM;

@Observable
public class CargaMetodologiaViewModel {

	private Metodologia metodologia = new Metodologia();

	private List<CondicionTaxativaVM> condicionesT = new LinkedList<>();
	private List<CondicionNoTaxativaVM> condicionesNT = new LinkedList<>();
	private List<CondicionCombinadaVM> condicionesComb = new LinkedList<>();

	private CondicionTaxativaVM condicionTSeleccionada;
	private CondicionNoTaxativaVM condicionNTSeleccionada;
	private CondicionCombinadaVM condicionCombSeleccionada;

	private boolean habilitaCarga = true;

	public void nuevaMetodologia() {
		this.limpiarTodo();
		this.setHabilitaCarga(true);
	}

	public void limpiarTodo() {
		this.setMetodologia(new Metodologia());
		this.condicionesT.clear();
		this.condicionesNT.clear();
		this.condicionesComb.clear();
		//no hace falta esto para que se actualize
		// ObservableUtils.firePropertyChanged(this, "condicionesT");
		// ObservableUtils.firePropertyChanged(this, "condicionesNT");
		// ObservableUtils.firePropertyChanged(this, "condicionesComb");
	}

	public void cargarMetodologia() {
		if (metodologia.getNombre().isEmpty())
			throw new UserException("Complete el nombre de la Metodología.");
		if (RepoMetodologias.getInstance().existeElemento(metodologia)) {
			throw new UserException("La Metodología ingresada ya existe.");
		}

		metodologia.setCondicionesT(condicionesT.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));
		metodologia
				.setCondicionesNT(condicionesNT.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));
		metodologia.setCondicionesComb(
				condicionesComb.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));

		RepoMetodologias.getInstance().agregarElemento(metodologia);
		RepoMetodologias.getInstance().guardar();
		this.setHabilitaCarga(false);
	}

	public void borrarCondicionTaxativa() {
		if (condicionTSeleccionada != null)
			this.condicionesT.remove(condicionTSeleccionada);
		else
			throw new UserException("Debe seleccionar una condicion taxativa para borrar");
	}

	public void borrarCondicionNoTaxativa() {
		if (condicionNTSeleccionada != null)
			this.condicionesNT.remove(condicionNTSeleccionada);
		else
			throw new UserException("Debe seleccionar una condicion no taxativa para borrar");
	}

	public void borrarCondicionCombinada() {
		if (condicionCombSeleccionada != null)
			this.condicionesComb.remove(condicionCombSeleccionada);
		else
			throw new UserException("Debe seleccionar una condicion combinada para borrar");
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

	public List<CondicionTaxativaVM> getCondicionesT() {
		return condicionesT;
	}

	public void setCondicionesT(List<CondicionTaxativaVM> condicionesT) {
		this.condicionesT = condicionesT;
	}

	public List<CondicionNoTaxativaVM> getCondicionesNT() {
		return condicionesNT;
	}

	public void setCondicionesNT(List<CondicionNoTaxativaVM> condicionesNT) {
		this.condicionesNT = condicionesNT;
	}

	public List<CondicionCombinadaVM> getCondicionesComb() {
		return condicionesComb;
	}

	public void setCondicionesComb(List<CondicionCombinadaVM> condicionesComb) {
		this.condicionesComb = condicionesComb;
	}

	public CondicionTaxativaVM getCondicionTSeleccionada() {
		return condicionTSeleccionada;
	}

	public void setCondicionTSeleccionada(CondicionTaxativaVM condicionTSeleccionada) {
		this.condicionTSeleccionada = condicionTSeleccionada;
	}

	public CondicionNoTaxativaVM getCondicionNTSeleccionada() {
		return condicionNTSeleccionada;
	}

	public void setCondicionNTSeleccionada(CondicionNoTaxativaVM condicionNTSeleccionada) {
		this.condicionNTSeleccionada = condicionNTSeleccionada;
	}

	public CondicionCombinadaVM getCondicionCombSeleccionada() {
		return condicionCombSeleccionada;
	}

	public void setCondicionCombSeleccionada(CondicionCombinadaVM condicionCombSeleccionada) {
		this.condicionCombSeleccionada = condicionCombSeleccionada;
	}

}
