package model.condiciones.taxativas;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.uqbar.commons.model.UserException;

@Converter
public class TipoCondicionTaxativaConverter implements AttributeConverter<TipoCondicionTaxativa, String> {

	@Override
	public String convertToDatabaseColumn(TipoCondicionTaxativa tipoCondicionTaxativa) {
		return tipoCondicionTaxativa.toString();
	}

	@Override
	public TipoCondicionTaxativa convertToEntityAttribute(String tipoCondicionTaxativaString) {
	
		switch (tipoCondicionTaxativaString) {
		case "Mediana":
			return new Mediana();
		case "Promedio":
			return new Promedio();
		case "Simple":
			return new Simple();
		case "Sumatoria":
			return new Sumatoria();
		case "Tendencia":
			return new Sumatoria();
		default:
			throw new UserException("");
		}

	}

}