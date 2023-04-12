package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigadores;

public class SolucionEjercicio3AG {

	private Integer calidad;
	private List<Investigadores> investigadores_f;
	private List<List<Integer>> horas;

	private SolucionEjercicio3AG() {
		calidad = 0;
		investigadores_f = new ArrayList<>();
		horas = new ArrayList<>();
	}

	private SolucionEjercicio3AG(List<Integer> ls) {
		Integer numInv = DatosEjercicio3.getNumInvestigadores();
		Integer numTrab = DatosEjercicio3.getNumTrabajos();
		Integer numEsp = DatosEjercicio3.getNumEspecialidades();
		calidad = 0;
		investigadores_f = new ArrayList<>();
		investigadores_f.addAll(DatosEjercicio3.getInvestigadores());
		horas = new ArrayList<>();
		for (int i = 0; i < numInv; i++) {
			horas.add(new ArrayList<>());
		}
		for (int j = 0; j < numTrab; j++) {
			Integer jj = j * numInv;
			List<Integer> trab = ls.subList(jj, jj + numInv);
			for (int i = 0; i < numInv; i++) {
				horas.get(i).add(trab.get(i));
			}
			Boolean realiza = true;
			for (int k = 0; k < numEsp; k++) {
				Integer suma = 0;
				for (int i = 0; i < numInv; i++) {
					suma += trab.get(i) * DatosEjercicio3.getTrabajadorEspecialidad(i, k);
				}
				if (suma < DatosEjercicio3.getDiasNecesariosTrabajoEspecialidad(j, k)) {
					realiza = false;
					k = numEsp;
				}
			}
			if (realiza) {
				calidad += DatosEjercicio3.getCalidad(j);
			}
		}
	}

	public static SolucionEjercicio3AG of_Range(List<Integer> ls) {
		System.out.println(ls);
		return new SolucionEjercicio3AG();
	}

	public String toString() {
		String s = investigadores_f.stream().map(i -> "INV" + (i.id() + 1) + ": " + horas.get(i.id()))
				.collect(Collectors.joining("\n", "Reparto de horas:\n", "\n"));
		return String.format("%sSuma de las calidades de los trabajos realizados: %d", s, calidad);
	}
	

}
