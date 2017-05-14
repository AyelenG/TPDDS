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
public class CargaEmpresasDesdeArchivoWindow  extends SimpleWindow<CargaEmpresasDesdeArchivoViewModel> {
	
	public CargaEmpresasDesdeArchivoWindow(WindowOwner parent) {
		super(parent, new CargaEmpresasDesdeArchivoViewModel());
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Cargar")
		.onClick(() -> this.getModelObject().cargarCuentas())
		.bindEnabledToProperty("botonCargarCuentas");
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(() -> this.close());
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
		FileSelector fileSelector = new FileSelector(mainPanel);
		fileSelector.extensions(
				this.getModelObject().getExtensiones().get(0),
				this.getModelObject().getExtensiones().get(1),
				this.getModelObject().getExtensiones().get(2));
		fileSelector.setCaption("Examinar el equipo...");	
		fileSelector.bindValueToProperty("ruta");
		fileSelector.bindEnabledToProperty("habilitaSelector");
		new Label(mainPanel).bindValueToProperty("ruta");
		

		Label labelExito = new Label(mainPanel);
		labelExito.setForeground(Color.GREEN);
		labelExito.setText("Carga realizada Exitosamente");
		labelExito.bindVisibleToProperty("botonCerrar");

		new Label(mainPanel);
		
	}

}
