package model.condiciones.taxativas;

import java.math.BigDecimal;

import model.Empresa;

public class Sumatoria implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativa cond) {
		// la sumatoria de los ultimos N anios al comparar con valorDeReferencia es > 0
		BigDecimal sumatoria = emp.getUltimosNAnios(cond.getCantidadAnios()).stream()
						.map(p-> cond.getIndicador().evaluar(p))
						.reduce(BigDecimal.ZERO, BigDecimal::add);
		return cond.getComparador().aplicar(sumatoria,cond.getValorDeReferencia()) > 0;
	}
	

}
