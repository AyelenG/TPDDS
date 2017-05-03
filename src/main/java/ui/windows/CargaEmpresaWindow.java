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
		TextBox tbSymbol = new TextBox(datosPanel);
		tbSymbol.bindValueToProperty("empresa.symbol");
		tbSymbol.bindEnabledToProperty("habilitaCarga");
		
		new Label(datosPanel).setText("Nombre");
		TextBox tbNombre = new TextBox(datosPanel);
		tbNombre.setWidth(200);
		tbNombre.bindValueToProperty("empresa.nombre");
		tbNombre.bindEnabledToProperty("habilitaCarga");

		new Button(mainPanel)
			.setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarEmpresa())
			.bindEnabledToProperty("habilitaCarga");
		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
		new Button(mainPanel)
			.setCaption("Nueva Empresa")
			.onClick(() -> this.getModelObject().nuevaEmpresa())
			.bindVisibleToProperty("habilitaNueva");
		new Button(mainPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close())
			.bindVisibleToProperty("habilitaNueva");
	}
}
