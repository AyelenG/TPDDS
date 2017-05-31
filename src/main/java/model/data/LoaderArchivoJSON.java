package model.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Cuenta;
import model.Empresa;
import model.Indicador;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import exceptions.ErrorCargaException;
import exceptions.ErrorGuardadoExcepcion;

public class LoaderArchivoJSON extends LoaderArchivo {

	public LoaderArchivoJSON(String ruta) {
		super(ruta);
	}

	@Override
	protected List<Empresa> parse(FileReader archivo) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Empresa> empresas = mapper.readValue(new BufferedReader(archivo),
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, Empresa.class));

		return empresas;
	}

	public List<Indicador> loadIndicadores() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, Indicador.class));
		} catch (IOException e) {
			throw new ErrorCargaException(e);
		}
	}

	public List<Cuenta> loadCuentas() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, Cuenta.class));
		} catch (IOException e) {
			throw new ErrorCargaException(e);
		}
	}

	public void saveIndicadores(List<Indicador> indicadores) {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(ruta), indicadores);
		} catch (IOException e) {
			throw new ErrorGuardadoExcepcion(e);
		}

	}

	public void saveCuentas(List<Cuenta> cuentas) {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(ruta), cuentas);
		} catch (IOException e) {
			throw new ErrorGuardadoExcepcion(e);
		}

	}

}
