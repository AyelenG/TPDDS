package ui.vm;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.repositories.Repositorios;

@Observable
public class CargaEmpresaViewModel {

	Empresa empresa = new Empresa();
	private String mensajeExito = "";
	private boolean habilitaCarga = true;
	private boolean habilitaNueva = false;
	
	public void nuevaEmpresa() {		
		this.setEmpresa(new Empresa());
		this.setMensajeExito("");		
		this.setHabilitaCarga(true);
		this.setHabilitaNueva(false);
	}
	
	public void cargarEmpresa() {		
		if (Repositorios.empresas.existeEmpresa(empresa))
			throw new UserException("La empresa ingresada ya existe.");
		if(empresa.getSymbol() == null || empresa.getNombre() == null){
			throw new UserException("Complete los datos de la empresa.");
		}
		Repositorios.empresas.agregarEmpresa(empresa);
		this.setMensajeExito("Carga realizada Exitosamente");
		this.setHabilitaCarga(false);
		this.setHabilitaNueva(true);
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getMensajeExito() {
		return mensajeExito;
	}

	public void setMensajeExito(String mensajeExito) {
		this.mensajeExito = mensajeExito;
	}

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
	}

	public boolean isHabilitaNueva() {
		return habilitaNueva;
	}

	public void setHabilitaNueva(boolean habilitaNueva) {
		this.habilitaNueva = habilitaNueva;
	}

}
