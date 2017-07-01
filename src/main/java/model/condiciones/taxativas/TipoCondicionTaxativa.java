package model.condiciones.taxativas;

import model.Empresa;

public interface TipoCondicionTaxativa {
	
	public boolean comparar(Empresa emp, CondicionTaxativa condicion);
}
