package ui.windows.metodologia;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.condiciones.combinadas.CondicionCombinada;
import model.condiciones.notaxativas.CondicionNoTaxativa;
import model.condiciones.taxativas.CondicionTaxativa;
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
		.setCaption("Agregar Condicion Taxativa")
		.onClick(()-> this.getModelObject().cargarCondicionT());
		new Button(actionsPanel)
		.setCaption("Agregar Condicion No Taxativa")
		.onClick(()-> this.getModelObject().cargarCondicionNT());
		new Button(actionsPanel)
		.setCaption("Agregar Condicion Combinada")
		.onClick(()-> this.getModelObject().cargarCondicionComb());
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Agregar Condicion Primitiva");
		new Label(mainPanel).setHeight(20).setWidth(200);
		
		new Label(mainPanel).setText("Elija una condicion primitiva combinada:").setFontSize(14);
		Selector<CondicionCombinada> selectorCondComb = new Selector<CondicionCombinada>(mainPanel).allowNull(true);
		selectorCondComb.bindItemsToProperty("condicionesComb");
		selectorCondComb.bindValueToProperty("condicionCombSeleccionada");
		
		new Label(mainPanel).setHeight(20).setWidth(200);
		
		new Label(mainPanel).setText("Elija una condicion primitiva taxativa:").setFontSize(14);
		Selector<CondicionTaxativa> selectorCondT = new Selector<CondicionTaxativa>(mainPanel).allowNull(true);
		selectorCondT.bindItemsToProperty("condicionesT");
		selectorCondT.bindValueToProperty("condicionTSeleccionada");
		
		new Label(mainPanel).setHeight(20).setWidth(200);
		
		new Label(mainPanel).setText("Elija una condicion primitiva no taxativa:").setFontSize(14);
		Selector<CondicionNoTaxativa> selectorCondNT = new Selector<CondicionNoTaxativa>(mainPanel).allowNull(true);
		selectorCondNT.bindItemsToProperty("condicionesNT");
		selectorCondNT.bindValueToProperty("condicionNTSeleccionada");
		
		new Label(mainPanel).setHeight(20).setWidth(200);
	}
}
