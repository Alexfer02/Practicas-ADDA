package ejemplos.ejemplo2;


import java.util.List;
import java.util.Set;

import _datos.DatosSubconjuntos;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record SubconjuntosVertex(Integer index, Set<Integer> remaining) 
    implements VirtualVertex<SubconjuntosVertex, SubconjuntosEdge, Integer>{
	
	public static SubconjuntosVertex of(Integer i, Set<Integer> rest) {
		return new SubconjuntosVertex(i, rest);
	}
	
	// TODO Consulte las clases GraphsPI5 y TestPI5 
	
	@Override
	public List<Integer> actions() {
		// TODO Alternativas de un vertice 
		return null;
	}
	
	@Override
	public SubconjuntosVertex neighbor(Integer a) {
		// TODO Vertice siguiente al actual segun la alternativa a 
		return null;
	}
	
	@Override
	public SubconjuntosEdge edge(Integer a) {
		return SubconjuntosEdge.of(this, neighbor(a), a);
	}
	
	// Se explica en practicas.
	public SubconjuntosEdge greedyEdge() {
		Set<Integer> rest = Set2.difference(remaining, DatosSubconjuntos.getElementos(index));
		return rest.equals(remaining)? edge(0): edge(1);
	}
}
