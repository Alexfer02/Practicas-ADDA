package ejemplos;

import java.util.stream.Stream;

public class Ejemplo2 {

	public static void main(String[] args) {

	}

	public static String solucionRecursivaNoFinal(Integer a, Integer b) {
		String ac = null;
		if (a < 5 || b < 5) { // caso base
			ac = String.format("(%d)", a * b);
		} else { // caso recursivo
			ac = String.format("(%d)", a + b) + solucionRecursivaNoFinal(a / 2, b - 2);
		}

		return ac;
	}

	public static String solucionRecursivaFinal(Integer a, Integer b) {
		String ac = "";
		ac = solucionRecursivaFinal(a, b, ac);
		return ac;
	}

	private static String solucionRecursivaFinal(Integer a, Integer b, String ac) {
		if (a < 5 || b < 5) { // caso base
			ac = ac + String.format("(%d)", a * b);
		} else { // caso recursivo
			ac = solucionRecursivaFinal(a / 2, b - 2, String.format("(%s%d)", ac, a + b));
		}

		return ac;
	}

	public static String solucionIterativa(Integer a, Integer b) {
		String ac = "";
		while (!(a < 5 || b < 5)) { // mientras haya siguiente no estoy en el caso base
			ac = String.format("(%s%d)", ac, a + b);
			a = a / 2;
			b = b - 2;
		}
		ac = ac + String.format("(%d)", a * b);

		return ac;
	}

	private static record Tupla(String ac, Integer a, Integer b) {
		public static Tupla of(String ac, Integer a, Integer b) {
			return new Tupla(ac, a, b);
		}

		public static Tupla First(Integer a, Integer b) { // VALOR INICIAL DE LA SECUENCIA
			return of("", a, b);
		}

		public Tupla next() { // METODO SIGUIENTE DE LA SECUENCIA
			return of(ac + String.format("%d", a + b), a / 2, b - 2);
		}

		public Boolean isBaseCase() { // PARADO
			return a < 5 || b < 5;
		}
	}

	public static String solucionFuncional(Integer a, Integer b) {
		Tupla elementoFinal = Stream.iterate(Tupla.First(a, b), elem -> elem.next()).filter(elem -> elem.isBaseCase())
				.findFirst().get();
		// Tupla elementoFinal2 = Stream.iterate(Tupla.First(a,
		// b),elem->elem.next()).dropWhile(elem->!(elem.isBaseCase())).findFirst().get();

		return elementoFinal.ac + String.format("(%d)", elementoFinal.a * elementoFinal.b);
	}

}
