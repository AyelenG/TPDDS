package model.evaluador.terminales;

import java.math.BigDecimal;

import model.Indicadores;
import model.Periodo;
import model.evaluador.Expresion;

public class TerminalLiteral implements Expresion {

	private BigDecimal literal;
	
	public TerminalLiteral(BigDecimal literal) {
		this.literal = literal;
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Indicadores indicadores) {
		return literal;
	}
	
	public String getLiteral(){
		return literal.toString();
	}

}
