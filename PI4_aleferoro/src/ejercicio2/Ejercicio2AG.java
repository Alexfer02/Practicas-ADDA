package ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2AG;
import us.lsi.ag.BinaryData;

public class Ejercicio2AG implements BinaryData<SolucionEjercicio2AG> {
	public Ejercicio2AG(String fichero) {
		DatosEjercicio2.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosEjercicio2.getNumCursos();
	}

	@Override
	public SolucionEjercicio2AG solucion(List<Integer> ls) {
		return SolucionEjercicio2AG.create(ls);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0., error = 0.;
		for (int i = 0; i < size(); i++) {
			if (ls.get(i) > 0) {
				goal += DatosEjercicio2.getPrecioInscripcion(i);
			}
		}
		Set<Integer> ts = new HashSet<>();
		Set<Integer> cs = new HashSet<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				ts.addAll(DatosEjercicio2.getCurso(i).tematicas());
				cs.add(DatosEjercicio2.getCurso(i).centro());
			}
		}
		Integer m = DatosEjercicio2.getNumTematicas();
		Integer nc = DatosEjercicio2.getMaxCentros();
		// Restricción de selección de temáticas
		if (ts.size() < m) {
			error += m - ts.size();
		}
		// Restricción de selección de centros
		if (cs.size() > nc) {
			error += cs.size() - nc;
		}
		return -goal - 10000 * error * error;
	}

}
