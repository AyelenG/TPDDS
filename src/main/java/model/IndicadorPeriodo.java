package model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import model.repositories.RepoCuentas;

@Entity
public class IndicadorPeriodo {
	
	@Id
	@GeneratedValue
	@Getter private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	Periodo periodo;
	
	@ManyToOne (fetch = FetchType.LAZY)	
	Indicador indicador;

	@Column
	private BigDecimal valor;
	
	public IndicadorPeriodo(){
		
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
		
	
}
