package _datos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosEjercicio1 {

	public record Tipos(String tipo, Integer kilos_disponibles) {

		public static Tipos of(String linea) {
			String tipo = linea.split(":")[0];
			Integer kilos = Integer.valueOf(linea.split("=")[1].replace(";", ""));
			return new Tipos(tipo, kilos);
		}

	}

	public record Variedad(String variedad, Integer beneficio, Map<String, Double> mapa) {
		public static Variedad of(String linea, List<Tipos> tipos) {

			String vari = linea.split("->")[0].strip();
			Integer bene = Integer.valueOf(linea.split("->")[1].strip().replace("beneficio=", "").split(";")[0]);
			String[] comp = linea.split(";")[1].replace("comp=", "").strip().split(",");
			Map<String, Double> mapa_ = new HashMap<>();
			for (int i = 0; i < tipos.size(); i++) {
				mapa_.put(tipos_f.get(i).tipo, 0.);
			}
			for (int i = 0; i < comp.length; i++) {
				String aux = comp[i].replace("(", "").replace(")", "");
				String clave = aux.split(":")[0];
				Double valor = Double.valueOf(aux.split(":")[1]);
				if (mapa_.containsKey(clave)) {
					mapa_.put(clave, valor);
				}
			}

			return new Variedad(vari, bene, mapa_);
		}
	}

	private static List<Tipos> tipos_f;
	private static List<Variedad> variedad_f;

	public static void iniDatos(String fichero) {
		List<String> v1 = Files2.linesFromFile(fichero);
		Integer p1 = v1.indexOf("// TIPOS");
		Integer p2 = v1.indexOf("// VARIEDADES");
		List<String> tipos = v1.subList(p1 + 1, p2);
		List<String> variedad = v1.subList(p2 + 1, v1.size());
		tipos_f = List2.empty();
		variedad_f = List2.empty();
		for (int i = 0; i < tipos.size(); i++) {
			tipos_f.add(Tipos.of(tipos.get(i)));
		}
		for (int i = 0; i < variedad.size(); i++) {
			variedad_f.add(Variedad.of(variedad.get(i), tipos_f));
		}
		toConsole();
	}

	public static Integer getNumTipos() {
		return tipos_f.size();
	}

	public static Integer getNumVariedades() {
		return variedad_f.size();
	}

	public static Integer getKilosDisp(Integer i) {
		return tipos_f.get(i).kilos_disponibles;
	}

	public static Integer getKilosDispPorVariedad(Integer i) {
		Integer kilos=0;
		List<Entry<String, Double>> sol =variedad_f.get(i).mapa.entrySet().stream().collect(Collectors.toList());
		List<String> aux = new ArrayList<>();
		for(int j =0;j<sol.size();j++) {
			if(!sol.get(j).getValue().equals(0.0)) {
				aux.add(sol.get(j).getKey());
			}
		}
		for(int j=0;j<tipos_f.size();j++) {
			if(aux.contains(tipos_f.get(j).tipo)) {
				kilos+=tipos_f.get(j).kilos_disponibles;
			}
		}
		return kilos;
	}

	public static Integer getTotalKilos() {
		Integer sol = 0;
		for (int i = 0; i < tipos_f.size(); i++) {
			sol += getKilosDisp(i);
		}
		return sol;
	}

	public static Integer getBeneficioVariedad(Integer i) {
		return variedad_f.get(i).beneficio;
	}

	public static Double getPorcentajeCafeJParaVariedadI(Integer i, Integer j) {
		return variedad_f.get(i).mapa.get(tipos_f.get(j).tipo);
	}

	public static List<Tipos> getTipos() {
		return tipos_f;
	}

	public static List<Variedad> getVariedades() {
		return variedad_f;
	}

	public static void toConsole() {
		String2.toConsole("Tipos: %s", tipos_f);
		String2.toConsole(variedad_f, "Variedades");
		String2.toConsole("num variedades",getNumVariedades());
		String2.toConsole(String2.linea());
	}

	public static void main(String[] args) throws IOException {
		iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
		System.out.println(getNumTipos());
		System.out.println(getNumVariedades());

	}

}
