package testsEjercicios;



import org.jgrapht.Graph;
import datos.Ciudad;
import datos.Trayecto;
import ejercicios.Ejercicio2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio2 {
	public static void main(String[] args) {
		String fichero = "PI3E2_DatosEntrada";
		Graph<Ciudad, Trayecto> g = GraphsReader.newGraph("ficheros/testsAlumnos/" + fichero + ".txt",
				Ciudad::ofFormat,
				Trayecto::ofFormat,
				Graphs2::simpleWeightedGraph,
				w->w.duracion());
		// Generamos gv del grafo datos en el .txt
		GraphColors.toDot(g, "resultados/ejercicio2/" + fichero + ".gv", x -> x.toString(),
				v -> v.precio().toString() + " precio\n" + v.duracion().toString() + " minutos",
				v -> GraphColors.color(Color.black), e -> GraphColors.color(Color.black));
		System.out.println("#### Resultados fichero: " + fichero + " ####\n");
		System.out.println("########################APARTADO A############################\n");
		Ejercicio2.ApartadoA(g, fichero);
		System.out.println("########################APARTADO B############################\n");
		Ejercicio2.ApartadoB(g, fichero);
		System.out.println("########################APARTADO C############################\n");
		Ejercicio2.ApartadoC(g,fichero);
		System.out.println("########################APARTADO D############################\n");
		Ejercicio2.ApartadoD(g, fichero);
	}

}
