package model.evaluador;

import java.math.BigDecimal;

import model.Periodo;
import model.repositories.RepoIndicadores;

public interface Expresion {
	BigDecimal getValor(Periodo periodo, RepoIndicadores indiceIndicadores);
}
