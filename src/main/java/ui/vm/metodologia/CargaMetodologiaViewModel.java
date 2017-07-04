package ui.vm.metodologia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Metodologia;
import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;

@Observable
public class CargaMetodologiaViewModel {
	
	private Metodologia metodologia = new Metodologia();
	
	private boolean habilitaCarga = true;
	
	@Observable
	public class CondicionVM {
		private String titulo;

		public CondicionVM(String titulo) {
			this.titulo = titulo;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
	}
	
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

	public List<CondicionVM> getCondiciones() {
		List<CondicionVM> condiciones = new LinkedList<>();
		condiciones.addAll(metodologia.getCondicionesNT().stream()
					.map(m->new CondicionVM(m.getTitulo())).collect(Collectors.toList()));
		condiciones.addAll(metodologia.getCondicionesT().stream()
				.map(m->new CondicionVM(m.getTitulo())).collect(Collectors.toList()));
		condiciones.addAll(metodologia.getCondicionesComb().stream()
				.map(m->new CondicionVM(m.getTitulo())).collect(Collectors.toList()));
		return condiciones;
	}
}
