package model.condiciones.taxativas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.condiciones.Validable;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;

@JsonDeserialize(as = CondicionTaxativaConfigurable.class)
public interface CondicionTaxativa extends Validable{

	boolean convieneInvertirEn(Empresa emp);
}