package ejercicio3;

import java.util.List;

import _datos.DatosEjercicio3;
import _soluciones.SolucionEjercicio3AG;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio3AG implements ValuesInRangeData<Integer, SolucionEjercicio3AG> {

	public Ejercicio3AG(String fichero) {
		DatosEjercicio3.iniDatos(fichero);
	}

	@Override
	public Integer size() {

		return DatosEjercicio3.getNumInvestigadores()*DatosEjercicio3.getNumTrabajos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, kk = 0, capacidad = 0;
		// Definimos algunos valores por comodidad
		Integer numInv = DatosEjercicio3.getNumInvestigadores();
		Integer numTrab = DatosEjercicio3.getNumTrabajos();
		Integer numEsp = DatosEjercicio3.getNumEspecialidades();
		for (int j = 0; j < numTrab; j++) {
			// Obtenemos un índice para dividir la lista de entrada en los distintos trabajos
			Integer jj = j * numInv;
			List<Integer> trab = ls.subList(jj, jj + numInv);
			Boolean realiza = true;
			for (int k = 0; k < numEsp; k++) {
				Integer suma = 0;
				for (int i = 0; i < numInv; i++) {
					suma += trab.get(i) * 
							DatosEjercicio3.getTrabajadorEspecialidad(i, k);
				}
				// Restricción de días necesarios
				if (suma != DatosEjercicio3.getDiasNecesariosTrabajoEspecialidad(j, k)) {
					realiza = false;
					error += Math.abs(suma - DatosEjercicio3.getDiasNecesariosTrabajoEspecialidad(j, k));
				}
				if (realiza) {
					// Si el trabajo se realiza, sumamos su calidad
					goal += DatosEjercicio3.getCalidad(j);
				}
			}
		}
		for (int i = 0; i < numInv; i++) {
			capacidad = 0;
			for (int ii = i; ii < ls.size(); ii += numInv) {
				capacidad += ls.get(ii);
			}
			// Restricción de días disponibles
			if (capacidad > DatosEjercicio3.getDiasDisponiblesTrabajador(i)) {
				error += capacidad - DatosEjercicio3.getDiasDisponiblesTrabajador(i);
			}
		}
		// Cálculo de k
		Integer suma = 0;
		for (int j = 0; j < numTrab; j++) {
			suma += DatosEjercicio3.getCalidad(j);
		}
		kk = Math.pow(suma, 2);
		return goal - kk * error;
	}

	@Override
	public SolucionEjercicio3AG solucion(List<Integer> ls) {
		return SolucionEjercicio3AG.of_Range(ls);
	}

	@Override
	public Integer max(Integer i) {
		Integer l = i % DatosEjercicio3.getNumInvestigadores();
		return DatosEjercicio3.getDiasDisponiblesTrabajador(l) + 1;

	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
