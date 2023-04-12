package ejercicios;

public record Datos1(Integer varA, String varB, Integer varC, String varD, Integer varE) {

	public static Datos1 of(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return new Datos1(varA, varB, varC, varD, varE);
	}


}

