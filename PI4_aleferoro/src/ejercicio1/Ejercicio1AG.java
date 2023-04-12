package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Tipos;
import _soluciones.SolucionEjercicio1AG;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio1AG implements ValuesInRangeData<Integer, SolucionEjercicio1AG> {

	public Ejercicio1AG(String linea) {
		DatosEjercicio1.iniDatos(linea);
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	public Integer size() {
		return DatosEjercicio1.getNumVariedades();
	}

	public Integer min(Integer i) {
		return 0;
	}

	public Integer max(Integer i) {
		return DatosEjercicio1.getKilosDispPorVariedad(i) + 1;
	}

	public Double fitnessFunction(List<Integer> ls) {
		double sum = 0., kilos = 0, error = 0.;
		Map<String, Double> map = new HashMap<>();
		for (int i = 0; i < size(); i++) {
			if (ls.get(i) > 0) {
				// ls.get(i) multiplicarlo por cada porcentaje de cada uno de los compuestos
				for (int j = 0; j < DatosEjercicio1.getNumTipos(); j++) {
					String clave = DatosEjercicio1.getTipos().get(j).tipo();
					if (map.containsKey(clave)) {
						map.put(clave,
								map.get(clave) + (DatosEjercicio1.getPorcentajeCafeJParaVariedadI(i, j) * ls.get(i)));
					} else {
						map.put(clave, DatosEjercicio1.getPorcentajeCafeJParaVariedadI(i, j) * ls.get(i));
					}
				}
				kilos += ls.get(i);
				sum += ls.get(i) * DatosEjercicio1.getBeneficioVariedad(i);
			}
		}
		List<Tipos> tipos = DatosEjercicio1.getTipos();
		for (int i = 0; i < map.keySet().size(); i++) {
			if (tipos.get(i).kilos_disponibles() < map.get(tipos.get(i).tipo())) {
				double diferencia = tipos.get(i).kilos_disponibles() - map.get(tipos.get(i).tipo());
				error += Math.abs(kilos - diferencia);
			}
		}
		return sum - 10000 * error * error;
	}

	@Override
	public SolucionEjercicio1AG solucion(List<Integer> value) {
		return SolucionEjercicio1AG.of_Range(value);
	}

}
