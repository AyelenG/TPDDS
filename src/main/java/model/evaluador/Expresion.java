package model.evaluador;

import java.math.BigDecimal;

import model.Periodo;
import model.Usuario;

public interface Expresion {
	BigDecimal getValor(Periodo periodo, Usuario user);
}
