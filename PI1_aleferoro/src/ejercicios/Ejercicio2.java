package ejercicios;

import java.util.stream.Stream;

import us.lsi.common.Trio;

public class Ejercicio2 {

	private static record Tupla(Integer a, Integer b, String s, Integer acumulador) {
		public static Tupla of(Integer a, Integer b, String s, Integer acumulador) {
			return new Tupla(a, b, s, acumulador);
		}

		public static Tupla First(Integer a, Integer b, String s, Integer acumulador) {
			return of(a, b, s, 0);
		}

		public Tupla next() {
			Tupla devolucion;
			Integer nuevo_acumulador = 0;
			String cadena = "";
			if (a % s.length() < b % s.length()) {
				cadena = s.substring(a % s.length(), b % s.length());
				nuevo_acumulador = acumulador + a + b;
				devolucion = of(a - 1, b / 2, cadena, nuevo_acumulador);

			} else {
				nuevo_acumulador = acumulador + a * b;
				cadena = s.substring(b % s.length(), a % s.length());
				devolucion = of(a / 2, b - 1, cadena, nuevo_acumulador);
			}
			return devolucion;
		}

		public Boolean isBaseCase() {
			return (s.length() == 0) || (a < 2 || b < 2);
		}
	}

	public static Integer funcional(Integer a, Integer b, String s) {
		Tupla ac = Stream.iterate(Tupla.First(a, b, s, 0), elem -> elem.next()).filter(elem -> elem.isBaseCase())
				.findFirst().get();
		return ac.s.length() == 0 ? ac.acumulador + ac.a * ac.a + ac.b * ac.b
				: ac.acumulador + ac.s.length() + ac.a + ac.b;
	}

	public static Integer iterativa(Integer a, Integer b, String s) {
		Integer acc = 0;
		while (!(s.length() == 0 || (a < 2 || b < 2))) {
			if (a % s.length() < b % s.length()) {
				acc = acc + a + b;
				s = s.substring(a % s.length(), b % s.length());
				a = a - 1;
				b = b / 2;

			} else {
				acc = acc + a * b;
				s = s.substring(b % s.length(), a % s.length());
				a = a / 2;
				b = b - 1;

			}
		}
		if (s.length() == 0) {
			acc = acc + a * a + b * b;
		} else if (a < 2 || b < 2) {
			acc = acc + s.length() + a + b;
		}
		return acc;
	}

	public static Integer recursivaFinal(Integer a, Integer b, String s) {
		return recursivaFinal(a, b, s, 0);
	}

	private static Integer recursivaFinal(Integer a, Integer b, String s, Integer res) {
		if (s.length() == 0) {
			res = a * a + b * b;
		} else if (a < 2 || b < 2) {
			res = s.length() + a + b;
		} else if (a % s.length() < b % s.length()) {
			res = a + b + recursivaFinal(a - 1, b / 2, s.substring(a % s.length(), b % s.length()), res);
		} else {
			res = a * b + recursivaFinal(a / 2, b - 1, s.substring(b % s.length(), a % s.length()), res);
		}
		return res;
	}

	public static Integer recursivaNofinal(Integer a, Integer b, String s) {
		Integer res = 0;
		if (s.length() == 0) {
			res = a * a + b * b;
		} else if (a < 2 || b < 2) {
			res = s.length() + a + b;
		} else if (a % s.length() < b % s.length()) {
			res = a + b + recursivaNofinal(a - 1, b / 2, s.substring(a % s.length(), b % s.length()));
		} else {
			res = a * b + recursivaNofinal(a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
		}
		return res;

	}
}
