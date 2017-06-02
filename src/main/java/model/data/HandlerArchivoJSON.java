package model.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Empresa;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import exceptions.ErrorCargaException;
import exceptions.ErrorGuardadoExcepcion;

public class HandlerArchivoJSON extends HandlerArchivo {

	public HandlerArchivoJSON(String ruta) {
		super(ruta);
	}

	@Override
	protected List<Empresa> parseEmpresas(FileReader archivo) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Empresa> empresas = mapper.readValue(new BufferedReader(archivo),
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, Empresa.class));

		return empresas;
	}

	public <T> List<T> load(Class<T> elementClass ) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, elementClass));
		} catch (IOException e) {
			throw new ErrorCargaException(e);
		}
	}

	public <T> void save(List<T> elements) {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(ruta), elements);
		} catch (IOException e) {
			throw new ErrorGuardadoExcepcion(e);
		}

	}

}
