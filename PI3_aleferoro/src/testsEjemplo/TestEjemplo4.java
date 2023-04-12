package testsEjemplo;

import java.util.Set;

import org.jgrapht.Graph;

import datos.Pasillo;
import ejemplos.Ejemplo4;
import us.lsi.colors.GraphColors;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjemplo4 {

	public static void main(String[] args) {
		testEjemplo4("Supermercado1");
		testEjemplo4("Supermercado2");
		testEjemplo4("Supermercado3");

	}
	
	public static void testEjemplo4(String file) {
		System.out.println("========================================== " + "Soluciones para " + file + "========================================== " );
		
		//1º Implementar el tipo pasillo
		
		Graph<String, Pasillo> grafo = GraphsReader.newGraph("ficheros/testsProfesores/" + file + ".txt",
															v -> v[0], 
															Pasillo::ofFormat, 
															Graphs2::simpleWeightedGraph, 
															Pasillo::mts);
		
		GraphColors.toDot(grafo, "/resultados/ejemplo4/" + file + "_original.gv");
		
		System.out.println("\nArchivo " + file + ".txt Datos de entrada: " + grafo);
		
		//Apartado A
		Set<String> solApartadoA = Ejemplo4.apartadoA(grafo);
		System.out.println("\n Apartado A): ");
		System.out.println("Las camaras deben colocarse en los siguientes cruces: " + solApartadoA.size());
		solApartadoA.forEach(c -> System.out.println("\t- Cruce: " + c));
		
		//Apartado B
		System.out.println("\n Apartado B): ");
		Ejemplo4.apartadoB(grafo, solApartadoA, file);
	
		//Apartado C
		System.out.println("\n Apartado C): ");
		System.out.println(file + "C.gv generado en resultados/ejemplo4");

	}

}
