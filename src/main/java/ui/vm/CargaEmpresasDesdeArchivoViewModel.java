package ui.vm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.data.HandlerArchivoCSV;
import model.data.HandlerArchivoJSON;
import model.repositories.RepoCuentas;
import model.repositories.RepoEmpresas;
import model.repositories.RepoEmpresasBD;

@Observable
public class CargaEmpresasDesdeArchivoViewModel {

	private List<String> extensiones = Arrays.asList("*.json", "*.txt", "*.csv");
	private String extensionSeleccionada = extensiones.get(0);
	private String ruta = "";
	private boolean habilitaSelector = true;
	private boolean botonCargarCuentas = false;

	/*****************************************
	 * Este es el metodo que tiene que ser polimorfico La lista empresas recibe
	 * lo que le devuelven los manipuladores y los añade al repositorio
	 */
	public void cargarCuentas() {
		List<Empresa> empresas = new LinkedList<>();
		if (extensionSeleccionada.equals(extensiones.get(0)) && ruta.endsWith(".json"))			
			empresas = new HandlerArchivoJSON(this.ruta).loadEmpresas();
		else if ( (extensionSeleccionada.equals(extensiones.get(1)) && ruta.endsWith(".txt")) 
					|| (extensionSeleccionada.equals(extensiones.get(2)) && ruta.endsWith(".csv")) )			
			empresas = new HandlerArchivoCSV(this.ruta).loadEmpresas();
		else
			throw new UserException("El archivo no tiena la extensión correcta");
		
		/* En este llamado ya esta chequeado los repetidos en el modelo */
		RepoEmpresasBD.getInstance().insertarVarios(empresas);
		
		/* Agrega las cuentas nuevas al Repositorio de Cuentas Predeterminadas */
//		RepoCuentas.getInstance().agregarDesdeEmpresas(empresas);
//		RepoCuentas.getInstance().guardar();
		/*--NO HACE FALTA YA QUE AL CARGAR EL JSON DE EMPRESAS 
		EL SETTER DE LAS CUENTAS DE EMPRESA ACTUALIZA EL REPO DE CUENTAS--*/


		this.setHabilitaSelector(false);
		this.setBotonCargarCuentas(false);
	}

	public List<String> getExtensiones() {
		return extensiones;
	}

	public void setExtensiones(List<String> extensiones) {
		this.extensiones = extensiones;
	}

	public String getExtensionSeleccionada() {
		return extensionSeleccionada;
	}

	public void setExtensionSeleccionada(String extensionSeleccionada) {
		this.extensionSeleccionada = extensionSeleccionada;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
		this.setBotonCargarCuentas(true);
	}

	public boolean isBotonCargarCuentas() {
		return botonCargarCuentas;
	}

	public void setBotonCargarCuentas(boolean botonCargarCuentas) {
		this.botonCargarCuentas = botonCargarCuentas;
	}

	public boolean isHabilitaSelector() {
		return habilitaSelector;
	}

	public void setHabilitaSelector(boolean habilitaSelector) {
		this.habilitaSelector = habilitaSelector;
		ObservableUtils.firePropertyChanged(this, "exito");
	}
	
	public boolean getExito() {
		return !habilitaSelector;
	}

}
