package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargaEmpresaViewModel;

@SuppressWarnings("serial")
public class CargaEmpresaWindow  extends SimpleWindow<CargaEmpresaViewModel> {
	
	public CargaEmpresaWindow(WindowOwner parent) {
		super(parent, new CargaEmpresaViewModel());
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Cargar nueva Empresa");
		mainPanel.setLayout(new VerticalLayout());		
		Panel datosPanel = new Panel(mainPanel);
		datosPanel.setLayout(new ColumnLayout(2));
		
		new Label(datosPanel).setText("Simbolo");
		new TextBox(datosPanel)			
			.bindValueToProperty("empresa.symbol");
				
		new Label(datosPanel).setText("Nombre");
		new TextBox(datosPanel)
			.setWidth(200)
			.bindValueToProperty("empresa.nombre");

		new Button(mainPanel).setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarEmpresa());
		new Label(mainPanel).setForeground(Color.GREEN).bindValueToProperty("mensajeExito");
		new Label(mainPanel);
	}
}
