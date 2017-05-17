package model.repositories;

import model.Empresas;
import model.Indicador;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Cuentas;
@Observable
public class Repositorios {
	public static Empresas empresas = new Empresas();
	public static Cuentas cuentasPredeterminadas = new Cuentas();
	public static List<Indicador> indicadores = new LinkedList<>();
	
}
