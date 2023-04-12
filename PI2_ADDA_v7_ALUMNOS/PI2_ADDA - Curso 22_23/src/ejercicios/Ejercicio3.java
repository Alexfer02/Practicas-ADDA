package ejercicios;

import java.util.ArrayList;
import java.util.List;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio3 {

	public static List<String> solucion_recursivaBinaria(BinaryTree<Character> tree, Character second) {
		return solucion_recursiva(tree, second, new ArrayList<String>(),"");
	}

	private static List<String> solucion_recursiva(BinaryTree<Character> tree, Character second,
			ArrayList<String> ls, String s) {

		return switch (tree) {
		case BEmpty<Character> t -> ls;
		case BLeaf<Character> t -> {
			if (!t.label().equals(second)) {
				ls.add(s+ String.valueOf(t.label()));		
			}
			yield ls;
		}
		case BTree<Character> t -> {
			if (!t.label().equals(second)) {
				solucion_recursiva(t.left(), second, ls,s+String.valueOf(t.label()));
				solucion_recursiva(t.right(), second, ls,s+String.valueOf(t.label()));
			}
			yield ls;
		}
		};
	}

	public static List<String> solucion_recursivaNaria(Tree<Character> tree, Character second) {
		return solucion_recursivaN(tree ,second , new ArrayList<String>(),"");
	}
	
	private static List<String> solucion_recursivaN(Tree<Character> tree, Character second,ArrayList<String> ls, String s){
		return switch(tree) {
		case TEmpty<Character> t-> ls;
		case TLeaf<Character> t->{
			if(!t.label().equals(second)) {
				ls.add(s+ String.valueOf(t.label()));
			}
			yield ls;
		}
		case TNary<Character> t->{
			if(!t.label().equals(second)) {
				t.elements().forEach(tc-> solucion_recursivaN(tc, second, ls, s+ String.valueOf(t.label())));
			}
			yield ls;
		}
		};
	}
  
}
