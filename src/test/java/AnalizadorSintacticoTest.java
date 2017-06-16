import static org.junit.Assert.*;

import org.junit.Test;

import model.parser.AnalizadorSintactico;

public class AnalizadorSintacticoTest {
	private final String formulaCorrecta = "6.876 * ((<PEPE> + -0.41) / ([INGRESOS BRUTOS] - 124))" ;
	private final String formulaIncorrecta = "+3.3031 *- ([EBITDA]";
	
	@Test
	public void verificarFormulaCorrecta() {
		assertTrue(new AnalizadorSintactico(formulaCorrecta).chequear());
	}

	@Test
	public void verificarEvaluacion1() {
		assertFalse(new AnalizadorSintactico(formulaIncorrecta).chequear());
	}

}
