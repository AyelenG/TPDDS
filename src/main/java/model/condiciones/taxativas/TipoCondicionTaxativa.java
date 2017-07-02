package model.condiciones.taxativas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.data.deserializadores.TipoCondicionDeserializer;

@JsonDeserialize(using = TipoCondicionDeserializer.class)
public interface TipoCondicionTaxativa {
	
	public boolean aplicar(Empresa emp, CondicionTaxativa condicion);
}
