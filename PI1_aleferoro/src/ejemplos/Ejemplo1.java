package ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejemplo1 {

	public static Map<Cuadrante, Double> solucionFuncional(List<Punto2D> ls) {
		return ls.stream().collect(Collectors.groupingBy(Punto2D::getCuadrante,
				Collectors.<Punto2D, Double>reducing(0., x -> x.x(), (x, y) -> x + y)));
	}

	public static Map<Cuadrante, Double> solucionIterativa(List<Punto2D> ls) {
		// Inicializa la secuencia
		// Inicializa el acumulador
		// Abro un bucle while
		// Función de acumulación
		// next elemento
		// return del acumulador

		Integer e = 0;
		Map<Punto2D.Cuadrante, Double> ac = new HashMap();
		while (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.getCuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}
			e++;
		}
		return ac;
	}

	public static Map<Cuadrante, Double> solucionRecursivaFinal(List<Punto2D> ls) {
		// Inicializa la secuencia
		// Inicializa el acumulador
		// acumulador= llamadarecursiva()
		// return ac;
		Integer e = 0;
		Map<Cuadrante, Double> ac = new HashMap<>();
		ac = solucionRecursivaFinal(e, ac, ls);
		return ac;
	}

	private static Map<Cuadrante, Double> solucionRecursivaFinal(Integer e, Map<Cuadrante, Double> ac,
			List<Punto2D> ls) {
		// if hay siguiente
		// llamada recursiva (next(e),funcionAcumulacion,ls)
		// return acumulador

		if (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.getCuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}

			ac = solucionRecursivaFinal(e + 1, ac, ls);
		}
		return ac;
	}

}
