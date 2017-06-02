package model;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.data.HandlerArchivoJSON;
import model.repositories.Repositorio;

@Observable
public class Cuentas extends Repositorio<Cuenta> {

	private static final String RUTA = "data/CuentasPredeterminadas.json";

	public boolean sonIguales(Cuenta c1, Cuenta c2) {
		return c1.getNombre().equals(c2.getNombre());
	}

	/* Carga desde archivo JSON */
	public void cargar() {
		this.agregarElementos(new HandlerArchivoJSON(RUTA).<Cuenta>load(Cuenta.class));

		/* IMPRESION POR CONSOLA PARA CONTROL */
		/*
		 * for ( Object cuenta :
		 * Repositorios.cuentasPredeterminadas.getCuentas())
		 * System.out.println(((Cuenta) cuenta).getNombre());
		 */
	}

	/* Guardado en archivo JSON*/
	public void guardar() {
		new HandlerArchivoJSON(RUTA).<Cuenta>save(this.getElementos());
	}

	/**
	 * Desde una Lista de Empresas toma todas las cuentas e ingresa solo las que
	 * no estan repetidas en el Repositorio y guarda el archivo en disco
	 * 
	 * @param empresas
	 */

	/* Agregar al Repositorio las cuentas distintas */
	public void agregarDesdeEmpresas(List<Empresa> empresas) {

		for (Empresa empresa : empresas)
			for (Periodo periodo : empresa.getPeriodos())
				this.agregarElementos(periodo.getCuentas());
	}

}
