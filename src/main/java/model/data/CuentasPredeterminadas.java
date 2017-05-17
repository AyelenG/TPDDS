package model.data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

public class CuentasPredeterminadas {
	
	private static final String RUTA = "data/CuentasPredeterminadas.json";
	
	public static void cargar() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Repositorios
				.cuentasPredeterminadas
				.agregarCuentas(mapper.readValue(new File(RUTA),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, Cuenta.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* IMPRESION POR CONSOLA PARA CONTROL */	
		/*for ( Object cuenta : Repositorios.cuentasPredeterminadas.getCuentas())			
			System.out.println(((Cuenta) cuenta).getNombre());*/
	}
	
	/** Desde una Lista de Empresas toma todas las cuentas e ingresa
	 * solo las que no estan repetidas en el Repositorio y guarda
	 * el archivo en disco
	 * @param empresas
	 */
	
	/* Agregar al Repositorio las cuentas distintas */
	public static void agregarDistintas(List<Empresa> empresas) {
		
		for (Empresa empresa : empresas)
			for (Periodo periodo : empresa.getPeriodos())
				Repositorios.cuentasPredeterminadas
					.agregarCuentas(periodo.getCuentas());
		actualizarJSON();
	}
	
	/* Del Repositorio al Archivo JSON */
	public static void actualizarJSON() {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(RUTA),
						Repositorios.cuentasPredeterminadas.getCuentas());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
