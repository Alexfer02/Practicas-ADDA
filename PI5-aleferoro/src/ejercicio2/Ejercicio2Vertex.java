package ejercicio2;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import _datos.DatosEjercicio2;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio2Vertex(Integer index, Set<Integer> remaining, Set<Integer> centers)
		implements VirtualVertex<Ejercicio2Vertex, Ejercicio2Edge, Integer> {

// Vértice: (curso por elegir o no, temáticas por cubrir, centros elegidos)

	public static Ejercicio2Vertex of(Integer i, Set<Integer> r, Set<Integer> c) {
		return new Ejercicio2Vertex(i, r, c);
	}

	public static Ejercicio2Vertex initial() {
		return of(0, DatosEjercicio2.getTematicas().stream().collect(Collectors.toSet()), Set2.empty());
	}

	public static Predicate<Ejercicio2Vertex> goal() {
		return v -> v.index() == DatosEjercicio2.getNumCursosN();
	}

	public static Predicate<Ejercicio2Vertex> goalHasSolution() {
		return v -> v.remaining().isEmpty() && v.centers().size() <= DatosEjercicio2.getMaxCentros();
	}

	private Boolean noViable() {
		return this.centers().size() == DatosEjercicio2.getMaxCentros()
				&& !this.centers().contains(DatosEjercicio2.getCentroCurso(this.index()));
	}

	@Override
	public List<Integer> actions() {
		if (goal().test(this)) {
			return List2.empty();
		} else if (noViable()) {
			return List2.of(0);
		} else {
			return List2.of(0, 1);
		}
	}

	@Override
	public Ejercicio2Vertex neighbor(Integer a) {
		Integer n_indice = this.index() + 1;
		Set<Integer> n_remaining = Set2.copy(this.remaining());
		Set<Integer> n_centers = Set2.copy(this.centers());

		if (a == 1) {
			n_remaining = Set2.difference(n_remaining, DatosEjercicio2.getTematicasCurso(this.index()));

			n_centers.add(DatosEjercicio2.getCentroCurso(this.index()));
		}

		return of(n_indice, n_remaining, n_centers);
	}

	@Override
	public Ejercicio2Edge edge(Integer a) {
		return Ejercicio2Edge.of(this, neighbor(a), a);
	}

	public Ejercicio2Edge greedyEdge() {
		Set<Integer> rest = Set2.difference(this.remaining(), DatosEjercicio2.getTematicasCurso(this.index()));
		Integer a;
		if (noViable()) {
			a = 0;
		} else {
			a = rest.equals(this.remaining()) ? 0 : 1;
		}
		return edge(a);
	}
}
