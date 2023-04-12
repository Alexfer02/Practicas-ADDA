package ejercicio2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	private static List<Integer> total_tematicas;
	private static List<Curso> cursos;
	private static Integer max;

	public static List<Integer> getTematicas() {
		return total_tematicas;
	}

	public static List<Curso> getCursos() {
		return cursos;
	}

	public static Integer getNumCursos() {// n
		return cursos.size();
	}

	public static Integer getNumTematicas() {// m
		return total_tematicas.size();
	}

	public static Integer getNumCentros() { // nc
		Set<Integer> centros = new HashSet<>();
		for (int i = 0; i < cursos.size(); i++) {
			centros.add(cursos.get(i).centro());
		}
		return centros.size();
	}

	public static Integer getMaxCentros() { // maxCentros
		return max;
	}

	public static Integer contieneTematicaCurso(Integer i, Integer j) { // t ij: binaria, en el curso i se trata la
																		// temática j, i en [0,n), j en [0,m)
		return cursos.get(i).tematicas().contains(total_tematicas.get(j)) ? 1 : 0;
	}

	public static Double getPrecioInscripcion(Integer i) {// p i: real, precio de inscripción del curso i, i en [0,n)
		return cursos.get(i).coste();
	}

	public static Integer seImparteCursoEnCentro(Integer i, Integer k) { // c ik: binaria, el curso i se imparte en el
																			// centro k, i en [0,n), k en [0,nc)
		// nc = numero de centros
		return cursos.get(i).centro().equals(k) ? 1 : 0;
	}

	public static void ejercicio2_model() throws IOException {
		for (int i = 3; i < 4; i++) {
			DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada" + i + ".txt");

			total_tematicas = DatosEjercicio2.getTematicas();
			cursos = DatosEjercicio2.getCursos();
			max=DatosEjercicio2.getMaxCentros();
			AuxGrammar.generate(Ejercicio2PLE.class, "lsi_models/Ejercicio2.lsi",
					"gurobi_models/Ejercicio2-" + i + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-" + i + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
			System.out.println("\n####################################################################\n");
		}
	}

	public static void main(String[] args) throws IOException {
		ejercicio2_model();
	}
}
