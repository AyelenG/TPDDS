package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonValue;

import model.Empresa;
import model.Indicador;
import model.Periodo;
import utils.UtilsListas;

public enum TiposCondicionTaxativa implements TipoCondicionTaxativa {

	Simple {
		@Override
		public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
			// el indicador fue mayor o menor al valor de referencia en todos
			// los
			// anios
			List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
			
			return ultimosNAnios.stream()
					.allMatch(p -> cond.getComparador().aplicar(indicador.getValor(p), cond.getValorDeReferencia()) > 0);
		}
	},
	Sumatoria {
		@Override
		public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
			// la sumatoria de los ultimos N anios al comparar con
			// valorDeReferencia
			// es > 0
			Function<Periodo,BigDecimal> f = p -> indicador.getValor(p);
			
			BigDecimal sumatoria = UtilsListas.sumatoria(emp.getUltimosNAnios(cond.getCantidadAnios()),f);
			return cond.getComparador().aplicar(sumatoria, cond.getValorDeReferencia()) > 0;
		}
	},
	Promedio {
		@Override
		public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
			// el promedio de los ultimos N anios al comparar con
			// valorDeReferencia
			// es > 0
			List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
			if (ultimosNAnios.isEmpty())
				return false;

			Function<Periodo,BigDecimal> f = p -> indicador.getValor(p);
			
			BigDecimal promedio = UtilsListas.promedio(ultimosNAnios, f);
			return cond.getComparador().aplicar(promedio, cond.getValorDeReferencia()) > 0;
		}
	},
	Mediana {
		@Override
		public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
			// la mediana de los ultimos N anios al comparar con
			// valorDeReferencia
			// es > 0
			Function<Periodo,BigDecimal> f = p -> indicador.getValor(p);

			BigDecimal mediana = UtilsListas.mediana(emp.getUltimosNAnios(cond.getCantidadAnios()),f);
			return cond.getComparador().aplicar(mediana, cond.getValorDeReferencia()) > 0;
		}

	},
	Tendencia {
		@Override
		public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable cond, Indicador indicador) {
			// ordenar los ultimos N periodos aplicando el comparador
			// si la lista de ultimos N periodos ordenada es igual a la
			// original, se
			// cumple
			List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cond.getCantidadAnios());
			List<Periodo> ultimosNAniosOrdenados = ultimosNAnios.stream()
					.sorted((p1, p2) -> cond.getComparador().aplicar
							(indicador.getValor(p1),
							indicador.getValor(p2)))
					.collect(Collectors.toList());
			return ultimosNAnios.equals(ultimosNAniosOrdenados);
		}
	};

	@Override
	@JsonValue
	public String toString() {
		return super.toString();
	}
}
