package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import model.Periodo;
import utils.UtilsListas;

public class Promedio implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// el promedio de los ultimos N anios al comparar con valorDeReferencia
		// es > 0
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		if (ultimosNAnios.isEmpty())
			return false;
		
		BigDecimal promedio = UtilsListas.promedio(ultimosNAnios,p->indicador.evaluar(p));
		return cond.getComparador().aplicar(promedio, cond.getValorDeReferencia()) > 0;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Promedio";
	}

}