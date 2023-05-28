package ejercicio1;

import java.util.function.Predicate;

import _datos.DatosCafe;

public class CafeHeuristic {
	public static Double heuristic(CafeVertex v1, Predicate<CafeVertex> goal, CafeVertex v2) {
		Double res = 0.;
		if (v1.index() < DatosCafe.getNumVariedades()) {
			res += 100.;
		}
		return 15000.;
	}
}
