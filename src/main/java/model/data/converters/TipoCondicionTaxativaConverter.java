package model.data.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.uqbar.commons.model.UserException;

import model.condiciones.taxativas.TipoCondicionTaxativa;
import model.condiciones.taxativas.TiposCondicionTaxativa;

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
			return TiposCondicionTaxativa.Mediana;
		case "Promedio":
			return TiposCondicionTaxativa.Promedio;
		case "Simple":
			return TiposCondicionTaxativa.Simple;
		case "Sumatoria":
			return TiposCondicionTaxativa.Sumatoria;
		case "Tendencia":
			return TiposCondicionTaxativa.Tendencia;
		default:
			throw new UserException("Error en la base de Datos.");
		}

	}

}