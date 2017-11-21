package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class IndicadorPeriodoSinValor {
	
	@Id
	@GeneratedValue
	@Getter private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	Periodo periodo;
	
	@ManyToOne (fetch = FetchType.LAZY)	
	Indicador indicador;

	@Column(length=250)
	private String mensaje;
	
	public IndicadorPeriodoSinValor(){
		
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
		
	
}
