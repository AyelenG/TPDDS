package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;

public class Mediana implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// la mediana de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		List<BigDecimal> valoresOrd = emp.getUltimosNAnios(cond.getCantidadAnios()).stream()
				.map(p -> indicador.evaluar(p)).sorted().collect(Collectors.toList());
		int cant = valoresOrd.size();
		if (cant == 0)
			return false;
		return cond.getComparador().aplicar(valoresOrd.get(cant / 2), cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Mediana";
	}
}