package model;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public class ManipuladorDeJson {
	public static Empresa crearEmpresa(Object jsonEmpresa) {
		Empresa empresa = new Empresa();
		empresa.setSymbol(((JSONObject) jsonEmpresa).get("symbol").toString());
		empresa.setNombre(((JSONObject) jsonEmpresa).get("nombreEmpresa").toString());		
		return empresa;
	}
	
	public static Periodo crearPeriodo(Object jsonPeriodo) {
		Periodo periodo = new Periodo();
		periodo.setAnio(Integer.valueOf(((JSONObject) jsonPeriodo).get("year").toString()));
		return periodo;
	}
	
	public static Cuenta crearCuenta(Object jsonCuenta) {
		Cuenta cuenta = new Cuenta();
		cuenta.setNombre(((JSONObject) jsonCuenta).get("nombreCuenta").toString());
		cuenta.setValor((new BigDecimal (((JSONObject) jsonCuenta).get("valor").toString())));
		return cuenta;
	}
}
