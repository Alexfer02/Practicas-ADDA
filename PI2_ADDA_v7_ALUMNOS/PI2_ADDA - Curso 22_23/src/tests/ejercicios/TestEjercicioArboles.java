package tests.ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class TestEjercicioArboles {

	public static void main(String[] args) {
		//test_ejercicio3();
		testsEjercicio4();
	}

	// Ejercicio3

	public static void test_ejercicio3() {

		String binario = "ficheros/Ejercicio3DatosEntradaBinario.txt";
		String nario = "ficheros/Ejercicio3DatosEntradaNario.txt";

		List<Pair<BinaryTree<Character>, Character>> inputsB = Files2.streamFromFile(binario).map(linea -> {
			String[] aux = linea.split("#");
			Preconditions.checkArgument(aux.length == 2);
			return Pair.of(BinaryTree.parse(aux[0], s -> s.charAt(0)), aux[1].charAt(0));

		}).toList();
		List<Pair<Tree<Character>, Character>> inputsN = Files2.streamFromFile(nario).map(linea -> {
			String[] aux = linea.split("#");
			return Pair.of(Tree.parse(aux[0], s -> s.charAt(0)), aux[1].charAt(0));

		}).toList();

		System.out.println("\n###########EJERCICIO 3###########");
		System.out.println("Arboles binarios: ");
		inputsB.stream().forEach(par -> {
			BinaryTree<Character> tree = par.first();
			System.out.println("Arbol: " + tree + "\tCaracter: " + par.second() + "\t["
					+ Ejercicio3.solucion_recursivaBinaria(tree, par.second()) + "] \n");

		});
		System.out.println("Arboles n-arios: ");
		inputsN.stream().forEach(par -> {
			Tree<Character> tree = par.first();
			System.out.println("Arbol: " + tree + "\tCaracter: " + par.second() + "\t["
					+ Ejercicio3.solucion_recursivaNaria(tree, par.second()) + "] \n");

		});
		System.out.println("\n#################################");

	}

	// Ejercicio4

	public static void testsEjercicio4() {
		String binario = "ficheros/Ejercicio4DatosEntradaBinario.txt";
		String nario = "ficheros/Ejercicio4DatosEntradaNario.txt";

		List<BinaryTree<String>> inputsB = Files2.streamFromFile(binario).map(BinaryTree::parse).toList();
		List<Tree<String>> inputsN = Files2.streamFromFile(nario).map(Tree::parse).toList();
		System.out.println(
				"\n---------------------------------------------------------------------------------------------");
		System.out.println("EJERCICIO 4");
		System.out.println(
				"---------------------------------------------------------------------------------------------\n");

		System.out.println("\n SOLUCION RECURSIVA BINARIA");
		inputsB.stream().forEach(x -> System.out.println(x + ":" + Ejercicio4.solucion_recursivaBinaria(x)));

		System.out.println("\n SOLUCION RECURSIVA N-ARIA");
		inputsN.stream().forEach(x -> System.out.println(x + ":" + Ejercicio4.solucion_recursivaNaria(x)));
	}

}
