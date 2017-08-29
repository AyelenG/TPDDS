package model.repositories;

import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.data.HandlerArchivoJSON;

public class RepoCuentas extends Repositorio<Cuenta> {

	private static final RepoCuentas instance = new RepoCuentas();

	private final String RUTA = "data/CuentasPredeterminadas.json";

	private RepoCuentas() {
	}

	public static RepoCuentas getInstance() {
		return instance;
	}

	@Override
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

	/* Guardado en archivo JSON */
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
			for (Periodo periodo : empresa.getPeriodos()){
				List<Cuenta> cuentas = periodo.getCuentas().stream()
														.map(c->c.getCuenta())
														.collect(Collectors.toList());
				this.agregarElementos(cuentas);
			}
	}

}
