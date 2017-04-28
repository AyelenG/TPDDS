package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.exceptions.ArchivoConErroresException;
import model.exceptions.ErrorCargaException;
import model.exceptions.RutaIncorrectaException;

public abstract class LoaderArchivo {
	private String ruta;

	public LoaderArchivo(String ruta) {
		this.ruta = ruta;
	}

	public List<Empresa> getEmpresas() {
		FileReader archivo;
		try {
			archivo = new FileReader(this.ruta);
		} catch (FileNotFoundException e) {
			throw new RutaIncorrectaException(e);
		}
		try {
			List<Empresa> empresas = this.parse(archivo);
			return empresas;
		} catch (ArchivoConErroresException e) {
			throw e;
		} catch (Exception e) {
			throw new ErrorCargaException(e);
		} finally {
			try {
				archivo.close();
			} catch (IOException e) {
				throw new ErrorCargaException(e);
			}
		}
	}

	protected abstract List<Empresa> parse(FileReader archivo) throws IOException, ParseException;
}
