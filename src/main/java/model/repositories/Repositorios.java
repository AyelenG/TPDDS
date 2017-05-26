package model.repositories;

import model.Empresas;
import model.Cuentas;
import model.Indicadores;

import org.uqbar.commons.utils.Observable;

@Observable
public class Repositorios {
	
	public static Empresas repoEmpresas = new Empresas();
	public static Cuentas repoCuentas = new Cuentas();
	public static Indicadores repoIndicadores = new Indicadores();
	
}
