package ui.vm.metodologia;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Metodologia;
import model.condiciones.Condicion;
import model.repositories.RepoMetodologias;
import ui.vm.metodologia.auxiliares.CondicionVM;

@Observable
public class CargaMetodologiaViewModel {

	private Metodologia metodologia = new Metodologia();

	private List<CondicionVM> condicionesT = new LinkedList<>();
	private List<CondicionVM> condicionesNT = new LinkedList<>();
	private List<CondicionVM> condicionesPrim = new LinkedList<>();

	private CondicionVM condicionTSeleccionada;
	private CondicionVM condicionNTSeleccionada;
	private CondicionVM condicionPrimSeleccionada;

	private boolean habilitaCarga = true;

	public void nuevaMetodologia() {
		this.limpiarTodo();
		this.setHabilitaCarga(true);
	}

	public void limpiarTodo() {
		this.setMetodologia(new Metodologia());
		this.condicionesT.clear();
		this.condicionesNT.clear();
		this.condicionesPrim.clear();
		//no hace falta esto para que se actualize
		// ObservableUtils.firePropertyChanged(this, "condicionesT");
		// ObservableUtils.firePropertyChanged(this, "condicionesNT");
		// ObservableUtils.firePropertyChanged(this, "condicionesPrim");
	}

	public void cargarMetodologia() {
		RepoMetodologias metodologias = RepoMetodologias.getInstance();
		if (metodologia.getNombre().isEmpty())
			throw new UserException("Complete el nombre de la Metodología.");
		if (metodologias.existeElemento(metodologia)) {
			throw new UserException("La Metodología ingresada ya existe.");
		}

		List<Condicion> condiciones = new LinkedList<>();
		condiciones.addAll(condicionesT.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));
		condiciones.addAll(condicionesNT.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));
		condiciones.addAll(condicionesPrim.stream().map(cvm -> cvm.getCondicion()).collect(Collectors.toList()));
		metodologia.setCondiciones(condiciones);
		
		metodologias.insertar(metodologia);
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

	public void borrarCondicionPrimitiva() {
		if (condicionPrimSeleccionada != null)
			this.condicionesPrim.remove(condicionPrimSeleccionada);
		else
			throw new UserException("Debe seleccionar una condicion primitiva para borrar");
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

	public List<CondicionVM> getCondicionesT() {
		return condicionesT;
	}

	public void setCondicionesT(List<CondicionVM> condicionesT) {
		this.condicionesT = condicionesT;
	}

	public List<CondicionVM> getCondicionesNT() {
		return condicionesNT;
	}

	public void setCondicionesNT(List<CondicionVM> condicionesNT) {
		this.condicionesNT = condicionesNT;
	}

	public List<CondicionVM> getCondicionesPrim() {
		return condicionesPrim;
	}

	public void setCondicionesPrim(List<CondicionVM> condicionesPrim) {
		this.condicionesPrim = condicionesPrim;
	}

	public CondicionVM getCondicionTSeleccionada() {
		return condicionTSeleccionada;
	}

	public void setCondicionTSeleccionada(CondicionVM condicionTSeleccionada) {
		this.condicionTSeleccionada = condicionTSeleccionada;
	}

	public CondicionVM getCondicionNTSeleccionada() {
		return condicionNTSeleccionada;
	}

	public void setCondicionNTSeleccionada(CondicionVM condicionNTSeleccionada) {
		this.condicionNTSeleccionada = condicionNTSeleccionada;
	}

	public CondicionVM getCondicionPrimSeleccionada() {
		return condicionPrimSeleccionada;
	}

	public void setCondicionPrimSeleccionada(CondicionVM condicionPrimSeleccionada) {
		this.condicionPrimSeleccionada = condicionPrimSeleccionada;
	}

}
