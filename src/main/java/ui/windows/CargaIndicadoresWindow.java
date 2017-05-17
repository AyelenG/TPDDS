package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import ui.vm.InicioViewModel;
import ui.vm.AnalisisViewModel;
import ui.vm.CargaCuentaEmpresaViewModel;
import ui.vm.CargaEmpresasDesdeArchivoViewModel;
import ui.vm.CargaIndicadoresViewModel;


@SuppressWarnings("serial")
public class CargaIndicadoresWindow extends SimpleWindow <CargaIndicadoresViewModel> {
			
		public CargaIndicadoresWindow(WindowOwner parent) {
			super(parent, new CargaIndicadoresViewModel());
		}
		
		
		@Override
		protected void addActions(Panel actionsPanel) {
		}
		
	
public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Crear nuevo Indicador");
		mainPanel.setLayout(new VerticalLayout());		
		new Label(mainPanel).setHeight(30);
		
		//Nombre del indicador
		Panel nombrePanel = new Panel(mainPanel);
		nombrePanel.setLayout(new HorizontalLayout());
				
		new Label(nombrePanel).setText("Nombre del Indicador  ");
		TextBox nombre = new TextBox(nombrePanel);
		nombre.bindValueToProperty("indicadorNuevo.nombre");
		
		//Los botones de los simbolos/operadores
		Panel simbolosPanel = new Panel (mainPanel);
		simbolosPanel.setLayout(new HorizontalLayout());
		
		new Button(simbolosPanel)
		.setCaption("(")
		.onClick(() -> this.getModelObject().agregarSimbolo("("));
		
		new Button(simbolosPanel)
		.setCaption(")")
		.onClick(() -> this.getModelObject().agregarSimbolo(")"));
		
		new Button(simbolosPanel)
		.setCaption("*")
		.onClick(() -> this.getModelObject().agregarSimbolo("*"));
		
		new Button(simbolosPanel)
		.setCaption("+")
		.onClick(() -> this.getModelObject().agregarSimbolo("+"));
		
		new Button(simbolosPanel)
		.setCaption("-")
		.onClick(() -> this.getModelObject().agregarSimbolo("-"));
		
		new Button(simbolosPanel)
		.setCaption("/")
		.onClick(() -> this.getModelObject().agregarSimbolo("/"));
		
		//Cuentas
		Panel cuentasPanel = new Panel (mainPanel);
		cuentasPanel.setLayout(new HorizontalLayout());
		
		new Label(cuentasPanel).setText("Cuentas");
		Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(cuentasPanel).allowNull(true);
		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");
		
		new Button(cuentasPanel)
		.setCaption("Ingresar")
		.onClick(() -> this.getModelObject().ingresarCuenta());
		
		//Indicadores
		
		Panel indicadoresPanel = new Panel (mainPanel);
		indicadoresPanel.setLayout(new HorizontalLayout());
		
		new Label(indicadoresPanel).setText("Indicadores");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(indicadoresPanel).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new Button(indicadoresPanel)
		.setCaption("Ingresar")
		.onClick(() -> this.getModelObject().ingresarIndicador());
		
		//Constante
		
		Panel constantePanel = new Panel (mainPanel);
		constantePanel.setLayout(new HorizontalLayout());
		
		new Label(constantePanel).setText("Constante");
		TextBox constante = new TextBox(constantePanel);
		constante.bindValueToProperty("constante");
		
		new Button(constantePanel)
		.setCaption("Ingresar")
		.onClick(() -> this.getModelObject().ingresarConstante());
		
		//Label con la formula mas botones		
		new Label(mainPanel).setText("Indicador creado:");
		new Label(mainPanel).bindValueToProperty("ingresado");
		
		Panel botonesPanel = new Panel (mainPanel);
		botonesPanel.setLayout(new HorizontalLayout());
		
		new Button(botonesPanel)
		.setCaption("Borrar todo")
		.onClick(() -> this.getModelObject().setIngresado(""));
		
		new Button(botonesPanel)
		.setCaption("Cargar")
		.onClick(() -> this.getModelObject().cargarIndicador());

		}
}
