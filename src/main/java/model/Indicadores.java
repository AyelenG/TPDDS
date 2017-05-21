package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.uqbar.commons.utils.Observable;

import model.repositories.Repositorios;

@Observable
public class Indicadores {

	private List<Indicador> indicadores = new LinkedList<>();
	private static final String RUTA = "data/indicadores.json";

	public void agregarIndicadores(List<Indicador> indicadores) {
		for (Indicador indicador : indicadores)
			if (!existeIndicador(indicador))
				this.agregarIndicador(indicador);
	}
	
	public static void cargar() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Repositorios
				.indicadoresPredefinidos
				.agregarIndicadores(mapper.readValue(new File(RUTA),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, Indicador.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
	public static void actualizarJSON() {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(RUTA),
						Repositorios.indicadoresPredefinidos.getIndicadores());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public int indexOf(Indicador indicador) {
		return indicadores.indexOf(indicador);
	}

	public int size() {
		return indicadores.size();
	}

}
