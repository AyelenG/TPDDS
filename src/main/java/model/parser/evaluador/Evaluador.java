package model.parser.evaluador;

import java.math.BigDecimal;

import model.Cuenta;
import model.Indicador;
import model.Indicadores;
import model.Periodo;

public class Evaluador {
	/* Evalua un Indicador en un Periodo determinado */
	public static BigDecimal evaluar(Indicador indicador, Periodo periodo, Indicadores indicadores) {
		try {
			String formula = indicador.getFormula();

			double result = (new Object() {
				int pos = -1, ch;

				void nextChar() {
					ch = (++pos < formula.length()) ? formula.charAt(pos) : -1;
				}

				boolean eat(int charToEat) {
					while (ch == ' ')
						nextChar();
					if (ch == charToEat) {
						nextChar();
						return true;
					}
					return false;
				}

				double parse() {
					nextChar();
					double x = parseExpression();
					if (pos < formula.length())
						throw new RuntimeException("Unexpected: " + (char) ch);
					return x;
				}

				// Grammar:
				// expression = term | expression `+` term | expression `-` term
				// term = factor | term `*` factor | term `/` factor
				// factor = `+` factor | `-` factor | `(` expression `)`
				// | number | functionName factor | factor `^` factor |
				// `[` nombreCuenta `]` | `<` nombreIndicador `>`

				double parseExpression() {
					double x = parseTerm();
					for (;;) {
						if (eat('+'))
							x += parseTerm(); // addition
						else if (eat('-'))
							x -= parseTerm(); // subtraction
						else
							return x;
					}
				}

				double parseTerm() {
					double x = parseFactor();
					for (;;) {
						if (eat('*'))
							x *= parseFactor(); // multiplication
						else if (eat('/'))
							x /= parseFactor(); // division
						else
							return x;
					}
				}

				double parseFactor() {
					if (eat('+'))
						return parseFactor(); // unary plus
					if (eat('-'))
						return -parseFactor(); // unary minus

					double x;
					int startPos = this.pos;
					if (eat('(')) { // parentheses
						x = parseExpression();
						eat(')');
						/* Reemplazo nombre de Cuentas */
					} else if (eat('[')) {
						while (ch != ']')
							nextChar();
						String nombre = formula.substring(startPos + 1, this.pos);
						x = parseCuenta(nombre);
						eat(']');
						/* Reemplazo nombre de Indicadores */
					} else if (eat('<')) {
						while (ch != '>')
							nextChar();
						String nombre = formula.substring(startPos + 1, this.pos);
						x = parseIndicador(nombre);
						eat('>');
					} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
						while ((ch >= '0' && ch <= '9') || ch == '.')
							nextChar();
						x = Double.parseDouble(formula.substring(startPos, this.pos));
					} else if (ch >= 'a' && ch <= 'z') { // functions
						while (ch >= 'a' && ch <= 'z')
							nextChar();
						String func = formula.substring(startPos, this.pos);
						x = parseFactor();
						if (func.equals("sqrt"))
							x = Math.sqrt(x);
						else if (func.equals("sin"))
							x = Math.sin(Math.toRadians(x));
						else if (func.equals("cos"))
							x = Math.cos(Math.toRadians(x));
						else if (func.equals("tan"))
							x = Math.tan(Math.toRadians(x));
						else
							throw new RuntimeException("Unknown function: " + func);
					} else {
						throw new RuntimeException("Unexpected: " + (char) ch);
					}

					if (eat('^'))
						x = Math.pow(x, parseFactor()); // exponentiation

					return x;
				}

				private double parseIndicador(String nombre) {
					Indicador indicadorInterno = new Indicador(nombre);
					BigDecimal x;
					if (indicadores.existeIndicador(indicadorInterno)) {
						x = Evaluador.evaluar(indicadores.buscarIndicador(indicadorInterno), periodo, indicadores);
						if (x != null)
							return x.doubleValue();
						else
							throw new RuntimeException("No se puede evaluar indicador: " + nombre);
					} else
						throw new RuntimeException("No existe indicador: " + nombre);
				}

				private double parseCuenta(String nombre) {
					Cuenta cuenta = new Cuenta(nombre);
					if (periodo.existeCuenta(cuenta))
						return periodo.buscarCuenta(cuenta).getValor().doubleValue();
					else
						throw new RuntimeException("No existe cuenta: " + nombre);
				}
			}.parse());

			try {
				return BigDecimal.valueOf(result);
			} catch (NumberFormatException e) {
				return null; //division por 0, result = infinito o NaN
			}
		} catch (Exception e) {
			return null; //no se pudo resolver la formula
		}
	}

}
