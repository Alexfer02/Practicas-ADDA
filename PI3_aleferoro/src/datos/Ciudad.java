package datos;

public record Ciudad(String nombre, Integer puntuacion) {

	public static Ciudad ofFormat(String[] formato) {
		String nombre= formato[0];
		Integer puntuacion= Integer.parseInt(formato[1].replace("p", ""));
		return new Ciudad(nombre, puntuacion);
	}
	
	public static Ciudad of(String nombre,Integer puntuacion) {
		return new Ciudad(nombre,puntuacion);
	}

	@Override
	public String toString() {
		return  nombre;
	}
	
	
}
