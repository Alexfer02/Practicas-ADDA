package _datos;

import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class DatosEjercicio2 {

	public static record Curso(Integer id, Set<Integer> tematicas, Double coste, Integer centro) {

		public static int cont;

		public static Curso create(String linea) {
			String[] trozos = linea.split(":");
			Set<Integer> tem = Set2.parse(trozos[0].replace("{", "").replace("}", "").trim(), ",",
					s -> Integer.valueOf(s));
			Double coste = Double.valueOf(trozos[1].trim());
			Integer centro = Integer.valueOf(trozos[2].trim());
			return new Curso(cont++, tem, coste, centro);
		}
	}

	private static List<Curso> cursos;
	private static List<Integer> tematicas;
	private static List<Integer> centros;
	private static Integer maxCentros;

	public static void iniDatos(String fichero) {
		Curso.cont = 0;

		cursos = List2.empty();

		List<String> lineas = Files2.linesFromFile(fichero);
		String[] v = lineas.get(0).split("=");
		maxCentros = Integer.valueOf(v[1].trim());

		for (int i = 1; i < lineas.size(); i++) {
			Curso c = Curso.create(lineas.get(i));
			cursos.add(c);
		}

		tematicas = cursos.stream().flatMap(c -> c.tematicas().stream()).distinct().toList();
		centros = cursos.stream().map(c -> c.centro()).distinct().toList();
	}

//Info de cursos, centros y tematicas

	public static Curso getCurso(Integer i) {
		return cursos.get(i);
	}

	public static Double getCosteCurso(Integer i) {
		return cursos.get(i).coste();
	}

	public static Set<Integer> getTematicasCurso(Integer i) {
		return cursos.get(i).tematicas();
	}

	public static Integer getCentroCurso(Integer i) {
		return cursos.get(i).centro();
	}

	public static Integer getTematica(Integer j) {
		return tematicas.get(j);
	}

	public static Integer getCentro(Integer k) {
		return centros.get(k);
	}

//Getters de variables privadas
	public static List<Curso> getCursos() {
		return cursos;
	}

	public static List<Integer> getTematicas() {
		return tematicas;
	}

	public static List<Integer> getCentros() {
		return centros;
	}

	public static Integer getMaxCentros() {
		return maxCentros;
	}

//Getters de número de elementos
	public static Integer getNumCursosN() {
		return cursos.size();
	}

	public static Integer getNumTematicasM() {
		return tematicas.size();
	}

	public static Integer getNumCentrosNC() {
		return centros.size();
	}

//Variables binarias
	public static Integer cursoTematica(Integer i, Integer j) {
		Integer tematica = tematicas.get(j);
		return cursos.get(i).tematicas().contains(tematica) ? 1 : 0;
	}

	public static Integer cursoCentro(Integer i, Integer k) {
		Integer centro = centros.get(k);
		return cursos.get(i).centro().equals(centro) ? 1 : 0;
	}

//Test lectura de fichero
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		System.out.println(cursos);
		System.out.println(tematicas);

		System.out.println(centros);
		System.out.println(maxCentros);
	}

}
