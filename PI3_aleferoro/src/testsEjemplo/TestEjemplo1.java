package testsEjemplo;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import datos.Carretera;
import datos.CiudadEjemplo;
import ejemplos.Ejemplo1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjemplo1 {

	public static void main(String[] args) {
		testEjemplo1("Andalucia");
		testEjemplo1("Castilla La Mancha");
	}

	public static void testEjemplo1(String file) {
		Graph<CiudadEjemplo, Carretera> g = GraphsReader.newGraph("ficheros/testsProfesores/" + file + ".txt", CiudadEjemplo::ofFormat,
				Carretera::ofFormat, Graphs2::simpleGraph);

		System.out.println("\nArchivo " + file + ".txt \n Datos de entrada: " + g);

		GraphColors.toDot(g, "resultados/ejemplo1" + file + ".gv", x -> x.nombre(), x -> x.nombre(),
				v -> GraphColors.color(Color.black), e -> GraphColors.color(Color.black));

		// Primer predicado
		Predicate<CiudadEjemplo> pv1 = c -> c.nombre().contains("e");
		Predicate<Carretera> pa1 = ca -> ca.km() < 200;

		Ejemplo1.crearVista(file, g, pv1, pa1, "Primer predicado");

		// Segundo predicado
		Predicate<CiudadEjemplo> pv2 = c -> c.habitantes() < 500000;
		Predicate<Carretera> pa2 = ca -> ca.km() > 100
				&& (g.getEdgeSource(ca).nombre().length() > 5 || g.getEdgeTarget(ca).nombre().length() > 5);

		Ejemplo1.crearVista(file, g, pv2, pa2, "Segundo predicado");
	}
}