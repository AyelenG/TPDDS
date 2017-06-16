package model.evaluador.terminales;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Indicador;
import model.Indicadores;
import model.Periodo;
import model.evaluador.Expresion;

public class TerminalIndicador implements Expresion {
	
	private String nombreIndicador;

	public TerminalIndicador(String nombreIndicador) {
		super();
		this.nombreIndicador = nombreIndicador;
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Indicadores indiceIndicadores) {
		Indicador indicador = indiceIndicadores.buscarElemento(new Indicador(nombreIndicador));
		if (indicador == null)
			throw new NoSePuedeEvaluarException
			("No existe indicador <" + nombreIndicador + "> en el sistema");
		return indicador.evaluar(periodo, indiceIndicadores);
	}

}
