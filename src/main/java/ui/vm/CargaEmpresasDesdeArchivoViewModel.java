package ui.vm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.data.LoaderArchivo;
import model.data.LoaderArchivoCSV;
import model.data.LoaderArchivoJSON;
import model.repositories.Repositorios;

@Observable
public class CargaEmpresasDesdeArchivoViewModel {

	private List<String> extensiones = Arrays.asList("*.json", "*.txt", "*.csv");
	private String extensionSeleccionada = extensiones.get(0);
	private String ruta = "";
	private boolean habilitaSelector = true;
	private boolean botonCargarCuentas = false;
	private boolean botonCerrar = false;

	/*****************************************
	 * Este es el metodo que tiene que ser polimorfico La lista empresas recibe
	 * lo que le devuelven los manipuladores y los añade al repositorio
	 */
	public void cargarCuentas() {
		List<Empresa> empresas = new LinkedList<>();
		LoaderArchivo loader;
		
		if (extensionSeleccionada.equals(extensiones.get(0)) && ruta.endsWith(".json")) {
			/* JSON */
			loader = new LoaderArchivoJSON(this.ruta);
		} else if ( (extensionSeleccionada.equals(extensiones.get(1)) && ruta.endsWith(".txt")) 
					|| (extensionSeleccionada.equals(extensiones.get(2)) && ruta.endsWith(".csv")) )
		{
			/* CSV o TXT */
			loader = new LoaderArchivoCSV(this.ruta);
		}
		else{
			throw new UserException("El archivo no tiena la extensión correcta");
		}
		empresas = loader.getEmpresas();
		/* En este llamado ya esta chequeado los repetidos en el modelo */
		Repositorios.empresas.agregarEmpresas(empresas);
		this.setHabilitaSelector(false);
		this.setBotonCargarCuentas(false);
		this.setBotonCerrar(true);
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
	}

	public boolean isBotonCerrar() {
		return botonCerrar;
	}

	public void setBotonCerrar(boolean botonCerrar) {
		this.botonCerrar = botonCerrar;
	}

}
