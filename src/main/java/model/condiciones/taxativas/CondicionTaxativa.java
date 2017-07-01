package model.condiciones.taxativas;

import java.math.BigDecimal;

import model.Empresa;
import model.Indicador;
import model.condiciones.Comparador;

public class CondicionTaxativa {
	
	private String nombre;
	
	private Comparador comparador;
	private Indicador indicador;
	
	private Integer cantidadAnios;
	private TipoCondicionTaxativa tipo;
	
	private BigDecimal valorDeReferencia;

	
	public CondicionTaxativa(String nombre, Comparador comparador, Indicador indicador, Integer cantidadAnios,
			TipoCondicionTaxativa tipo, BigDecimal valorDeReferencia) {
		this.nombre = nombre;
		this.comparador = comparador;
		this.indicador = indicador;
		this.cantidadAnios = cantidadAnios;
		this.tipo = tipo;
		this.valorDeReferencia = valorDeReferencia;
	}


	public boolean convieneInvertirEn(Empresa emp){
		return tipo.comparar(emp, this);
		
	}
}
