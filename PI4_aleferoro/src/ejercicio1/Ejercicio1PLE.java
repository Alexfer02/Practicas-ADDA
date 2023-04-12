package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Tipos;
import _datos.DatosEjercicio1.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {

	private static List<Tipos> tipos_f;
	private static List<Variedad> variedad_f;
	
	public static Integer getNumTipos() {
		return tipos_f.size();
	}
	
	public static Integer getNumVariedades() {
		return variedad_f.size();
	}
	
	public static Integer getKilosDisp(Integer i) {
		return tipos_f.get(i).kilos_disponibles();
	}
	
	public static Integer getBeneficioVariedad(Integer i) {
		return variedad_f.get(i).beneficio();
	}
	
	public static Double getPorcentajeCafeJParaVariedadI(Integer i, Integer j) {
		return variedad_f.get(i).mapa().get(tipos_f.get(j).tipo());
	}
	public static List<Tipos> getTipos(){
		return tipos_f;
	}
	
	public static List<Variedad> getVariedades(){
		return variedad_f;
	}
	
	public static void ejercicio1_model() throws IOException {
		for(int i =1;i<4;i++) {
			DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada"+i+".txt");

			tipos_f = DatosEjercicio1.getTipos();
			variedad_f =DatosEjercicio1.getVariedades();
			//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
			AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_models/Ejercicio1-"+i+".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-"+i+".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println("Solucion apartado: "+i);
			System.out.println(solution.toString((s,d)->d>0.));
			System.out.println("\n################################################################################################\n");
		}
		
	}
	
	public static void main(String[] args) throws IOException {	
		ejercicio1_model();
	}

}
