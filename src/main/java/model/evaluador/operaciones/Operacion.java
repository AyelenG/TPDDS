package model.evaluador.operaciones;

import java.math.BigDecimal;

import model.Indicadores;
import model.Periodo;
import model.evaluador.Expresion;

public abstract class Operacion implements Expresion {

	private Expresion opIzq; // operando izquierdo
	private Expresion opDer; // operando derecho

	public Operacion(Expresion opIzq, Expresion opDer) {
		this.opIzq = opIzq;
		this.opDer = opDer;
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Indicadores indiceIndicadores) {
		return this.calcular(opIzq.getValor(periodo, indiceIndicadores), opDer.getValor(periodo, indiceIndicadores));
	}

	protected abstract BigDecimal calcular(BigDecimal opIzq, BigDecimal opDer);
	
	public Expresion getOpIzq(){
		return opIzq;
	}
	
	public Expresion getOpDer(){
		return opDer;
	}

}
