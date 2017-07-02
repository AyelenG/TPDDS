package model.condiciones;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.data.deserializadores.ComparadorDeserializer;

@JsonDeserialize(using = ComparadorDeserializer.class)
public interface Comparador {
	
	//determina el sentido de comparacion para las condiciones
	public int aplicar(BigDecimal valor1, BigDecimal valor2);
}
