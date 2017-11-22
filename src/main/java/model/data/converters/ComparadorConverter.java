package model.data.converters;

import javax.persistence.AttributeConverter;

import model.condiciones.Comparador;
import model.condiciones.Comparadores;

public class ComparadorConverter implements AttributeConverter<Comparador, String> {

	@Override
	public String convertToDatabaseColumn(Comparador comparador) {
		return comparador.toString();
	}

	@Override
	public Comparador convertToEntityAttribute(String comparadorString) {
	
		switch (comparadorString) {
		case "Mayor":
			return Comparadores.Mayor;
		case "Menor":
			return Comparadores.Menor;
		default:
			throw new RuntimeException("Error en la base de Datos.");
		}
		
	}

}