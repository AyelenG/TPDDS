package model.condiciones;

import java.math.BigDecimal;

public class Menor implements Comparador {

	@Override
	public int aplicar(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2) * -1;
	}

}
