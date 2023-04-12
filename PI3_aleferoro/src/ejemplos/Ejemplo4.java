package ejemplos;

import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import datos.Pasillo;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejemplo4 {
	public static Set<String> apartadoA(Graph<String, Pasillo> gf) {
		//Recubrimiento de vertices
		GreedyVCImpl<String, Pasillo> algA = new GreedyVCImpl<>(gf);
		Set<String> cruces = algA.getVertexCover();
		return cruces;
	}
	
	public static void apartadoB(Graph<String, Pasillo> gf, Set<String> cruces, String file) {
		Predicate<String> pv = c -> cruces.contains(c);
		Predicate<Pasillo> pe = p -> cruces.contains(gf.getEdgeSource(p)) && cruces.contains(gf.getEdgeTarget(p));
		
		Graph<String, Pasillo> sgf = SubGraphView.of(gf, pv, pe);
		
		//Apartado b
		//Componentes conexas
		ConnectivityInspector<String, Pasillo> algB1 = new ConnectivityInspector<>(sgf);
		System.out.println("Número de equipos necesarios: " + algB1.connectedSets().size());
		
		//Recubrimiento minimo
		KruskalMinimumSpanningTree<String, Pasillo> algB2 = new KruskalMinimumSpanningTree<>(sgf);
		SpanningTree<Pasillo> tree = algB2.getSpanningTree();
		System.out.println(String.format("Metros de cable necesarios: %.1f", tree.getWeight()));
		
		
		//Apartado c
		GraphColors.toDot(gf,
						"resultados/ejemplo4/" + file + ".gv",
						v -> v.toString(),
						v -> "",
						v -> GraphColors.colorIf(Color.blue, Color.blank, cruces.contains(v)),
						e -> GraphColors.colorIf(Color.blue, Color.blank, tree.getEdges().contains(e)));
		
		
	}
}
