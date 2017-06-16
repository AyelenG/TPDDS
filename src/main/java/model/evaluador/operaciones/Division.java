package model.evaluador.operaciones;

import java.math.BigDecimal;

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
		return opIzq.divide(opDer);
	}

}
