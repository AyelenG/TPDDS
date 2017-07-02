package Indicadores;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Cuenta;
import model.Indicador;
import model.Periodo;
import model.repositories.RepoIndicadores;

@RunWith(Theories.class)
public class EvaluadorTest {

	Periodo periodo = new Periodo();
	Cuenta cuenta1 = new Cuenta("Ingreso neto en operaciones continuas", new BigDecimal(5));
	Cuenta cuenta2 = new Cuenta("Ingreso neto en operaciones discontinuas", new BigDecimal(3));
	Cuenta cuenta3 = new Cuenta("Dividendos", new BigDecimal(2.5));
	Cuenta cuenta4 = new Cuenta("Capital total", new BigDecimal(-2.75));

	private static RepoIndicadores indicadores = RepoIndicadores.getInstance();
	// indicadores.get(0); //Ingreso Neto -- ING. NETO EN OP. CONTINUAS + ING. NETO EN OP. DISC.
	// indicadores.get(1); //Retorno Sobre Capital Total -- (ING. NETO - DIVIDENDOS) / CAP. TOTAL

	@Before
	public void inicio() {
		periodo.agregarCuenta(cuenta1);
		periodo.agregarCuenta(cuenta2);
		periodo.agregarCuenta(cuenta3);
		periodo.agregarCuenta(cuenta4);
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
			new Pair(indicadores.get(0), 8), new Pair(indicadores.get(1), -2) };

	@Test
	public void compruebaValoresDeCuentas() {
		assertEquals(5,
				periodo.buscarCuenta(new Cuenta("Ingreso neto en operaciones continuas")).getValor().doubleValue(), 0);
		assertEquals(3,
				periodo.buscarCuenta(new Cuenta("Ingreso neto en operaciones discontinuas")).getValor().doubleValue(),
				0);
		assertEquals(2.5, periodo.buscarCuenta(new Cuenta("Dividendos")).getValor().doubleValue(), 0);
		assertEquals(-2.75, periodo.buscarCuenta(new Cuenta("Capital total")).getValor().doubleValue(), 0);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiCuentaNoEstaEnPeriodo() {	
		new Indicador("5", "[NO ESTA] + 1").evaluar(periodo);
	}
	
	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiIndicadorInternoNoSePuedeCalcularPorqueNoExiste() {
		new Indicador("6", "8 * <INCALCULABLE>").evaluar(periodo);
	}

	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiIndicadorInternoNoSePuedeCalcularPorqueNoTieneCuenta() {
		Indicador ind = new Indicador("PAPA","[EBITDA]");
		indicadores.agregarElemento(ind);
		new Indicador("7", "8 * <PAPA>").evaluar(periodo);
	}
	
	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiDividePor0() {
		new Indicador("8", "5/0").evaluar(null);
	}

	@Theory
	public void verificarEvaluacion(Pair pair) {
		assertEquals(pair.valor, pair.ind.evaluar(periodo).doubleValue(), 0);
	}

}
