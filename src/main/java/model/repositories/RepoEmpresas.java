package model.repositories;

import java.util.List;
import model.Empresa;

public class RepoEmpresas extends RepoArchivo<Empresa> {

	private static final RepoEmpresas instance = new RepoEmpresas();

	private RepoEmpresas() {

	}

	public static RepoEmpresas getInstance() {
		return instance;
	}
		
	@Override
	public boolean sonIguales(Empresa e1, Empresa e2) {
		return e1.getSymbol().equals(e2.getSymbol());
	}

	@Override
	public void insertarVarios(List<Empresa> empresas) {
		for (Empresa empresa : empresas) {
			if (!existeElemento(empresa))
				this.insertar(empresa);
			else
				this.buscarElemento(empresa).agregarPeriodos(empresa.getPeriodos());
		}
	}	

}
