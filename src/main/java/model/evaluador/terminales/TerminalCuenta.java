package model.evaluador.terminales;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Cuenta;
import model.Indicadores;
import model.Periodo;
import model.evaluador.Expresion;

public class TerminalCuenta implements Expresion {

	private String nombreCuenta;

	public TerminalCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta.replace("]", "").replace("[", "");
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Indicadores indiceIndicadores) {
		Cuenta cuenta = periodo.buscarCuenta(new Cuenta(nombreCuenta));
		if (cuenta == null)
			throw new NoSePuedeEvaluarException(
					"No existe cuenta [" + nombreCuenta + "] en el periodo." + " No puede evaluarse el indicador.");
		return cuenta.getValor();

	}

}
