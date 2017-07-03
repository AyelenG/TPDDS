package model.data.deserializadores;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.combinadas.CondicionCombinadaCompuesta;
import model.condiciones.combinadas.Longevidad;

public class CondicionCombinadaDeserializer extends StdDeserializer<CondicionCombinada> {

	public CondicionCombinadaDeserializer() {
		this(null);
	}

	protected CondicionCombinadaDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public CondicionCombinada deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode root = mapper.readTree(jp);
		if (root.toString().equals("\"Longevidad\"")) {
			return new Longevidad();
		} else{
			return mapper.readValue(root.toString(), CondicionCombinadaCompuesta.class);
		}
	}

}