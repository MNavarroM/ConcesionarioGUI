package concesionario.funcionalidad.excepciones;

public class MatriculaNoExisteException extends Exception {
	public MatriculaNoExisteException(String mensaje) {
		super(mensaje);
	}
}
