package tests;

import java.util.List;

import _datos.DatosClientes;
import _soluciones.SolucionClientes;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicio4.ClientesVertex;

public class TestEjercicio4 {
	public static void main(String[] args) {
		List.of(1, 2).forEach(num_test -> {
			TestsPI5.iniTest("Ejercicio4DatosEntrada", num_test, DatosClientes::iniDatos);

			TestsPI5.tests(ClientesVertex.initial(), // Vertice Inicial
					ClientesVertex.goal(), // Predicado para un vertice final
					GraphsPI5::clientesBuilder, // Referencia alBuilder del grafo
					ClientesVertex::greedyEdge, // Referencia a laFuncion para la arista voraz
					SolucionClientes::of); // Referencia almetodo factoria para la solucion
		});
	}

}