package model;

import java.util.List;

import model.repositories.Repositorio;

public class Empresas extends Repositorio<Empresa> {

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
