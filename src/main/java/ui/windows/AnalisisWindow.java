package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.CuentaEmpresa;
import model.Empresa;
import ui.vm.AnalisisViewModel;

@SuppressWarnings("serial")
public class AnalisisWindow extends SimpleWindow<AnalisisViewModel> {

	public AnalisisWindow(WindowOwner parent) {
		super(parent, new AnalisisViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Cargar Cuenta a Empresa Existente")
			.onClick(() -> new CargaCuentaEmpresaWindow(this, this.getModelObject()).open());
		new Button(actionsPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Análisis de Empresas");

		Panel consultaPanel = new Panel(mainPanel);
		consultaPanel.setLayout(new VerticalLayout());
		Panel indicadoresPanel = new Panel(mainPanel);
		Panel indicadoresPanel1 = new Panel(indicadoresPanel);
		Panel indicadoresPanel2 = new Panel(indicadoresPanel);
		indicadoresPanel1.setLayout(new ColumnLayout(2));
		indicadoresPanel2.setLayout(new HorizontalLayout()); // no funcionan las
																// tablas en
																// columnas

		new Label(consultaPanel).setHeight(30);
		new Label(consultaPanel).setText("Elija la empresa que desea analizar");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(consultaPanel).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");

		new Label(consultaPanel).setText("Elija el periodo").bindEnabledToProperty("selectorPeriodo");
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(consultaPanel).allowNull(true);
		selectorPeriodo.bindEnabledToProperty("selectorPeriodo");
		selectorPeriodo.bindItemsToProperty("periodosSeleccionados");
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");

		new Button(consultaPanel).setCaption("Consultar cuentas e indicadores")
				.onClick(() -> this.getModelObject().consultar()).bindEnabledToProperty("botonConsultar");

		new Label(consultaPanel).setHeight(20);
		new Label(consultaPanel).setText("Cuentas").setFontSize(12);
		Table<CuentaEmpresa> tablaCuentas = new Table<>(consultaPanel, CuentaEmpresa.class);
		tablaCuentas.setNumberVisibleRows(10).bindItemsToProperty("cuentasSeleccionadas");
		Column<CuentaEmpresa> columnaNombre = new Column<CuentaEmpresa>(tablaCuentas);
		columnaNombre.setFont(10).setTitle("Nombre").setFixedSize(300).bindContentsToProperty("nombre");
		Column<CuentaEmpresa> columnaValor = new Column<CuentaEmpresa>(tablaCuentas);
		columnaValor.setFont(10).setTitle("Valor").setFixedSize(200).bindContentsToProperty("valor");

		new Label(consultaPanel).setHeight(20);
		new Label(indicadoresPanel1).setText("Indicadores evaluados").setFontSize(16);
		new Label(indicadoresPanel1).setText("Indicadores no evaluados").setFontSize(16);

		Table<AnalisisViewModel.IndicadorVM> tablaIndicadoresConValor = new Table<>(indicadoresPanel2,
				AnalisisViewModel.IndicadorVM.class);
		tablaIndicadoresConValor.setNumberVisibleRows(10).bindItemsToProperty("indicadoresConValor");
		Column<AnalisisViewModel.IndicadorVM> columnaNombreIndicadorConValor = new Column<>(tablaIndicadoresConValor);
		columnaNombreIndicadorConValor.setFont(9).setTitle("Nombre").setFixedSize(200).bindContentsToProperty("nombre");
		Column<AnalisisViewModel.IndicadorVM> columnaValorIndicador = new Column<>(tablaIndicadoresConValor);
		columnaValorIndicador.setFont(9).setTitle("Valor").setFixedSize(200).bindContentsToProperty("valor");

		Table<AnalisisViewModel.IndicadorVM> tablaIndicadoresSinValor = new Table<>(indicadoresPanel2,
				AnalisisViewModel.IndicadorVM.class);
		tablaIndicadoresSinValor.setNumberVisibleRows(10).bindItemsToProperty("indicadoresSinValor");
		Column<AnalisisViewModel.IndicadorVM> columnaNombreIndicadorSinValor = new Column<>(tablaIndicadoresSinValor);
		columnaNombreIndicadorSinValor.setFont(9).setTitle("Nombre").setFixedSize(200).bindContentsToProperty("nombre");
		Column<AnalisisViewModel.IndicadorVM> columnaMensajeIndicador = new Column<>(tablaIndicadoresSinValor);
		columnaMensajeIndicador.setFont(9).setTitle("Error").setFixedSize(500).bindContentsToProperty("mensaje");
		
		new Label(mainPanel);
		new Label(mainPanel).setText("Montos en Millones de USD");
	}

}