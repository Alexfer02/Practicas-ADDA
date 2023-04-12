package ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejercicio3 {

	private static Punto2D punto(String linea) {
		List<String> valores = Arrays.asList(linea.split(","));
		return Punto2D.of(Double.valueOf(valores.get(0)), Double.valueOf(valores.get(1)));
	}

	private static Boolean esCuadrante(Punto2D punto) {
		Boolean res = false;
		if (punto.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || punto.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
			res = true;
		}
		return res;
	}

	private static String nx(Iterator<String> it) {
		String r = null;
		while (it.hasNext() && r == null) {
			String ps = it.next();
			Punto2D paux = punto(ps);
			if (paux != null && esCuadrante(paux)) {
				r = ps;
			}
		}
		return r;
	}

	public static List<Punto2D> iterativa(Iterator<String> it1, Iterator<String> it2) {
		List<Punto2D> res = new ArrayList<>();
		String p1 = nx(it1);
		String p2 = nx(it2);
		while (p1 != null || p2 != null) {
			if (p1 == null) {
				res.add(punto(p2));
				p2 = nx(it2);
			} else if (p2 == null) {
				res.add(punto(p1));
				p1 = nx(it1);
			} else {
				if ((punto(p1)).compareTo(punto(p2)) == -1) {
					res.add(punto(p1));
					p1 = nx(it1);
				} else {
					res.add(punto(p2));
					p2 = nx(it2);
				}
			}
		}

		return res;

	}

	private static record Tupla(Iterator<String> a, Iterator<String> b, List<Punto2D> ac, String p1, String p2) {
		public static Tupla of(Iterator<String> a, Iterator<String> b, List<Punto2D> ac, String p1, String p2) {
			return new Tupla(a, b, ac, p1, p2);

		}

		public static Tupla First(Iterator<String> it1, Iterator<String> it2) {
			return of(it1, it2, new ArrayList<>(), nx(it1), nx(it2));
		}

		public Tupla next() {
			Tupla sol = null;
			if (!(p1 == null || p2 == null)) {
				if ((punto(p1)).compareTo(punto(p2)) == -1) {
					ac.add(punto(p1));
					sol = of(a, b, ac, nx(a), p2);
				} else {
					ac.add(punto(p2));
					sol = of(a, b, ac, p1, nx(b));
				}
			} else if ((p1 == null && p2 != null)) {
				ac.add(punto(p2));
				sol = of(a, b, ac, p1, nx(b));
			} else if ((p2 == null && p1 != null)) {
				ac.add(punto(p1));
				sol = of(a, b, ac, nx(a), p2);
			}
			return sol;
		}

		public Boolean fin() {
			Boolean s = false;
			if (p1 == null && p2 == null) {
				s = true;
			}
			return s;
		}
	}

	public static List<Punto2D> funcional(Iterator<String> it1, Iterator<String> it2) {
		Tupla sol = Stream.iterate(Tupla.First(it1, it2), elem -> elem.next()).filter(elem -> elem.fin()).findFirst()
				.get();
		return sol.ac;
	}

	public static List<Punto2D> recursivaFinal(Iterator<String> it1, Iterator<String> it2) {
		return recursiva(it1, it2, new ArrayList<>(), nx(it1), nx(it2));
	}

	private static List<Punto2D> recursiva(Iterator<String> it1, Iterator<String> it2, List<Punto2D> acc, String p1,
			String p2) {

		if (!(p1 == null || p2 == null)) {
			if ((punto(p1)).compareTo(punto(p2)) == -1) {
				acc.add(punto(p1));
				recursiva(it1, it2, acc, nx(it1), p2);
			} else {
				acc.add(punto(p2));
				recursiva(it1, it2, acc, p1, nx(it2));
			}
		} else if ((p1 == null && p2 != null)) {
			acc.add(punto(p2));
			recursiva(it1, it2, acc, p1, nx(it2));
		} else if ((p2 == null && p1 != null)) {
			acc.add(punto(p1));
			recursiva(it1, it2, acc, nx(it1), p2);
		}

		return acc;

	}
}