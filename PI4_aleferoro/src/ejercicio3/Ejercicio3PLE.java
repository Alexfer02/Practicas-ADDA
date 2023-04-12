package ejercicio3;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigadores;
import _datos.DatosEjercicio3.Trabajos;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	private static List<Investigadores> investigadores_f;
	private static List<Trabajos> trabajos_f;

	public static Integer getNumInvestigadores() {
		return investigadores_f.size();
	}

	public static Integer getNumEspecialidades() {
		Set<Integer> aux = new HashSet<>();
		for (int i = 0; i < investigadores_f.size(); i++) {
			aux.add(investigadores_f.get(i).especialidad());
		}
		return aux.size();
	}

	public static Integer getNumTrabajos() {
		return trabajos_f.size();
	}

	public static Integer getTrabajadorEspecialidad(Integer i, Integer k) {
		return investigadores_f.get(i).especialidad().equals(k) ? 1 : 0;
	}

	public static Integer getDiasDisponiblesTrabajador(Integer i) {
		return investigadores_f.get(i).capacidad();
	}

	public static Integer getDiasNecesariosTrabajoEspecialidad(Integer j, Integer k) {
		return trabajos_f.get(j).reparto().get(k);
	}

	public static Integer getCalidad(Integer j) {
		return trabajos_f.get(j).calidad();
	}

	public static Integer getMaxM() {
		return investigadores_f.stream().map(i -> i.capacidad()).max(Comparator.naturalOrder()).get() + 1;
	}

	public static void ejercicio3_model() throws IOException {
		for (int i = 1; i < 4; i++) {
			DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada" + i + ".txt");

			investigadores_f = DatosEjercicio3.getInvestigadores();
			trabajos_f = DatosEjercicio3.getTrabajos();
			AuxGrammar.generate(Ejercicio3PLE.class, "lsi_models/Ejercicio3.lsi",
					"gurobi_models/Ejercicio3-" + i + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-" + i + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
			System.out.println("\n####################################################################\n");
		}
	}

	public static void main(String[] args) throws IOException {
		ejercicio3_model();
	}
}
