package model.condiciones.combinadas;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;

public class Longevidad implements CondicionCombinada {

	@Override
	public boolean convieneInvertirEn(Empresa emp) {
		return emp.antiguedad() > 10;
	}

	@Override
	public int comparar(Empresa emp1, Empresa emp2) {
		return emp1.antiguedad() - emp2.antiguedad(); // el peso es la
														// diferencia de
														// antiguedad
	}

	@Override
	@JsonValue
	public String toString() {
		return "Longevidad";
	}
}
