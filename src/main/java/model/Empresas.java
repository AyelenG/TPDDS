package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class Empresas {
	private List<Empresa> empresas = new LinkedList<>();
	
	public void agregarEmpresas(List<Empresa> empresas) {
		for (Object empresaObject : empresas) {			
			Empresa empresa = (Empresa) empresaObject;
			if (!existeEmpresa(empresa)) {
				this.agregarEmpresa(empresa);
			}
			else {
				this.buscarEmpresa(empresa).agregarPeriodos(empresa.getPeriodos());
			}
		}					
	}	
	
	public boolean existeEmpresa(Empresa empresa){
		return empresas.stream().anyMatch(_empresa -> _empresa.esIgual(empresa));		
	}
	
	public Empresa buscarEmpresa(Empresa empresa){
		Optional<Empresa> empresaEncontrada = empresas.stream().filter(_empresa -> _empresa.esIgual(empresa)).findFirst();
		return empresaEncontrada.isPresent() ? empresaEncontrada.get() : null;
	}
	
	public void agregarEmpresa(Empresa empresa) {		
		this.empresas.add(empresa);
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
}
