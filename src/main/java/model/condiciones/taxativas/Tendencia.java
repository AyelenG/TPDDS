package model.condiciones.taxativas;

import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import model.Periodo;

public class Tendencia implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativa cond) {
		// ordenar los ultimos N periodos aplicando el comparador 
		// si la lista de ultimos N periodos ordenada es igual a la original, se cumple
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		List<Periodo> ultimosNAniosOrdenados = ultimosNAnios.stream()
												.sorted((p1,p2) -> cond.getComparador().aplicar
							(cond.getIndicador().evaluar(p1),cond.getIndicador().evaluar(p2)))
												.collect(Collectors.toList());
		return ultimosNAnios.equals(ultimosNAniosOrdenados);
	}

}
