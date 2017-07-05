package model.condiciones.taxativas;

import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import model.Periodo;

public class Simple implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
		// el indicador fue mayor o menor al valor de referencia en todos los
		// anios
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());

		return ultimosNAnios.stream().allMatch(
				p -> cond.getComparador().aplicar(indicador.evaluar(p), cond.getValorDeReferencia()) > 0);
	}

	@Override
	@JsonValue
	public String toString() {
		return "Simple";
	}
}
