package model.condiciones;

import java.math.BigDecimal;


public interface Comparador {
	
	//determina el sentido de comparacion para las condiciones
	public int aplicar(BigDecimal valor1, BigDecimal valor2);
}
