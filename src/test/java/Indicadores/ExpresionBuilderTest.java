package Indicadores;

import org.junit.Test;

import exceptions.FormulaIndicadorIncorrectaException;
import model.parser.ExpresionBuilder;


public class ExpresionBuilderTest {
	private final String formulaCorrecta = "6.876 * ((<PEPE> + -0.41) / ([INGRESOS BRUTOS] - 124))" ;
	private final String formulaIncorrecta = "+3.3031 *- ([EBITDA]";
	
	@Test
	public void verificarFormulaCorrecta() {
		new ExpresionBuilder(formulaCorrecta).build();
	}

	@Test(expected = FormulaIndicadorIncorrectaException.class)
	public void verificarFormulaIncorrecta() {
		new ExpresionBuilder(formulaIncorrecta).build();
	}

}