package ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Trio;

public class Ejercicio4 {

	private static String toString(Integer a) {

		return a.toString();
	}

	public static String RecursivaSinMemoria(Integer a, Integer b, Integer c) {
		String s = "";
		if (a < 2 && b <= 2 || c < 2) {
			s = "(" + toString(a) + "+" + toString(b) + "+" + toString(c) + ")";
		} else if (a < 3 || b < 3 && c <= 3) {
			s = "(" + toString(c) + "+" + toString(b) + "+" + toString(a) + ")";
		} else if (b % a == 0 && (a % 2 == 0 || b % 3 == 0)) {
			s = "(" + RecursivaSinMemoria(a - 1, b / a, c - 1) + "*" + RecursivaSinMemoria(a - 2, b / 2, c / 2);
		} else {
			s = "(" + RecursivaSinMemoria(a / 2, b - 2, c / 2) + "/" + RecursivaSinMemoria(a / 3, b - 1, c / 3) + ")";
		}
		return s;
	}

	public static String RecursivaConMemoria(Integer a, Integer b, Integer c) {
		Map<Trio, String> memoria = new HashMap<>();
		return recursivaMemoria(a, b, c, memoria);
	}

	private static String recursivaMemoria(Integer a, Integer b, Integer c, Map<Trio, String> memoria) {
		String ac = null;
		Trio<Integer, Integer, Integer> key = Trio.of(a, b, c);
		if (memoria.containsKey(key)) {
			ac = memoria.get(key);
		} else {
			if (a < 2 && b <= 2 || c < 2) {
				ac = "(" + toString(a) + "+" + toString(b) + "+" + toString(c) + ")";
			} else if (a < 3 || b < 3 && c <= 3) {
				ac = "(" + toString(c) + "+" + toString(b) + "+" + toString(a) + ")";
			} else if (b % a == 0 && (a % 2 == 0 || b % 3 == 0)) {
				ac = "(" + recursivaMemoria(a - 1, b / a, c - 1, memoria) + "*"
						+ recursivaMemoria(a - 2, b / 2, c / 2, memoria);
			} else {
				ac = "(" + recursivaMemoria(a / 2, b - 2, c / 2, memoria) + "/"
						+ recursivaMemoria(a / 3, b - 1, c / 3, memoria) + ")";
			}

			memoria.put(key, ac);

		}
		return ac;
	}

	public static String Iterativa(Integer a, Integer b, Integer c) {
		String acc = "";
		Map<Trio, String> map = new HashMap<>();
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				for (int v = 0; v <= c; v++) {
					if (i < 2 && j <= 2 || v < 2) {
						acc = "(" + toString(i) + "+" + toString(j) + "+" + toString(v) + ")";
					} else if (i < 3 || j < 3 && v <= 3) {
						acc = "(" + toString(v) + "-" + toString(j) + "-" + toString(i) + ")";
					} else if (j % i == 0 && (i % 2 == 0 || j % 3 == 0)) {
						acc = "(" + map.get(Trio.of(i - 1, j / i, v - 1)) + "*" + map.get(Trio.of(i - 2, j / 2, v / 2))
								+ ")";
					} else {
						acc = "(" + map.get(Trio.of(i / 2, j - 2, v / 2)) + "/" + map.get(Trio.of(i / 3, j - 1, v / 3))
								+ ")";
					}
					map.put(Trio.of(i, j, v), acc);
				}
			}
		}
		return map.get(Trio.of(a, b, c));
	}

}
