import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Indicador;
import model.Indicadores;
import model.Periodo;
import model.parser.evaluador.Evaluador;

public class EvaluadorTest {

	Indicador indicador = new Indicador("PODER", "[FDS] * [EBITDA]");
	Periodo periodo = new Periodo();
	Cuenta cuenta1 = new Cuenta("EBITDA", new BigDecimal(5));
	Cuenta cuenta2 = new Cuenta("FDS", new BigDecimal(3));
	Indicadores indicadores = new Indicadores();

	@Before
	public void inicio() {
		periodo.agregarCuenta(cuenta1);
		periodo.agregarCuenta(cuenta2);
		indicadores.agregarIndicador(indicador);
	}

	@Test
	public void compruebaValoresDeCuentas() {
		assertEquals(3, periodo.buscarCuenta(new Cuenta("FDS")).getValor().doubleValue(), 0);
		assertEquals(5, periodo.buscarCuenta(new Cuenta("EBITDA")).getValor().doubleValue(), 0);
	}

	@Test
	public void evaluacionConIndicadorEnFormula() {
		assertEquals(20, Evaluador.evaluar(new Indicador("1", "<PODER> + 5"), periodo, indicadores).doubleValue(), 0);
	}

	@Test
	public void evaluacionConUnPeriodoConCuentasDevuelve3() {
		assertEquals(3,
				Evaluador.evaluar(new Indicador("2", "([FDS]+[EBITDA]+1)/3"), periodo, indicadores).doubleValue(), 0);
	}

	@Test
	public void nullSiCuentaNoExiste() {
		assertNull(Evaluador.evaluar(new Indicador("3", "[FDS]+[No existe]+1"), periodo, indicadores));
	}

	@Test
	public void verificarEvaluacion() {
		assertEquals(4, Evaluador.evaluar(new Indicador("4", "5+2-3"), null, indicadores).doubleValue(), 0);
	}

	@Test
	public void verificarEvaluacion1() {
		assertEquals(6.5, Evaluador.evaluar(new Indicador("5", "(5+3/2)"), null, indicadores).doubleValue(), 0);
	}

	@Test
	public void verificarEvaluacion2() {
		assertEquals(0, Evaluador.evaluar(new Indicador("6", "0/2"), null, indicadores).doubleValue(), 0);
	}

	@Test
	public void nullSiDividePor0() {
		assertNull(Evaluador.evaluar(new Indicador("7", "5/0"), null, indicadores));
	}

	@Test
	public void verificarFuncionCosenoCeroGradosDa1() {
		assertEquals(1, Evaluador.evaluar(new Indicador("8", "cos0"), null, indicadores).doubleValue(), 0);
	}

}
