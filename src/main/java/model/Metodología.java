package model;

import java.util.LinkedList;
import java.util.List;

public class Metodología {
	
	private String nombre = "";
	private List<CondicionNT> condicionesNT = new LinkedList<>();
	private List<CondicionT> condicionesT = new LinkedList<>();
	
	public List<Empresa> filtrarPorCondicionesTaxativas(List<Empresa> empresas){
		return null;
	}
	
	public List<Empresa> ordenarPorCondicionesNT(List<Empresa> empresas){
		return null;
	}
	
	public int comparar(Empresa emp1, Empresa emp2){
		// comparar por todas las condiciones y devolver 1, -1 o 0 
		return 0;
	}
}
