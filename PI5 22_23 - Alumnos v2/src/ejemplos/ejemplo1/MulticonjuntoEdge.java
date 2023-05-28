package ejemplos.ejemplo1;

import java.util.List;

import _datos.DatosMulticonjunto;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record MulticonjuntoEdge(MulticonjuntoVertex source, MulticonjuntoVertex target, Integer action, Double weight)
		implements SimpleEdgeAction<MulticonjuntoVertex, Integer> {

	public static MulticonjuntoEdge of(MulticonjuntoVertex s, MulticonjuntoVertex t, Integer a) {
		// TODO La arista debe tener peso
		return null;
	}

	@Override
	public MulticonjuntoEdge(Integer a) {
		return MulticonjuntoEdge.of(this, neighbor(a), a);
	}
	
	public List<Integer> actions(){
		List<Integer> alternativas = List2.empty();
		if(index<DatosMulticonjunto.getNumElementos()) {
			Integer value = DatosMulticonjunto.getElemento(index);
			Integer options = remainig / value;
			if(index== DatosMulticonjunto.getNumElementos()-1) {
				if(remaining %value==0) {
					alternativas = List2.of(remaining/value);
				}else {
					alternativas=List2.of(0);
				}
			}else {
				alternativas=List2.rangeList(0, options+1);
			}
		}
		return alternativas;
	}

	private MulticonjuntoVertex neighbor(Integer a) {
		return of(index + 1,remaining -a*DatosMulticonjunto.getElemento(index));
	}

	public MulticonjuntoEdge greedyEdge() {
		return existeMayorMejor() ? edge(0) : edge(remaining / DatosMulticonjunto.getElemento(index));
	}

	private boolean existeMayorMejor() {
		Integer max = IntStream.range(index+1,DatosMulticonjunto.getNumElementos())
				.map(i-> DatosMulticonjunto.getElemento(i)).filter(e-> remaining%e==0).max().orElse(0);
		return max>DatosMulticonjunto.getElemento(index);
	}

	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}

}
