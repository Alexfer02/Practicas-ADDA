package ejemplos;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Carretera;
import datos.CiudadEjemplo;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.graphs.Graphs2;

public class Ejemplo2 {

	private static Boolean someEdgeEqualsto1000(CiudadEjemplo v, Graph<CiudadEjemplo, Carretera> gf) {
		Boolean res = false;
		for (Carretera c : gf.edgesOf(v)) {
			if (c.km() == 1000) {
				res = true;
				break;
			}
		}
		return res;

	}

	public static void apartadoA(SimpleWeightedGraph<CiudadEjemplo, Carretera> grafo, String file) {
		Graph<CiudadEjemplo, Carretera> g = Graphs2.explicitCompleteGraph(grafo, 1000., Graphs2::simpleWeightedGraph,
				() -> Carretera.of(1000.), Carretera::km);

		GraphColors.toDot(g, "resultados/ejemplo2/" + file + "A.gv", x -> x.nombre(), x -> "",
				v -> GraphColors.colorIf(Color.blue,
						grafo.edgesOf(v).stream().anyMatch(e -> ((Carretera) e).km() == 1000.)),
				e -> GraphColors.colorIf(Color.blue, g.getEdgeWeight(e) == 1000.));

		System.out.println(file + "A.gv generado en resultados/ejemplo2");
	}

	private static CiudadEjemplo getVertexOfCiudad(Graph<CiudadEjemplo, Carretera> gf, String nombre) {
		return gf.vertexSet().stream().filter(c -> c.nombre().equals(nombre)).findFirst().get();
	}

	public static void apartadoB(SimpleWeightedGraph<CiudadEjemplo, Carretera> gf, String file, String c1, String c2) {
		DijkstraShortestPath<CiudadEjemplo, Carretera> alg = new DijkstraShortestPath<>(gf);

		CiudadEjemplo origen = getVertexOfCiudad(gf, c1);
		CiudadEjemplo destino = getVertexOfCiudad(gf, c2);

		GraphPath<CiudadEjemplo, Carretera> path = alg.getPath(origen, destino);

		GraphColors.toDot(gf, "resultados/ejemplo2/" + file + "B.gv", x -> x.nombre(), x -> x.nombre(),
				v -> GraphColors.styleIf(Style.bold, path.getVertexList().contains(v)),
				e -> GraphColors.styleIf(Style.bold, path.getEdgeList().contains(e)));

		System.out.println(file + "B.gv generado en resultados/ejemplo2");
	}

	public static void apartadoC(SimpleWeightedGraph<CiudadEjemplo, Carretera> gf, String file) {
		Graph<CiudadEjemplo, Carretera> g = Graphs2.toDirectedWeightedGraph(gf,
				(Carretera x) -> Carretera.of(x.km(), x.nombre()));

		GraphColors.toDot(g, "resultados/ejemplo2/" + file + "C.gv", x -> x.nombre(), x -> x.nombre(),
				v -> GraphColors.color(Color.black), e -> GraphColors.style(Style.bold));

		System.out.println(file + "C.gv generado en resultados/ejemplo2");

	}

	public static void apartadoD(SimpleWeightedGraph<CiudadEjemplo, Carretera> gf, String file) {
		// Componentes conexas
		ConnectivityInspector<CiudadEjemplo, Carretera> alg = new ConnectivityInspector<>(gf);
		List<Set<CiudadEjemplo>> ls = alg.connectedSets();
		System.out.println("Hay " + ls.size() + "componentes conexas.");

		GraphColors.toDot(gf, "resultados/ejemplo2/" + file + "D.gv", x -> x.nombre(), x -> x.nombre(),
				v -> GraphColors.color(asignaColor(v, ls, alg)),
				e -> GraphColors.color(asignaColor(gf.getEdgeSource(e), ls, alg)));
	}

	private static Color asignaColor(CiudadEjemplo v, List<Set<CiudadEjemplo>> ls, ConnectivityInspector<CiudadEjemplo, Carretera> alg) {
		Color[] vc = Color.values(); // Una lista de colores
		Set<CiudadEjemplo> s = alg.connectedSetOf(v);// La componente conexa de un vértice dado
		return vc[ls.indexOf(s)];
	}

}
