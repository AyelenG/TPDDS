package model.condiciones.taxativas;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import model.condiciones.Comparador;
import model.condiciones.CondicionConfigurable;
import model.data.converters.TipoCondicionTaxativaConverter;

@Entity
@JsonDeserialize(as = CondicionTaxativaConfigurable.class)
@JsonIgnoreProperties({ "changeSupport" })
public class CondicionTaxativaConfigurable extends CondicionConfigurable {

	@Convert(converter = TipoCondicionTaxativaConverter.class)
	@Column(length = 50)
	@Getter @Setter private TipoCondicionTaxativa tipoTaxatividad;
	
	@Getter @Setter private BigDecimal valorDeReferencia;

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
	public boolean convieneInvertirEn(Empresa emp, Usuario user) {
		// obtengo indicador desde repositorio
		Indicador indicador = obtenerIndicador(nombreIndicador,user);
		return tipoTaxatividad.aplicar(emp, this, indicador);

	}
	
	@Override
	public int comparar(Empresa emp1, Empresa emp2, Usuario user) {
		//devuelve el elemento neutro para no influir
		return 0;
	}
}
