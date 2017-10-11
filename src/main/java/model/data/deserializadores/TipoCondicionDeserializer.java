package model.data.deserializadores;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import model.condiciones.taxativas.TipoCondicionTaxativa;
import model.condiciones.taxativas.TiposCondicionTaxativa;

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
		if (root.toString().equals("\"Simple\"")) {
			return TiposCondicionTaxativa.Simple;
		} else if (root.toString().equals("\"Promedio\"")) {
			return TiposCondicionTaxativa.Promedio;
		}else if (root.toString().equals("\"Mediana\"")) {
			return TiposCondicionTaxativa.Mediana;
		}else if (root.toString().equals("\"Sumatoria\"")) {
			return TiposCondicionTaxativa.Sumatoria;
		}else if (root.toString().equals("\"Tendencia\"")) {
			return TiposCondicionTaxativa.Tendencia;
		}
		return null;
	}

}
