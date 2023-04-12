package testsEjemplo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import ejemplos.Ejemplo3;
import us.lsi.colors.GraphColors;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjemplo3 {
    //MUY SIMILAR A NUESTRO 3
    public static void main(String[] args) {
        testEjemplo3("Comensales");
    }
    public static void testEjemplo3(String file) {
        //montar un grafo con todo por defecto
        Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false); //aristas por defecto
        Files2.streamFromFile("ficheros/testsProfesores/"+file + ".txt").forEach(linea -> {
            String[] v = linea.split(",");
            g.addVertex(v[0]);
            g.addVertex(v[1]);
            g.addEdge(v[0],v[1]);
        });

        GraphColors.toDot(g, "resultados/ejemplo3/"+file+"_inicial.gv"); //grafo para que lo pinte por defecto 
        Ejemplo3.todosLosApartados(g,file);
    }

}
