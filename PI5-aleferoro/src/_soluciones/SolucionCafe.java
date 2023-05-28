package _soluciones;

import java.util.List;

import org.jgrapht.GraphPath;

import _datos.DatosCafe;
import ejercicio1.CafeEdge;
import ejercicio1.CafeVertex;
import us.lsi.common.Multiset;

public class SolucionCafe {
	// Solucion cafe de la PI4
	public static SolucionCafe of(List<Integer> ls) {
		return new SolucionCafe(ls);
	}

	// Ahora en la PI5

	public static SolucionCafe of(GraphPath<CafeVertex, CafeEdge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionCafe res = of(ls);
		res.path = ls;
		return res;
	}

	// De la PI4
	private Integer suma;
	private Multiset<Integer> solucion;

	// Ahora en la PI5
	private List<Integer> path;

	private SolucionCafe(List<Integer> ls) {
		suma = 0;
		solucion = Multiset.of();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = ls.get(i);
				Integer v = DatosCafe.getBeneficioVariedad(i);
				solucion.add(v, e);
				suma += v * e;
			}
		}
	}

	// Ahora en la PIS

	public String toString() {
		String res = String.format("Beneficio = %d", suma);
		return path == null ? res : String.format("%s\nPath de la solucion: %s", res, path);
	}
}