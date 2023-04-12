package _soluciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Variedad;

public class SolucionEjercicio1AG {
	public static SolucionEjercicio1AG of_Range(List<Integer> ls) {
		return new SolucionEjercicio1AG(ls);
	}

	private Integer beneficio;
	private Map<String,Integer> solucion= new HashMap<>();


	private SolucionEjercicio1AG(List<Integer> ls) {
		beneficio = 0;
		List<Variedad> ls_variedad = DatosEjercicio1.getVariedades();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer e = ls.get(i);
				beneficio += ls_variedad.get(i).beneficio() * e;
				String v = ls_variedad.get(i).variedad();
				solucion.put(v,e);
			}
		}
	}

	@Override
	public String toString() {
		int error = Math.abs(DatosEjercicio1.getNumVariedades() - beneficio);
		String e = error < 1 ? "" : String.format("Error = %d", error);
		return String.format("Variedades de cafe seleccionadas: = %s; Beneficio: = %d", solucion, beneficio, e);
	}
}
