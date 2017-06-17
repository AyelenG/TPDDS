package model.evaluador.operaciones;

import java.math.BigDecimal;
import java.math.RoundingMode;

import exceptions.NoSePuedeEvaluarException;
import model.evaluador.Expresion;

public class Division extends Operacion {

	public Division(Expresion opIzq, Expresion opDer) {
		super(opIzq, opDer);
	}

	@Override
	protected BigDecimal calcular(BigDecimal opIzq, BigDecimal opDer) {
		if (opDer.equals(BigDecimal.ZERO))
			throw new NoSePuedeEvaluarException("No se puede evaluar - Division por cero.");
		try {
			return opIzq.divide(opDer);
		} catch (ArithmeticException e) {
			return opIzq.divide(opDer, 4, RoundingMode.HALF_EVEN);
		}
	}

}
