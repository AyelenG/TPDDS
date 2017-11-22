package model.precalculo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import model.Indicador;
import model.Periodo;

@Entity
@Table(
		uniqueConstraints = {@UniqueConstraint(columnNames={"peri_id", "ind_id"})}
		)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class IndicadorPrecalculado {

	@Id
	@GeneratedValue
	@Getter private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "peri_id", nullable = false)
	@Getter @Setter Periodo periodo;
	
	@ManyToOne (fetch = FetchType.LAZY)	
	@JoinColumn(name = "ind_id", nullable = false)
	@Getter @Setter Indicador indicador;
	
	public IndicadorPrecalculado(){
		
	}
	
	public IndicadorPrecalculado(Periodo p, Indicador i){
		this.setPeriodo(p);
		this.setIndicador(i);
	}

}
