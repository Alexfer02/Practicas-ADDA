package ejercicios;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;

public class Ejercicio3 {
	public static void todosLosApartados( String file, Graph<String, DefaultEdge> gf,String nomFich) {
		System.out.println("\n######## APARTADO A ########\n");
		GreedyColoring<String, DefaultEdge> alg = new GreedyColoring<>(gf);
		Coloring<String> coloring = alg.getColoring();
		System.out.println("Numero de franjas horarios necesarias: " + coloring.getNumberColors());
		System.out.println("Actividades para impartirse en paralelo por franja horaria: ");
		List<Set<String>> composicion = coloring.getColorClasses();
		for(int i = 0;i<composicion.size();i++){
			System.out.println("Franja numero "+(i+1)+": "+composicion.get(i));
			
		}
		
		System.out.println("\n######## APARTADO B ########\n");
		Map<String, Integer> map = coloring.getColors();
		GraphColors.toDot(gf, "resultados/ejercicio3/"+"FicheroB"+"/"+file+"ApartadoB"+".gv",
				v->v.toString(),
				e->"",
				v->GraphColors.color(map.get(v)), //si ponemos de los vertices tambien tenemos que meter 
				e -> GraphColors.style(Style.bold));
		System.out.println("Fichero " + file + "ApartadoB" + ".gv generado en resultados/ejercicio3" + nomFich);
	}
}