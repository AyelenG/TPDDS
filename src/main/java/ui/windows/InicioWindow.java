package ui.windows;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.InicioViewModel;
import ui.windows.CargaIndicadorWindow;

@SuppressWarnings("serial")
public class InicioWindow extends Window<InicioViewModel> {
		
	public InicioWindow(WindowOwner parent) {
		super(parent, new InicioViewModel());
	}

	@Override
	public void createContents(Panel mainPanel) {

		this.setTitle("¿Dónde Invierto? v1.0");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setHeight(30).setWidth(400);
		
		new Button(mainPanel)
			.setCaption("Cargar Datos de Empresas")
			.onClick(()-> new CargaDatosWindow(this).open());
		
		new Button(mainPanel)
			.setCaption("Agregar Cuenta Disponible")
			.onClick(()-> new CargaNuevaCuentaWindow(this,null).open());

		new Button(mainPanel)
			.setCaption("Agregar Indicador Disponible")
			.onClick(()-> new CargaIndicadorWindow(this).open());
		
		new Label(mainPanel).setHeight(30);
		
		new Button(mainPanel)
			.setCaption("Analizar Empresa")
			.onClick(()-> new AnalisisWindow(this).open());

		new Label(mainPanel).setHeight(30);
		new Label(mainPanel).setText("Desarrolladores").setFontSize(12);
		new Label(mainPanel).setText("Ayelen Guimarey");
		new Label(mainPanel).setText("Carolina González");
		new Label(mainPanel).setText("Damián Cohen");
		new Label(mainPanel).setText("Gerónimo Corti");
		new Label(mainPanel).setText("Juan Pablo Bulbulian");
		new Label(mainPanel).setHeight(30);
		new Label(mainPanel).setText("DDS - 2017 - JM - Grupo 12").setFontSize(12);
		new Label(mainPanel).setHeight(30);
	}

}
