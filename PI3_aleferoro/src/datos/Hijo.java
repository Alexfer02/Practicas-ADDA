package datos;

public record Hijo(Integer idpadre, Integer idhijo) {

	
	public static Hijo ofFormat(String[] formato) {
		Integer idpadre = Integer.parseInt(formato[0]);
		Integer idhijo = Integer.parseInt(formato[1]);
		return new Hijo(idpadre, idhijo);
	}
	
	public static Hijo of(Integer idpadre, Integer idhijo) {
		return new Hijo(idpadre, idhijo);
	}
	@Override
	public String toString() {
		return "("+this.idpadre +this.idhijo+")";
	}
	
}