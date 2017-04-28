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
/*
	@Before
	public void inicio() {
		this.empresa = new Empresa();
		this.empresa.setNombreEmpresa("Metrogas");
		this.periodo = new Periodo(2017);
		this.fds = new Cuenta("FDS");
		this.EDITBA = new Cuenta("EDITBA");
		periodo.agregarCuenta(fds);
		periodo.agregarCuenta(EDITBA);
		empresa.agregarPeriodo(periodo);
	}

	@Test
	public void registroDeCuentasEnUnPeriodo() {
		Assert.assertEquals(periodo.getCuentas().size(), 2);
	}

	@Test
	public void verificarBuscarCuentaYAgregarSiNoEsta() {
		Assert.assertEquals(fds, periodo.buscarCuentaYAgregar("FDS"));
	}
*/
}
