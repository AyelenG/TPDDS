package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.exceptions.ErrorCargaException;
import model.exceptions.RutaIncorrectaException;

public class ManipuladorArchivo {
	private String ruta;

	public ManipuladorArchivo(String archivo) {
		this.ruta = archivo;
	}

	// Transforma un archivo JSON en una lista de empresas (lo parsea)
	public List<Empresa> getEmpresas() {
		
		List<Empresa> listaEmpresasFinal = new LinkedList<>();
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonArchivo = (JSONObject) parser.parse(new FileReader(this.ruta));
			JSONArray jsonEmpresas = (JSONArray) jsonArchivo.get("empresas");
			return parseoEmpresas(listaEmpresasFinal, jsonEmpresas);
		} catch (FileNotFoundException e) {
			throw new RutaIncorrectaException(e);
		} catch (Exception e) {
			throw new ErrorCargaException(e);
		}

	}

	private List<Empresa> parseoEmpresas(List<Empresa> listaEmpresasFinal, JSONArray jsonEmpresas) {
		for (Object e : jsonEmpresas) {
			Empresa nuevaEmpresa = ManipuladorDeJson.crearEmpresa(e);
			listaEmpresasFinal.add(nuevaEmpresa);
			parseoPeriodo(e, nuevaEmpresa);
		}
		return listaEmpresasFinal;
	}

	private void parseoPeriodo(Object e, Empresa nuevaEmpresa) {
		JSONArray jsonPeriodos = (JSONArray) ((JSONObject) e).get("periodos");
		for (Object p : jsonPeriodos) {
			Periodo nuevoPeriodo = ManipuladorDeJson.crearPeriodo(p);
			nuevaEmpresa.agregarPeriodo(nuevoPeriodo);
			parseoCuenta(p, nuevoPeriodo);
		}
	}

	private void parseoCuenta(Object p, Periodo nuevoPeriodo) {
		JSONArray jsonCuentas = (JSONArray) ((JSONObject) p).get("cuentas");
		for (Object c : jsonCuentas) {
			Cuenta nuevaCuenta = ManipuladorDeJson.crearCuenta(c);
			nuevoPeriodo.agregarCuenta(nuevaCuenta);
		}
	}
}
