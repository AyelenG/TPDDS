package model.condiciones.combinadas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;
import model.data.deserializadores.CondicionCombinadaDeserializer;

@JsonDeserialize(using = CondicionCombinadaDeserializer.class)
public interface CondicionCombinada extends CondicionTaxativa,CondicionNoTaxativa {


}