package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.IndicadorPeriodo;

public class RepoIndicadoresPeriodos extends RepoBD<IndicadorPeriodo>{
	
private static final RepoIndicadoresPeriodos instance = new RepoIndicadoresPeriodos();
	
	private RepoIndicadoresPeriodos() {
		this.entidad = IndicadorPeriodo.class;
	}
	
	public static RepoIndicadoresPeriodos getInstance() {
		return instance;
	}

	@Override
	protected List<Object> valoresDeBusqueda(IndicadorPeriodo indicador) {
		return Arrays.asList(indicador.getId());			
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodo> getIndicadoresConValor(){
		return entityManager().createQuery("from " + entidad.getSimpleName() + " e where e.valor != NULL").getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodo> getIndicadoresSinValor(){
		return entityManager().createQuery("from " + entidad.getSimpleName() + " e where e.valor = NULL").getResultList();
		
	}

}
