package concesionario.funcionalidad;

public enum Modelo {

	CORDOBA(Marca.SEAT), TOLEDO(Marca.SEAT), IBIZA(Marca.SEAT), SERIE1(Marca.BMW), SERIE2(Marca.BMW), SERIE3(Marca.BMW);

	private Marca marca;

	private Modelo(Marca marca) {
		this.marca = marca;
	}

	public Marca getMarca() {
		return marca;
	}

	@Override
	public String toString() {
		return marca + " " + name();
	}

	public static String[] toArray() {
		String[] modelos = new String[Modelo.values().length];
		int i = 0;
		for (Modelo c : Modelo.values()) {
			modelos[i++] = c.toString();
		}
		return modelos;
	}

}
