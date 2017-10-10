package model.evaluador.terminales;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import lombok.Getter;
import model.Cuenta;
import model.CuentaPeriodo;
import model.Periodo;
import model.evaluador.Expresion;
import model.repositories.RepoIndicadores;

public class TerminalCuenta implements Expresion {

	@Getter private String nombreCuenta;

	public TerminalCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta.replace("]", "").replace("[", "");
	}

	@Override
	public BigDecimal getValor(Periodo periodo, RepoIndicadores indiceIndicadores) {
		CuentaPeriodo cuenta = periodo.buscarCuenta(new Cuenta(nombreCuenta));
		if (cuenta == null)
			throw new NoSePuedeEvaluarException(
					"No existe cuenta [" + nombreCuenta + "] en el periodo." + " No puede evaluarse el indicador.");
		return cuenta.getValor();

	}
}
