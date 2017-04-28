package ui.vm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.ManipuladorArchivo;
import model.repositories.Repositorios;

@Observable
public class CargaEmpresasDesdeArchivoViewModel {

	private List<String> extensiones = Arrays.asList("*.json","*.txt");
	private String extensionSeleccionada = extensiones.get(0);
	private String ruta = "";
	private String mensajeExito = "";
	private boolean habilitaSelector = true;
	private boolean botonCargarCuentas = false;
	
	
	/*****************************************
	 * Este es el metodo que tiene que ser polimorfico
	 * La lista empresas recibe lo que le devuelven los
	 * manipuladores y los añade al repositorio
	 */
	public void cargarCuentas() {		
		List<Empresa> empresas = new LinkedList<>();		
		if (extensionSeleccionada.equals(extensiones.get(0))) {
			/* JSON */
			ManipuladorArchivo manipulador = new ManipuladorArchivo(this.ruta);
			empresas = manipulador.getEmpresas();			
		}
		else
		{
			/* CSV */
		}
		
		/*En este llamado ya esta chequeado los repetidos en el modelo */
		Repositorios.empresas.agregarEmpresas(empresas);		
		this.setBotonCargarCuentas(false);
		this.setMensajeExito("Carga realizada Exitosamente");
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
		this.setHabilitaSelector(false);
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

	public String getMensajeExito() {
		return mensajeExito;
	}

	public void setMensajeExito(String mensajeExito) {
		this.mensajeExito = mensajeExito;
	}

}
