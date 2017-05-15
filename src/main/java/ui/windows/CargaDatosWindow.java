package ui.windows;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.InicioViewModel;

@SuppressWarnings("serial")
public class CargaDatosWindow  extends SimpleWindow<InicioViewModel> {
	
	public CargaDatosWindow(WindowOwner parent) {
		super(parent, ((InicioWindow)parent).getModelObject());
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Cargar Datos");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel);
		new Button(mainPanel)
			.setCaption("Cargar nueva Empresa")
			.onClick(()-> new CargaEmpresaWindow(this).open());
	
		new Label(mainPanel);
		new Button(mainPanel)
			.setCaption("Cargar Cuenta a Empresa Existente")
			.onClick(()-> new CargaCuentaEmpresaWindow(this,null).open());
		
		new Label(mainPanel);
		new Button(mainPanel)
			.setCaption("Cargar Empresas desde Archivo")
			.onClick(()-> new CargaEmpresasDesdeArchivoWindow(this).open());
		
		new Label(mainPanel);
	}
}
