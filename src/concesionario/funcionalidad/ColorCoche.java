package concesionario.funcionalidad;

/**
 * Enum de colores para coche
 * @author Mario Navarro Madrid
 *
 */
public enum ColorCoche {

	AZUL, ROJO, PLATA;

	/**
	 * M\u00e9todo para devolver los elementos de la enumeraci\u00f3n a un array de String.
	 * @return
	 */
	public static String[] toArray() {
		String[] colores = new String[ColorCoche.values().length];
		int i = 0;
		for (ColorCoche c : ColorCoche.values()) {
			colores[i++] = c.toString();
		}
		return colores;
	}

}
