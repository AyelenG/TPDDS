package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoaderArchivoJSON extends LoaderArchivo {

	public LoaderArchivoJSON(String ruta) {
		super(ruta);
	}

	@Override
	protected List<Empresa> parse(FileReader archivo) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonArchivo = (JSONObject) parser.parse(archivo);
		return parseEmpresas(jsonArchivo);
	}

	private List<Empresa> parseEmpresas(JSONObject jsonArchivo) {

		List<Empresa> listaEmpresasFinal = new LinkedList<>();
		JSONArray jsonEmpresas = (JSONArray) jsonArchivo.get("empresas");
		for (Object e : jsonEmpresas) {
			Empresa nuevaEmpresa = ManipuladorDeJson.crearEmpresa(e);
			listaEmpresasFinal.add(nuevaEmpresa);
			parsePeriodo(e, nuevaEmpresa);
		}
		return listaEmpresasFinal;
	}

	private void parsePeriodo(Object e, Empresa nuevaEmpresa) {
		JSONArray jsonPeriodos = (JSONArray) ((JSONObject) e).get("periodos");
		for (Object p : jsonPeriodos) {
			Periodo nuevoPeriodo = ManipuladorDeJson.crearPeriodo(p);
			nuevaEmpresa.agregarPeriodo(nuevoPeriodo);
			parseCuenta(p, nuevoPeriodo);
		}
	}

	private void parseCuenta(Object p, Periodo nuevoPeriodo) {
		JSONArray jsonCuentas = (JSONArray) ((JSONObject) p).get("cuentas");
		for (Object c : jsonCuentas) {
			Cuenta nuevaCuenta = ManipuladorDeJson.crearCuenta(c);
			nuevoPeriodo.agregarCuenta(nuevaCuenta);
		}
	}
}
