package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManipuladorArchivo {
/*	private String ruta;
	
	public ManipuladorArchivo(String archivo){
		this.ruta = archivo;
	}
	
	//Transforma un archivo JSON en una lista de empresas (lo parsea)
	//Ver lo de la exception
	public List<Empresa> deArchivoAEmpresas() throws FileNotFoundException, IOException{
	
		List<Empresa> listaEmpresasFinal = new LinkedList<>();
		JSONParser parser = new JSONParser();
		try {
			
			JSONObject jsonArchivo = (JSONObject) parser.parse(new FileReader("pruebame.json"));
            
			JSONArray jsonEmpresas = (JSONArray) jsonArchivo.get("empresas");
			for (Object e : jsonEmpresas) {
				Empresa nuevaEmpresa = ManipuladorDeJson.crearEmpresa(e); //HACER eSTO
				listaEmpresasFinal.add(nuevaEmpresa);
				JSONArray jsonPeriodos = (JSONArray) ((JSONObject) e).get("periodos");
				
				for (Object p : jsonPeriodos) {
					Periodo nuevoPeriodo = ManipuladorDeJson.crearPeriodo(p); //HACER eSTO
					nuevaEmpresa.agregarPeriodo(nuevoPeriodo);
					JSONArray jsonCuentas = (JSONArray) ((JSONObject) p).get("cuentas");
					
						for (Object c : jsonCuentas) {
							Cuenta nuevaCuenta = ManipuladorDeJson.crearCuenta(c); //HACER eSTO
							nuevoPeriodo.agregarCuenta(nuevaCuenta);

						}
			
				}
			}
			return listaEmpresasFinal;

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	
	}	*/
}
