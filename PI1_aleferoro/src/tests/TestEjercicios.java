package tests;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import ejercicios.Datos1;
import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.common.Trio;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class TestEjercicios {

	public static void main(String[] args) {
		//TestEjercicio1("ficheros/PI1Ej1DatosEntrada.txt");
		//TestEjercicio2("ficheros/PI1Ej2DatosEntrada.txt");
		//TestEjercicio3("ficheros/PI1Ej3DatosEntrada1A.txt","ficheros/PI1Ej3DatosEntrada2A.txt");
		//TestEjercicio4("ficheros/PI1Ej4DatosEntrada.txt");

	}

	private static void TestEjercicio1(String fichero) {
		Function<String, Datos1> parseEntrada = datos -> {
			String[] v = datos.split(",");
			return Datos1.of(Integer.valueOf(v[0]), v[1], Integer.valueOf(v[2]), v[3], Integer.valueOf(v[4]));
		};

		List<Datos1> ld = Stream2.file(fichero).map(parseEntrada).toList();

		System.out.println("///////////////////EJERCICIO 1 /////////////////////");
		for (Integer i = 0; i < ld.size(); i++) {
			Datos1 ob1 = ld.get(i);
			System.out.println("\n----ITERACION " + (i + 1) + "----\n");
			System.out.println("----Solucion Funcional ----");
			System.out.println("Test " + (i + 1) + ": "
					+ Ejercicio1.ejercicioAFuncional(ob1.varA(), ob1.varB(), ob1.varC(), ob1.varD(), ob1.varE()));
			System.out.println("----Solucion Iterativa----");
			System.out.println("Test " + (i + 1) + ": "
					+ Ejercicio1.ejercicioAIterativa(ob1.varA(), ob1.varB(), ob1.varC(), ob1.varD(), ob1.varE()));
			System.out.println("----Solucion Recursiva----");
			System.out.println("Test " + (i + 1) + ": "
					+ Ejercicio1.ejercicioARecursiva(ob1.varA(), ob1.varB(), ob1.varC(), ob1.varD(), ob1.varE()));
		}
		System.out.println("\n///////////////////////////////////////////////////////\n");
	}

	private static void TestEjercicio2(String fichero) {
		Function<String, Trio<Integer, Integer, String>> parseDatos = datos -> {
			String[] v = datos.split(",");
			return Trio.of(Integer.valueOf(v[0]), Integer.valueOf(v[1]), v[2]);
		};
		List<Trio<Integer, Integer, String>> ld = Stream2.file(fichero).map(parseDatos).toList();

		System.out.println("///////////////////EJERCICIO 2 /////////////////////");
		for (Integer i = 0; i < ld.size(); i++) {
			Trio<Integer, Integer, String> ob = ld.get(i);
			System.out.println("\n----ITERACION " + (i + 1) + "----");
			System.out.println("----Solucion Recursiva----");
			System.out.println("Test " + (i + 1) + ": "+ Ejercicio2.recursivaFinal(ob.first(), ob.second(), ob.third()));
			System.out.println("----Solucion Iterativa----");
			System.out.println("Test " + (i + 1) + ": " + Ejercicio2.iterativa(ob.first(), ob.second(), ob.third()));
			System.out.println("----Solucion Funcional ----");
			System.out.println("Test " + (i + 1) + ": " + Ejercicio2.funcional(ob.first(), ob.second(), ob.third()));
			System.out.println("----Solucion Recursiva No Final----");
			System.out.println(
					"Test " + (i + 1) + ": " + Ejercicio2.recursivaNofinal(ob.first(), ob.second(), ob.third()));
		}
	}

	private static void TestEjercicio3(String fichero1, String fichero2) {
		System.out.println("\n/////////////EJERCICIO3/////////////\n");
		for (int i = 1; i < 4; i++) {

			System.out.println("\n----- Fichero PI1Ej3DatosEntrada" + i + " -----");
			System.out.println("#####Solucion Iterativa#####");
			List<Punto2D> li = Ejercicio3.iterativa(
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "A.txt").iterator(),
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "B.txt").iterator());
			System.out.println("Test" + i + ": " + "Los siguientes " + li.size() + " puntos:\n " + li);
			System.out.println("#####Solucion Recursiva Final#####");
			List<Punto2D> lrf = Ejercicio3.recursivaFinal(
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "A.txt").iterator(),
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "B.txt").iterator());
			System.out.println("Test" + i + ": " + "Los siguientes " + lrf.size() + " puntos:\n " + lrf);
			System.out.println("#####Solucion Funcional#####");
			List<Punto2D> lf = Ejercicio3.funcional(
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "A.txt").iterator(),
					Stream2.file("ficheros/PI1Ej3DatosEntrada" + i + "B.txt").iterator());
			System.out.println("Test" + i + ": " + "Los siguientes " + lf.size() + " puntos:\n" + lf);

		}
		System.out.println("\n////////////////////////////////////\n");
	}

	private static void TestEjercicio4(String fichero) {
		Function<String, Trio<Integer, Integer, Integer>> parseDatos = datos -> {
			String[] v = datos.split(",");
			return Trio.of(Integer.valueOf(v[0]), Integer.valueOf(v[1]), Integer.valueOf(v[2]));
		};
		List<Trio<Integer, Integer, Integer>> ld = Stream2.file(fichero).map(parseDatos).toList();
		System.out.println("///////////////////EJERCICIO 4 /////////////////////");
		for (int i = 0; i < ld.size(); i++) {
			Trio<Integer, Integer, Integer> ob = ld.get(i);
			System.out.println("\n----ITERACION " + (i + 1) + "----");
			System.out.println("---------RECURSIVA SIN MEMORIA---------");
			System.out.println("Test " + (i + 1) + ":"
					+ Ejercicio4.RecursivaSinMemoria(ob.first(), ob.second(), ob.third()));
			System.out.println("---------RECURSIVA CON MEMORIA---------");
			System.out.println("Test " + (i + 1) + ":"
					+ Ejercicio4.RecursivaConMemoria(ob.first(), ob.second(), ob.third()));
			System.out.println("---------ITERATIVA---------");
			System.out.println(
					"Test " + (i + 1) + ":" + Ejercicio4.Iterativa(ob.first(), ob.second(), ob.third()));
		}
	}

}
