package model.condiciones;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.data.deserializadores.CondicionDeserializer;

@JsonDeserialize(using = CondicionDeserializer.class)
public abstract class Condicion{

	public abstract boolean esValida(Empresa emp);
	
	public abstract boolean convieneInvertirEn(Empresa emp);

	public abstract int comparar(Empresa emp1, Empresa emp2);

}
