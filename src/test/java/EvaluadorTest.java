import static org.junit.Assert.*;

import org.junit.Test;

import model.parser.evaluador.Evaluador;

public class EvaluadorTest {	

	@Test
	public void verificarEvaluacion(){
		assertEquals(4, Evaluador.eval("5+2-3"), 0);
	}
	
	@Test
	public void verificarEvaluacion1(){
		assertEquals(6.5, Evaluador.eval("(5+3/2)"), 0);
	}
	
}
