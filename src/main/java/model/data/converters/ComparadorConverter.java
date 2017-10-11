package model.data.converters;

import javax.persistence.AttributeConverter;

import org.uqbar.commons.model.UserException;

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
			throw new UserException("Error en la base de Datos.");
		}
		
	}

}