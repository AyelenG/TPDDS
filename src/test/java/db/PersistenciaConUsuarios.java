package db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.PersistenceException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import model.Indicador;
import model.Usuario;
import model.repositories.RepoIndicadores;
import model.repositories.RepoUsuarios;

public class PersistenciaConUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private RepoUsuarios repoUsuarios = RepoUsuarios.getInstance();
	private RepoIndicadores repoInd = RepoIndicadores.getInstance();
	
	private Usuario juan;
	private Usuario user;
	private Indicador ind;
	
	@Before
	public void init() {
		juan = new Usuario("juan","juan");
		user = juan;
		
		entityManager().persist(user);
		ind = new Indicador("TestJuan1","1+2",user);
		persist(ind);
		ind = new Indicador("TestJuan2","1+2",user);
		persist(ind);
		
		user = new Usuario("pepe","pepe");
		entityManager().persist(user);
		
		ind = new Indicador("TestPepe1","1+2",user);
		persist(ind);
		ind = new Indicador("TestPepe2","1+2",user);
		persist(ind);
		ind = new Indicador("TestComun","1+2",user);
		persist(ind);
		
		entityManager().clear();
	}
	
	@Test
	public void usuariosSePersitenCorrectamente(){
		Usuario juan = repoUsuarios.buscarElemento(new Usuario("juan"));
		//equivalen al mismo usuario
		assertEquals(this.juan,juan);
	}
	
	@Test
	public void usuariosSePersitenYRecuperanCorrectamenteDentroDeIndicadores(){
		//obtengo el usuario del indicador 1 de juan del repo indicadores
		Indicador testJuan1 = repoInd.buscarElemento(new Indicador("TestJuan1","",this.juan));
		Usuario userTestJuan1 = testJuan1.getUser();
		//obtengo a juan del repo usuarios
		Usuario juan = repoUsuarios.buscarElemento(new Usuario("juan"));
		//son el mismo usuario
		assertTrue(userTestJuan1 == juan);
	}

	@Test
	public void indicadorUniqueKeyAceptaNombresRepetidos(){
		Usuario juan = repoUsuarios.buscarElemento(new Usuario("juan"));
		//puedo persistir un indicador con un nombre existente con otro usuario
		ind = new Indicador("TestComun","1+2",juan);
		persist(ind);
		assertEquals(ind, repoInd.buscarElemento(new Indicador("TestComun","",juan)));
	}
	
	@Test(expected = PersistenceException.class)
	public void indicadorUniqueKeyNoAceptaNombresYUsuariosRepetidos(){
		Usuario pepe = repoUsuarios.buscarElemento(new Usuario("pepe"));
		//no puedo persistir un indicador con un nombre existente con otro usuario
		ind = new Indicador("TestPepe1","1+2",pepe);
		persist(ind);
	}
	
	@Test
	public void obtenerIndicadoresDeJuanDevuelveLaListaCorrecta(){
		List<Indicador> list;
		list = repoInd.findAllBy("user", juan.getId());
		assertEquals("[TESTJUAN1, TESTJUAN2]", list.toString());
	}
	
	@After
	public void clean() {
	}
	
	
}
