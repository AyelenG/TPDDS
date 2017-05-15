package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargaNuevaCuentaViewModel;

@SuppressWarnings("serial")
public class CargaNuevaCuentaWindow extends SimpleWindow<CargaNuevaCuentaViewModel> {
	
	public CargaNuevaCuentaWindow(WindowOwner parent) {
		super(parent, new CargaNuevaCuentaViewModel());
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Cargar nueva Cuenta");
		mainPanel.setLayout(new VerticalLayout());		
		new Label(mainPanel).setHeight(30);
		
		new Label(mainPanel).setText("Nombre de la cuenta a ingresar al");
		new Label(mainPanel).setText(" listado de cuentas disponibles: ");
		TextBox tbSymbol = new TextBox(mainPanel);
		tbSymbol.bindValueToProperty("cuenta.nombre");
		tbSymbol.bindEnabledToProperty("habilitaCarga");

		new Button(mainPanel)
			.setCaption("Ingresar")
			.onClick(() -> this.getModelObject().cargarCuenta())
			.bindEnabledToProperty("habilitaCarga");
		new Button(mainPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
		
		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
		
		new Button(mainPanel)
			.setCaption("Nueva Cuenta")
			.onClick(() -> this.getModelObject().nuevaCuenta())
			.bindVisibleToProperty("habilitaNueva");

	}
}
