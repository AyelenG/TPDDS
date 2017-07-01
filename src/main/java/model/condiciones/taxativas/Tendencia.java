package model.condiciones.taxativas;

import model.Empresa;

public class Tendencia implements TipoCondicionTaxativa {

	@Override
	public boolean comparar(Empresa empresa, CondicionTaxativa condicion) {
		// ordenar los ultimos N periodos aplicando el comparador 
		// si la lista de ultimos N periodos ordenada es igual a la original, se cumple
		return false;
	}

}
