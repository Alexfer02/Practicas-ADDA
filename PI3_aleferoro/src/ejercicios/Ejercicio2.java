package ejercicios;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.tour.HeldKarpTSP;
import datos.Ciudad;
import datos.Trayecto;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio2 {
	public static void ApartadoA(Graph<Ciudad, Trayecto> g, String file) {
		List<Set<Ciudad>> vista = new ConnectivityInspector<>(g).connectedSets();
		Map<Ciudad,Color>mapV=new HashMap<>();
		Map<Trayecto,Color>mapA=new HashMap<>();
		Integer tamaño = vista.size();
		System.out.println("Hay " + tamaño + " grupos de ciudades");
		for(int i=0;i<tamaño;i++) {
			Color color= Color.values()[i];
			Graph<Ciudad,Trayecto>alg=SubGraphView.of(g,vista.get(i));
			for(Ciudad ciudad :vista.get(i)) {
				mapV.put(ciudad, color);
			}
			for(Trayecto arista:alg.edgeSet()) {
				mapA.put(arista, color);
			}
			
		}
		
		for (int i = 1; i <= tamaño; i++) {
			System.out.println("Grupo numero " + i + ":" + vista.get(i - 1));
		}
		GraphColors.toDot(g, "resultados/ejercicio2/A" + file + ".gv", x -> x.nombre(), x -> "",
				v -> GraphColors.color(mapV.get(v)),
				e -> GraphColors.color(mapA.get(e)));
		System.out.println("\n");
	}

/////////////////////////////////////APARTADO B/////////////////////////////////////////////////
	public static void ApartadoB(Graph<Ciudad, Trayecto> g, String file) {

		List<Set<Ciudad>> vista = new ConnectivityInspector<>(g).connectedSets();
		Set<Ciudad> sol=suma(vista);
		System.out.println("Grupo de ciudades que maximiza la suma de puntuaciones: " + sol+"\n");
		Graph<Ciudad, Trayecto> grafo = SubGraphView.of(g, sol);
		GraphColors.toDot(g, "resultados/ejercicio2/B" + file + ".gv", x -> x.nombre()+"\n"+x.puntuacion()+" Puntos", x -> "",
				v -> GraphColors.colorIf(Color.blue, Color.black, grafo.containsVertex(v)),
				e -> GraphColors.colorIf(Color.blue, Color.black, grafo.containsEdge(e)));

	}

	private static Set<Ciudad> suma(List<Set<Ciudad>> lc) {
		Set<Ciudad> sol = lc.get(0);
		Integer suma0 = sol.stream().map(x -> x.puntuacion()).reduce((a, b) -> a + b).get();
		for (int i = 1; i < lc.size(); i++) {
			Integer sumaPro = lc.get(i).stream().map(x -> x.puntuacion()).reduce((a, b) -> a + b).get();
			if (suma0 < sumaPro) {
				suma0 = sumaPro;
				sol = lc.get(i);
			}
		}
		return sol;
	}

////////////////////////////////////APARTADO C////////////////////////////////////////////////////
	public static void ApartadoC(Graph<Ciudad, Trayecto> g, String file) {
		Map<Set<Ciudad>, Double> map = new HashMap<>();
		List<Set<Ciudad>> componentes = new ConnectivityInspector<>(g).connectedSets();
		for (Set<Ciudad> comp : componentes) {
			Graph<Ciudad, Trayecto> grafo = SubGraphView.of(g, comp);
			HeldKarpTSP<Ciudad, Trayecto> alg = new HeldKarpTSP<Ciudad, Trayecto>();
			List<Trayecto> caminos = alg.getTour(grafo).getEdgeList();
			Double precio = 0.;
			for (Trayecto c : caminos) {
				precio += c.precio();
			}
			map.put(comp, precio);
		}
		Set<Ciudad> masBarato = obtenerMasBarato(map.entrySet());
		Graph<Ciudad, Trayecto> vista = SubGraphView.of(g, masBarato);
		// repetimos sabiendo cual es el mas grande
		HeldKarpTSP<Ciudad, Trayecto> alg2 = new HeldKarpTSP<Ciudad, Trayecto>();
		List<Trayecto> caminos = alg2.getTour(vista).getEdgeList();
		GraphColors.toDot(g, "resultados/ejercicio2/C" + file + ".gv", v -> v.nombre(),
				a -> a.precio().toString() + " euros", v -> GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e -> GraphColors.colorIf(Color.blue, caminos.contains(e)));

	}

	private static Set<Ciudad> obtenerMasBarato(Set<Entry<Set<Ciudad>, Double>> entrada) {
		Double precio = 0.;
		Set<Ciudad> sol = new HashSet<>();
		for (Entry<Set<Ciudad>, Double> set : entrada) {
			if (sol.isEmpty()) {
				precio = set.getValue();
				sol = set.getKey();
			} else {
				if (set.getValue() < precio) {
					precio = set.getValue();
					sol = set.getKey();
				}
			}
		}
		System.out.println(
				"Grupo de ciudades a visitar que dan lugar al camino cerrado de menor precio: " + sol + "-->" + precio+"\n");
		return sol;
	}

//////////////////////////////////////////////// APARTADO D/////////////////////////////////////////////////////////
	public static void ApartadoD(Graph<Ciudad, Trayecto> g, String file) {

		ConnectivityInspector<Ciudad, Trayecto> componentes = new ConnectivityInspector<>(g);
		int i = 0;

		for (Set<Ciudad> componente : componentes.connectedSets()) {
			resolverD(g, componente, file, i);
			i++;

		}
	}

	private static void resolverD(Graph<Ciudad, Trayecto> g, Set<Ciudad> set, String file, int numComp) {
		Map<GraphPath<Ciudad, Trayecto>, Double> mapa = new HashMap<>();
		List<Ciudad> lc = set.stream().toList();
		Graph<Ciudad, Trayecto> vista = SubGraphView.of(g, v -> lc.contains(v), null);
		FloydWarshallShortestPaths<Ciudad, Trayecto> alg2 = new FloydWarshallShortestPaths<>(vista);
		for (int i = 0; i < lc.size() - 1; i++) {
			Ciudad ciudad1 = lc.get(i);
			for (int j = i + 1; j < lc.size(); j++) {
				Ciudad ciudad2 = lc.get(j);
				Integer saltos = alg2.getPath(ciudad1, ciudad2).getLength();
				if (saltos > 1 && g.edgesOf(ciudad2).stream()
						.allMatch(a -> g.getEdgeTarget(a) != ciudad1 && g.getEdgeSource(a) != ciudad1)) {
					mapa.put(alg2.getPath(ciudad1, ciudad2), alg2.getPathWeight(ciudad1, ciudad2));
				}
			}
		}
		GraphPath<Ciudad, Trayecto> mas_corto = Collections
				.min(mapa.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
		System.out.println(
				"Para el grupo " + lc + ", las " + " ciudades no conectadas directamente entre las que se puede viajar"
						+ " en menor tiempo son:" + "\nOrigen: " + mas_corto.getStartVertex() + " y Destino: "
						+ mas_corto.getEndVertex() + " --> Tiempo:" + mapa.get(mas_corto));
		System.out.println("\n##################\n");
		List<Ciudad> ciudades = new ArrayList<>();
		ciudades.add(mas_corto.getStartVertex());
		ciudades.add(mas_corto.getEndVertex());
		GraphColors.toDot(g, "resultados/ejercicio2/D" + numComp + file + ".gv", v -> v.nombre(),
				a -> a.duracion().toString() + " minutos", v -> GraphColors.styleIf(Style.bold, ciudades.contains(v)),
				e -> GraphColors.styleIf(Style.bold, mas_corto.getEdgeList().contains(e)));
	}

}