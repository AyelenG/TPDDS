package model.condiciones.taxativas;

import model.Empresa;

public class Sumatoria implements TipoCondicionTaxativa {

	@Override
	public boolean comparar(Empresa empresa, CondicionTaxativa condicion) {
		// la sumatoria de los ultimos N anios al comparar con valorDeReferencia es > 0
		return false;
	}
	

}
