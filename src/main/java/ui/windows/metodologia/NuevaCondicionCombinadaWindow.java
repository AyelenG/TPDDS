package ui.windows.metodologia;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionCombinadaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionCombinadaWindow  extends SimpleWindow<NuevaCondicionCombinadaViewModel> {
			
	public NuevaCondicionCombinadaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionCombinadaViewModel(_parentVM));	
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}
}
