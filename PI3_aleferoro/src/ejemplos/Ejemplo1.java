package ejemplos;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import datos.Carretera;
import datos.CiudadEjemplo;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejemplo1 {

	public static void crearVista(String file, Graph<CiudadEjemplo, Carretera> g, Predicate<CiudadEjemplo> pv,
			Predicate<Carretera> pa, String nombreVista) {
		
		Graph<CiudadEjemplo, Carretera> vista = SubGraphView.of(g, pv, pa);
		
		GraphColors.toDot(vista, "resultados/ejemplo1"+file+nombreVista+".gv",
				x->x.nombre(),
				x->x.nombre(),
				v->GraphColors.colorIf(Color.red, vista.edgesOf(v).size()>1),
				e->GraphColors.color(Color.black));
		
		System.out.println(file+nombreVista+".gv generado en resultados/ejemplo1");
	}

}
