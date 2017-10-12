package model.evaluador.operaciones;

import java.math.BigDecimal;

import model.Periodo;
import model.Usuario;
import model.evaluador.Expresion;

public abstract class Operacion implements Expresion {

	private Expresion opIzq; // operando izquierdo
	private Expresion opDer; // operando derecho

	public Operacion(Expresion opIzq, Expresion opDer) {
		this.opIzq = opIzq;
		this.opDer = opDer;
	}

	@Override
	public BigDecimal getValor(Periodo periodo, Usuario user) {
		return this.calcular(opIzq.getValor(periodo, user), opDer.getValor(periodo, user));
	}

	protected abstract BigDecimal calcular(BigDecimal opIzq, BigDecimal opDer);
	
	public Expresion getOpIzq(){
		return opIzq;
	}
	
	public Expresion getOpDer(){
		return opDer;
	}

}
