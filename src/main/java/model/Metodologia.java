package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;

@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Metodologia {

	private String nombre = "";

	private List<CondicionNoTaxativa> condicionesNT = new LinkedList<>();
	private List<CondicionTaxativa> condicionesT = new LinkedList<>();
	private List<CondicionCombinada> condicionesComb = new LinkedList<>();

	public Metodologia() {
	}

	public Metodologia(String nombre) {
		this.setNombre(nombre);
	}

	public Metodologia(String nombre, List<CondicionNoTaxativa> condicionesNT, List<CondicionTaxativa> condicionesT,
			List<CondicionCombinada> condicionesComb) {
		this.setNombre(nombre);
		this.setCondicionesNT(condicionesNT);
		this.setCondicionesT(condicionesT);
		this.setCondicionesComb(condicionesComb);
	}

	/**
	 * Aplica la metodologia a la lista de empresas de entrada y devuelve la
	 * lista de empresas en las que es deseable invertir ordenadas por prioridad
	 * 
	 * @param empresas
	 *            - lista de empresas de entrada
	 * @return lista de empresas de salida
	 */
	public List<Empresa> aplicar(List<Empresa> empresas) {
		return this.obtenerOrdenadas(this.obtenerFiltradas(empresas));
	}

	public List<Empresa> obtenerFiltradas(List<Empresa> empresas) {
		// filtrar por las condicionesT y por las condicionesComb y devolver la
		// lista resultante
		List<Empresa> empresasFiltradas = empresas;
		empresasFiltradas = empresasFiltradas.stream()
				.filter(emp -> this.condicionesT.stream().allMatch(cond -> cond.convieneInvertirEn(emp)))
				.collect(Collectors.toList());
		empresasFiltradas = empresasFiltradas.stream()
				.filter(emp -> this.condicionesComb.stream().allMatch(cond -> cond.convieneInvertirEn(emp)))
				.collect(Collectors.toList());
		return empresasFiltradas;
	}

	public List<Empresa> obtenerOrdenadas(List<Empresa> empresas) {
		// ordenar segun metodo comparar y devolver la lista resultante
		return empresas.stream()
				.sorted(this::comparar)
				.collect(Collectors.toList());
	}

	private int comparar(Empresa emp1, Empresa emp2) {
		// comparar por las condicionesNT y por las condicionesComb y devolver
		// 1, -1 o 0
		// este metodo se usa en obtenerOrdenadas
		int puntajeEmp1 = 0;
		int puntajeEmp2 = 0;
		int r;

		for (CondicionNoTaxativa cond : condicionesNT) {
			r = cond.comparar(emp1, emp2);
			if (r >= 0)
				puntajeEmp1 += r;
			else
				puntajeEmp2 += -r;
		}
		for (CondicionCombinada cond : condicionesComb) {
			r = cond.comparar(emp1, emp2);
			if (r >= 0)
				puntajeEmp1 += r;
			else
				puntajeEmp2 += -r;
		}

		return puntajeEmp1 < puntajeEmp2? 1 : puntajeEmp1 > puntajeEmp2? -1 : 0; 
		//de mayor a menor;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CondicionNoTaxativa> getCondicionesNT() {
		return condicionesNT;
	}

	public void setCondicionesNT(List<CondicionNoTaxativa> condicionesNT) {
		if (condicionesNT != null)
			this.condicionesNT = condicionesNT;
	}

	public List<CondicionTaxativa> getCondicionesT() {
		return condicionesT;
	}

	public void setCondicionesT(List<CondicionTaxativa> condicionesT) {
		if (condicionesT != null)
			this.condicionesT = condicionesT;
	}

	public List<CondicionCombinada> getCondicionesComb() {
		return condicionesComb;
	}

	public void setCondicionesComb(List<CondicionCombinada> condicionesComb) {
		if (condicionesComb != null)
			this.condicionesComb = condicionesComb;
	}
}
