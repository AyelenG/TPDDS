package model.condiciones.taxativas;

import model.Empresa;

public interface TipoCondicionTaxativa {
	
	public boolean aplicar(Empresa emp, CondicionTaxativa condicion);
}
