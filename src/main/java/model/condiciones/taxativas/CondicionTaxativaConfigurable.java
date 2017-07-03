package model.condiciones.taxativas;

import java.math.BigDecimal;

import exceptions.NoSePuedeAplicarException;
import model.Empresa;
import model.Indicador;
import model.condiciones.Comparador;
import model.repositories.RepoIndicadores;

public class CondicionTaxativaConfigurable implements CondicionTaxativa {

	private String nombre;

	private Comparador comparador;
	private TipoCondicionTaxativa tipo;
	private String nombreIndicador;
	private Integer cantidadAnios;
	private BigDecimal valorDeReferencia;

	public CondicionTaxativaConfigurable() {
	}

	public CondicionTaxativaConfigurable(String nombre) {
		this.setNombre(nombre);
	}

	public CondicionTaxativaConfigurable(String nombre, Comparador comparador, TipoCondicionTaxativa tipo,
			String indicador, Integer cantidadAnios, BigDecimal valorDeReferencia) {
		this.setNombre(nombre);
		this.setComparador(comparador);
		this.setTipo(tipo);
		this.setIndicador(indicador);
		this.setCantidadAnios(cantidadAnios);
		this.setValorDeReferencia(valorDeReferencia);
	}

	@Override
	public boolean convieneInvertirEn(Empresa emp) {
		// obtengo indicador desde repositorio
		Indicador indicador = RepoIndicadores.getInstance().buscarElemento(new Indicador(nombreIndicador));
		if (indicador == null) {
			throw new NoSePuedeAplicarException("No se puede aplicar la metodologia '" + nombre
					+ "' por falta de indicador <" + nombreIndicador + ">.");
		}

		return tipo.aplicar(emp, this, indicador);

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

	public String getIndicador() {
		return nombreIndicador;
	}

	public void setIndicador(String indicador) {
		this.nombreIndicador = indicador;
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
