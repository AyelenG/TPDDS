package Indicadores;
import static org.junit.Assert.*;
import org.junit.experimental.theories.*;
//import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.math.BigDecimal;

import exceptions.NoSePuedeEvaluarException;
import model.Cuenta;
import model.CuentaPeriodo;
import model.Indicador;
import model.Periodo;
import model.Usuario;
import model.repositories.RepoCuentas;
import model.repositories.RepoIndicadores;
import model.repositories.RepoUsuarios;

//@RunWith(Theories.class)
public class EvaluadorTest {

	private static Usuario testUser = new Usuario("test","test");
	private static Periodo periodo = new Periodo();
	private static RepoIndicadores indicadores = RepoIndicadores.getInstance();
	// indicadores.get(0); //Ingreso Neto -- ING. NETO EN OP. CONTINUAS + ING. NETO EN OP. DISC.
	// indicadores.get(1); //Retorno Sobre Capital Total -- (ING. NETO - DIVIDENDOS) / CAP. TOTAL

	@BeforeClass
	public static void inicio() {
		indicadores.insertar(new Indicador("Ingreso Neto", "[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]",testUser));
		indicadores.insertar(new Indicador("Retorno Sobre Capital Total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]",testUser));
		periodo.agregarCuenta(new CuentaPeriodo("Ingreso neto en operaciones continuas", new BigDecimal(5)));
		periodo.agregarCuenta(new CuentaPeriodo("Ingreso neto en operaciones discontinuas", new BigDecimal(3)));
		periodo.agregarCuenta(new CuentaPeriodo("Dividendos", new BigDecimal(2.5)));
		periodo.agregarCuenta(new CuentaPeriodo("Capital total", new BigDecimal(-2.75)));
		pairs[0] = new Pair(indicadores.buscarElemento(new Indicador("Ingreso Neto","",testUser)), 8);
		pairs[1] = new Pair(indicadores.buscarElemento(new Indicador("Retorno Sobre Capital Total","",testUser)), -2);
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
	public static Pair[] pairs = new Pair[2];

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
		Indicador ind = new Indicador("PAPA","[EBITDA]",testUser);
		indicadores.insertar(ind);
		new Indicador("7", "8 * <PAPA>").evaluar(periodo);
	}
	
	@Test(expected = NoSePuedeEvaluarException.class)
	public void mensajeDeErrorSiDividePor0() {
		new Indicador("8", "5/0").evaluar(null);
	}

	@Test
	public void verificarEvaluacion1(){
		assertEquals(pairs[0].valor, pairs[0].ind.evaluar(periodo).doubleValue(), 0);
	}
	
	@Test
	public void verificarEvaluacion2(){
		assertEquals(pairs[1].valor, pairs[1].ind.evaluar(periodo).doubleValue(), 0);
	}
	
//	@Theory
//	public void verificarEvaluacion(Pair pair) {
//		assertEquals(pair.valor, pair.ind.evaluar(periodo).doubleValue(), 0);
//	}

	@AfterClass
	public static void clean(){
		indicadores.clean();
		RepoCuentas.getInstance().clean();
		RepoUsuarios.getInstance().clean();
	}
}
