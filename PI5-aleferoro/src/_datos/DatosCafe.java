package _datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Files2;

public class DatosCafe {
	public static List<Integer> tipos;
	public static List<Variedad> variedades;

	public record Variedad(int id, Integer beneficio, List<Double> composicion) {

		public static int i;
		public static Variedad create(String línea) {
			List<Double> compo = new ArrayList<>();
			for (int j = 0; j < tipos.size(); j++) {
				compo.add(0.);
			}

			String[] var = línea.split(";");
			Integer benef = Integer.parseInt(var[0].split("=")[1].replace(";", "").trim());

			String[] composiciones = var[1].split("=")[1].trim().split(",");

			for (int j = 0; j < composiciones.length; j++) {
				String[] porcent = composiciones[j].replace("(C", "").replace(")", "").split(":");
				Integer tipo = Integer.parseInt(porcent[0].trim()) - 1;
				Double porcentaje = Double.parseDouble(porcent[1].trim());
				compo.set(tipo, porcentaje);
			}
			return new Variedad(i++, benef, new ArrayList<>(compo));
		}
	}

	public static void iniDatos(String fichero) {
		Variedad.i = 0;

		List<String> lineas = Files2.linesFromFile(fichero);
		int pos = lineas.indexOf("// VARIEDADES");
		List<String> tipoCafe = lineas.subList(1, pos);
		List<String> varCafe = lineas.subList(pos + 1, lineas.size());
		List<Integer> aux = new ArrayList<>();
		for (int i = 0; i < tipoCafe.size(); i++) {
			Integer valor = Integer.parseInt(tipoCafe.get(i).split("=")[1].replace(";", "").trim());
			aux.add(valor);
		}
		tipos = new ArrayList<>(aux);
		variedades = new ArrayList<>();
		for (int i = 0; i < varCafe.size(); i++) {
			variedades.add(Variedad.create(varCafe.get(i)));
		}
		toConsole();
	}

	public static Integer getNumTipos() {
		return tipos.size();

	}

	public static Integer getNumVariedades() {
		return variedades.size();

	}

	public static Integer getBeneficioVariedad(Integer i) {
		return variedades.get(i).beneficio();
	}

	public static Double getKgTipoVariedad(Integer j, Integer i) {
		return variedades.get(i).composicion().get(j);
	}

	public static Integer getKgTipo(Integer j) {
		return tipos.get(j);
	}

	public static List<Variedad> getVariedades() {
		return new ArrayList<>(variedades);
	}

	public static Integer getMaxKgVariedad (Integer i) {
        List<Double> listaMax = new ArrayList<>();
        for (int j=0; j<tipos.size(); j++) {
        listaMax.add(getKgTipo(j) / getKgTipoVariedad(j, i));
        }
        listaMax.sort(Comparator.naturalOrder());
        return listaMax.get(0).intValue();
    }

	private static void toConsole() {
		System.out.println("Kgs de cada tipo: " + tipos + "InVariedades: " + variedades);
	}

	public static void main(String[] args) {
		for (int i = 0; 1 < 3; i++) {
			System.out.println("**********   DATOS DE ENTRADA " + (i + 1) + "***********");
			String fichero = "ficheros/Ejercicio1DatosEntrada" + String.valueOf(i + 1) + ".txt";
			iniDatos(fichero);
			System.out.println("\n");
		}

	}
}
