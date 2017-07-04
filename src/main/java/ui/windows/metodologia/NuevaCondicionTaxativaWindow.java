package ui.windows.metodologia;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import model.Indicador;
import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionTaxativaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionTaxativaWindow  extends Window<NuevaCondicionTaxativaViewModel> {
			
	public NuevaCondicionTaxativaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionTaxativaViewModel(_parentVM));	
	}
	
	protected void addActions(Panel actionsPanel) {		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
	}
	
	public void createContents(Panel mainPanel) {
		this.setTitle("Agregar Condición Taxativa");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20);
		
		// Nombre de la condicion
			Panel nombrePanel = new Panel(mainPanel);
			nombrePanel.setLayout(new HorizontalLayout());

			new Label(nombrePanel).setText("Nombre de la condición  ");
			TextBox nombre = new TextBox(nombrePanel);
			nombre.bindValueToProperty("condTaxativa.nombre");
			
			
			// Tipo
			Panel tipoPanel = new Panel(mainPanel);
			tipoPanel.setLayout(new VerticalLayout());
			
			new Label(tipoPanel).setText("Tipo");
			Selector<String> selectorTipo = new Selector<String>(tipoPanel).allowNull(true);
			selectorTipo.bindItemsToProperty("tipos");
			selectorTipo.bindValueToProperty("tipoSeleccionado");

			// Indicadores

			Panel comparadorPanel = new Panel(mainPanel);
			comparadorPanel.setLayout(new HorizontalLayout());
			
				Panel indicadorPanel = new Panel(comparadorPanel);
				indicadorPanel.setLayout(new VerticalLayout());

				new Label(indicadorPanel).setText("Indicadores");
				Selector<Indicador> selectorIndicador = new Selector<Indicador>(indicadorPanel).allowNull(true);
				selectorIndicador.bindItemsToProperty("indicadores");
				selectorIndicador.bindValueToProperty("indicadorSeleccionado");

			
				Panel operadorPanel = new Panel(comparadorPanel);
				operadorPanel.setLayout(new VerticalLayout());

				new Label(operadorPanel).setText("Comparador");
				Selector<String> selectorOperador = new Selector<String>(operadorPanel).allowNull(true);
				selectorOperador.bindItemsToProperty("comparadores");
				selectorOperador.bindValueToProperty("comparadorSeleccionado");

				
				Panel valorPanel = new Panel(comparadorPanel);
				valorPanel.setLayout(new VerticalLayout());

				new Label(valorPanel).setText("Valor de referencia");
				TextBox valor = new TextBox(valorPanel);
				valor.bindEnabledToProperty("tendencia");
				valor.bindValueToProperty("valorRef");
				
				
			Panel anioPanel = new Panel(mainPanel);
			anioPanel.setLayout(new VerticalLayout());
			
			new Label(anioPanel).setText("Años a aplicar contando desde el último");
			TextBox anios = new TextBox(anioPanel);
			anios.bindValueToProperty("anios");
		
		
		
		
		new Button(mainPanel)
		.setCaption("nueva")
		.onClick(()-> this.getModelObject().nueva());
	}
}
