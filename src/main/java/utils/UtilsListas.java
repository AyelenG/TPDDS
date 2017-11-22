package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UtilsListas {
	public static <T> BigDecimal sumatoria(List<T> list, Function<T,BigDecimal> f ){
		return list.stream().map(f).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public static <T> BigDecimal promedio(List<T> list, Function<T,BigDecimal> f ){
		BigDecimal sumatoria = sumatoria(list,f);
		BigDecimal cant = BigDecimal.valueOf(list.size());
		return sumatoria.divide(cant, 5, RoundingMode.HALF_UP);
	}
	
	public static <T> BigDecimal mediana(List<T> list, Function<T,BigDecimal> f ){
		if(list.isEmpty())
			throw new RuntimeException("Lista vacía");
		List<BigDecimal> valoresOrd = list.stream().map(f).sorted().collect(Collectors.toList());
		return valoresOrd.get(valoresOrd.size() / 2);
	}	
}
