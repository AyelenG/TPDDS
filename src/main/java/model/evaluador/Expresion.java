package model.evaluador;

import java.math.BigDecimal;

import model.Indicadores;
import model.Periodo;

public interface Expresion {
	BigDecimal getValor(Periodo periodo, Indicadores indiceIndicadores);
}
