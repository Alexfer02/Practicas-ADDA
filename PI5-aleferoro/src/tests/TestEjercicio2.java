package tests;

import java.util.List;

import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicio2.Ejercicio2Vertex;

public class TestEjercicio2 {

	public static void main(String[] args) {
		List.of(1, 2, 3).forEach(num_test -> {

			TestsPI5.iniTest("Ejercicio2DatosEntrada", num_test, DatosEjercicio2::iniDatos);

			TestsPI5.tests(Ejercicio2Vertex.initial(), // Vertice inicial

					Ejercicio2Vertex.goal(), // Predicado para un vertice final
					GraphsPI5::ejercicio2Builder, // Referencia al Builder del grafo
					Ejercicio2Vertex::greedyEdge, // Referencia a la Funcion para la arista voraz
					SolucionEjercicio2::of); // Referencia al metodo factoria para la solucion
		});
	}
}
