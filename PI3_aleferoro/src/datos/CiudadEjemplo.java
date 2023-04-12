package datos;


public record CiudadEjemplo(String nombre, Integer habitantes)  {

	public static CiudadEjemplo ofFormat(String[] formato) {
		String nombre = formato[0];
		Integer habitantes = Integer.parseInt(formato[1]);
		return new CiudadEjemplo(nombre,habitantes);
	}
	
	public static CiudadEjemplo of(String nombre, Integer habitantes) {
		return new CiudadEjemplo(nombre,habitantes);
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
