package model.condiciones;

import java.util.List;

import exceptions.NoSePuedeAplicarException;
import exceptions.NoSePuedeEvaluarException;
import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.repositories.RepoIndicadores;

public abstract class CondicionConfigurable {

	protected String nombre = "";

	protected Comparador comparador;
	protected String nombreIndicador;
	protected Integer cantidadAnios;

	public CondicionConfigurable() {
	}

	public CondicionConfigurable(String nombre) {
		this.setNombre(nombre);
	}

	public CondicionConfigurable(String nombre, Comparador comparador, String nombreIndicador, Integer cantidadAnios) {
		this.setNombre(nombre);
		this.setComparador(comparador);
		this.setNombreIndicador(nombreIndicador);
		this.setCantidadAnios(cantidadAnios);
	}
	
	protected Indicador obtenerIndicador(String nombreIndicador) {
		Indicador indicador = RepoIndicadores.getInstance().buscarElemento(new Indicador(nombreIndicador));
		if (indicador == null) {
			throw new NoSePuedeAplicarException("No se puede aplicar la metodologia '" + nombre
					+ "' por falta de indicador <" + nombreIndicador + ">.");
		}
		return indicador;
	}
	
	public boolean esValida(Empresa emp) {
		// obtengo indicador desde repositorio
		Indicador indicador = obtenerIndicador(nombreIndicador);

		// obtengo los periodos de los ultimos N anios de cada empresa
		List<Periodo> ultimosNAnios = emp.getUltimosNAnios(cantidadAnios);
		int cantPeriodosEmp = ultimosNAnios.size();
		
		if (cantPeriodosEmp != this.getCantidadAnios())
			return false;
		
		try{
		ultimosNAnios.forEach(p->indicador.evaluar(p));
		}
		catch(NoSePuedeEvaluarException e){
			return false;
		}
		return true;
	}
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public Integer getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(Integer cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}

}