package testsEjercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;

import datos.Hijo;
import datos.Persona;
import ejercicios.Ejercicio1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {

	public static void main(String[] args) {
		List<String> ficheros = new ArrayList<>();
		ficheros.add("PI3E1A_DatosEntrada");
		ficheros.add("PI3E1B_DatosEntrada");
		for (int i = 0; i < ficheros.size(); i++) {
			SimpleDirectedGraph<Persona, Hijo> g = GraphsReader.newGraph(
					"ficheros/testsAlumnos/" + ficheros.get(i) + ".txt", Persona::ofFormat, Hijo::ofFormat,
					Graphs2::simpleDirectedGraph);
			// Generamos gv del grafo datos en el .txt
			GraphColors.toDot(g, "resultados/ejercicio1/" + ficheros.get(i) + ".gv", x -> x.toString(), x -> "",
					v -> GraphColors.color(Color.black), e -> GraphColors.color(Color.black));
			System.out.println("\n#### Resultados fichero: " + ficheros.get(i) + " ####");
			testEjercicio1A(ficheros.get(i),g);
			testEjercicio1B(ficheros.get(i),g);
			testEjercicio1C(ficheros.get(i),g);
			testEjercicio1D(ficheros.get(i),g);
			testEjercicio1E(ficheros.get(i));
		}

	}

	public static void testEjercicio1A(String file, SimpleDirectedGraph<Persona, Hijo> g) {
		System.out.println("\n######## APARTADO A ########\n");
		if (file.equals("PI3E1A_DatosEntrada"))
			Ejercicio1.crearVista(file, g, "ApartadoA", "FicheroA");
		else
			Ejercicio1.crearVista(file, g, "ApartadoA", "FicheroB");
	}

	public static void testEjercicio1B(String file, SimpleDirectedGraph<Persona, Hijo> g) {
		System.out.println("\n######## APARTADO B ########\n");
		if (file.equals("PI3E1A_DatosEntrada"))
			Ejercicio1.crearVistaApB(file, g, "ApartadoB", "FicheroA", Persona.of(13, "Maria", 2008, "Sevilla"));
		else
			Ejercicio1.crearVistaApB(file, g, "ApartadoB", "FicheroB", Persona.of(13, "Raquel", 1993, "Sevilla"));
	}

	public static void testEjercicio1C(String file, SimpleDirectedGraph<Persona, Hijo> g) {
		System.out.println("######## APARTADO C ########");
		if (file.equals("PI3E1A_DatosEntrada")) {
			Ejercicio1.apartadoC(g, Persona.of(16, "Rafael", 2020, "Malaga"), Persona.of(14, "Sara", 2015, "Jaen"));
			Ejercicio1.apartadoC(g, Persona.of(13, "Maria", 2008, "Sevilla"),
					Persona.of(12, "Patricia", 2010, "Cordoba"));
			Ejercicio1.apartadoC(g, Persona.of(8, "Carmen", 1989, "Jaen"), Persona.of(16, "Rafael", 2020, "Malaga"));

		}

		else {
			Ejercicio1.apartadoC(g, Persona.of(14, "Julia", 1996, "Jaen"), Persona.of(6, "Angela", 1997, "Sevilla"));
			Ejercicio1.apartadoC(g, Persona.of(15, "Alvaro", 2000, "Sevilla"),
					Persona.of(13, "Raquel", 1993, "Sevilla"));
			Ejercicio1.apartadoC(g, Persona.of(3, "Laura", 1965, "Jerez"), Persona.of(13, "Raquel", 1993, "Sevilla"));
		}
	}

	public static void testEjercicio1D(String file, SimpleDirectedGraph<Persona, Hijo> g) {
		System.out.println("######## APARTADO D ########");
		if (file.equals("PI3E1A_DatosEntrada"))
			Ejercicio1.crearVistaApD(file, g, "ApartadoD", "FicheroA");
		else
			Ejercicio1.crearVistaApD(file, g, "ApartadoD", "FicheroB");
	}

	public static void testEjercicio1E(String file) {
		System.out.println("######## APARTADO E ########");
		var g = GraphsReader.newGraph("ficheros/testsAlumnos/" + file + ".txt", Persona::ofFormat, Hijo::ofFormat,
				Graphs2::simpleGraph);
		if (file.equals("PI3E1A_DatosEntrada"))
			Ejercicio1.crearVistaApE(file, g, "ApartadoE", "FicheroA");
		else
			Ejercicio1.crearVistaApE(file, g, "ApartadoE", "FicheroB");
	}
}
