package model.condiciones.notaxativas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;

@JsonDeserialize(as = CondicionNoTaxativaConfigurable.class)
public interface CondicionNoTaxativa {

	int comparar(Empresa emp1, Empresa emp2);

	String getTitulo();
}