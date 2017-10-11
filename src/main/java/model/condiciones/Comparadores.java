package model.condiciones;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonValue;

public enum Comparadores implements Comparador {
	Mayor {
		@Override
		public int aplicar(BigDecimal valor1, BigDecimal valor2) {
			return valor1.compareTo(valor2);
		}

	},
	Menor {
		@Override
		public int aplicar(BigDecimal valor1, BigDecimal valor2) {
			return valor1.compareTo(valor2) * -1;
		}
	};
	
	@Override
	@JsonValue
	public String toString() {
		return super.toString();
	}
}
