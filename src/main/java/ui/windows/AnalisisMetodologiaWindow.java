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

import model.Empresa;
import model.Metodologia;
import ui.vm.AnalisisMetodologiaViewModel;

@SuppressWarnings("serial")
public class AnalisisMetodologiaWindow extends SimpleWindow<AnalisisMetodologiaViewModel> {

	public AnalisisMetodologiaWindow(WindowOwner parent) {
		super(parent, new AnalisisMetodologiaViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Cerrar").onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Análisis de Metodologías");

		Panel consultaPanel = new Panel(mainPanel);
		consultaPanel.setLayout(new VerticalLayout());
		Panel metodologiasPanel = new Panel(mainPanel);
		Panel empresasTot = new Panel(metodologiasPanel);
		Panel empresasTot2 = new Panel(metodologiasPanel);
		empresasTot.setLayout(new ColumnLayout(3));
		empresasTot2.setLayout(new HorizontalLayout());
		empresasTot.setWidth(200);
		empresasTot2.setWidth(200);

		new Label(consultaPanel).setHeight(30);
		new Label(consultaPanel).setText("Elija la metodología");
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(consultaPanel).allowNull(true);
		selectorMetodologia.bindItemsToProperty("metodologias");
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");

		new Button(consultaPanel).setCaption("Analizar").onClick(() -> this.getModelObject().analizar())
				.bindEnabledToProperty("botonAnalizar");

		new Label(consultaPanel).setHeight(30);
		new Label(empresasTot).setText("Empresas en las que conviene invertir").setFontSize(10);
		new Label(empresasTot).setText("Empresas en las que no conviene invertir").setFontSize(10);
		new Label(empresasTot).setText("Empresas sin la información necesaria").setFontSize(10);
		
		Table<Empresa> empresasDeS = new Table<>(empresasTot2, Empresa.class);

		empresasDeS.setNumberVisibleRows(10).bindItemsToProperty("empresasDeseables");
		Column<Empresa> columnaNombreEmpresaDeseable = new Column<>(empresasDeS);
		columnaNombreEmpresaDeseable.setFixedSize(500).setFont(10).setTitle("Nombre").setFixedSize(200).bindContentsToProperty("nombre");

		new Label(empresasTot2).setWidth(100);
		
		Table<Empresa> empresasNoDes = new Table<>(empresasTot2, Empresa.class);

		empresasNoDes.setNumberVisibleRows(10).bindItemsToProperty("empresasNoDeseables");
		Column<Empresa> columnaNombreEmpresaNoDeseable = new Column<>(empresasNoDes);
		columnaNombreEmpresaNoDeseable.setFixedSize(500).setFont(10).setTitle("Nombre").setFixedSize(200)
				.bindContentsToProperty("nombre");

		new Label(empresasTot2).setWidth(100);
		
		Table<Empresa> empresasInv = new Table<>(empresasTot2, Empresa.class);
		
		empresasInv.setNumberVisibleRows(10).bindItemsToProperty("empresasInvalidas");
		Column<Empresa> columnaNombreEmpresaInvalida = new Column<>(empresasInv);
		columnaNombreEmpresaInvalida.setFixedSize(500).setFont(10).setTitle("Nombre").setFixedSize(200)
				.bindContentsToProperty("nombre");
	}

}
