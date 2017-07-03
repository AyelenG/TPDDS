package model.condiciones.notaxativas;

import java.math.BigDecimal;
import java.util.List;

import exceptions.NoSePuedeAplicarException;
import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.condiciones.Comparador;
import model.repositories.RepoIndicadores;

public class CondicionNoTaxativaConfigurable implements CondicionNoTaxativa {
	private String nombre;
	private Integer peso;

	private Comparador comparador;
	private String nombreIndicador;
	private Integer cantidadAnios;

	public CondicionNoTaxativaConfigurable() {
	}

	public CondicionNoTaxativaConfigurable(String nombre) {
		this.setNombre(nombre);
	}

	public CondicionNoTaxativaConfigurable(String nombre, Integer peso, Comparador comparador, String indicador,
			Integer cantidadAnios) {
		this.setNombre(nombre);
		this.setPeso(peso);
		this.setComparador(comparador);
		this.setIndicador(indicador);
		this.setCantidadAnios(cantidadAnios);
	}

	@Override
	public int comparar(Empresa emp1, Empresa emp2) {
		// compara 2 empresas a traves de los ultimos N anios, --POR SUMATORIA--
		// devuelve el peso si la mejor es la emp1, devuelve -peso si es emp2 y
		// 0 si son iguales

		//obtengo indicador desde repositorio
		Indicador indicador = RepoIndicadores.getInstance().buscarElemento(new Indicador(nombreIndicador));
		if (indicador == null) {
			throw new NoSePuedeAplicarException("No se puede aplicar la metodologia '" + nombre
					+ "' por falta de indicador <" + nombreIndicador + ">.");
		}
		
		// obtengo los periodos de los ultimos N anios de cada empresa
		List<Periodo> ultimosNAnios1 = emp1.getUltimosNAnios(cantidadAnios);
		List<Periodo> ultimosNAnios2 = emp2.getUltimosNAnios(cantidadAnios);
		int cantPeriodosEmp1 = ultimosNAnios1.size();
		int cantPeriodosEmp2 = ultimosNAnios2.size();

		// chequeo si tienen datos en los N anios, el que no tenga pierde
		// si ninguno tiene empatan
		if (cantPeriodosEmp1 == this.cantidadAnios && cantPeriodosEmp2 != this.cantidadAnios)
			return this.peso;
		else if (cantPeriodosEmp1 != this.cantidadAnios && cantPeriodosEmp2 == this.cantidadAnios)
			return this.peso * -1;
		else if (cantPeriodosEmp1 != this.cantidadAnios && cantPeriodosEmp2 != this.cantidadAnios)
			return 0;

		// obtengo la sumatoria de los valores de esos periodos
		BigDecimal sumEmp1 = ultimosNAnios1.stream().map(p -> indicador.evaluar(p)).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		BigDecimal sumEmp2 = ultimosNAnios2.stream().map(p -> indicador.evaluar(p)).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		return this.comparador.aplicar(sumEmp1, sumEmp2) * peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public String getIndicador() {
		return nombreIndicador;
	}

	public void setIndicador(String indicador) {
		this.nombreIndicador = indicador;
	}

	public Integer getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(Integer cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}
}
