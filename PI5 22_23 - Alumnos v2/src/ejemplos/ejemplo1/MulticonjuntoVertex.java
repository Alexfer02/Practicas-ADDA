package ejemplos.ejemplo1;

import java.util.List;

import org.antlr.v4.runtime.IntStream;

import _datos.DatosMulticonjunto;
import us.lsi.graphs.virtual.VirtualVertex;

public record MulticonjuntoVertex(Integer index, Integer remaining)
		implements VirtualVertex<MulticonjuntoVertex, MulticonjuntoEdge, Integer> {

	public static MulticonjuntoVertex of(Integer i, Integer rest) {
		return new MulticonjuntoVertex(i, rest);
	}

	// TODO Consulte las clases GraphsPI5 y TestPI5

	@Override
	public List<Integer> actions() {
		// TODO Alternativas de un vertice
		return null;
	}

	@Override
	public MulticonjuntoVertex neighbor(Integer a) {
		// TODO Vertice siguiente al actual segun la alternativa a
		return null;
	}

	@Override
	public MulticonjuntoEdge edge(Integer a) {
		return MulticonjuntoEdge.of(this, neighbor(a), a);
	}

	// Se explica en practicas.
	public MulticonjuntoEdge greedyEdge() {
		return existeMayorMejor() ? edge(0) : edge(remaining / DatosMulticonjunto.getElemento(index));
	}

	private Boolean existeMayorMejor() {
		Integer max = IntStream.range(index + 1, DatosMulticonjunto.getNumElementos())
				.map(i -> DatosMulticonjunto.getElemento(i)).filter(e -> remaining % e == 0).max().orElse(0);
		return max > DatosMulticonjunto.getElemento(index);
	}

	@Override
	public String toString() {
		return String.format("%d; %d", index, remaining);
	}

}
