package ejercicios;

import java.math.BigInteger;

public class Ejercicio1 {

	public static Double facR(Integer numero) {
		if (numero <= 1)
			return 1.;
		return numero * facR(numero - 1);
	}

	public static Double facIter(Integer numero) {
		if (numero < 0)
			numero = numero * -1;
		if (numero <= 0)
			return 1.;
		Double factorial = 1.;
		while (numero > 1) {
			factorial = factorial * (numero);
			numero--;
		}
		return factorial;
	}
	
	public static BigInteger facRBI(Integer numero) {
		if (numero <= 1)
			return BigInteger.ONE;
		return facRBI(numero - 1).multiply(BigInteger.valueOf(numero));
	}

	public static BigInteger facIterBI(Integer numero) {
		if (numero < 0)
			numero = numero * -1;
		if (numero <= 0)
			return BigInteger.ONE;
		BigInteger factorial = BigInteger.ONE;
		while (numero > 1) {
			factorial = factorial.multiply(BigInteger.valueOf(numero));
			numero--;
		}
		return factorial;
	}
	
	
}
