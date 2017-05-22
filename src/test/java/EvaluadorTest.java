import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import model.Cuenta;
import model.Indicador;
import model.Periodo;
import model.parser.evaluador.Evaluador;

public class EvaluadorTest {	

	
	Periodo periodo = new Periodo();
	Cuenta cuenta1 = new Cuenta("EBITDA", new BigDecimal(5));
	Cuenta cuenta2 = new Cuenta("FDS", new BigDecimal(3));
	
	@Before
	public void inicio() {
		periodo.agregarCuenta(cuenta1);
		periodo.agregarCuenta(cuenta2);
	}
	
	@Test
	public void compruebaValoresDeCuentas(){
		assertEquals(3, periodo.buscarCuenta(new Cuenta("FDS")).getValor().doubleValue(), 0);
		assertEquals(5, periodo.buscarCuenta(new Cuenta("EBITDA")).getValor().doubleValue(), 0);
	}
	
	@Test
	public void evaluacionConUnPeriodoConCuentasDevuelve3(){
		assertEquals(3, Evaluador.evaluar(new Indicador("2","([FDS]+[EBITDA]+1)/3"), periodo).doubleValue(), 0);
	}
	
	@Test
	public void nullSiCuentaNoExiste(){
		assertNull(Evaluador.evaluar(new Indicador("3","[FDS]+[No existe]+1"), periodo));
	}
		
	@Test
	public void verificarEvaluacion(){
		assertEquals(4, Evaluador.evaluar(new Indicador("4","5+2-3"), null).doubleValue(), 0);
	}
	
	@Test
	public void verificarEvaluacion1(){
		assertEquals(6.5, Evaluador.evaluar(new Indicador("5","(5+3/2)"), null).doubleValue(), 0);
	}
	
	@Test
	public void verificarEvaluacion2(){
		assertEquals(0, Evaluador.evaluar(new Indicador("6","0/2"), null).doubleValue(), 0);
	}
	
	@Test
	public void verificarFuncionCosenoCeroGradosDa1(){
		assertEquals(1, Evaluador.evaluar(new Indicador("8","cos0"), null).doubleValue(), 0);
	}
	
}
