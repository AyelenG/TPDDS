package model;

import java.util.LinkedList;
import java.util.List;

import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;

public class Metodologia {
	
	private String nombre = "";
	
	private List<CondicionNoTaxativa> condicionesNT = new LinkedList<>();
	private List<CondicionTaxativa> condicionesT = new LinkedList<>();
	private List<CondicionCombinada> condicionesComb = new LinkedList<>();
	
	public Metodologia(String nombre, List<CondicionNoTaxativa> condicionesNT, List<CondicionTaxativa> condicionesT,
			List<CondicionCombinada> condicionesComb) {
		this.setNombre(nombre);
		this.condicionesNT = condicionesNT;
		this.condicionesT = condicionesT;
		this.condicionesComb = condicionesComb;
	}

	public List<Empresa> obtenerFiltradas(List<Empresa> empresas){
		//filtrar por las condicionesT y por las condicionesComb y devolver la lista resultante
		return null;
	}
	
	public List<Empresa> obtenerOrdenadas(List<Empresa> empresas){
		//ordenar segun metodo comparar y devolver la lista resultante
		return null;
	}
	
	private int comparar(Empresa emp1, Empresa emp2){
		// comparar por las condicionesNT y por las condicionesComb y devolver 1, -1 o 0 
		// este metodo se usa en obtenerOrdenadas
		return 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
