package _soluciones;

import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import us.lsi.common.List2;

public class SolucionEjercicio2AG {

	public SolucionEjercicio2AG(List<Integer> ls) {
		total = 0.;
		cursos = List2.empty();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				total += DatosEjercicio2.getPrecioInscripcion(i);
				cursos.add(DatosEjercicio2.getCurso(i));
			}
		}
	}

	@Override
	public String toString() {
		String s = cursos.stream().map(e->"S"+e.id()).collect(Collectors.joining(",","Cursos elegidos: {","}\n"));
		return String.format("%sCoste Total: %.1f",s,total);
	}

	public static SolucionEjercicio2AG create(List<Integer> ls) {
		return new SolucionEjercicio2AG(ls);
	}

	private Double total;
	private List<Curso> cursos;

}
