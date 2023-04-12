package ejemplos.ejemplo2.manual;

import java.util.List;

import _datos.DatosSubconjuntos;
import _soluciones.SolucionSubconjuntos;
import us.lsi.common.List2;

public class SubconjuntosState {

	SubconjuntosProblem actual;
	Double acumulado;
	List<Integer> acciones;
	List<SubconjuntosProblem> anteriores;
	
	private SubconjuntosState(SubconjuntosProblem p, Double a, 
		List<Integer> ls1, List<SubconjuntosProblem> ls2) {
		// TODO Inicializar las propiedades individuales
	}

	public static SubconjuntosState initial() {
		// TODO Crear el estado inicial
	}

	public static SubconjuntosState of(SubconjuntosProblem prob, Double acum, List<Integer> lsa,
			List<SubconjuntosProblem> lsp) {
		return new SubconjuntosState(prob, acum, lsa, lsp);
	}

	public void forward(Integer a) {		
		// TODO Avanzar un estado segun la alternativa a
	}

	public void back() {
		// TODO Retroceder al estado anterior
	}

	public List<Integer> alternativas() {
		// TODO Alternativas segun el actual
		return null;
	}

	public Double cota(Integer a) {
		// TODO Cota = acumulado + func(a, actual) + h(vecino(actual, a))
		return null;
	}

	public Boolean esSolucion() {
		// TODO Cuando todos los elementos del universo se han cubierto
		return null;
	}

	public Boolean esTerminal() {
		// TODO Cuando se han recorrido todos los subconjuntos
		return null;
	}

	public SolucionSubconjuntos getSolucion() {
		// TODO Aprovechamos lo hecho en la PI4
		return null;
	}

}
