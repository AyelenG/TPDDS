package model.repositories;

import model.Empresas;
import model.Cuentas;
import model.Indicadores;

import org.uqbar.commons.utils.Observable;
@Observable
public class Repositorios {
	public static Empresas empresas = new Empresas();
	public static Cuentas cuentasPredeterminadas = new Cuentas();
	public static Indicadores indicadores = new Indicadores();
	
}
