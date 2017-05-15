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
import ui.vm.CargaCuentaEmpresaViewModel;

@SuppressWarnings("serial")
public class CargaCuentaEmpresaWindow  extends SimpleWindow<CargaCuentaEmpresaViewModel> {
	
	public CargaCuentaEmpresaWindow(WindowOwner parent, AnalisisViewModel parentVM) {
		super(parent, new CargaCuentaEmpresaViewModel(parentVM));
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarCuenta())
			.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel)
			.setCaption("Cargar Nueva Cuenta al Listado")
			.onClick(()-> new CargaNuevaCuentaWindow(this,this.getModelObject()).open());
		new Button(actionsPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
		new Button(actionsPanel)
			.setCaption("Nueva Cuenta")
			.onClick(() -> this.getModelObject().nuevaCuenta())
			.bindVisibleToProperty("habilitaNueva");

	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Cargar Cuenta a Empresa Existente");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setHeight(30);
		new Label(mainPanel).setText("Elija la empresa").setFontSize(12);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(mainPanel).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindEnabledToProperty("habilitaCarga");
		
		new Label(mainPanel).setHeight(30);
		new Label(mainPanel).setText("Ingrese los datos de la cuenta").setFontSize(12);
		
		Panel cuentasPanel = new Panel(mainPanel);
		cuentasPanel.setLayout(new ColumnLayout(2));

		new Label(cuentasPanel).setText("Periodo");
		
		NumericField nfAnio = new NumericField(cuentasPanel);
		nfAnio.setWidth(50);
		nfAnio.bindValueToProperty("anio");
		nfAnio.bindEnabledToProperty("habilitaCarga");

		new Label(cuentasPanel).setText("Cuenta");
		Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(cuentasPanel);//.allowNull(true);
		selectorCuenta.bindEnabledToProperty("habilitaCarga");
		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");

		new Label(cuentasPanel).setText("Valor");
		TextBox tbValor = new TextBox(cuentasPanel);
		tbValor.setWidth(100);
		tbValor.bindValueToProperty("valor");
		tbValor.bindEnabledToProperty("habilitaCarga");
		
		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
		
		new Label(mainPanel);
	}
}
