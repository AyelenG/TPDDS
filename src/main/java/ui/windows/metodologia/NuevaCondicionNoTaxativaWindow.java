package ui.windows.metodologia;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Indicador;
import ui.vm.metodologia.CargaMetodologiaViewModel;
import ui.vm.metodologia.NuevaCondicionNoTaxativaViewModel;

@SuppressWarnings("serial")
public class NuevaCondicionNoTaxativaWindow extends SimpleWindow<NuevaCondicionNoTaxativaViewModel> {

	public NuevaCondicionNoTaxativaWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new NuevaCondicionNoTaxativaViewModel(_parentVM));
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Cerrar").onClick(() -> this.close());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Agregar Condición No Taxativa");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20);

		// Nombre de la condicion
		Panel nombrePanel = new Panel(mainPanel);
		nombrePanel.setLayout(new HorizontalLayout());

		new Label(nombrePanel).setText("Nombre de la condición  ");
		TextBox nombre = new TextBox(nombrePanel);
		nombre.bindValueToProperty("nueva.nombre");

		new Label(nombrePanel).setText("Peso");
		TextBox peso = new TextBox(nombrePanel);
		peso.bindValueToProperty("peso");

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

		Panel anioPanel = new Panel(mainPanel);
		anioPanel.setLayout(new VerticalLayout());

		new Label(anioPanel).setText("Años a aplicar contando desde el último");
		TextBox anios = new TextBox(anioPanel);
		anios.bindValueToProperty("anios");
	}
}
