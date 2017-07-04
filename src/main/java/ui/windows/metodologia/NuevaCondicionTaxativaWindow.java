package ui.windows.metodologia;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionTaxativaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionTaxativaWindow  extends Window<NuevaCondicionTaxativaViewModel> {
			
	public NuevaCondicionTaxativaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionTaxativaViewModel(_parentVM));	
	}
	
	protected void addActions(Panel actionsPanel) {		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}
	
	public void createContents(Panel mainPanel) {
		
	}
}
