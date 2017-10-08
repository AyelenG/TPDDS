package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import model.repositories.RepoUsuarios;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, length = 50, nullable=false)
	private String nombre = "";
	
	@Column(length = 50, nullable=false)
	private String pass = "";
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String pass) {
		this.nombre = nombre;
		this.pass = pass;
	}

	private boolean chequeoNombre() {		
		return RepoUsuarios.getInstance().existeElemento(this);
	}

	public boolean chequeoPassword() {		
		if (this.chequeoNombre()) {
			Usuario existente = RepoUsuarios.getInstance().buscarElemento(this);
			return existente.getPass().equals(this.pass);
		}
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
