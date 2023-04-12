package datos;


public record Persona(Integer id, String nombre, Integer anio, String borncity)  {

	public static Persona ofFormat(String[] formato) {
		Integer id = Integer.parseInt(formato[0]);
		String nombre = formato[1];
		Integer anio = Integer.parseInt(formato[2]);
		String borncity = formato[3];
		return new Persona(id, nombre, anio, borncity);
	}
	
	public static Persona of(Integer id, String nombre, Integer anio, String borncity) {
		return new Persona(id, nombre, anio, borncity);
	}
	
	public String toString() {
        return this.nombre +"\n"+" "+ this.borncity + " "+ this.anio ;
    }
}
