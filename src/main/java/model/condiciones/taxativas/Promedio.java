package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import model.Empresa;
import model.Periodo;

public class Promedio implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativa cond) {
		// el promedio de los ultimos N anios al comparar con valorDeReferencia es > 0
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		if (ultimosNAnios.isEmpty()) return false;
		BigDecimal sumatoria = ultimosNAnios.stream()
				.map(p-> cond.getIndicador().evaluar(p))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal cant = BigDecimal.valueOf(ultimosNAnios.size());
		BigDecimal promedio = sumatoria.divide(cant,5,RoundingMode.HALF_UP);
		return cond.getComparador().aplicar(promedio, cond.getValorDeReferencia()) > 0;
	}
	
}