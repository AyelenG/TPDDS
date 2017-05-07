package ui.windows;


import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import ui.vm.AnalisisViewModel;

@SuppressWarnings("serial")
public class AnalisisWindow extends SimpleWindow<AnalisisViewModel>{
		
		public AnalisisWindow(WindowOwner parent) {
			super(parent, new AnalisisViewModel());			
		}

		@Override
		protected void addActions(Panel actionsPanel) {
			/*new Button(actionsPanel)
				.setCaption("Cargar nueva Empresa")
				.onClick(()-> new CargaEmpresaWindow(this).open());
			*/  
			new Button(actionsPanel)
				.setCaption("Cargar Cuenta a Empresa Existente")
				.onClick(()-> new CargaCuentaWindow
					(this,this.getModelObject()).open());
			/*
			new Button(actionsPanel)
				.setCaption("Cargar Empresas desde Archivo")
				.onClick(()-> new CargaEmpresasDesdeArchivoWindow(this).open());*/
		}

		@Override
		protected void createFormPanel(Panel mainPanel) {
			this.setTitle("Análisis de Empresas");
		
			Panel consultaPanel = new Panel(mainPanel);
			consultaPanel.setLayout(new VerticalLayout());

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
						
			new Button(consultaPanel).setCaption("Consultar cuentas")
			.onClick(() -> this.getModelObject().consultarCuentas()).bindEnabledToProperty("botonConsultarCuentas");
			
			new Label(consultaPanel).setHeight(30);
			new Label(consultaPanel).setText("Cuentas").setFontSize(16);
			Table<Cuenta> tablaCuentas = new Table<>(consultaPanel, Cuenta.class);
			tablaCuentas.setNumberVisibleRows(10).bindItemsToProperty("cuentasSeleccionadas");
			Column<Cuenta> columnaNombre = new Column<Cuenta>(tablaCuentas);
			columnaNombre.setFont(12).setTitle("Nombre").setFixedSize(300).bindContentsToProperty("nombre");
			Column<Cuenta> columnaValor = new Column<Cuenta>(tablaCuentas);
			columnaValor.setFont(12).setTitle("Valor").setFixedSize(200).bindContentsToProperty("valor");			
			
		}
		
}
