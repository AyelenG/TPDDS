package ui.vm;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Cuentas;
import model.Indicador;
import model.Indicadores;
import model.parser.analizadorSintactico.AnalizadorSintactico;
import model.repositories.Repositorios;

@Observable
public class CargaIndicadorViewModel {

	private Cuentas cuentas = Repositorios.repoCuentas;
	private Indicadores indicadores = Repositorios.repoIndicadores;
	private String ingresado = "";
	private List<String> tokens = new LinkedList<>();
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

	public void agregarToken(String token) {
		if (ingresado != "")
			ingresado += " " + token;
		else
			ingresado = token;
		tokens.add(token);
	}

	public void ingresarCuenta() {
		if (cuentaSeleccionada != null) {
			this.agregarToken("[" + cuentaSeleccionada.getNombre() + "]");
			this.setCuentaSeleccionada(null);
		} else
			throw new UserException("Debe elegir una cuenta de la lista.");
	}

	public void ingresarIndicador() {
		if (indicadorSeleccionado != null) {
			this.agregarToken("<" + indicadorSeleccionado.getNombre() + ">");
			this.setIndicadorSeleccionado(null);
		}else 
			throw new UserException("Debe elegir un indicador de la lista.");
	}

	public void ingresarConstante() {
		if (NumberUtils.isNumber(constante)) {
			this.agregarToken(constante);
			this.setConstante("");
		} else
			throw new UserException("La constante debe ser numerica.");

	}

	public void cargarIndicador() {
		if (indicadorNuevo.getNombre().isEmpty())
			throw new UserException("Complete el nombre del indicador.");
		if (indicadores.existeIndicador(indicadorNuevo))
			throw new UserException("El indicador ingresado ya existe.");
		if (ingresado.isEmpty())
			throw new UserException("Ingrese una formula para el indicador.");
		if (new AnalizadorSintactico(ingresado).chequear() == false) {
			// this.limpiarTodo();
			throw new UserException("Sintaxis de formula incorrecta.");
		}

		this.indicadorNuevo.setFormula(ingresado);
		indicadores.agregarIndicador(indicadorNuevo);
		indicadores.guardar();
		this.setHabilitaCarga(false);
		ObservableUtils.firePropertyChanged(this, "indicadores");

	}

	public void limpiarTodo() {
		this.setIngresado("");
		this.tokens.clear();
		this.setCuentaSeleccionada(null);
		this.setIndicadorSeleccionado(null);
		this.setConstante("");
	}

	public void borrarUltimo() {
		try {
			tokens.remove(tokens.size() - 1);
			ingresado = String.join(" ", tokens);
		} catch (IndexOutOfBoundsException e) {
			// si no hay tokens para borrar no hago nada
		}
	}

	public List<Cuenta> getCuentas() {
		return cuentas.getCuentas();
	}

	public void setCuentas(Cuentas cuentas) {
		this.cuentas = cuentas;
	}

	public List<Indicador> getIndicadores() {
		return indicadores.getIndicadores();
	}

	public void setIndicadores(Indicadores indicadores) {
		this.indicadores = indicadores;
	}

	public String getIngresado() {
		return ingresado;
	}

	public void setIngresado(String ingresado) {
		this.ingresado = ingresado;
	}

	// necesario poner getter/setter de tokens si no falla Arena
	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
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
