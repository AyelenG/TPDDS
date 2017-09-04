package ui.windows.metodologia;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.condiciones.Condicion;
import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionPrimitivaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionPrimitivaWindow  extends SimpleWindow<NuevaCondicionPrimitivaViewModel> {
			
	public NuevaCondicionPrimitivaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionPrimitivaViewModel(_parentVM));	
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Agregar Condicion Primitiva")
		.onClick(()-> this.getModelObject().cargarCondicionPrim());
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Agregar Condicion Primitiva");
		new Label(mainPanel).setHeight(20).setWidth(200);
		
		new Label(mainPanel).setText("Elija una condicion primitiva:").setFontSize(14);
		Selector<Condicion> selectorPrim = new Selector<Condicion>(mainPanel).allowNull(true);
		selectorPrim.bindItemsToProperty("condicionesPrim");
		selectorPrim.bindValueToProperty("condicionPrimSeleccionada");
		
		new Label(mainPanel).setHeight(20).setWidth(200);
	}
}
