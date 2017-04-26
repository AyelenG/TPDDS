import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import model.Cuenta;
import model.Empresa;
import model.Periodo;

public class pruebaDeMetodosTest {
	private Empresa empresa;
	private Periodo periodo;
	private Cuenta fds;
	private Cuenta EDITBA;
	@Before
	public void inicio(){
		this.empresa=new Empresa("Metrogas");
		this.periodo= new Periodo(2017);
		this.fds=new Cuenta("FDS");
		this.EDITBA=new Cuenta("EDITBA");
		periodo.agregarCuenta(fds);
		periodo.agregarCuenta(EDITBA);
	}
	@Test
	public void registroDeCuentasEnUnPeriodo() {
		empresa.agregarPeriodo(periodo);
		Assert.assertEquals(periodo.getCuentas().size(),2);
	}
	@Test
	public void agregaLaCuentaSiNoEsta(){
		empresa.agregarPeriodo(periodo);
		periodo.buscarCuenta("Free Cash Flow");
		//si no la agrega debe dar 2
		Assert.assertEquals(periodo.getCuentas().size(),3);
	}
}
