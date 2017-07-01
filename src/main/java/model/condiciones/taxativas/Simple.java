package model.condiciones.taxativas;

import model.Empresa;

public class Simple implements TipoCondicionTaxativa {

	@Override
	public boolean comparar(Empresa empresa, CondicionTaxativa condicion) {
		//
		// empresa.getUltimosNAnios(cantidadAnios)
		// 		.all(p -> comparador.aplicar(indicador.evaluar(p),valorDeReferencia) > 0)
		/*En empresa: getUltimosNAnios(int n)
		  return empresa.getPeriodos().stream()
			.filter(p -> p.getAnio() > anioActual - n)
			.collect(Collectors.toList());*/
		return false;
	}

}
