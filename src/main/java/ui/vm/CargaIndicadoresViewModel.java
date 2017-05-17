package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Indicador;
import model.repositories.Repositorios;

@Observable
public class CargaIndicadoresViewModel {
	
	private List <Cuenta> cuentas = Repositorios.cuentasPredeterminadas.getCuentas();
	private List<Indicador> indicadores;
	private String ingresado = "";
	private Indicador indicadorNuevo = new Indicador();
	private String indicadorSeleccionado;
	private Cuenta cuentaSeleccionada; //trato la cuenta como un objeto y no como un string
	private String constante;
	
	public void agregarSimbolo(String simbolo){
		ingresado += " " + simbolo;
	}
	
	public void ingresarCuenta(){
		this.agregarSimbolo(cuentaSeleccionada.getNombre());
	}
	
	public void ingresarIndicador(){
		this.agregarSimbolo(indicadorSeleccionado);
	}
	
	public void ingresarConstante(){
		this.agregarSimbolo(constante);
	}
	
	public void cargarIndicador(){
		//HAcerlo
		
		
	}
	public void borrarUltimo(){
		//borra el ultimo token
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public String getIngresado() {
		return ingresado;
	}

	public void setIngresado(String ingresado) {
		this.ingresado = ingresado;
	}

	public Indicador getIndicadorNuevo() {
		return indicadorNuevo;
	}

	public void setIndicadorNuevo(String indicador) {
		this.indicadorNuevo.setNombre(indicador);
	}

	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(String indicadorIngresado) {
		this.indicadorSeleccionado = indicadorIngresado;
	}

	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(Cuenta cuentaIngresada) {
		this.cuentaSeleccionada = cuentaIngresada;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
}
