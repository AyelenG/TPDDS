package model.repositories;

import model.Indicador;
import model.data.HandlerArchivoJSON;

public class RepoIndicadores extends RepoBD<Indicador> {
	
	private static final RepoIndicadores instance = new RepoIndicadores();
	
	private RepoIndicadores() {

	}
	
	public static RepoIndicadores getInstance() {
		return instance;
	}
	
	public void cargarBDDesdeArchivo() {
		this.insertarVarios(new HandlerArchivoJSON("data/Indicadores.json").<Indicador>load(Indicador.class));
	}

	@Override
	protected String valorDeBusqueda(Indicador elemento) {
		return elemento.getNombre();
	}

	@Override
	protected String campoDeBusqueda() {
		return "nombre";
	}

	@Override
	protected Class<Indicador> getEntityClass() {
		return Indicador.class;
	}

	@Override
	protected String getEntityName() {
		return "Indicador";
	}

}
