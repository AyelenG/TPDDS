package model.precalculo;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class IndicadorPeriodoSinValor extends IndicadorPrecalculado {
	
	@Column(length=250)
	@Getter @Setter private String mensaje;
	
	public IndicadorPeriodoSinValor(){
		
	}
}
