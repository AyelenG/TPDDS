package model.condiciones;

import javax.persistence.AttributeConverter;

import org.uqbar.commons.model.UserException;

public class ComparadorConverter implements AttributeConverter<Comparador, String> {

	@Override
	public String convertToDatabaseColumn(Comparador comparador) {
		return comparador.toString();
	}

	@Override
	public Comparador convertToEntityAttribute(String comparadorString) {
	
		switch (comparadorString) {
		case "Mayor":
			return new Mayor();
		case "Menor":
			return new Menor();
		default:
			throw new UserException("");
		}
		
	}

}