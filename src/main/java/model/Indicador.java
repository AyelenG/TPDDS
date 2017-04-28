package model;

public class Indicador {
	
	public Indicador(String nombre, int periodo, float valor) {
		super();
		this.nombre = nombre;
		this.periodo = periodo;
		this.valor = valor;
	}
	
	private String nombre;
	private int periodo;
	private float valor;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
