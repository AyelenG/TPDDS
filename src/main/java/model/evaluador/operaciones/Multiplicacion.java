package model.evaluador.operaciones;

import java.math.BigDecimal;

import model.evaluador.Expresion;

public class Multiplicacion extends Operacion {

	public Multiplicacion(Expresion opIzq, Expresion opDer) {
		super(opIzq, opDer);
	}

	@Override
	protected BigDecimal calcular(BigDecimal opIzq, BigDecimal opDer) {
		return opIzq.multiply(opDer);
	}

	

}
