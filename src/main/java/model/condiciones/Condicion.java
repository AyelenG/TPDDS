package model.condiciones;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.data.deserializadores.CondicionDeserializer;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonDeserialize(using = CondicionDeserializer.class)
public abstract class Condicion {

	@Id
	@GeneratedValue
	private long id;
	
	public abstract boolean esValida(Empresa emp);
	
	public abstract boolean convieneInvertirEn(Empresa emp);

	public abstract int comparar(Empresa emp1, Empresa emp2);

}
