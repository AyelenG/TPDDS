package model.condiciones.taxativas;

import java.util.List;

import model.Empresa;
import model.Periodo;

public class Simple implements TipoCondicionTaxativa {

	@Override
	public boolean aplicar(Empresa emp, CondicionTaxativa cond) {
		//el indicador fue mayor o menor al valor de referencia en todos los anios
		
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
		int cantPeriodosEmp = ultimosNAnios.size();
		
		//si no tiene datos en todos los anios, no se cumple
		if(cantPeriodosEmp != cond.getCantidadAnios()) return false;
		
		return ultimosNAnios.stream().allMatch(p -> cond.getComparador().
				aplicar(cond.getIndicador().evaluar(p),cond.getValorDeReferencia()) > 0);
	}

}
