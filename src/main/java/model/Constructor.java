package model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import model.repositories.RepoIndicadores;
import model.repositories.RepoPeriodos;

public class Constructor {
	
	public String[] asStrings(Object[] array) {
	    String[] stringArray = new String[array.length];
	    for (int i = 0; i < array.length; i++)
	        stringArray[i] = String.valueOf(array[i]);
	    return stringArray;
	}

	public List<IndicadorPeriodoConValor> construirIndicadoresConValor(List<Object[]> indicadores) {
		List<IndicadorPeriodoConValor> indicadoresConValor = new LinkedList<>();
		for (int i = 0; i < indicadores.size(); i++) {
			
			String[] elemento = this.asStrings(indicadores.get(i));
			long idIndicador = Long.valueOf(elemento[0]).longValue();
			BigDecimal valorIndicador = new BigDecimal(elemento[1]);
			Indicador indicador = RepoIndicadores.getInstance().get(Long.valueOf(elemento[2]));
			Periodo periodo = RepoPeriodos.getInstance().get(Long.valueOf(elemento[3]));
			IndicadorPeriodoConValor indicadorConValor = new IndicadorPeriodoConValor();
			
			indicadorConValor.setId(idIndicador);
			indicadorConValor.setValor(valorIndicador);
			indicadorConValor.setIndicador(indicador);
			indicadorConValor.setPeriodo(periodo);
			indicadoresConValor.add(indicadorConValor);

		}
		return indicadoresConValor;
	}
	
	public List<IndicadorPeriodoSinValor> construirIndicadoresSinValor(List<Object[]> indicadores) {
		List<IndicadorPeriodoSinValor> indicadoresSinValor = new LinkedList<>();
		for (int i = 0; i < indicadores.size(); i++) {
			String[] elemento = this.asStrings(indicadores.get(i));
			
			long idIndicador = Long.valueOf(elemento[0]).longValue();
			String mensaje = elemento[1];
			Indicador indicador = RepoIndicadores.getInstance().get(Long.valueOf(elemento[2]));
			Periodo periodo = RepoPeriodos.getInstance().get(Long.valueOf(elemento[3]));
			IndicadorPeriodoSinValor indicadorSinValor = new IndicadorPeriodoSinValor();
			
			indicadorSinValor.setId(idIndicador);
			indicadorSinValor.setMensaje(mensaje);
			indicadorSinValor.setIndicador(indicador);
			indicadorSinValor.setPeriodo(periodo);
			indicadoresSinValor.add(indicadorSinValor);

		}
		return indicadoresSinValor;
	}

}
