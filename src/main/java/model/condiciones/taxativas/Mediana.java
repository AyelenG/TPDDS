package model.condiciones.taxativas;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import utils.UtilsListas;

public class Mediana implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// la mediana de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		BigDecimal mediana = UtilsListas.mediana(emp.getUltimosNAnios(cond.getCantidadAnios()),p -> indicador.evaluar(p));
		return cond.getComparador().aplicar(mediana, cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Mediana";
	}
}