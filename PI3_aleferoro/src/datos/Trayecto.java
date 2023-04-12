package datos;

public record Trayecto(String origen, String destino, Double precio, Double duracion) {

	public static Trayecto ofFormat(String[] formato) {
		String origen = formato[0];
		String destino = formato[1];
		Double precio = Double.valueOf(formato[2].replace("euros", ""));
		Double duracion = Double.valueOf(formato[3].replace("min", ""));
		return new Trayecto(origen, destino, precio, duracion);
	}

	public static Trayecto of(String origen, String destino, Double precio, Double duracion) {
		return new Trayecto(origen, destino, precio, duracion);
	}

	@Override
	public String toString() {
		return "Trayecto [origen=" + origen + ", destino=" + destino + ", precio=" + precio + ", duracion=" + duracion
				+ "]";
	}

	
	
	

}
