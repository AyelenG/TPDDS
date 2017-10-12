package model.condiciones;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import lombok.Getter;
import model.Empresa;
import model.Usuario;
import model.data.deserializadores.CondicionDeserializer;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "clase_condicion")
@JsonDeserialize(using = CondicionDeserializer.class)
public abstract class Condicion {

	@Id
	@GeneratedValue
	@Getter private long id;
	
	public abstract boolean esValida(Empresa emp, Usuario user);
	
	public abstract boolean convieneInvertirEn(Empresa emp, Usuario user);

	public abstract int comparar(Empresa emp1, Empresa emp2, Usuario user);

}
