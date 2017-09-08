package model.repositories;

import java.util.LinkedList;
import java.util.List;

import model.Metodologia;
import model.condiciones.Condicion;
import model.condiciones.Mayor;
import model.condiciones.Menor;
import model.condiciones.notaxativas.CondicionNoTaxativaConfigurable;
import model.condiciones.primitivas.Longevidad;
import model.condiciones.taxativas.CondicionTaxativaConfigurable;
import model.condiciones.taxativas.Tendencia;
import model.data.HandlerArchivoJSON;

public class RepoMetodologiasBD extends RepoBD<Metodologia> {
	
	private static final RepoMetodologiasBD instance = new RepoMetodologiasBD();

	private RepoMetodologiasBD() {
	
	}
	
	public static RepoMetodologiasBD getInstance() {
		return instance;
	}
	
	@Override
	public boolean sonIguales(Metodologia m1, Metodologia m2) {
		return m1.getNombre().equals(m2.getNombre());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Metodologia> findAll(){					
		return this.entityManager.createQuery("from Metodologia").getResultList();
	}
	
	public void cargarWarrenBuffet() {
		List<Condicion> condiciones = new LinkedList<>(); 
		condiciones.add(new CondicionNoTaxativaConfigurable("Max. ROE - 10 años", 5, new Mayor(),
				"Retorno sobre capital total", 10));
		condiciones.add(new CondicionNoTaxativaConfigurable("Min. Nv.Deuda - 1 año", 5, new Menor(),
				"Nivel de deuda", 1));
		condiciones.add(new CondicionTaxativaConfigurable("Margen Creciente - 10 años > 50", new Mayor(),
				new Tendencia(),"Margen", 10, null));
		condiciones.add(new Longevidad());		
		this.insertar(new Metodologia("Warren-Buffet", condiciones));
	}
	
	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/Metodologias.json").<Metodologia>load(Metodologia.class));
	}

}
