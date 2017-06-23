package Listas;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.runner.RunWith;

import model.Cuenta;
import model.Periodo;

import org.junit.experimental.theories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(Theories.class)
public class SumatoriaTest {
	
	@Test
	public void sumatoriaDeLista(){		
		List <BigDecimal> lista = Arrays.asList(new BigDecimal(5), new BigDecimal(5),new BigDecimal(5));
		int sumatoria = lista.stream().mapToInt(bigDecimal -> bigDecimal.intValue()).sum();
		Assert.assertEquals(15, sumatoria);

	}
	
	@Test
	public void sumatoriaIntDeListaDePeriodos(){
	
	Periodo periodo1 = new Periodo();
	Periodo periodo2 = new Periodo();
	Periodo periodo3 = new Periodo();
	Cuenta cuenta = new Cuenta("ROE", new BigDecimal(5));
	periodo1.agregarCuenta(cuenta);
	periodo2.agregarCuenta(cuenta);
	periodo3.agregarCuenta(cuenta);
	List<Periodo> lista = Arrays.asList(periodo1,periodo2,periodo3);
	
	
	int sumatoria = lista.stream().mapToInt(periodo -> periodo.buscarCuenta(cuenta).getValor().intValue()).sum();
	Assert.assertEquals (15, sumatoria);
	}
	
	@Test
	public void sumatoriaBigDecimalDeListaDePeriodos(){
	
	Periodo periodo1 = new Periodo();
	Periodo periodo2 = new Periodo();
	Periodo periodo3 = new Periodo();
	Cuenta cuenta = new Cuenta("ROE", new BigDecimal(5));
	periodo1.agregarCuenta(cuenta);
	periodo2.agregarCuenta(cuenta);
	periodo3.agregarCuenta(cuenta);
	List<Periodo> lista = Arrays.asList(periodo1,periodo2,periodo3);
	
	
	/*BigDecimal sumatoria = lista.stream().map(periodo -> periodo.buscarCuenta(cuenta).getValor()).sumarBigDecimal();
	Assert.assertEquals (15, sumatoria);*/
	}
	
	
}
