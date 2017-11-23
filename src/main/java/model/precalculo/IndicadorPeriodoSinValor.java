package model.precalculo;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import model.Indicador;
import model.Periodo;

@Entity
public class IndicadorPeriodoSinValor extends IndicadorPrecalculado {
	
	@Column(length=250)
	@Getter @Setter private String mensaje;
	
	public IndicadorPeriodoSinValor(){
		
	}
	
	public IndicadorPeriodoSinValor(Periodo periodo, Indicador indicador) {
		super(periodo,indicador);
	}
}
