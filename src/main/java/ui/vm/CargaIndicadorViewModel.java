package ui.vm;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Indicador;
import model.parser.analizadorSintactico.AnalizadorSintactico;
import model.repositories.Repositorios;

@Observable
public class CargaIndicadorViewModel {

	private List<Cuenta> cuentas = Repositorios.repoCuentas.getCuentas();
	private List<Indicador> indicadores = Repositorios.repoIndicadores.getIndicadores();
	private String ingresado = "";
	private Indicador indicadorNuevo = new Indicador();
	private Indicador indicadorSeleccionado;
	private Cuenta cuentaSeleccionada; // trato la cuenta como un objeto y no
										// como un string
	private String constante;

	private boolean habilitaCarga = true;

	public void nuevoIndicador() {
		this.setIndicadorNuevo(new Indicador());
		this.limpiarTodo();
		this.setHabilitaCarga(true);
	}

	public void agregarSimbolo(String simbolo) {
		if (ingresado != "")
			ingresado += " " + simbolo;
		else
			ingresado = simbolo;
	}

	public void ingresarCuenta() {
		this.agregarSimbolo("[" + cuentaSeleccionada.getNombre() + "]");
		this.setCuentaSeleccionada(null);
	}

	public void ingresarIndicador() {
		this.agregarSimbolo("<" + indicadorSeleccionado.getNombre() + ">");
		this.setIndicadorSeleccionado(null);
	}

	public void ingresarConstante() {
		if (NumberUtils.isNumber(constante)) {
			this.agregarSimbolo(constante);
			this.setConstante("");
		} else
			throw new UserException("La constante debe ser numerica.");

	}

	public void cargarIndicador() {
		if (indicadorNuevo.getNombre().isEmpty())
			throw new UserException("Complete el nombre del indicador.");
		if (Repositorios.repoIndicadores.existeIndicador(indicadorNuevo))
			throw new UserException("El indicador ingresado ya existe.");
		if (ingresado.isEmpty())
			throw new UserException("Ingrese una formula para el indicador.");
		if (new AnalizadorSintactico(ingresado).chequear() == false) {
//			this.limpiarTodo();
			throw new UserException("Sintaxis de formula incorrecta.");
		}

		this.indicadorNuevo.setFormula(ingresado);
		Repositorios.repoIndicadores.agregarIndicador(indicadorNuevo);
		Repositorios.repoIndicadores.guardar();
		this.setHabilitaCarga(false);
		ObservableUtils.firePropertyChanged(this, "indicadores");

	}

	public void limpiarTodo() {
		this.setIngresado("");
		this.setCuentaSeleccionada(null);
		this.setIndicadorSeleccionado(null);
		this.setConstante("");
	}

	public void borrarUltimo() {
		// borra el ultimo token
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

	public void setIndicadorNuevo(Indicador indicador) {
		this.indicadorNuevo = indicador;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorIngresado) {
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

	public boolean isHabilitaCarga() {
		return habilitaCarga;
	}

	public void setHabilitaCarga(boolean habilitaCarga) {
		this.habilitaCarga = habilitaCarga;
		ObservableUtils.firePropertyChanged(this, "habilitaNuevo");
	}

	public boolean isHabilitaNuevo() {
		return !habilitaCarga;
	}

}
