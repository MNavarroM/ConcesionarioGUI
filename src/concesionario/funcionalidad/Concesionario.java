package concesionario.funcionalidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import concesionario.funcionalidad.excepciones.CocheExisteException;
import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.CocheNuloException;
import concesionario.funcionalidad.excepciones.ColorNoValidoException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.IndiceFueraDeRangoException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionario.funcionalidad.excepciones.ModeloNoValidoException;

/**
 * Clase que permite la gesti\u00f3n de un concesionario.
 * 
 * @author Mario Navarro Madrid
 *
 */
public class Concesionario implements Serializable, Iterable<Coche>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Arraylist de Coches
	 */
	
	private ArrayList<Coche> coches = null;

	/**
	 * Constructor del objeto concesionario
	 */
	public Concesionario() {
		coches = new ArrayList<Coche>();
	}

	/**
	 * A�ade un coche al GestionConcesionario.concesionario
	 * 
	 * @param coche	Coche a a\u00f1 adir
	 * @throws CocheExisteException
	 *             Cuando el coche ya existe en el concesionario
	 * @throws CocheNuloException
	 *             Cuando el coche introducido es nulo            
	 * @throws MatriculaNoValidaException 
	 * 			   Cuado la matr\u00edcula no es v\u00e1lida
	 * @throws ColorNoValidoException
	 * 	  			   Cuando el color introducido es nulo 
	 * @throws ModeloNoValidoException 
	 * 	  			   Cuando el modelo introducido es nulo
	 */
	public void add(Modelo modelo, ColorCoche color, String matricula) throws CocheExisteException, CocheNuloException, ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		Coche coche = new Coche(matricula,modelo,color);
		if (coches.contains(coche))
			throw new CocheExisteException(
					"El coche con matr\u00edcula " + coche.getMatricula() + " ya existe en el concecionario");			
		coches.add(coche);
	}
	
	/**
	 * M\u00e9todo usado por otros m\u00e9todo mostrarCoches(Color color) para a\u00f1adir coches a un arraylist 
	 * @param coche Coche a a\u00f1adir
	 * @throws CocheExisteException
	 *             Cuando el coche ya existe en el concesionario
	 * @throws CocheNuloException
	 *             Cuando el coche introducido es nulo            
	 * @throws MatriculaNoValidaException 
	 * 			   Cuado la matr\u00edcula no es v\u00e1lida
	 * @throws ColorNoValidoException
	 * 	  			   Cuando el color introducido es nulo 
	 * @throws ModeloNoValidoException 
	 * 	  			   Cuando el modelo introducido es nulo
	 */	
	public void add(Coche coche) throws CocheExisteException, CocheNuloException, ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		if (coche == null)
			throw new CocheNuloException("No se puede introducir un coche nulo");
		if (coches.contains(coche))
			throw new CocheExisteException(
					"El coche con matr\\u00edcula " + coche.getMatricula() + " ya existe en el concecionario");			
		coches.add(coche);
	}

	/**
	 * Elimina un coche del concesionario
	 * 
	 * @param matricula
	 *            Matr\u00edcula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Cuando el formato de la matr\u00edcula no es v\u00e1lido
	 * @throws ConcesionarioVacioException
	 *             Cuando el concesionario est\u00e1 vac\u00edo
	 */
	public void remove(String matricula)
			throws MatriculaNoValidaException, CocheNoExisteException, ConcesionarioVacioException {
		if (estaVacio())
			throw new ConcesionarioVacioException("El concesionario se encuentra vac\u00edo, no se puede eliminar");
		if (!Coche.EsMatriculaValida(matricula))
			throw new MatriculaNoValidaException("El formato de la matr\u00edcula no es v\u00e1lido");
		if (!coches.remove((new Coche(matricula))))
			throw new CocheNoExisteException("No existe el coche a eliminar");
	}

	/**
	 * Devuelve un coche con la matr\u00edcula indicada
	 * 
	 * @param matricula
	 *            Matr\u00edcula del coche a devolver
	 * @return El coche con la matr\u00edcula indicada.
	 * @throws ConcesionarioVacioException
	 *             Cuando el concesionario est\u00e1 vac\u00edo
	 * @throws CocheNoExisteException
	 *             Cuando no existe la matr\u00edcula del coche a obtener
	 */
	public Coche get(String matricula)
			throws MatriculaNoValidaException, ConcesionarioVacioException, CocheNoExisteException {
		if (estaVacio())
			throw new ConcesionarioVacioException("El concesionario se encuentra vac\u00e1do");
		try {
			return coches.get(coches.indexOf(new Coche(matricula)));
		} catch (IndexOutOfBoundsException e) {
			throw new CocheNoExisteException("No existe el coche indicado seg\u00fan matr\u00edcula");
		}
	}
	
	/**
	 * Devuelve el coche pasado si este existe en el arraylist
	 * @param coche Coche 
	 * @return El coche pasado si existe
	 * @throws IndiceFueraDeRangoException
	 *             Cuando el \u00e9ndice est\u00e1 fuera de rango
	 */
	public Coche get(Coche coche) throws IndiceFueraDeRangoException{
		try {
			return coches.get(coches.indexOf(coche));
		} catch (IndexOutOfBoundsException e) {
			throw new IndiceFueraDeRangoException("El indice se encuentra fuera de rango");
		}
	}

	/**
	 * Devuelve si el concesionario est\u00e1 vac\u00e9o.
	 * @return true o falso en funci\u00f3n de si el concesinario estest\u00e1 vac\u00e9o.
	 */
	private boolean estaVacio() {
		return coches.isEmpty();
	}

	/**
	 * Cuenta los coches del concesionario
	 * 
	 * @return Devuelve la cantidad de coches del concesionario
	 */
	public int contarCoches() {
		return coches.size();
	}

	/**
	 * Muestra los coches del seg�n el color indicado
	 * 
	 * @param color
	 *            Color del Coche
	 * @return Devuelve una cadena con los coches de el color indicado
	 * @throws NoEncuentraCocheException
	 *             Cuando no encuentra coches del color indicado
	 * @throws ConcesionarioVacioException
	 *             Cuando el GestionConcesionario.concesionario est� vac�o
	 * @throws CocheNuloException Cuando el coche es nulo
	 * @throws CocheExisteException Cuando el coche ya existe en el array
	 * @throws MatriculaNoValidaException 
	 * @throws ColorNoValidoException 
	 * @throws ModeloNoValidoException 
	 */
	public Concesionario mostrarCoches(ColorCoche color) throws ConcesionarioVacioException, CocheExisteException, CocheNuloException, ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		if (isEmpty())
			throw new ConcesionarioVacioException("El concesionario se encuentra vac\\u00edo");
		Concesionario colores = new Concesionario();
		for (Coche c : coches) {
			if (c.getColor() == color)
				colores.add(c);
		}
		if (colores.isEmpty())
			throw new ConcesionarioVacioException("No se encontraron coches de ese color");
		return colores;
	}
	
	/**
	 * Comprueba si est\u009e1 vac\u00edo
	 */
	public boolean isEmpty() {
		return estaVacio();
	}

	/**
	 * M�todo toString
	 */
	@Override
	public String toString() {
		String mensaje = "";
		for (Coche c : coches) {
			mensaje += c.toString() + "\n";
		}
		return mensaje;
	}

	@Override
	public ListIterator<Coche> iterator() {
		return coches.listIterator();
	}

}
