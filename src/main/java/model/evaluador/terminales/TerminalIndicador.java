package model.evaluador.terminales;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Indicador;
import model.Periodo;
import model.evaluador.Expresion;
import model.repositories.RepoIndicadores;

public class TerminalIndicador implements Expresion {
	
	private String nombreIndicador;

	public TerminalIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador.replace(">", "").replace("<", "");
	}

	@Override
	public BigDecimal getValor(Periodo periodo, RepoIndicadores indiceIndicadores) {
		Indicador indicador = indiceIndicadores.buscarElemento(new Indicador(nombreIndicador));
		if (indicador == null)
			throw new NoSePuedeEvaluarException
			("No existe indicador <" + nombreIndicador + "> en el sistema");
		return indicador.evaluar(periodo);
	}
	
	public String getNombreIndicador(){
		return nombreIndicador;
	}
}
