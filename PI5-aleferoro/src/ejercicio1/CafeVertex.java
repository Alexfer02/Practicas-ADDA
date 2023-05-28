package ejercicio1;

import java.util.List;
import java.util.function.Predicate;

import _datos.DatosCafe;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CafeVertex(Integer index, List<Integer> remaining)
		implements VirtualVertex<CafeVertex, CafeEdge, Integer> {

	public static CafeVertex of(Integer i, List<Integer> rem) {
		return new CafeVertex(i, rem);
	}
	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		if (index == DatosCafe.getNumVariedades()) {
			return List2.empty();
		} else {
			Integer maxKg = DatosCafe.getMaxKgVariedad(index);
			for (int i = 0; i <= maxKg; i++) {
				alternativas.add(i);
			}
		}
		return alternativas;

	}

	@Override
	public CafeVertex neighbor(Integer a) {
		List<Integer> newRem = List2.copy(remaining);
		if (a == 0) {
			return of(index + 1, List2.copy(remaining));
		} else {
			// TODO sacar la nueva lista de tipos de
			// cafe y sus cantidades restantes
			// TODO sacar la nueva lista de tipos de
			// cafe y sus cantidades restantes
			for (int i = 0; i < DatosCafe.getNumTipos(); i++) {
				newRem.set(i, remaining.get(i) - a * (10 * DatosCafe.getKgTipoVariedad(i, index).intValue()));
			}
		}
		return of(index + 1, newRem);

	}

	@Override
	public CafeEdge edge(Integer a) {
		return CafeEdge.of(this, neighbor(a), a);
	}

	public static CafeVertex initial() {
		return of(0, DatosCafe.tipos);
	}

	public static Predicate<CafeVertex> goal() {
		return v -> v.index() == DatosCafe.getNumVariedades();
	}

	public static Predicate<CafeVertex> goalHasSolution() {
		return v -> true;
	}

	public CafeEdge greedyEdge() {
		Integer a = DatosCafe.getMaxKgVariedad(index);
		return edge(a);
	}

	

}
