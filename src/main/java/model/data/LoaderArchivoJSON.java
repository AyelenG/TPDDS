package model.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Empresa;

import org.codehaus.jackson.map.ObjectMapper;

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
		
}
