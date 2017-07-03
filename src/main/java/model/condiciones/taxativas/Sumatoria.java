package model.condiciones.taxativas;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;

public class Sumatoria implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// la sumatoria de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		BigDecimal sumatoria = sumatoria(indicador, emp.getUltimosNAnios(cond.getCantidadAnios()));
		return cond.getComparador().aplicar(sumatoria, cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Sumatoria";
	}

}
