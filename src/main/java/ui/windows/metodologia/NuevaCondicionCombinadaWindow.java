package ui.windows.metodologia;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionCombinadaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionCombinadaWindow  extends Window<NuevaCondicionCombinadaViewModel> {
			
	public NuevaCondicionCombinadaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionCombinadaViewModel(_parentVM));	
	}
	
	protected void addActions(Panel actionsPanel) {		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}
	
	public void createContents(Panel mainPanel) {
		
	}
}
