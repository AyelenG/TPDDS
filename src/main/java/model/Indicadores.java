package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
@Observable
public class Indicadores {

	private List<Indicador> indicadores = new LinkedList<>();

	public void agregarIndicadores(List<Indicador> indicadores) {
		for (Indicador indicador : indicadores)
			if (!existeIndicador(indicador))
				this.agregarIndicador(indicador);
	}

	public void agregarIndicador(Indicador indicador) {
		this.indicadores.add(indicador);
	}

	public Indicador buscarIndicador(Indicador indicador) {
		return indicadores.stream().filter(_indicador -> _indicador.esIgual(indicador)).findFirst().orElse(null);
	}

	public boolean existeIndicador(Indicador indicador) {
		return indicadores.stream().anyMatch(_indicador -> _indicador.esIgual(indicador));
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador get(int i) {
		return indicadores.get(i);
	}

	public int indexOf(Cuenta cuenta) {
		return indicadores.indexOf(cuenta);
	}

	public int size() {
		return indicadores.size();
	}


}
