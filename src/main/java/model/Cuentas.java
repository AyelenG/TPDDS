package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.data.LoaderArchivoJSON;

@Observable
public class Cuentas {

	private static final String RUTA = "data/CuentasPredeterminadas.json";

	private List<Cuenta> cuentas = new LinkedList<>();

	public void agregarCuentas(List<Cuenta> cuentas) {
		for (Cuenta cuenta : cuentas)
			if (!existeCuenta(cuenta))
				this.agregarCuenta(cuenta);
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public Cuenta buscarCuenta(Cuenta cuenta) {
		return cuentas.stream().filter(_cuenta -> _cuenta.esIgual(cuenta)).findFirst().orElse(null);
	}

	public boolean existeCuenta(Cuenta cuenta) {
		return cuentas.stream().anyMatch(_cuenta -> _cuenta.esIgual(cuenta));
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta get(int i) {
		return cuentas.get(i);
	}

	public int indexOf(Cuenta cuenta) {
		return cuentas.indexOf(cuenta);
	}

	public int size() {
		return cuentas.size();
	}

	public void cargar() {
		this.agregarCuentas(new LoaderArchivoJSON(RUTA).loadCuentas());

		/* IMPRESION POR CONSOLA PARA CONTROL */
		/*
		 * for ( Object cuenta :
		 * Repositorios.cuentasPredeterminadas.getCuentas())
		 * System.out.println(((Cuenta) cuenta).getNombre());
		 */
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
				this.agregarCuentas(periodo.getCuentas());
	}

	/* Del Repositorio al Archivo JSON */
	public void guardar() {
		new LoaderArchivoJSON(RUTA).saveCuentas(this.getCuentas());

	}

}
