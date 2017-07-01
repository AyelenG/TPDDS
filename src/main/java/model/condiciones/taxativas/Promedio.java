package model.condiciones.taxativas;

import model.Empresa;

public class Promedio implements TipoCondicionTaxativa {

	@Override
	public boolean comparar(Empresa empresa, CondicionTaxativa condicion) {
		// el promedio de los ultimos N anios al comparar con valorDeReferencia es > 0
		return false;
	}
	
}