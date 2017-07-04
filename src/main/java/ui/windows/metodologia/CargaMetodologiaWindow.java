package ui.windows.metodologia;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Metodologia;
import model.condiciones.taxativas.CondicionTaxativa;
import ui.vm.metodologia.CargaMetodologiaViewModel;

@SuppressWarnings("serial")
public class CargaMetodologiaWindow extends SimpleWindow<CargaMetodologiaViewModel> {
	
	public CargaMetodologiaWindow(WindowOwner parent) {
		super(parent, new CargaMetodologiaViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Cargar")
			.onClick(() -> this.getModelObject().cargarMetodologia())
			.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel)
			.setCaption("Agregar Condición")
			.onClick(()-> new CargaCondicionWindow(this, this.getModelObject()).open())
			.bindEnabledToProperty("habilitaCarga");
		new Button(actionsPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
		new Button(actionsPanel)
			.setCaption("Nueva Metodología")
			.onClick(() -> this.getModelObject().nuevaMetodologia())
			.bindVisibleToProperty("habilitaNueva");
	}

	public void createFormPanel(Panel mainPanel) {

		this.setTitle("Crear Metodología");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20);
		
		// Nombre
		Panel nombrePanel = new Panel(mainPanel).setLayout(new ColumnLayout(2));
		new Label(nombrePanel).setText("Nombre");
		new TextBox(nombrePanel).bindValueToProperty("metodologia.nombre");
		new Label(mainPanel).setHeight(20);
		
		// Condiciones
		new Label(mainPanel).setText("Condiciones");
		Panel condicionesPanel = new Panel(mainPanel).setLayout(new ColumnLayout(3));
		
//		Table<CondicionTaxativa> tablaCondicionesTaxativas = new Table<>(condicionesPanel, CondicionTaxativa.class);
//		tablaCondicionesTaxativas.setNumberVisibleRows(6).bindItemsToProperty("metodologia.condicionesT");
//		Column<CondicionTaxativa> columnaTaxativas = new Column<CondicionTaxativa>(tablaCondicionesTaxativas);
//		columnaTaxativas.setTitle("Taxativas").bindContentsToProperty("metodologia.condicionesT");
//		
//		Column<Metodologia> columnaNoTaxativas = new Column<Metodologia>(tablaCondiciones);
//		columnaNoTaxativas.setTitle("No Taxativas").bindContentsToProperty("metodologia.condicionesNT");
//		
//		Column<Metodologia> columnaCombinadas = new Column<Metodologia>(tablaCondiciones);
//		columnaCombinadas.setTitle("Combinadas").bindContentsToProperty("metodologia.condicionesComb");
				
		
		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("habilitaNueva");
	}

}
