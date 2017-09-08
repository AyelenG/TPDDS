package model.condiciones.taxativas;

import java.math.BigDecimal;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Indicador;
import model.condiciones.Comparador;
import model.condiciones.CondicionConfigurable;

@Entity
@Observable
@JsonDeserialize(as = CondicionTaxativaConfigurable.class)
@JsonIgnoreProperties({ "changeSupport" })
public class CondicionTaxativaConfigurable extends CondicionConfigurable {

	@Convert(converter = TipoCondicionTaxativaConverter.class)
	private TipoCondicionTaxativa tipoTaxatividad;
	
	private BigDecimal valorDeReferencia;

	public CondicionTaxativaConfigurable() {
	}

	public CondicionTaxativaConfigurable(String nombre) {
		super(nombre);
	}

	public CondicionTaxativaConfigurable(String nombre, Comparador comparador, TipoCondicionTaxativa tipo,
			String nombreIndicador, Integer cantidadAnios, BigDecimal valorDeReferencia) {
		super(nombre, comparador, nombreIndicador, cantidadAnios);
		this.setTipoTaxatividad(tipo);
		this.setValorDeReferencia(valorDeReferencia);
	}

	@Override
	public boolean convieneInvertirEn(Empresa emp) {
		// obtengo indicador desde repositorio
		Indicador indicador = obtenerIndicador(nombreIndicador);
		return tipoTaxatividad.aplicar(emp, this, indicador);

	}
	
	@Override
	public int comparar(Empresa emp1, Empresa emp2) {
		//devuelve el elemento neutro para no influir
		return 0;
	}

	public TipoCondicionTaxativa getTipoTaxatividad() {
		return tipoTaxatividad;
	}

	public void setTipoTaxatividad(TipoCondicionTaxativa tipoTaxatividad) {
		this.tipoTaxatividad = tipoTaxatividad;
	}

	public BigDecimal getValorDeReferencia() {
		return valorDeReferencia;
	}

	public void setValorDeReferencia(BigDecimal valorDeReferencia) {
		this.valorDeReferencia = valorDeReferencia;
	}

}
