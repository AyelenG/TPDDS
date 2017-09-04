package model.data.deserializadores;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import model.condiciones.Condicion;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;

public class CondicionDeserializer extends StdDeserializer<Condicion> {

	public CondicionDeserializer() {
		this(null);
	}

	protected CondicionDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Condicion deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode root = mapper.readTree(jp);
		if (root.toString().equals("\"Longevidad\"")) {
			return new Longevidad();
		}
		/*
		 * else if(root.toString().equals("\"NuevaPrimitiva\"")){ return new
		 * NuevaPrimitiva(); }
		 */
		else if (root.has("peso")) {
			return mapper.readValue(root.toString(), CondicionNoTaxativaConfigurable.class);
		} else {
			return mapper.readValue(root.toString(), CondicionTaxativaConfigurable.class);
		}
	}
}
