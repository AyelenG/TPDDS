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
import ui.vm.metodologia.auxiliares.CondicionCombinadaVM;
import ui.vm.metodologia.auxiliares.CondicionNoTaxativaVM;
import ui.vm.metodologia.auxiliares.CondicionTaxativaVM;

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
		Panel tablasPanel = new Panel(mainPanel).setLayout(new HorizontalLayout());
		
		//Panel condicionesPanel2 = new Panel(mainPanel).setLayout(new HorizontalLayout());

		//Taxativas
		Panel taxativasPanel = new Panel(tablasPanel);
		taxativasPanel.setLayout(new VerticalLayout());
		
		new Label(taxativasPanel).setText("     Condiciones Taxativas       ").setFontSize(15);
		
		Table<CondicionTaxativaVM> tablaCondicionesT = new Table<>(taxativasPanel, CondicionTaxativaVM.class);
		tablaCondicionesT.setNumberVisibleRows(15).bindItemsToProperty("condicionesT");
		tablaCondicionesT.bindSelectionToProperty("condicionTSeleccionada");
		Column<CondicionTaxativaVM> columnaT = new Column<>(tablaCondicionesT);
		columnaT.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");

		//no taxativas
		Panel noTaxativasPanel = new Panel(tablasPanel);
		noTaxativasPanel.setLayout(new VerticalLayout());
		
		new Label(noTaxativasPanel).setText("     Condiciones No Taxativas    ").setFontSize(15);
		
		Table<CondicionNoTaxativaVM> tablaCondicionesNT = new Table<>(noTaxativasPanel, CondicionNoTaxativaVM.class);
		tablaCondicionesNT.setNumberVisibleRows(15).bindItemsToProperty("condicionesNT");
		tablaCondicionesNT.bindSelectionToProperty("condicionNTSeleccionada");
		Column<CondicionNoTaxativaVM> columnaNT = new Column<>(tablaCondicionesNT);
		columnaNT.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");
		
		//combinadas
		Panel combinadasPanel = new Panel(tablasPanel);
		combinadasPanel.setLayout(new VerticalLayout());
		
		new Label(combinadasPanel).setText("   Condiciones Combinadas      ").setFontSize(15);
		
		Table<CondicionCombinadaVM> tablaCondicionesComb = new Table<>(combinadasPanel, CondicionCombinadaVM.class);
		tablaCondicionesComb.setNumberVisibleRows(15).bindItemsToProperty("condicionesComb");
		tablaCondicionesComb.bindSelectionToProperty("condicionCombSeleccionada");
		Column<CondicionCombinadaVM> columnaComb = new Column<>(tablaCondicionesComb);
		columnaComb.setTitle("Nombre/Descripcion").setFixedSize(300).bindContentsToProperty("titulo");

		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
	}

}
