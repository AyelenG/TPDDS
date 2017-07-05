package model.condiciones.taxativas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;

@JsonDeserialize(as = CondicionTaxativaConfigurable.class)
public interface CondicionTaxativa {

	boolean convieneInvertirEn(Empresa emp);
	
	boolean esValida(Empresa emp);
}