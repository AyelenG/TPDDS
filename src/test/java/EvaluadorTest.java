import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Cuenta;
import model.Indicador;
import model.Indicadores;
import model.Periodo;

@RunWith(Theories.class)
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
		indicadores.agregarElemento(indicador1);
		indicadores.agregarElemento(indicador2);
	}

	static class Pair {
		Indicador ind;
		double valor;

		Pair(Indicador ind, double valor) {
			this.ind = ind;
			this.valor = valor;
		}
	}

	@DataPoints
	public static Pair[] pairs = { 
			new Pair(new Indicador("1", "<PODER> + 5"), 20),
			new Pair(new Indicador("2", "([FDS]+[EBITDA]+1)/3"), 3),
			new Pair(new Indicador("3", "5+2-3"), 4),
			new Pair(new Indicador("4", "0/2"), 0)
	};

	@Test
	public void compruebaValoresDeCuentas() {
		assertEquals(3, periodo.buscarCuenta(new Cuenta("FDS")).getValor().doubleValue(), 0);
		assertEquals(5, periodo.buscarCuenta(new Cuenta("EBITDA")).getValor().doubleValue(), 0);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiCuentaNoEstaEnPeriodo() {
		new Indicador("5", "[FDS]+[NO ESTA]+1").evaluar(periodo, indicadores);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiIndicadorInternoNoSePuedeCalcular() {
		new Indicador("6", "8 * <INCALCULABLE>").evaluar(periodo, indicadores);
	}

	@Theory
	public void verificarEvaluacion(Pair pair) {
		assertEquals(pair.valor, pair.ind.evaluar(periodo, indicadores).doubleValue(),0);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiDividePor0() {
		new Indicador("7", "5/0").evaluar(null, indicadores);
	}


}
