package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import model.condiciones.Condicion;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Metodologia {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 50, nullable=false, unique=true)
	private String nombre = "";

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "meto_id", nullable = false)
	private List<Condicion> condiciones = new LinkedList<>();
	
	public Metodologia() {
	}

	public Metodologia(String nombre) {
		this.setNombre(nombre);
	}


	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.setNombre(nombre);
		this.setCondiciones(condiciones);
	}

	/**
	 * Retorna las listas validas para aplicar la metodologia
	 * 
	 * @param empresas
	 *            - lista de empresas de entrada
	 * @return lista de empresas de salida
	 */
	public List<Empresa> obtenerValidas(List<Empresa> empresas) {
		List<Empresa> empresasValidas = new LinkedList<>();

		empresasValidas.addAll(empresas.stream()
				.filter(e -> condiciones.stream().allMatch(c -> c.esValida(e))).collect(Collectors.toList()));
		return empresasValidas;
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

		// uno todas las condiciones, las taxativas y combinadas 
		//tienen el metodo convieneInvertir con logica, 
		//las no taxativas simplemente devuelven true para no afectar

		empresasFiltradas = empresasFiltradas.stream()
				.filter(emp -> condiciones.stream().allMatch(cond -> cond.convieneInvertirEn(emp)))
				.collect(Collectors.toList());
		return empresasFiltradas;
	}

	public List<Empresa> obtenerOrdenadas(List<Empresa> empresas) {
		// ordenar segun metodo comparar y devolver la lista resultante
		return empresas.stream().sorted(this::comparar).collect(Collectors.toList());
	}

	private int comparar(Empresa emp1, Empresa emp2) {
		// compara por todas las condiciones, las no taxativas y combinadas devuelven
		// 1 o -1 multiplicado por el peso de la condicion segun que empresa gane
		// o 0 si son iguales
		// las taxativas no influyen en la comparacion, devuelven siempre 0
		int puntajeEmp1 = 0;
		int puntajeEmp2 = 0;
		int r;

		for (Condicion cond : condiciones) {
			r = cond.comparar(emp1, emp2);
			if (r >= 0)
				puntajeEmp1 += r;
			else
				puntajeEmp2 += -r;
		}

		return puntajeEmp1 < puntajeEmp2 ? 1 : puntajeEmp1 > puntajeEmp2 ? -1 : 0;
		// de mayor a menor;
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

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}
