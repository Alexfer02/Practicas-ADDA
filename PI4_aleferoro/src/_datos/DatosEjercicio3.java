package _datos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosEjercicio3 {

	public static record Investigadores(int id, Integer capacidad, Integer especialidad) {

		public static int cont;
		@Override
		public String toString() {
			return "INV" + id + ", capacidad=" + capacidad + ", especialidad=" + especialidad + "]";
		}

		public static Investigadores of(String linea) {
			Integer cap = Integer.valueOf(linea.split(":")[1].split(";")[0].replace("capacidad=", "").trim());
			Integer esp = Integer.valueOf(linea.split(":")[1].split(";")[1].replace("especialidad=", "").trim());
			return new Investigadores(cont++, cap, esp);

		}
	}

	public static record Trabajos(int id, Integer calidad, Map<Integer, Integer> reparto) {

		public static int cont;
		@Override
		public String toString() {
			return "T0" + id + ", calidad=" + calidad + ", reparto=" + reparto + "]";
		}

		public static Trabajos of(String linea) {

			Integer cal = Integer.valueOf(linea.split("->")[1].trim().split(";")[0].replace("calidad=", "").trim());
			Map<Integer, Integer> rep = new HashMap<>();
			String[] provisional = linea.split("->")[1].trim().split(";")[1].replace("reparto=", "").split(",");
			for (int i = 0; i < provisional.length; i++) {
				Integer espe = Integer.valueOf(provisional[i].split(":")[0].replace("(", "").trim());
				Integer dias = Integer.valueOf(provisional[i].split(":")[1].replace(")", "").trim());
				if (!rep.containsKey(espe)) {
					rep.put(espe, dias);
				}
			}
			return new Trabajos(cont++, cal, rep);
		}

	}

	private static List<Investigadores> investigadores_f;
	private static List<Trabajos> trabajos_f;

	public static void iniDatos(String fichero) {
		Investigadores.cont = 1;
		Trabajos.cont = 1;
		List<String> v1 = Files2.linesFromFile(fichero);
		Integer p1 = v1.indexOf("// INVESTIGADORES");
		Integer p2 = v1.indexOf("// TRABAJOS");
		List<String> investigadores = v1.subList(p1 + 1, p2);
		List<String> trabajos = v1.subList(p2 + 1, v1.size());
		investigadores_f = List2.empty();
		trabajos_f = List2.empty();
		for (int i = 0; i < investigadores.size(); i++) {
			investigadores_f.add(Investigadores.of(investigadores.get(i)));
		}
		for (int i = 0; i < trabajos.size(); i++) {
			trabajos_f.add(Trabajos.of(trabajos.get(i)));
		}
		toConsole();
	}

	public static List<Investigadores> getInvestigadores() {
		return investigadores_f;
	}

	public static List<Trabajos> getTrabajos() {
		return trabajos_f;
	}

	public static Integer getNumInvestigadores() {
		return investigadores_f.size();
	}

	public static Integer getNumEspecialidades() {
		Set<Integer> aux = new HashSet<>();
		for (int i = 0; i < investigadores_f.size(); i++) {
			aux.add(investigadores_f.get(i).especialidad);
		}
		return aux.size();
	}

	public static Integer getNumTrabajos() {
		return trabajos_f.size();
	}

	public static Integer getTrabajadorEspecialidad(Integer i, Integer k) {
		return investigadores_f.get(i).especialidad.equals(k) ? 1 : 0;
	}

	public static Integer getDiasDisponiblesTrabajador(Integer i) {
		return investigadores_f.get(i).capacidad;
	}

	public static Integer getDiasNecesariosTrabajoEspecialidad(Integer j, Integer k) {
		return trabajos_f.get(j).reparto.get(k);
	}

	public static Integer getCalidad(Integer j) {
		return trabajos_f.get(j).calidad;
	}

	public static Integer getMaxM() {
		return investigadores_f.stream().map(i -> i.capacidad()).max(Comparator.naturalOrder()).get() + 1;
	}

	private static void toConsole() {
		String2.toConsole(investigadores_f, "Investigadores");
		String2.toConsole(trabajos_f, "Trabajos");
		String2.toConsole(String2.linea());

	}

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
	}

}
