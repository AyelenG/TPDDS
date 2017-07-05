package ui.vm;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Metodologia;
import model.repositories.RepoEmpresas;
import model.repositories.RepoMetodologias;

@Observable
public class AnalisisMetodologiaViewModel {
	private Metodologia metodologiaSeleccionada;
	private List<Empresa> empresasDeseables = new LinkedList<>();
	
	private List<Empresa> empresasNoDeseables = new LinkedList<>();
	private List<Empresa> empresas = RepoEmpresas.getInstance().getElementos();
	
	private boolean botonAnalizar = false;
	
		
	public List<Metodologia> getMetodologias() {
		return RepoMetodologias.getInstance().getElementos();
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}
	public void analizar(){
		this.empresasDeseables.clear();
		this.empresasNoDeseables.clear();
		this.setEmpresasDeseables(metodologiaSeleccionada.aplicar(this.empresas));
		this.setEmpresasNoDeseables(empresas.stream().filter(emp->emp.noEstaEn(this.empresasDeseables)).collect(Collectors.toList()));
		ObservableUtils.firePropertyChanged(this, "empresasDeseables");
		ObservableUtils.firePropertyChanged(this, "empresasNoDeseables");
		
	}
	public List<Empresa> getEmpresasNoDeseables() {
		return empresasNoDeseables;
	}

	public void setEmpresasNoDeseables(List<Empresa> empresasNoDeseables) {
		this.empresasNoDeseables = empresasNoDeseables;
	}


	public List<Empresa> getEmpresasDeseables() {
		return empresasDeseables;
	}

	public void setEmpresasDeseables(List<Empresa> empresasDeseables) {
		this.empresasDeseables = empresasDeseables;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
		this.setBotonAnalizar(true);
	}
	public boolean isBotonAnalizar() {
		return botonAnalizar;
	}

	public void setBotonAnalizar(boolean botonAnalizar) {
		this.botonAnalizar = botonAnalizar;
	}

	
	
}
