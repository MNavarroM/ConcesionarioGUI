package concesionario.gui;

import java.util.ListIterator;
import concesionario.funcionalidad.Coche;
import concesionario.funcionalidad.ColorCoche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.CocheExisteException;
import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.CocheNuloException;
import concesionario.funcionalidad.excepciones.ColorNoValidoException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionario.funcionalidad.excepciones.ModeloNoValidoException;

/**
 * Muestra los coches de un color del concesionario
 * @author Mario Navarro Madrid
 *
 */
public class MostrarConcesionarioColor extends ListarConcesionario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MostrarConcesionarioColor(ColorCoche color) throws CocheNoExisteException, ConcesionarioVacioException, CocheExisteException, CocheNuloException, ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException{
		super(obtenerConcesionario(color));		
	}

	/**
	 * Obtiene el LisIterator del ArrayList de coches del color pasado como parametro
	 * @param color
	 * @return
	 * @throws CocheNoExisteException
	 * @throws ConcesionarioVacioException
	 * @throws CocheExisteException
	 * @throws CocheNuloException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 * @throws MatriculaNoValidaException
	 */
	private static ListIterator<Coche> obtenerConcesionario(ColorCoche color)
			throws CocheNoExisteException, ConcesionarioVacioException, CocheExisteException, CocheNuloException,
			ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		return GestionConcesionario.mostrarCoches(color).iterator();
	}
}
