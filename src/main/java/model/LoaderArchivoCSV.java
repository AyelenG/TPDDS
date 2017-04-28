package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import model.exceptions.ArchivoConErroresException;

public class LoaderArchivoCSV extends LoaderArchivo {

	public LoaderArchivoCSV(String ruta) {
		super(ruta);
	}

	List<Empresa> empresas = new LinkedList<>();

	private static enum Registro {
		SYMBOL(0), EMPRESA(1), CUENTA(2), PERIODO(3), VALOR(4);

		private final int index;

		private Registro(int index) {
			this.index = index;
		}

		private final int getIndex() {
			return index;
		}

	}
	private final int CANT_REGISTROS = 5;
	
	@Override
	protected List<Empresa> parse(FileReader archivo) throws IOException {

		String linea;
		BufferedReader br = new BufferedReader(archivo);
	
		while ((linea = br.readLine()) != null) {
			Empresa empresa = obtenerEmpresaDesdeLinea(linea);
			empresas.add(empresa);
		}
		return empresas;

	}

	private Empresa obtenerEmpresaDesdeLinea(String linea) {
		String[] campos = linea.split(",");
		if( campos.length != this.CANT_REGISTROS){
			throw new ArchivoConErroresException(null);
		}
			
		String symbolEmpresa = campos[Registro.SYMBOL.getIndex()].trim();
		String nombreEmpresa = campos[Registro.EMPRESA.getIndex()].trim();
		String nombreCuenta = campos[Registro.CUENTA.getIndex()].trim();
		String periodoString = campos[Registro.PERIODO.getIndex()].trim();
		String valorString = campos[Registro.VALOR.getIndex()].trim();

		Empresa empresa = new Empresa(symbolEmpresa, nombreEmpresa);
		Periodo periodo = obtenerPeriodo(nombreCuenta, periodoString, valorString);
		empresa.agregarPeriodo(periodo);
		return empresa;
	}

	private Periodo obtenerPeriodo(String nombreCuenta, String periodoString, String valorString) {
		Integer anio;
		BigDecimal valor;
		try {
			anio = new Integer(periodoString);
			valor = new BigDecimal(valorString);
		} catch (NumberFormatException e) {
			throw new ArchivoConErroresException(e);
		}
		Periodo periodo = new Periodo(anio);
		periodo.agregarCuenta(new Cuenta(nombreCuenta, valor));
		return periodo;
	}

}