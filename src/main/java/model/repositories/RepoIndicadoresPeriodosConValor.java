package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.precalculo.IndicadorPeriodoConValor;
import model.Periodo;
import model.Usuario;

public class RepoIndicadoresPeriodosConValor extends RepoBD<IndicadorPeriodoConValor>{
	
private static final RepoIndicadoresPeriodosConValor instance = new RepoIndicadoresPeriodosConValor();
	
	private RepoIndicadoresPeriodosConValor() {
		this.entidad = IndicadorPeriodoConValor.class;
	}
	
	public static RepoIndicadoresPeriodosConValor getInstance() {
		return instance;
	}

	@Override
	protected List<String> camposDeBusqueda() {
		return Arrays.asList("indicador","periodo");
	}
	
	@Override
	protected List<Object> valoresDeBusqueda(IndicadorPeriodoConValor indicador) {
		return Arrays.asList(indicador.getIndicador(),indicador.getPeriodo());			
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodoConValor> findAllByUserYPeriodo(Periodo periodo, Usuario user){		
		
		return this.entityManager().createQuery
				("from " + getEntidad().getSimpleName() + " e "
						 + "where e.periodo = " + periodo.getId()
						 + " and e.indicador.user = " + user.getId()
						 + " order by e.id asc").getResultList();
	}
}
