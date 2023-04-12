package ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejemplo3 {

	public static Integer solucionRecursivaSinMemoria(Integer a, Integer b) {
		Integer ac = null;
		if (a < 2 || b < 2) {
			ac = a * a + b;
		} else {
			ac = solucionRecursivaSinMemoria(a / 2, b - 1) + solucionRecursivaSinMemoria(a / 3, b - 2);
		}
		return ac;
	}

	public static Integer solucionRecursivaConMemoria(Integer a, Integer b) {
		Map<IntPair, Integer> m = new HashMap<>();
		return gRecConMem(m, a, b);
	}

	private static Integer gRecConMem(Map<IntPair, Integer> m, Integer a, Integer b) {
		Integer ac = null;
		IntPair key = IntPair.of(a, b);
		if (m.containsKey(key)) {// esta en memoria?
			ac = m.get(key);
		} else {
			if (a < 2 || b < 2) {
				ac = a * a + b;
			} else {
				ac = gRecConMem(m, a / 2, b - 1) + gRecConMem(m, a / 3, b - 2);
			}
			m.put(key, ac);
		}
		return ac;
	}

	public static Integer solucionIterativa(Integer a, Integer b) {

		Map<IntPair, Integer> m = new HashMap<>();
		Integer ac = null;
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				if (i < 2 || j < 2) {
					ac = i * i + j;
				} else {
					ac = m.get(IntPair.of(i / 2, j - 1)) + m.get(IntPair.of(i / 3, j - 2));
				}
				m.put(IntPair.of(i, j), ac);
			}
		}
		return m.get(IntPair.of(a, b));
	}

}
