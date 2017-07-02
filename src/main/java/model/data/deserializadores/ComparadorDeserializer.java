package model.data.deserializadores;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import model.condiciones.Comparador;
import model.condiciones.Mayor;
import model.condiciones.Menor;

public class ComparadorDeserializer extends StdDeserializer<Comparador> {

	public ComparadorDeserializer() {
		this(null);
	}

	protected ComparadorDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Comparador deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode root = mapper.readTree(jp);
		if (root.toString().contains("Mayor")) {
			return new Mayor();
		} else if (root.toString().contains("Menor")) {
			return new Menor();
		}
		return null;
	}

}
