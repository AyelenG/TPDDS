package model.condiciones.notaxativas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.condiciones.Validable;

@JsonDeserialize(as = CondicionNoTaxativaConfigurable.class)
public interface CondicionNoTaxativa extends Validable{

	int comparar(Empresa emp1, Empresa emp2);
}