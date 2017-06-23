package model.repositories;

import java.util.List;

import model.Empresa;

public class RepoEmpresas extends Repositorio<Empresa> {

	private static final RepoEmpresas instance = new RepoEmpresas();

	private RepoEmpresas() {

	}

	public static RepoEmpresas getInstance() {
		return instance;
	}
	
	public boolean sonIguales(Empresa e1, Empresa e2) {
		return e1.getSymbol().equals(e2.getSymbol());
	}

	@Override
	public void agregarElementos(List<Empresa> empresas) {
		for (Empresa empresa : empresas) {
			if (!existeElemento(empresa))
				this.agregarElemento(empresa);
			else
				this.buscarElemento(empresa).agregarPeriodos(empresa.getPeriodos());
		}
	}

}
