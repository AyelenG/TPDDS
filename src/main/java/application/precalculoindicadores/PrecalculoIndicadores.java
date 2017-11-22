package application.precalculoindicadores;

import java.math.BigDecimal;

import java.util.LinkedList;
import java.util.List;

import exceptions.NoSePuedeEvaluarException;
import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.Usuario;
import model.precalculo.IndicadorPeriodoConValor;
import model.precalculo.IndicadorPeriodoSinValor;
import model.repositories.RepoEmpresas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoIndicadoresPeriodosConValor;
import model.repositories.RepoIndicadoresPeriodosSinValor;
import model.repositories.RepoUsuarios;

public class PrecalculoIndicadores{
	
	public void precalcularTodos(){
		BigDecimal valor;
		//borra toda la info de las tablas
		RepoIndicadoresPeriodosConValor.getInstance().clean();
		RepoIndicadoresPeriodosSinValor.getInstance().clean();
		List<IndicadorPeriodoConValor> indicadoresConValor = new LinkedList<>();
		List<IndicadorPeriodoSinValor> indicadoresSinValor = new LinkedList<>();
		
		List<Usuario> usuarios = RepoUsuarios.getInstance().findAll();
		for (Usuario usuario : usuarios) {
			
			List<Indicador> indicadores = RepoIndicadores.getInstance().findAllBy("user",usuario.getId());
			List<Empresa> empresas = RepoEmpresas.getInstance().findAll();
			
			for (Empresa empresa : empresas) {
			
				List<Periodo> periodos = empresa.getPeriodos();
				
				for (Periodo periodo : periodos) {	
				
					for (Indicador indicador : indicadores) {
						
						IndicadorPeriodoConValor indicadorConValor = new IndicadorPeriodoConValor();
						IndicadorPeriodoSinValor indicadorSinValor = new IndicadorPeriodoSinValor();
						
						try {
							valor = indicador.evaluar(periodo);	
							indicadorConValor.setIndicador(indicador);
							indicadorConValor.setPeriodo(periodo);
							indicadorConValor.setValor(valor);
							indicadoresConValor.add(indicadorConValor);
							
						} catch (NoSePuedeEvaluarException e) {
							
							indicadorSinValor.setMensaje(e.getMensaje());
							indicadorSinValor.setIndicador(indicador);
							indicadorSinValor.setPeriodo(periodo);
							indicadoresSinValor.add(indicadorSinValor);
						}
						
					}
				}
			}		
		}
		
		RepoIndicadoresPeriodosConValor.getInstance().insertarVarios(indicadoresConValor);
		RepoIndicadoresPeriodosSinValor.getInstance().insertarVarios(indicadoresSinValor);
	}
	
	public void precalcularNuevo(Indicador indicador){
		BigDecimal valor;
		List<Empresa> empresas = RepoEmpresas.getInstance().findAll();
		List<IndicadorPeriodoConValor> indicadoresConValor = new LinkedList<>();
		List<IndicadorPeriodoSinValor> indicadoresSinValor = new LinkedList<>();
		
		for (Empresa empresa : empresas) {
			List<Periodo> periodos = empresa.getPeriodos();
			
			for (Periodo periodo : periodos) {
					
					IndicadorPeriodoConValor indicadorConValor = new IndicadorPeriodoConValor();
					IndicadorPeriodoSinValor indicadorSinValor = new IndicadorPeriodoSinValor();
					
					try{
						valor = indicador.evaluar(periodo);	
						indicadorConValor.setIndicador(indicador);
						indicadorConValor.setPeriodo(periodo);
						indicadorConValor.setValor(valor);
						indicadoresConValor.add(indicadorConValor);
					}
					catch(NoSePuedeEvaluarException e){
						indicadorSinValor.setMensaje(e.getMensaje());
						indicadorSinValor.setIndicador(indicador);
						indicadorSinValor.setPeriodo(periodo);
						indicadoresSinValor.add(indicadorSinValor);
					}
			}
		}
		
		RepoIndicadoresPeriodosConValor.getInstance().insertarVarios(indicadoresConValor);
		RepoIndicadoresPeriodosSinValor.getInstance().insertarVarios(indicadoresSinValor);
	}
	
}
