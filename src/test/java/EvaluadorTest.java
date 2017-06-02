import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import exceptions.NoSePuedeEvaluarException;
import model.Cuenta;
import model.Indicador;
import model.Indicadores;
import model.Periodo;
import model.parser.evaluador.Evaluador;

public class EvaluadorTest {

	Indicador indicador1 = new Indicador("PODER", "[FDS] * [EBITDA]");
	Indicador indicador2 = new Indicador("INCALCULABLE", "[NO ESTA] / 5");
	Periodo periodo = new Periodo();
	Cuenta cuenta1 = new Cuenta("EBITDA", new BigDecimal(5));
	Cuenta cuenta2 = new Cuenta("FDS", new BigDecimal(3));
	Indicadores indicadores = new Indicadores();

	@Before
	public void inicio() {
		periodo.agregarCuenta(cuenta1);
		periodo.agregarCuenta(cuenta2);
		indicadores.agregarIndicador(indicador1);
		indicadores.agregarIndicador(indicador2);
	}

	@Test
	public void compruebaValoresDeCuentas() {
		assertEquals(3, periodo.buscarCuenta(new Cuenta("FDS")).getValor().doubleValue(), 0);
		assertEquals(5, periodo.buscarCuenta(new Cuenta("EBITDA")).getValor().doubleValue(), 0);
	}

	@Test
	public void evaluacionConIndicadorEnFormula() {
		assertEquals(20, new Indicador("1", "<PODER> + 5").evaluar(periodo, indicadores).doubleValue(), 0);
	}

	@Test
	public void evaluacionConUnPeriodoConCuentasDevuelve3() {
		assertEquals(3, new Indicador("2", "([FDS]+[EBITDA]+1)/3").evaluar(periodo, indicadores).doubleValue(), 0);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiCuentaNoEstaEnPeriodo() {
		new Indicador("3", "[FDS]+[NO ESTA]+1").evaluar(periodo, indicadores);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiIndicadorInternoNoSePuedeCalcular() {
		new Indicador("4", "8 * <INCALCULABLE>").evaluar(periodo, indicadores);
	}

	@Test
	public void verificarEvaluacion() {
		assertEquals(4, new Indicador("5", "5+2-3").evaluar(null, indicadores).doubleValue(), 0);
	}

	@Test
	public void verificarEvaluacion1() {
		assertEquals(6.5, Evaluador.evaluar(new Indicador("6", "(5+3/2)"), null, indicadores).doubleValue(), 0);
	}

	@Test
	public void verificarEvaluacion2() {
		assertEquals(0, new Indicador("7", "0/2").evaluar(null, indicadores).doubleValue(), 0);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiDividePor0() {
		new Indicador("8", "5/0").evaluar(null, indicadores);
	}

	@Test
	public void verificarFuncionCosenoCeroGradosDa1() {
		assertEquals(1, new Indicador("9", "cos0").evaluar(null, indicadores).doubleValue(), 0);
	}

}
