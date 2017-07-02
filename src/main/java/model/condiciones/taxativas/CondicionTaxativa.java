package model.condiciones.taxativas;

import java.math.BigDecimal;

import model.Empresa;
import model.Indicador;
import model.condiciones.Comparador;

public class CondicionTaxativa {

	private String nombre;

	private Comparador comparador;
	private TipoCondicionTaxativa tipo;
	private Indicador indicador;
	private Integer cantidadAnios;
	private BigDecimal valorDeReferencia;

	public CondicionTaxativa() {
	}

	public CondicionTaxativa(String nombre) {
		this.setNombre(nombre);
	}

	public CondicionTaxativa(String nombre, Comparador comparador, TipoCondicionTaxativa tipo, Indicador indicador,
			Integer cantidadAnios, BigDecimal valorDeReferencia) {
		this.setNombre(nombre);
		this.setComparador(comparador);
		this.setTipo(tipo);
		this.setIndicador(indicador);
		this.setCantidadAnios(cantidadAnios);
		this.setValorDeReferencia(valorDeReferencia);
	}

	public boolean convieneInvertirEn(Empresa emp) {
		return tipo.aplicar(emp, this);

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public TipoCondicionTaxativa getTipo() {
		return tipo;
	}

	public void setTipo(TipoCondicionTaxativa tipo) {
		this.tipo = tipo;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Integer getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(Integer cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}

	public BigDecimal getValorDeReferencia() {
		return valorDeReferencia;
	}

	public void setValorDeReferencia(BigDecimal valorDeReferencia) {
		this.valorDeReferencia = valorDeReferencia;
	}

}
