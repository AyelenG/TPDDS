package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Constructor;
import model.Empresa;
import model.IndicadorPeriodoConValor;
import model.Periodo;

public class RepoIndicadoresPeriodosConValor extends RepoBD<IndicadorPeriodoConValor>{
	
private static final RepoIndicadoresPeriodosConValor instance = new RepoIndicadoresPeriodosConValor();
	
	private RepoIndicadoresPeriodosConValor() {
		this.entidad = IndicadorPeriodoConValor.class;
	}
	
	public static RepoIndicadoresPeriodosConValor getInstance() {
		return instance;
	}

	@Override
	protected List<Object> valoresDeBusqueda(IndicadorPeriodoConValor indicador) {
		return Arrays.asList(indicador.getId());			
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodoConValor> getIndicadores(Empresa empresa, Periodo periodo, long idUsuario){		
		List<Object[]> indicadores = entityManager().createNativeQuery("SELECT e.id, e.valor, e.indicador_id, e.periodo_id from indicadorperiodoconvalor e JOIN indicador i ON (e.indicador_id = i.id) JOIN periodo p ON (e.periodo_id = p.id AND p.id = " + periodo.getId() + ") where i.user_id = " + idUsuario + " AND p.empr_id = " + empresa.getId()).getResultList();
		Constructor constructor = new Constructor();
		List<IndicadorPeriodoConValor> indicadoresConValor = constructor.construirIndicadoresConValor(indicadores);
		return indicadoresConValor;
	}

}
