package concesionario.funcionalidad;

import java.io.File;
import java.io.IOException;
import concesionario.funcionalidad.excepciones.CocheExisteException;
import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.CocheNuloException;
import concesionario.funcionalidad.excepciones.ColorNoValidoException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.IndiceFueraDeRangoException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionario.funcionalidad.excepciones.ModeloNoValidoException;

public class GestionConcesionario {

	private static boolean modificado = false;
	private static File file = null;
	private static Concesionario concesionario = new Concesionario();

	public static void setModificado(boolean modificacion) {
		modificado = modificacion;
	}

	public static boolean hayCambios() {
		return modificado;
	}

	public static void setFile(File archivo) {
		file = archivo;
	}

	public static File getFile() {
		return file;
	}

	public static void altaCoche(Modelo modelo, ColorCoche color, String matricula) throws CocheExisteException,
			CocheNuloException, ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		GestionConcesionario.concesionario.add(modelo, color, matricula);
		GestionConcesionario.setModificado(true);
	}

	public static void bajaCoche(String matricula)
			throws MatriculaNoValidaException, CocheNoExisteException, ConcesionarioVacioException {
		GestionConcesionario.concesionario.remove(matricula);
		GestionConcesionario.setModificado(true);
	}

	public static Coche mostrarCoche(String matricula)
			throws MatriculaNoValidaException, ConcesionarioVacioException, CocheNoExisteException {
		return concesionario.get(matricula);

	}

	public static Coche getCoche(Coche coche) throws IndiceFueraDeRangoException {
		return concesionario.get(coche);
	}

	public static Concesionario mostrarCoches(ColorCoche color)
			throws CocheNoExisteException, ConcesionarioVacioException, CocheExisteException, CocheNuloException,
			ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		return concesionario.mostrarCoches(color);
	}

	public static String mostrarConcesionario() {
		return concesionario.toString();
	}

	public static int contarCoches() {
		int contarCoches = GestionConcesionario.concesionario.contarCoches();
		return contarCoches;
	}

	public static boolean deseaGuardar(char opcion) {
		if (opcion == 'S' || opcion == 's')
			return true;
		return false;
	}

	public static void nuevo() throws IOException {
		reset();
	}

	private static void reset() {
		setFile(null);
		setModificado(false);
		concesionario = new Concesionario();
	}

	public static void abrir(File archivo) throws ClassNotFoundException, IOException {
		concesionario = (Concesionario) Ficheros.leer(archivo);
		setFile(archivo);
	}

	public static void guardar() throws IOException {
		Ficheros.escribir(concesionario, file);
		setModificado(false);
		setFile(file);
	}

	public static Concesionario getConcesionario() {
		return concesionario;
	}

}
