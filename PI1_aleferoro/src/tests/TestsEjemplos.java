package tests;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.IntPair;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;
import ejemplos.Ejemplo1;
import ejemplos.Ejemplo2;
import ejemplos.Ejemplo3;

public class TestsEjemplos {

	public static void main(String[] args) {
		Ejemplo1("ficheros/Ejemplo1DatosEntrada.txt");
		Ejemplo2("ficheros/Ejemplo2DatosEntrada.txt");
		Ejemplo3("ficheros/Ejemplo3DatosEntrada.txt");
	}

	public static void Ejemplo1(String fichero) {
		Function<String, Punto2D> parsePunto = s -> {
			String[] v = s.split(",");
			return Punto2D.of(Double.valueOf(v[0]), Double.valueOf(v[1]));
		};

		List<Punto2D> ls = Stream2.file(fichero).map(parsePunto).toList();

		System.out.println("1) Solucion Funcional: \n" + Ejemplo1.solucionFuncional(ls));
		System.out.println("1) Solucion Iterativa: \n" + Ejemplo1.solucionIterativa(ls));
		System.out.println("1) Solucion Recursiva final: \n" + Ejemplo1.solucionRecursivaFinal(ls));
		System.out.println("----------------------------------------------------------------");
	}

	public static void Ejemplo2(String fichero) {
		List<IntPair> ls = Stream2.file(fichero).map(IntPair::parse).toList();

		ls.forEach(par -> {
			Integer a = par.first();
			Integer b = par.second();

			System.out.println("1) Solucion Recursiva NO Final: \n" + Ejemplo2.solucionRecursivaNoFinal(a, b));
			System.out.println("2) Solución Recursiva Final: \n " + Ejemplo2.solucionRecursivaFinal(a, b));
			System.out.println("3) Solución Iterativa: \n" + Ejemplo2.solucionIterativa(a, b));
			System.out.println("4) Solución Funcional: \n" + Ejemplo2.solucionFuncional(a, b));
			System.out.println("...................................................................\n");
		});
		System.out.println("----------------------------------------------");
	}

	public static void Ejemplo3(String fichero) {
		List<IntPair> ls = Stream2.file(fichero).map(IntPair::parse).toList();
		System.out.println(ls);
		ls.forEach(par -> {
			Integer a = par.first();
			Integer b = par.second();
			System.out.println("1) Solucion Recursiva Sin memoria: \n" + Ejemplo3.solucionRecursivaSinMemoria(a, b));
			System.out.println("2) Solucion Recursiva con memoria: \n" + Ejemplo3.solucionRecursivaConMemoria(a, b));
			System.out.println("3) Solucion Iterativa: \n" + Ejemplo3.solucionIterativa(a, b));

		});

	}
}
