package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Periodo;

public class RepoPeriodos extends RepoBD<Periodo> {
	
private static final RepoPeriodos instance = new RepoPeriodos();
	
	private RepoPeriodos() {
		this.entidad = Periodo.class;
	}
	
	public static RepoPeriodos getInstance() {
		return instance;
	}

	@Override
	protected List<Object> valoresDeBusqueda(Periodo periodo) {
		return Arrays.asList(periodo.getId());		
	}
	

}
