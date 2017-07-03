package model.condiciones.taxativas;

import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import model.Periodo;

public class Tendencia implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// ordenar los ultimos N periodos aplicando el comparador
		// si la lista de ultimos N periodos ordenada es igual a la original, se
		// cumple
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		List<Periodo> ultimosNAniosOrdenados = ultimosNAnios.stream().sorted((p1, p2) -> cond.getComparador()
				.aplicar(indicador.evaluar(p1), indicador.evaluar(p2)))
				.collect(Collectors.toList());
		return ultimosNAnios.equals(ultimosNAniosOrdenados);
	}

	@Override
	@JsonValue
	public String toString() {
		return "Tendencia";
	}

}
