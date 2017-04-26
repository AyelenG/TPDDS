package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import model.exceptions.ErrorCargaException;
import model.exceptions.RutaIncorrectaException;
import model.repositories.EmpresasRepository;
import model.repositories.Repositorios;

public class DataLoader {

	private static final EmpresasRepository empresas = Repositorios.empresasRepo;

	private static enum Registro {
		EMPRESA(0), CUENTA(1), PERIODO(2), VALOR(3);

		private final int index;

		private Registro(int index) {
			this.index = index;
		}

		private final int getIndex() {
			return index;
		}
	}

	public static void cargarDatosDesdeArchivo(String archivo) {

		String linea;
		FileReader f;

		try {
			f = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			throw new RutaIncorrectaException();
		}
		BufferedReader b = new BufferedReader(f);
		try {
			while ((linea = b.readLine()) != null) {
				String[] campos = linea.split(",");
				Empresa empresa = obtenerEmpresaDesdeLinea(campos);
				crearOModificarCuentaDesdeLinea(empresa, campos);
			}
			b.close();
		} catch (IOException e) {
			throw new ErrorCargaException();
		}
	}

	private static void crearOModificarCuentaDesdeLinea(Empresa empresa, String[] campos) {
		String nombreCuenta = campos[Registro.CUENTA.getIndex()].trim();
		String periodoString = campos[Registro.PERIODO.getIndex()].trim();
		String valorString = campos[Registro.VALOR.getIndex()].trim();
		Integer año;
		BigDecimal valor;
		try {
			año = new Integer(periodoString);
			valor = new BigDecimal(valorString);
		} catch (NumberFormatException e) {
			return;
		}
		Periodo periodo = empresa.buscarPeriodo(año);
		if(periodo == null){
			periodo = new Periodo(año);
			empresa.agregarPeriodo(periodo);
		}
		Cuenta cuenta = periodo.buscarCuenta(nombreCuenta);
		if(cuenta == null){
			cuenta = new Cuenta(nombreCuenta);
			periodo.agregarCuenta(cuenta);
		}
		cuenta.setValor(valor);
	}

	private static Empresa obtenerEmpresaDesdeLinea(String[] campos) {
		String nombreEmpresa = campos[Registro.EMPRESA.getIndex()].trim();
		Empresa empresa = empresas.buscarEmpresa(nombreEmpresa);
		if(empresa == null){
			empresa = new Empresa(nombreEmpresa);
			empresas.agregarEmpresa(empresa);
		}
		return empresa;
	}
}
