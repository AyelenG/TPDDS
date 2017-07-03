package model.condiciones.taxativas;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import model.Empresa;
import model.Indicador;
import model.Periodo;
import model.data.deserializadores.TipoCondicionDeserializer;

@JsonDeserialize(using = TipoCondicionDeserializer.class)
public interface TipoCondicionTaxativa {
	
	public boolean aplicar(Empresa emp, CondicionTaxativaConfigurable condicion, Indicador indicador);

	default public BigDecimal sumatoria(Indicador indicador, List<Periodo> periodos){
		return periodos.stream().map(p -> indicador.evaluar(p)).reduce(BigDecimal.ZERO, BigDecimal::add);
	};
}
