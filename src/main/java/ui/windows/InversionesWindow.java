package ui.windows;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import ui.vm.InversionesViewModel;

@SuppressWarnings("serial")
public class InversionesWindow extends SimpleWindow<InversionesViewModel> {

	public InversionesWindow(WindowOwner parent) {
		super(parent, new InversionesViewModel());
		
		this.setTitle("¿Donde invierto?");
		this.setTaskDescription("Seleccione la opción deseada");
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		mainPanel.setLayout(new VerticalLayout());
		Panel cargaPanel = new Panel(mainPanel);
		Panel consultaPanel = new Panel(mainPanel);
		cargaPanel.setLayout(new VerticalLayout());
		consultaPanel.setLayout(new VerticalLayout());
		
		new Label(cargaPanel).setHeight(20);
		new Label(cargaPanel).setText("Ingrese la ruta del archivo que contiene los datos de las cuentas:");
		TextBox t = new TextBox(cargaPanel);
		t.setWidth(300);
		t.bindEnabledToProperty("botonCargarDatos");
		t.bindValueToProperty("rutaArchivoDatos");
		new Button(cargaPanel).setCaption("Cargar cuentas")
				.onClick(() -> this.cargarCuentas()).bindEnabledToProperty("botonCargarDatos");
		
		new Label(consultaPanel).setHeight(20);
		new Label(consultaPanel).setText("Elija la empresa que desea consultar");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(consultaPanel).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(consultaPanel).setText("Elija el periodo").bindEnabledToProperty("selectorPeriodo");
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(consultaPanel).allowNull(true);
		selectorPeriodo.bindEnabledToProperty("selectorPeriodo");
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");
		
		new Button(consultaPanel).setCaption("Consultar cuentas")
		.onClick(() -> this.consultarCuentas()).bindEnabledToProperty("botonConsultarCuentas");
		
		new Label(consultaPanel).setText("Cuentas").setFontSize(20);
		Table<Cuenta> tablaCuentas = new Table<>(consultaPanel, Cuenta.class);
		tablaCuentas.setNumberVisibleRows(20).bindItemsToProperty("cuentasSeleccionadas");
		Column<Cuenta> columnaNombre = new Column<Cuenta>(tablaCuentas);
		columnaNombre.setFont(15).setTitle("Nombre").setFixedSize(500).bindContentsToProperty("nombre");
		Column<Cuenta> columnaPeriodo = new Column<Cuenta>(tablaCuentas);
		columnaPeriodo.setFont(15).setTitle("Periodo").setFixedSize(100).bindContentsToProperty("periodo");
		Column<Cuenta> columnaValor = new Column<Cuenta>(tablaCuentas);
		columnaValor.setFont(15).setTitle("Valor").setFixedSize(400).bindContentsToProperty("valor");
		
	}

	private void consultarCuentas() {
		this.getModelObject().consultarCuentas();
	}

	private void cargarCuentas() {
		this.getModelObject().cargarCuentas();
		System.out.println("Cargado correctamente");
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {

	}
}
