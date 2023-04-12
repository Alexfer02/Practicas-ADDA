package ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import datos.Hijo;
import datos.Persona;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {

	///////////////////////////////////////// APARTADO A////////////////////////////////////////////////////////////////////////
	public static void crearVista(String file, SimpleDirectedGraph<Persona, Hijo> g, String nombreVista,
			String nombreAp) {
		var vista = SubGraphView.of(g, comprobarPadres(g));
		GraphColors.toDot(g, "resultados/ejercicio1/" + nombreAp + "/" + file + nombreVista + ".gv", x -> x.nombre(),
				x -> "", v -> GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e -> GraphColors.colorIf(Color.blue, vista.containsEdge(e)));

		System.out.println("Fichero " + file + nombreVista + ".gv generado en resultados/ejercicio1");
	}

	private static Set<Persona> comprobarPadres(SimpleDirectedGraph<Persona, Hijo> g) {
		Set<Persona> vertices = new HashSet<Persona>();
		List<Persona> ls = g.vertexSet().stream().toList();
		for (int i = 0; i < ls.size(); i++) {
			List<Persona> padres = new ArrayList<>();
			padres.addAll(Graphs.predecessorListOf(g, ls.get(i)));
			if (!padres.isEmpty()) {
				if (padres.get(0).anio().equals(padres.get(1).anio())) {
					if (padres.get(0).borncity().equals(padres.get(1).borncity())) {
						vertices.add(ls.get(i));
					}
				}
			}
		}
		System.out.println("Personas cuyos padres aparecen en el grafo y cumplen los requisitos: "
				+ vertices.stream().map(x -> x.nombre()).toList());
		return vertices;
	}

	///////////////////////////////////////// APARTADO B////////////////////////////////////////////////////////////////////////

	public static void crearVistaApB(String file, SimpleDirectedGraph<Persona, Hijo> g, String nombreVista,
			String nombreAp, Persona personaElegida) {
		Set<Persona> ancestros = apartadoB(g, personaElegida, new HashSet<>());
		var vista = SubGraphView.of(g, ancestros);

		GraphColors.toDot(g, "resultados/ejercicio1/" + nombreAp + "/" + file + nombreVista + ".gv", x -> x.nombre(),

				x -> "",
				v -> vista.containsVertex(v) ? GraphColors.color(Color.blue)
						: GraphColors.colorIf(Color.red, Color.black, v.equals(personaElegida)),
				e -> GraphColors.color(Color.black));

		System.out.println(
				"Ancestros de " + personaElegida.nombre() + ": " + ancestros.stream().map(x -> x.nombre()).toList());
		System.out.println("Fichero " + file + nombreVista + ".gv generado en resultados/ejercicio1" + "/" + nombreAp);
	}

	private static Set<Persona> apartadoB(SimpleDirectedGraph<Persona, Hijo> g, Persona p, Set<Persona> ancestros) {
		List<Persona> padres = new ArrayList<>();
		if (p != null) {
			if (!Graphs.predecessorListOf(g, p).isEmpty()) {
				padres.addAll(Graphs.predecessorListOf(g, p));
				ancestros.addAll(padres);
				apartadoB(g, padres.get(0), ancestros);
				apartadoB(g, padres.get(1), ancestros);
			}
		}
		return ancestros;
	}

	/////////////////////////////// APARTADO C/////////////////////////////////////

	public enum asociacionC {
		HERMANOS, PRIMOS, OTROS
	}

	public static void apartadoC(SimpleDirectedGraph<Persona, Hijo> g, Persona p1, Persona p2) {

		List<Persona> padres1 = Graphs.predecessorListOf(g, p1);
		List<Persona> padres2 = Graphs.predecessorListOf(g, p2);
		List<Persona> abuelos1 = new ArrayList<>();
		List<Persona> abuelos2 = new ArrayList<>();

		if (padres1.stream().anyMatch(x -> padres2.contains(x))) {
			// PARA SER HERMANO UN PADRE EN COMÚN
			System.out.println(p1.nombre() + " y " + p2.nombre() + " son " + asociacionC.HERMANOS);
		} else {
			// PARA SER PRIMO TENER UN ABUELO EN COMÚN
			for (int i = 0; i < 2; i++) {
				abuelos1.addAll(Graphs.predecessorListOf(g, padres1.get(i)));
				abuelos2.addAll(Graphs.predecessorListOf(g, padres2.get(i)));
			}
			System.out.println(p1.nombre() + " y " + p2.nombre() + " son "
					+ (abuelos1.stream().anyMatch(x -> abuelos2.contains(x)) ? asociacionC.PRIMOS : asociacionC.OTROS));

		}

	}
	/////////////////////////////////// APARTADO D/////////////////////////////////////////

	public static void crearVistaApD(String file, SimpleDirectedGraph<Persona, Hijo> g, String nombreVista,
			String nombreAp) {
		Set<Persona> personas_con_hijos_distintos_padres = apartadoD(g);
		var vista = SubGraphView.of(g, personas_con_hijos_distintos_padres);

		GraphColors.toDot(g, "resultados/ejercicio1/" + nombreAp + "/" + file + nombreVista + ".gv", x -> x.nombre(),

				x -> "", v -> GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e -> GraphColors.color(Color.black));

		System.out.println("Personas que tienen hijos/as con distintas personas"
				+ personas_con_hijos_distintos_padres.stream().map(x -> x.nombre()).toList());
		System.out.println("Fichero " + file + nombreVista + ".gv generado en resultados/ejercicio1" + "/" + nombreAp);
	}

	private static Set<Persona> apartadoD(SimpleDirectedGraph<Persona, Hijo> g) {
		List<Persona> vertices = g.vertexSet().stream().toList();
		Set<Persona> sol = new HashSet<>();
		for (int i = 0; i < vertices.size(); i++) {// PARA RECORRER LOS VERTICES DEL GRAFO
			List<Persona> hijos = Graphs.successorListOf(g, vertices.get(i));// LE CALCULAMOS LOS HIJOS
			if (!hijos.isEmpty()) {
				Persona h1 = hijos.get(0);// PRIMER HIJO
				List<Persona> padres = Graphs.predecessorListOf(g, h1);// PADRES DEL PRIMER HIJO
				for (int j = 1; j < hijos.size(); j++) {// recorro los hijos de esa persona
					if (!padres.equals(Graphs.predecessorListOf(g, hijos.get(j)))) {
						sol.add(vertices.get(i));

					}
				}
			}
		}

		return sol;
	}
//////////////////////////////////////////APARTADO E////////////////////////////////////////////////////////////////////
	public static void crearVistaApE(String file, SimpleGraph<Persona,Hijo> g, String nombreAp,
			String nombreVista) {
		Set<Persona> conj_minimo = apartadoE(g);
		var vista = SubGraphView.of(g, conj_minimo);

		GraphColors.toDot(g, "resultados/ejercicio1/" + nombreVista + "/" + file + nombreAp + ".gv", x -> x.nombre(),

				x -> "", v -> GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e -> GraphColors.color(Color.black));

		System.out.println();
		System.out.println("Fichero " + file + nombreVista + ".gv generado en resultados/ejercicio1" + "/" + nombreAp);

	}

	private static Set<Persona> apartadoE(SimpleGraph<Persona, Hijo> g) {
		GreedyVCImpl<Persona, Hijo> conj=new GreedyVCImpl<>(g);
		Set<Persona> sol = conj.getVertexCover();
		return sol;
	}

}