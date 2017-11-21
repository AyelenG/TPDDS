package model.repositories;

import java.util.Arrays;
import java.util.List;

import model.Empresa;
import model.IndicadorPeriodoSinValor;
import model.Periodo;

public class RepoIndicadoresPeriodosSinValor extends RepoBD<IndicadorPeriodoSinValor>{
	
private static final RepoIndicadoresPeriodosSinValor instance = new RepoIndicadoresPeriodosSinValor();
	
	private RepoIndicadoresPeriodosSinValor() {
		this.entidad = IndicadorPeriodoSinValor.class;
	}
	
	public static RepoIndicadoresPeriodosSinValor getInstance() {
		return instance;
	}

	@Override
	protected List<Object> valoresDeBusqueda(IndicadorPeriodoSinValor indicador) {
		return Arrays.asList(indicador.getId());			
	}
	
	@SuppressWarnings("unchecked")
	public List<IndicadorPeriodoSinValor> getIndicadores(Empresa empresa, Periodo periodo, long idUsuario){
		return (List<IndicadorPeriodoSinValor>) entityManager().createNativeQuery("SELECT e.id, e.mensaje, e.indicador_id, e.periodo_id from indicadorperiodosinvalor e JOIN indicador i ON (e.indicador_id = i.id) JOIN periodo p ON (e.periodo_id = p.id AND p.id = " + periodo.getId() + ") where i.user_id = " + idUsuario + " AND p.empr_id = " + empresa.getId()).getResultList();
		 
	}
}
