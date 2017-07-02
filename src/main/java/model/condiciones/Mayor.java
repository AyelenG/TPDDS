package model.condiciones;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonValue;

public class Mayor implements Comparador {

	@Override
	public int aplicar(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2);
	}

	@Override
	@JsonValue
	public String toString() {
		return "Mayor";
	}

	

}
