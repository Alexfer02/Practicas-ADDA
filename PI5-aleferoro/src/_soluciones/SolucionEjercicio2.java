package _soluciones;

import java.util.List;
import org.jgrapht.GraphPath;
import _datos.DatosEjercicio2;
import ejercicio2.Ejercicio2Edge;
import ejercicio2.Ejercicio2Vertex;
import us.lsi.common.List2;

public class SolucionEjercicio2 {

	public static SolucionEjercicio2 of(List<Integer> ls) {
		return new SolucionEjercicio2(ls);
	}

	public static SolucionEjercicio2 of(GraphPath<Ejercicio2Vertex, Ejercicio2Edge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionEjercicio2 res = of(ls);
		res.path = ls;
		return res;
	}

	private Double precio;
	private List<Integer> solucion;

	private List<Integer> path;

	private SolucionEjercicio2(List<Integer> ls) {
		precio = 0.0;
		solucion = List2.empty();

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				precio += DatosEjercicio2.getCosteCurso(i);
				solucion.add(i);
			}
		}
	}

	@Override
	public String toString() {
		String cursos = "Cursos elegidos: " + solucion;
		String coste = "\nCoste Total: " + precio;
		String res = cursos + coste;
		return path == null ? res : String.format("%s\nPath de la solución:%s", res, path);
	}

}