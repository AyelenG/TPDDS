package ui.vm.metodologia.auxiliares;

import org.uqbar.commons.utils.Observable;

import model.condiciones.taxativas.TipoCondicionTaxativa;

@Observable
public class TipoCondicionVM{
	private TipoCondicionTaxativa tipoCondicionTaxativa;
	
	public TipoCondicionVM(TipoCondicionTaxativa tipo)
	{
		this.setTipoCondicionTaxativa(tipo);
	}
	
	public void setTipoCondicionTaxativa(TipoCondicionTaxativa tipo){
		tipoCondicionTaxativa = tipo;
	}

	public TipoCondicionTaxativa getTipoCondicionTaxativa() {
		return tipoCondicionTaxativa;
	}
	
	@Override
	public String toString(){
		return tipoCondicionTaxativa.toString();
	}
	
}