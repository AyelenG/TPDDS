package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargaMetodologiaViewModel;

@SuppressWarnings("serial")
public class CargaMetodologiaWindow extends SimpleWindow<CargaMetodologiaViewModel> {
	
	public CargaMetodologiaWindow(WindowOwner parent) {
		super(parent, new CargaMetodologiaViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarMetodologia())
			.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
		new Button(actionsPanel)
			.setCaption("Nueva Metodología")
			.onClick(() -> this.getModelObject().nuevaMetodologia())
			.bindVisibleToProperty("habilitaNueva");
	}

	public void createFormPanel(Panel mainPanel) {

		this.setTitle("Crear Metodología");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(30);
 
		Panel nombrePanel = new Panel(mainPanel)
				.setLayout(new HorizontalLayout());

		new Label(nombrePanel).setText("Nombre");
		new TextBox(nombrePanel)
				.bindValueToProperty("metodologia.nombre");

		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNuevo");
	}

}
