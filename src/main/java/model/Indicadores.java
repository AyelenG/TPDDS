package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import model.data.HandlerArchivoJSON;
import model.evaluador.operaciones.*;
import model.evaluador.terminales.*;
import model.repositories.Repositorio;

@Observable
public class Indicadores extends Repositorio<Indicador> {

	private static final String RUTA = "data/Indicadores.json";

	private final List<Indicador> indicadoresPredefinidos = Arrays.asList(
			new Indicador(
			"Ingreso Neto",
			"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]",
			new Suma
					(new TerminalCuenta("INGRESO NETO EN OPERACIONES CONTINUAS"),
					 new TerminalCuenta("INGRESO NETO EN OPERACIONES DISCONTINUAS")
					)
				),
			new Indicador(
			"Retorno sobre capital total",
			"(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]",
			new Division(
						new Resta(new TerminalIndicador("INGRESO NETO"),
								new TerminalCuenta("DIVIDENDOS")),
						new TerminalCuenta("CAPITAL TOTAL")
						)
					)
			);

	public Indicadores() {
		this.agregarElementos(indicadoresPredefinidos);
	}

	public boolean sonIguales(Indicador i1, Indicador i2) {
		return i1.getNombre().equals(i2.getNombre());
	}

	/*
	 * public void eliminarIndicadores(List<Indicador> indicadores) {
	 * indicadores.forEach(i -> this.eliminarIndicador(i)); }
	 * 
	 * public void eliminarIndicador(Indicador indicador) {
	 * this.indicadores.remove(indicador); }
	 */

	/* Carga desde archivo JSON */
	public void cargar() {
		this.agregarElementos(new HandlerArchivoJSON(RUTA).<Indicador>load(Indicador.class));
	}

	/* Guardado en archivo JSON */
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Indicador>save(this.getIndicadoresDeUsuario());
	}

	public List<Indicador> getIndicadoresPredefinidos() {
		return indicadoresPredefinidos;
	}

	public List<Indicador> getIndicadoresDeUsuario() {
		return this.getElementos().stream().filter(i -> !indicadoresPredefinidos.contains(i))
				.collect(Collectors.toList());
	}

}
