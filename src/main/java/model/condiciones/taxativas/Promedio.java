package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import model.Periodo;

public class Promedio implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// el promedio de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		if (ultimosNAnios.isEmpty())
			return false;
		
		BigDecimal sumatoria = sumatoria(indicador, ultimosNAnios);
		BigDecimal cant = BigDecimal.valueOf(ultimosNAnios.size());
		BigDecimal promedio = sumatoria.divide(cant, 5, RoundingMode.HALF_UP);
		return cond.getComparador().aplicar(promedio, cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Promedio";
	}

}