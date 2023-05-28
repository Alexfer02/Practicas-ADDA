package _utils;

public record Cliente(int id, Double beneficio) {

public static Cliente of(int id, Double beneficio) { return new Cliente(id, beneficio);
}

public static Cliente ofFormat(String[] formato) { Integer id = Integer.valueOf(formato[0].trim());
Double benef = Double.valueOf(formato[1].trim()); return of(id, benef);
}

public String toString() {
return String.valueOf(this.id());
}

}
