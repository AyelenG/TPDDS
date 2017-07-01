package model.condiciones.taxativas;

import model.Empresa;

public class Mediana implements TipoCondicionTaxativa {

	@Override
	public boolean comparar(Empresa empresa, CondicionTaxativa condicion) {
		// la mediana de los ultimos N anios al comparar con valorDeReferencia es > 0
		return false;
	}

}