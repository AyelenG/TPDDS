package model.data.deserializadores;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import model.condiciones.taxativas.Mediana;
import model.condiciones.taxativas.Promedio;
import model.condiciones.taxativas.Simple;
import model.condiciones.taxativas.Sumatoria;
import model.condiciones.taxativas.Tendencia;
import model.condiciones.taxativas.TipoCondicionTaxativa;

public class TipoCondicionDeserializer extends StdDeserializer<TipoCondicionTaxativa> {

	public TipoCondicionDeserializer() {
		this(null);
	}

	protected TipoCondicionDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public TipoCondicionTaxativa deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode root = mapper.readTree(jp);
		if (root.toString().contains("Simple")) {
			return new Simple();
		} else if (root.toString().contains("Promedio")) {
			return new Promedio();
		}else if (root.toString().contains("Mediana")) {
			return new Mediana();
		}else if (root.toString().contains("Sumatoria")) {
			return new Sumatoria();
		}else if (root.toString().contains("Tendencia")) {
			return new Tendencia();
		}
		return null;
	}

}
