package model.condiciones.notaxativas;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.Usuario;
import model.condiciones.Comparador;
import model.condiciones.CondicionConfigurable;
import utils.UtilsListas;

@Entity
@JsonDeserialize(as = CondicionNoTaxativaConfigurable.class)
@JsonIgnoreProperties({ "changeSupport" })
public class CondicionNoTaxativaConfigurable extends CondicionConfigurable {

	@Getter @Setter private Integer peso;

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
	public int comparar(Empresa emp1, Empresa emp2, Usuario user) {
		// compara 2 empresas a traves de los ultimos N anios, --POR SUMATORIA--
		// devuelve el peso si la mejor es la emp1, devuelve -peso si es emp2 y
		// 0 si son iguales

		// obtengo indicador desde repositorio
		Indicador indicador = obtenerIndicador(nombreIndicador,user);

		// obtengo los periodos de los ultimos N anios de cada empresa
		List<Periodo> ultimosNAnios1 = emp1.getUltimosNAnios(cantidadAnios);
		List<Periodo> ultimosNAnios2 = emp2.getUltimosNAnios(cantidadAnios);

		// obtengo la sumatoria de los valores de esos periodos
		Function<Periodo,BigDecimal> f = p -> indicador.getValor(p);
		BigDecimal sumEmp1 = UtilsListas.sumatoria(ultimosNAnios1, f);
		BigDecimal sumEmp2 = UtilsListas.sumatoria(ultimosNAnios2, f);
		
		return this.comparador.aplicar(sumEmp1, sumEmp2) * peso;
	}

	@Override
	public boolean convieneInvertirEn(Empresa emp, Usuario user) {
		//devuelve el elemento neutro para no influir
		return true;
	}
}
