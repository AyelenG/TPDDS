package model.precalculo;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import model.Indicador;
import model.Periodo;

@Entity
public class IndicadorPeriodoConValor extends IndicadorPrecalculado{
	
	@Getter @Setter private BigDecimal valor;
	
	public IndicadorPeriodoConValor(){
		
	}
	
	public IndicadorPeriodoConValor(Periodo periodo, Indicador indicador) {
		super(periodo,indicador);
	}
}
