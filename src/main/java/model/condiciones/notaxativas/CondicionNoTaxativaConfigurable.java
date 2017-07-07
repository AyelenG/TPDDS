package model.condiciones.notaxativas;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.condiciones.Comparador;
import model.condiciones.CondicionConfigurable;
import utils.UtilsListas;

@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class CondicionNoTaxativaConfigurable extends CondicionConfigurable implements CondicionNoTaxativa {

	private Integer peso;

	public CondicionNoTaxativaConfigurable() {
	}

	public CondicionNoTaxativaConfigurable(String nombre) {
		super(nombre);
	}

	public CondicionNoTaxativaConfigurable(String nombre, Integer peso, Comparador comparador, String nombreIndicador,
			Integer cantidadAnios) {
		super(nombre, comparador, nombreIndicador, cantidadAnios);
		this.setPeso(peso);
	}

	@Override
	public int comparar(Empresa emp1, Empresa emp2) {
		// compara 2 empresas a traves de los ultimos N anios, --POR SUMATORIA--
		// devuelve el peso si la mejor es la emp1, devuelve -peso si es emp2 y
		// 0 si son iguales

		// obtengo indicador desde repositorio
		Indicador indicador = obtenerIndicador(nombreIndicador);

		// obtengo los periodos de los ultimos N anios de cada empresa
		List<Periodo> ultimosNAnios1 = emp1.getUltimosNAnios(cantidadAnios);
		List<Periodo> ultimosNAnios2 = emp2.getUltimosNAnios(cantidadAnios);

		// obtengo la sumatoria de los valores de esos periodos
		BigDecimal sumEmp1 = UtilsListas.sumatoria(ultimosNAnios1, p->indicador.evaluar(p));
		BigDecimal sumEmp2 = UtilsListas.sumatoria(ultimosNAnios2, p->indicador.evaluar(p));
		
		return this.comparador.aplicar(sumEmp1, sumEmp2) * peso;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
