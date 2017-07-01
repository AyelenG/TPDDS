package model.condiciones.notaxativas;

import model.Empresa;
import model.Indicador;
import model.condiciones.Comparador;

public class CondicionNoTaxativa {
	private String nombre;
	
	private Comparador comparador;
	private Indicador indicador;
	
	private Integer cantidadAnios;

	public CondicionNoTaxativa(String nombre, Comparador comparador, Indicador indicador, Integer cantidadAnios) {
		this.nombre = nombre;
		this.comparador = comparador;
		this.indicador = indicador;
		this.cantidadAnios = cantidadAnios;
	}

	public int comparar(Empresa emp1, Empresa emp2){
		return 0;
		// compara 2 empresas a traves de los ultimos N periodos,
		//devuelve 1 si la mejor es la emp1, devuelve -1 si es emp2 y 0 si son iguales
		//se puede multiplicar lo que devuelve por un "peso" configurable
		//para darle prioridad a esta condicion sobre otras
		
		/*
		 * emp1.getUltimosNAnios(cantidadAnios).map(p->indicador.evaluar(p))
		 * emp2.getUltimosNAnios(cantidadAnios).map(p->indicador.evaluar(p))
		 * despues comparar 1 x 1 o por promedio 
		 */
	}
}
