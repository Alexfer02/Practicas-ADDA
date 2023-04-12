package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio1 {

	public static Map<Integer, List<String>> ejercicioAIterativa(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {

		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 2, elem.a() % 3 == 0 ? elem.s() + elem.a().toString()
					: elem.s().substring(elem.a() % elem.s().length()));
		};
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		Map<Integer, List<String>> acc = new HashMap<Integer, List<String>>();

		while (elem.a() < varC) {
			String nom = elem.s() + varD;
			if (nom.length() < varE) {
				Integer key = nom.length();
				if (acc.containsKey(key)) {
					acc.get(key).add(nom);
				} else if (!acc.containsKey(key)) {
					List<String> ls = new ArrayList<>();
					ls.add(nom);
					acc.put(key, ls);
				}
			}
			if (elem.a() % 3 == 0) {
				elem = EnteroCadena.of(elem.a() + 2, elem.s() + elem.a().toString());
			} else {
				elem = EnteroCadena.of(elem.a() + 2, elem.s().substring(elem.a() % elem.s().length()));
			}
		}
		return acc;
	}

	public static Map<Integer, List<String>> ejercicioARecursiva(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {
		return ejercicioARecursiva(new HashMap<Integer, List<String>>(), EnteroCadena.of(varA, varB), varC, varD, varE);
	}

	private static Map<Integer, List<String>> ejercicioARecursiva(Map<Integer, List<String>> acc, EnteroCadena elem,
			Integer varC, String varD, Integer varE) {
		if (elem.a() < varC) {
			String nom = elem.s() + varD;
			if (nom.length() < varE) {
				Integer key = nom.length();
				if (acc.containsKey(key)) {
					acc.get(key).add(nom);
				} else {
					List<String> ls = new ArrayList<>();
					ls.add(nom);
					acc.put(key, ls);
				}
			}
			if (elem.a() % 3 == 0) {
				ejercicioARecursiva(acc, EnteroCadena.of(elem.a() + 2, elem.s() + elem.a().toString()), varC, varD,
						varE);
			} else {
				ejercicioARecursiva(acc,
						EnteroCadena.of(elem.a() + 2, elem.s().substring(elem.a() % elem.s().length())), varC, varD,
						varE);
			}
		}
		return acc;
	}

	public static Map<Integer, List<String>> ejercicioAFuncional(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {

		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 2, elem.a() % 3 == 0 ? elem.s() + elem.a().toString()
					: elem.s().substring(elem.a() % elem.s().length()));
		};
		return Stream.iterate(EnteroCadena.of(varA, varB), elem -> elem.a() < varC, nx).map(elem -> elem.s() + varD)
				.filter(nom -> nom.length() < varE).collect(Collectors.groupingBy(String::length));
	}
}
