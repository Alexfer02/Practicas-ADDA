package _datos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.common.String2;

public class DatosEjercicio2 {

	public static record Curso(Integer id, Set<Integer> tematicas, Double coste, Integer centro) {

		public static int cont;

		@Override
		public String toString() {
			return "C" + id + ", tematicas del curso=" + tematicas + ", coste=" + coste + ", centro=" + centro + "]";
		}

		public static Curso create(String s) {
			String[] v = s.split(":");
			return new Curso(cont++, Set2.parse(v[0].trim(), "{,}", Integer::parseInt), Double.parseDouble(v[1].trim()),
					Integer.valueOf(v[2].trim()));
		}

	}

	private static List<Integer> total_tematicas;
	private static List<Curso> cursos;
	private static Integer max;

	public static void iniDatos(String fichero) {
		Curso.cont = 0;
		cursos = List2.empty();
		total_tematicas = List2.empty();
		Set<Integer> tem = new TreeSet<>();
		for (String linea : Files2.linesFromFile(fichero)) {
			if (linea.startsWith("Max_Centros")) {
				max = Integer.valueOf(linea.split("=")[1].trim());
			} else {
				Curso c = Curso.create(linea);
				cursos.add(c);
				tem.addAll(c.tematicas);
			}
		}
		total_tematicas = List2.ofCollection(tem);
		toConsole();
	}

	public static Integer getNumCursos() {// n
		return cursos.size();
	}

	public static Integer getNumTematicas() {// m
		return total_tematicas.size();
	}

	public static List<Integer> getTematicas() {
		return total_tematicas;
	}

	public static List<Curso> getCursos() {
		return cursos;
	}

	public static Curso getCurso(int i) {
		return cursos.get(i);
	}

	public static Integer getNumCentros() { // nc
		Set<Integer> centros = new HashSet<>();
		for (int i = 0; i < cursos.size(); i++) {
			centros.add(cursos.get(i).centro);
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
		return cursos.get(i).coste;
	}

	public static Integer seImparteCursoEnCentro(Integer i, Integer k) { // c ik: binaria, el curso i se imparte en el
																			// centro k, i en [0,n), k en [0,nc)
		// nc = numero de centros
		return cursos.get(i).centro.equals(k) ? 1 : 0;
	}

	public static Set<Integer> getCentros() {
		Set<Integer> centros = new HashSet<>();
		for (int i = 0; i < cursos.size(); i++) {
			centros.add(cursos.get(i).centro);
		}
		return centros;
	}

	public static void toConsole() {
		String2.toConsole("Maximo numero de centros: %s", max);
		String2.toConsole("Tematicas: %s", total_tematicas);
		System.out.println("Num tematicas= " + getNumTematicas());
		String2.toConsole(cursos, "Cursos");
		String2.toConsole(String2.linea());
	}

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		System.out.println(getNumCentros());
		System.out.println(getNumTematicas());

	}

}
