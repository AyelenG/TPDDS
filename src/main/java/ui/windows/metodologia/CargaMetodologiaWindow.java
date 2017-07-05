package ui.windows.metodologia;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionCombinadaVM;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionNoTaxativaVM;
import ui.vm.metodologia.CargaMetodologiaViewModel.CondicionTaxativaVM;

@SuppressWarnings("serial")
public class CargaMetodologiaWindow extends SimpleWindow<CargaMetodologiaViewModel> {

	public CargaMetodologiaWindow(WindowOwner parent) {
		super(parent, new CargaMetodologiaViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Cargar Metodologia").onClick(() -> this.getModelObject().cargarMetodologia())
				.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Agregar Condición")
				.onClick(() -> new CargaCondicionWindow(this, this.getModelObject()).open())
				.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Borrar Condicion Taxativa").onClick(() -> this.getModelObject().borrarCondicionTaxativa())
				.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Borrar Condicion No Taxativa").onClick(() -> this.getModelObject().borrarCondicionNoTaxativa())
		.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Borrar Condicion Combinada").onClick(() -> this.getModelObject().borrarCondicionCombinada())
		.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Limpiar Todo").onClick(() -> this.getModelObject().limpiarTodo())
				.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel).setCaption("Cerrar").onClick(() -> this.close());
		new Button(actionsPanel).setCaption("Nueva Metodología").onClick(() -> this.getModelObject().nuevaMetodologia())
				.bindVisibleToProperty("habilitaNueva");
	}

	@Override
	public void createFormPanel(Panel mainPanel) {

		this.setTitle("Crear Metodología");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20);

		// Nombre
		Panel nombrePanel = new Panel(mainPanel).setLayout(new HorizontalLayout());
		new Label(nombrePanel).setText("Nombre:  ").setFontSize(18);
		new TextBox(nombrePanel).setFontSize(12).setWidth(300).bindValueToProperty("metodologia.nombre");
		new Label(mainPanel).setHeight(20);

		// Condiciones
		Panel condicionesPanel1 = new Panel(mainPanel).setLayout(new HorizontalLayout());
		Panel condicionesPanel2 = new Panel(mainPanel).setLayout(new HorizontalLayout());

		new Label(condicionesPanel1).setText("     Condiciones Taxativas       ").setFontSize(18);
		new Label(condicionesPanel1).setText("     Condiciones No Taxativas    ").setFontSize(18);
		new Label(condicionesPanel1).setText("   Condiciones Combinadas      ").setFontSize(18);

		Table<CondicionTaxativaVM> tablaCondicionesT = new Table<>(condicionesPanel2, CondicionTaxativaVM.class);
		tablaCondicionesT.setNumberVisibleRows(15).bindItemsToProperty("condicionesT");
		tablaCondicionesT.bindSelectionToProperty("condicionTSeleccionada");
		Column<CondicionTaxativaVM> columnaT = new Column<>(tablaCondicionesT);
		columnaT.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");

		Table<CondicionNoTaxativaVM> tablaCondicionesNT = new Table<>(condicionesPanel2, CondicionNoTaxativaVM.class);
		tablaCondicionesNT.setNumberVisibleRows(15).bindItemsToProperty("condicionesNT");
		tablaCondicionesNT.bindSelectionToProperty("condicionCombSeleccionada");
		Column<CondicionNoTaxativaVM> columnaNT = new Column<>(tablaCondicionesNT);
		columnaNT.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");

		Table<CondicionCombinadaVM> tablaCondicionesComb = new Table<>(condicionesPanel2, CondicionCombinadaVM.class);
		tablaCondicionesComb.setNumberVisibleRows(15).bindItemsToProperty("condicionesComb");
		tablaCondicionesComb.bindSelectionToProperty("condicionTSeleccionada");
		Column<CondicionCombinadaVM> columnaComb = new Column<>(tablaCondicionesComb);
		columnaComb.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");

		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
	}

}
