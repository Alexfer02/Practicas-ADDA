package ejemplo1;

import java.util.List;
import _datos.DatosMulticonjunto;
import _soluciones.SolucionMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeMulticonjuntoAG implements ValuesInRangeData<Integer, SolucionMulticonjunto> {

	public InRangeMulticonjuntoAG(String linea) {
		DatosMulticonjunto.iniDatos(linea);
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	public Integer size() {
		return DatosMulticonjunto.getNumElementos();
	}

	public Integer min(Integer i) {
		return 0;
	}

	public Integer max(Integer i) {
		return DatosMulticonjunto.getMultiplicidad(i) + 1;
	}

	public SolucionMulticonjunto solucion(List<Integer> ls) {
		return SolucionMulticonjunto.of_Range(ls);
	}

	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0., sum = 0., error = 0.;
		System.out.println(ls);

		for (int i = 0; i < size(); i++) {
			if (ls.get(i) > 0) {
				goal += ls.get(i);
				sum += ls.get(i) * DatosMulticonjunto.getElemento(i);
			}
		}
		error += Math.abs(sum - DatosMulticonjunto.getSuma());
		return -goal - 10000 * error;
	}

}
