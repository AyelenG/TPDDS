package model.condiciones.combinadas;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;

@JsonDeserialize(as = CondicionCombinadaCompuesta.class)
public class CondicionCombinadaCompuesta implements CondicionCombinada {

	private String nombre = "";

	private CondicionNoTaxativa condNT;
	private CondicionTaxativa condT;

	public CondicionCombinadaCompuesta() {
	}

	public CondicionCombinadaCompuesta(String nombre) {
		this.setNombre(nombre);
	}

	public CondicionCombinadaCompuesta(String nombre, CondicionNoTaxativa condNT, CondicionTaxativa condT) {
		this.setNombre(nombre);
		this.setCondNT(condNT);
		this.setCondT(condT);
	}

	@Override
	public boolean convieneInvertirEn(Empresa emp) {
		return condT.convieneInvertirEn(emp);

	}

	@Override
	public int comparar(Empresa emp1, Empresa emp2) {
		return condNT.comparar(emp1, emp2);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CondicionNoTaxativa getCondNT() {
		return condNT;
	}

	public void setCondNT(CondicionNoTaxativa condNT) {
		this.condNT = condNT;
	}

	public CondicionTaxativa getCondT() {
		return condT;
	}

	public void setCondT(CondicionTaxativa condT) {
		this.condT = condT;
	}

	@Override
	public boolean esValida(Empresa emp) {
		return condNT.esValida(emp) && condT.esValida(emp);
	}

}
