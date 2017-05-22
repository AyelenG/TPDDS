package model.parser.evaluador;

import java.math.BigDecimal;

import model.Cuenta;
import model.Indicador;
import model.Periodo;

public class Evaluador {
	/* Evalua un Indicador en un Periodo determinado */
	public static BigDecimal evaluar(Indicador indicador, Periodo periodo) {
	    try {
			String formula = indicador.getFormula();
	    	return new BigDecimal ( new Object() {
		        int pos = -1, ch;
		        void nextChar() {
		            ch = (++pos < formula.length()) ? formula.charAt(pos) : -1;
		        }
	
		        boolean eat(int charToEat) {
		            while (ch == ' ') nextChar();
		            if (ch == charToEat) {
		                nextChar();
		                return true;
		            }
		            return false;
		        }
	
		        double parse() {
		            nextChar();
		            double x = parseExpression();
		            if (pos < formula.length()) throw new RuntimeException("Unexpected: " + (char)ch);
		            return x;
		        }
	
		        // Grammar:
		        // expression = term | expression `+` term | expression `-` term
		        // term = factor | term `*` factor | term `/` factor
		        // factor = `+` factor | `-` factor | `(` expression `)`
		        //        | number | functionName factor | factor `^` factor
	
		        double parseExpression() {
		            double x = parseTerm();
		            for (;;) {
		                if      (eat('+')) x += parseTerm(); // addition
		                else if (eat('-')) x -= parseTerm(); // subtraction
		                else return x;
		            }
		        }
	
		        double parseTerm() {
		            double x = parseFactor();
		            for (;;) {
		                if      (eat('*')) x *= parseFactor(); // multiplication
		                else if (eat('/')) x /= parseFactor(); // division
		                else return x;
		            }
		        }
	
		        double parseFactor() {
		            if (eat('+')) return parseFactor(); // unary plus
		            if (eat('-')) return -parseFactor(); // unary minus
	
		            double x;
		            int startPos = this.pos;
		            if (eat('(')) { // parentheses
		                x = parseExpression();
		                eat(')');
		            /* Reemplazo nombre de Cuentas */
		            } else if (eat('[')) { 
		                while (ch != ']') nextChar();
		                String nombre = formula.substring(startPos+1, this.pos);
		                Cuenta cuenta = new Cuenta(nombre);
		                if (periodo.existeCuenta(cuenta))
		                	x = periodo.buscarCuenta(cuenta).getValor().doubleValue();
		                else throw new RuntimeException("No existe cuenta: " + nombre);	            	            	
		            	eat(']');
		            /* Reemplazo nombre de Indicadores */
		            /* Falta cambiar cuentas por indicadores */
		            } else if (eat('<')) {
		                while (ch != '>') nextChar();
		                String nombre = formula.substring(startPos+1, this.pos);
		                Cuenta cuenta = new Cuenta(nombre);
		                if (periodo.existeCuenta(cuenta))
		                	x = periodo.buscarCuenta(cuenta).getValor().doubleValue();
		                else throw new RuntimeException("No existe indicador: " + nombre);	            	            	
		            	eat('>');
		            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
		                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
		                x = Double.parseDouble(formula.substring(startPos, this.pos));
		            } else if (ch >= 'a' && ch <= 'z') { // functions
		                while (ch >= 'a' && ch <= 'z') nextChar();
		                String func = formula.substring(startPos, this.pos);
		                x = parseFactor();
		                if (func.equals("sqrt")) x = Math.sqrt(x);
		                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
		                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
		                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
		                else throw new RuntimeException("Unknown function: " + func);
		            } else {
		                throw new RuntimeException("Unexpected: " + (char)ch);
		            }
	
		            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
	
		            return x;
		        }
		    }.parse() );
	    }
	    catch (Exception e) {
	    	return null;
	    }
	}
	
}
