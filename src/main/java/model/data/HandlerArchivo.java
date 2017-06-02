package model.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import exceptions.ArchivoConErroresException;
import exceptions.ErrorCargaException;
import exceptions.RutaIncorrectaException;
import model.Empresa;


public abstract class HandlerArchivo {
	protected String ruta;

	public HandlerArchivo(String ruta) {
		this.ruta = ruta;
	}

	public List<Empresa> loadEmpresas() {
		FileReader archivo;
		try {
			archivo = new FileReader(this.ruta);
		} catch (FileNotFoundException e) {
			throw new RutaIncorrectaException(e);
		}
		try {
			List<Empresa> empresas = this.parseEmpresas(archivo);
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

	protected abstract List<Empresa> parseEmpresas(FileReader archivo) throws IOException;
}
