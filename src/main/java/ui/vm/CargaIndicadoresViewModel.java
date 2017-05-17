package ui.vm;

import java.util.List;

import model.Cuenta;
import model.Cuentas;
import model.Indicador;
import model.repositories.Repositorios;

public class CargaIndicadoresViewModel {
	private Cuentas cuentas = Repositorios.cuentasPredeterminadas;
	private List<Indicador> indicadores;
	private String ingresado = "";
	private Indicador indicadorNuevo;
	
	public void agregarSimbolo(String simbolo){
		ingresado += " " + ingresado;
	}
	
	public void borrarUltimo(){
		//borra el ultimo token
	}
	
	public void getCuentas(){
		
	}
	
	
}
