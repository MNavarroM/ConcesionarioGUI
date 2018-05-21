package concesionario.funcionalidad.excepciones;

public class CocheNoExisteException extends Exception {
	public CocheNoExisteException(String mensaje) {
		super(mensaje);
	}
}
