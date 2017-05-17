package model.repositories;

import model.Empresas;
import model.Indicador;

import java.util.LinkedList;
import java.util.List;

import model.Cuentas;

public class Repositorios {
	public static Empresas empresas = new Empresas();
	public static Cuentas cuentasPredeterminadas = new Cuentas();
	public static List<Indicador> indicadores = new LinkedList<>();
	
}
