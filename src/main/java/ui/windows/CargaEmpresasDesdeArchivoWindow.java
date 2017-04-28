package ui.windows;

import java.awt.Color;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargaEmpresasDesdeArchivoViewModel;

@SuppressWarnings("serial")
public class CargaEmpresasDesdeArchivoWindow extends SimpleWindow<CargaEmpresasDesdeArchivoViewModel> {

	public CargaEmpresasDesdeArchivoWindow(WindowOwner parent) {
		super(parent, new CargaEmpresasDesdeArchivoViewModel());
	}

	@Override
	public void createFormPanel(Panel mainPanel) {

		this.setTitle("Cargar Empresas desde Archivo");
		mainPanel.setLayout(new VerticalLayout());

		new Label(mainPanel).setWidth(800);
		new Label(mainPanel).setText("Seleccione el tipo de archivo a cargar").setFontSize(12);
		RadioSelector<String> extensionesSelector = new RadioSelector<String>(mainPanel);
		extensionesSelector.bindItemsToProperty("extensiones");
		extensionesSelector.bindValueToProperty("extensionSeleccionada");
		extensionesSelector.bindEnabledToProperty("habilitaSelector");
		new FileSelector(mainPanel)
				.extensions(this.getModelObject().getExtensiones().get(0),
						this.getModelObject().getExtensiones().get(1))
				.setCaption("Examinar el equipo...").bindValueToProperty("ruta");
		new Label(mainPanel).bindValueToProperty("ruta");

		new Button(mainPanel).setCaption("Cargar").onClick(() -> this.cargarCuentas())
				.bindEnabledToProperty("botonCargarCuentas");
		new Label(mainPanel).setForeground(Color.GREEN).bindValueToProperty("mensajeExito");
		new Label(mainPanel);

	}

	private void cargarCuentas() {
		this.getModelObject().cargarCuentas();
		// this.close();
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

}
