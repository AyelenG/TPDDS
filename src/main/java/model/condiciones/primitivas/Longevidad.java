package model.condiciones.primitivas;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.condiciones.Condicion;

@Entity
public class Longevidad extends Condicion {

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

	@Override
	public boolean esValida(Empresa emp) {
		return true;
	}
}
