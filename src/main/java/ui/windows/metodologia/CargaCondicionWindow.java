package ui.windows.metodologia;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.metodologia.CargaMetodologiaViewModel;

@SuppressWarnings("serial")
public class CargaCondicionWindow extends Window<Object> {
	
	private CargaMetodologiaViewModel parentVM;
	
	public CargaCondicionWindow(WindowOwner parent, CargaMetodologiaViewModel _parentVM) {
		super(parent, new Object());
		parentVM = _parentVM;
	}
	
	public void createContents(Panel mainPanel) {
		
		this.setTitle("Agregar Condición");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setHeight(20).setWidth(200);
		
		new Button(mainPanel)
			.setCaption("Taxativa")
			.onClick(()-> new NuevaCondicionTaxativaWindow(this, parentVM).open());
		new Button(mainPanel)
			.setCaption("No Taxativa")
			.onClick(()-> new NuevaCondicionNoTaxativaWindow(this, parentVM).open());
		new Button(mainPanel)
			.setCaption("Combinada")
			.onClick(()-> new NuevaCondicionPrimitivaWindow(this, parentVM).open());
		
		new Label(mainPanel).setHeight(20);
		
		new Button(mainPanel)
			.setCaption("Cerrar")
			.onClick(() -> this.close());
	}
}
