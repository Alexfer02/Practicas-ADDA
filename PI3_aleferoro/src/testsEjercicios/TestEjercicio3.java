package testsEjercicios;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import ejercicios.Ejercicio3;
import us.lsi.colors.GraphColors;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjercicio3 {

	public static void main(String[] args) {
		List<String> ficheros = new ArrayList<>();
		ficheros.add("PI3E3A_DatosEntrada");
		ficheros.add("PI3E3B_DatosEntrada");
		for (int i = 0; i < ficheros.size(); i++) {
			System.out.println("\n----- Resultados fichero: " + ficheros.get(i) + " -----");
			test_ejercicio3(ficheros.get(i));
		}
	}

	public static void test_ejercicio3(String file) {
		// grafo por defecto
		Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false); // aristas por defecto
		Files2.streamFromFile("ficheros/testsAlumnos/" + file + ".txt").forEach(linea -> {
			String[] v = linea.split(":");
			String[] vex = v[1].replaceAll(" ", "").split(",");
			for (int i = 0; i < vex.length; i++) {
				if (!g.containsVertex(vex[i])) {
					g.addVertex(vex[i]);
				}
			}
			for (int i = 0; i < vex.length - 1; i++) {
				for (int j = i + 1; j < vex.length; j++) {
					if (!vex[i].isEmpty() && !vex[j].isEmpty()) {
						if (!g.containsEdge(vex[i], vex[j])) {
							g.addEdge(vex[i], vex[j]);
						}
					}
				}
			}
		});
		GraphColors.toDot(g, "resultados/ejercicio3/" + file + "_inicial.gv"); // grafo para que lo pinte por defecto
		Ejercicio3.todosLosApartados(file, g, "ApartadoA");
		Ejercicio3.todosLosApartados(file, g, "ApartadoB");
	}
}