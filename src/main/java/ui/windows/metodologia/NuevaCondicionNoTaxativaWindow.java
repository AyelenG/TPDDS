package ui.windows.metodologia;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionNoTaxativaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionNoTaxativaWindow  extends Window<NuevaCondicionNoTaxativaViewModel> {
			
	public NuevaCondicionNoTaxativaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionNoTaxativaViewModel(_parentVM));	
	}
	
	protected void addActions(Panel actionsPanel) {		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}
	
	public void createContents(Panel mainPanel) {
		this.setTitle("Agregar Condición No Taxativa");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20);
		
		// Nombre de la condicion
			Panel nombrePanel = new Panel(mainPanel);
			nombrePanel.setLayout(new HorizontalLayout());

			new Label(nombrePanel).setText("Nombre de la condición  ");
			TextBox nombre = new TextBox(nombrePanel);
			nombre.bindValueToProperty("condNoTaxativa.nombre");
	}
}
