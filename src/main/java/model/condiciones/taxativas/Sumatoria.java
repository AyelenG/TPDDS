package model.condiciones.taxativas;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import utils.UtilsListas;

public class Sumatoria implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// la sumatoria de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		BigDecimal sumatoria = UtilsListas.sumatoria(emp.getUltimosNAnios(cond.getCantidadAnios()),p -> indicador.evaluar(p));
		return cond.getComparador().aplicar(sumatoria, cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Sumatoria";
	}

}
