package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, length = 50, nullable=false)
	@Getter @Setter private String nombre = "";
	
	@Column(length = 50, nullable=false)
	@Getter @Setter private String pass = "";
	
	public Usuario(){
		
	}
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuario(String nombre, String pass) {
		this.nombre = nombre;
		this.pass = pass;
	}

}
