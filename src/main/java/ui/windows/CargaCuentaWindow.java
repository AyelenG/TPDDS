package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import ui.vm.AnalisisViewModel;
import ui.vm.CargaCuentaViewModel;

@SuppressWarnings("serial")
public class CargaCuentaWindow  extends SimpleWindow<CargaCuentaViewModel> {
	
	public CargaCuentaWindow(WindowOwner parent, AnalisisViewModel parentVM) {
		super(parent, new CargaCuentaViewModel(parentVM));
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarCuenta())
			.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel)
			.setCaption("Nueva Cuenta")
			.onClick(() -> this.getModelObject().nuevaCuenta())
			.bindVisibleToProperty("habilitaNueva");
		new Button(actionsPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close())
			.bindVisibleToProperty("habilitaNueva");
	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Cargar Cuenta a Empresa Existente");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setHeight(30);
		new Label(mainPanel).setText("Elija la empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindEnabledToProperty("habilitaCarga");
		
		new Label(mainPanel);
		new Label(mainPanel).setText("Ingrese los datos de la cuenta");
		
		Panel cuentasPanel = new Panel(mainPanel);
		cuentasPanel.setLayout(new ColumnLayout(3));
		
		new Label(cuentasPanel).setText("Periodo");
		new Label(cuentasPanel).setText("Cuenta");
		new Label(cuentasPanel).setText("Valor");
		NumericField nfAnio = new NumericField(cuentasPanel);
		nfAnio.setWidth(50);
		nfAnio.bindValueToProperty("anio");
		nfAnio.bindEnabledToProperty("habilitaCarga");
		
		Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(cuentasPanel).allowNull(true);
		selectorCuenta.bindEnabledToProperty("habilitaCarga");
		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");
		
		/*
		TextBox tbNombre = new TextBox(cuentasPanel);		
		tbNombre.setWidth(200);
		tbNombre.bindValueToProperty("nombre");
		tbNombre.bindEnabledToProperty("habilitaCarga");
		*/
		
		TextBox tbValor = new TextBox(cuentasPanel);
		tbValor.setWidth(200);
		tbValor.bindValueToProperty("valor");
		tbValor.bindEnabledToProperty("habilitaCarga");
		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
		new Label(mainPanel);
	}
}
