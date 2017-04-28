package ui.vm;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.exceptions.EmpresaVaciaException;
import model.repositories.Repositorios;

@Observable
public class CargaEmpresaViewModel {

	Empresa empresa = new Empresa();
	private String mensajeExito = "";
	
	public void cargarEmpresa() {	
		if(empresa.getSymbol() == null || empresa.getNombre() == null){
			throw new EmpresaVaciaException();
		}
		Repositorios.empresas.agregarEmpresa(empresa);
		this.setMensajeExito("Carga realizada Exitosamente");
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

}
