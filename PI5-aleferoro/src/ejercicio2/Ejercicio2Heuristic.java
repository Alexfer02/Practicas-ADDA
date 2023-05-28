package ejercicio2;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import _datos.DatosEjercicio2;
import us.lsi.common.Set2;

public class Ejercicio2Heuristic {

	public static Double heuristic(Ejercicio2Vertex v1, Predicate<Ejercicio2Vertex> goal, Ejercicio2Vertex v2) {
		Double h = 0.;

		if (!v1.remaining().isEmpty()) {
			h = IntStream.range(v1.index(), DatosEjercicio2.getNumCursosN())
					.filter(c -> !Set2.intersection(v1.remaining(), DatosEjercicio2.getTematicasCurso(c)).isEmpty())
					.mapToDouble(c -> DatosEjercicio2.getCosteCurso(c)).min().orElse(100000.);
		}

		return h;
	}

}
