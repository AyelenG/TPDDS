package model.evaluador.terminales;

import java.math.BigDecimal;

import model.Periodo;
import model.evaluador.Expresion;
import model.repositories.RepoIndicadores;

public class TerminalLiteral implements Expresion {

	private BigDecimal literal;
	
	public TerminalLiteral(BigDecimal literal) {
		this.literal = literal;
	}

	@Override
	public BigDecimal getValor(Periodo periodo, RepoIndicadores indicadores) {
		return literal;
	}
	
	public String getLiteral(){
		return literal.toString();
	}

}
