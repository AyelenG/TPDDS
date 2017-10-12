package model.evaluador.terminales;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import lombok.Getter;
import model.Indicador;
import model.Periodo;
import model.Usuario;
import model.evaluador.Expresion;
import model.repositories.RepoIndicadores;

public class TerminalIndicador implements Expresion {
	
	@Getter private String nombreIndicador;

	public TerminalIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador.replace(">", "").replace("<", "");
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Usuario user) {
		RepoIndicadores repo = RepoIndicadores.getInstance();
		Indicador indicador = repo.buscarElemento(new Indicador(nombreIndicador,"",user));
		if (indicador == null)
			throw new NoSePuedeEvaluarException
			("No existe indicador <" + nombreIndicador + "> en el sistema");
		return indicador.evaluar(periodo);
	}
}
