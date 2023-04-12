package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio4 {

	private static Integer contarVocales(String palabra) {
		Integer numVoc = 0;
		List<String> vocales = new ArrayList<>();
		vocales.add("a");
		vocales.add("e");
		vocales.add("i");
		vocales.add("o");
		vocales.add("u");
		for (int i = 0; i < palabra.length(); i++) {
			String letra = String.valueOf(palabra.charAt(i));
			if (vocales.contains(letra)) {
				numVoc += 1;
			}
		}
		return numVoc;
	}

	private static Boolean cuentavocalesN(List<Tree<String>> arboles) {
		Boolean res = true;
		Integer sumVoc = contarVocales(arboles.get(0).toString());
		Integer i = 0;
		while (i < arboles.size() && res) {
			String arbol = arboles.get(i).toString();
			Integer suma = contarVocales(arbol);

			res = sumVoc == suma;
			i++;
		}
		return res;
	}

	public static Boolean solucion_recursivaBinaria(BinaryTree<String> tree) {
		return solucion_Binaria(tree, 0);
	}

	public static Boolean solucion_Binaria(BinaryTree<String> tree, Integer numvoc) {
		return switch (tree) {
		case BEmpty<String> t -> false;
		case BLeaf<String> t -> {
			Integer voc = contarVocales(t.label());
			yield numvoc == voc;
		}
		case BTree<String> t -> {
			numvoc = contarVocales(t.label());
			yield solucion_Binaria(t.left(), numvoc) == solucion_Binaria(t.right(), numvoc);
		}

		};
	}

	public static Boolean solucion_recursivaNaria(Tree<String> tree) {
		return solucion_Naria(tree, true);
	}

	private static Boolean solucion_Naria(Tree<String> tree, Boolean res) {
		return switch (tree) {
		case TEmpty<String> t -> false;
		case TLeaf<String> t -> res;
		case TNary<String> t -> {
			if (res) {
				List<Tree<String>> hijos = t.elements();
				Boolean r = null;
				if (!hijos.contains(Tree.empty())) {
					r = cuentavocalesN(hijos);
					if (r) {
						for (Tree<String> hijo : hijos) {
							r = solucion_Naria(hijo, r);
						}
					} else {
						r = false;
					}
				}
				yield r;
			} else {
				yield res;
			}
		}
		};
	}

}
