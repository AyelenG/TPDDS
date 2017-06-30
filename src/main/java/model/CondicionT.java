package model;

import java.math.BigDecimal;

public class CondicionT {
	
	private String nombre;
	private Integer cantidadAnios;
	private Indicador indicador;
	private BigDecimal valorDeReferencia;
	private TipoDeCalculo tipo;
	
	public boolean esBuenoInvertir(Empresa emp){
		return false;
		
	}
}
