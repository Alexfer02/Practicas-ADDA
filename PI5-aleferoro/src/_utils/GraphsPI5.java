package _utils;

import java.util.function.Predicate;

import ejercicio1.CafeEdge;
import ejercicio1.CafeHeuristic;
import ejercicio1.CafeVertex;
import ejercicio2.Ejercicio2Edge;
import ejercicio2.Ejercicio2Heuristic;
import ejercicio2.Ejercicio2Vertex;
import ejercicio4.ClientesEdge;
import ejercicio4.ClientesHeuristic;
import ejercicio4.ClientesVertex;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.graphs.virtual.EGraphBuilder;
import us.lsi.path.EGraphPath.PathType;

// Clase Factoria para crear los constructores de los grafos de los ejercicios
public class GraphsPI5 {
	// Ejercicio1: Builder
	public static EGraphBuilder<CafeVertex, CafeEdge> cafeBuilder(CafeVertex v_inicial,
			Predicate<CafeVertex> es_terminal) {
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
				.goalHasSolution(CafeVertex.goalHasSolution()).heuristic(CafeHeuristic::heuristic);
	}

	// Ejercicio2: Builder
	public static EGraphBuilder<Ejercicio2Vertex, Ejercicio2Edge> ejercicio2Builder(Ejercicio2Vertex v_inicial,
			Predicate<Ejercicio2Vertex> es_terminal) {
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Min)

				.goalHasSolution(Ejercicio2Vertex.goalHasSolution()).heuristic(Ejercicio2Heuristic::heuristic);
	}

	// Ejercicio4: Builder
	public static EGraphBuilder<ClientesVertex, ClientesEdge> clientesBuilder(ClientesVertex v_inicial,
			Predicate<ClientesVertex> es_terminal) {
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
				.goalHasSolution(ClientesVertex.goalHasSolution()).heuristic(ClientesHeuristic::heuristic);
	}

}
