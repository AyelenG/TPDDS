package model.evaluador.operaciones;

import java.math.BigDecimal;

import model.evaluador.Expresion;

public class Suma extends Operacion {

	public Suma(Expresion opIzq, Expresion opDer) {
		super(opIzq, opDer);
	}

	@Override
	protected BigDecimal calcular(BigDecimal opIzq, BigDecimal opDer) {
		return opIzq.add(opDer);
	}

	

}
