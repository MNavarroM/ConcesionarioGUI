package concesionario.funcionalidad;

import java.io.Serializable;
import java.util.regex.Pattern;

import concesionario.funcionalidad.excepciones.ColorNoValidoException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import concesionario.funcionalidad.excepciones.ModeloNoValidoException;

/**
 * Clase para la creaci\u00d3n de coches
 * 
 * @author Mario Navarro Madrid
 *
 */
public class Coche implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Modelo del coche
	 */
	private Modelo modelo;

	/**
	 * Color del coche
	 */
	private ColorCoche color;

	/**
	 * Matr\u00edcula del coche
	 */
	private String matricula;

	/**
	 * Expresi\u00d3n regular para validar la matr\u00edcula
	 */
	private static String regex = "^(?i)([0-9]){4}([\\s\\-])?([B-Z&&[^EIOUQ]]){3}$";

	/**
	 * Constructor del coche
	 * 
	 * @param modelo
	 *            Modelo del coche
	 * @param color
	 *            Color del coche
	 * @param matr\u00edcula
	 *            Matr\u00edcula del coche
	 * @throws ModeloNoValidoException
	 *             Cuando el modelo no es v\u00e1lido
	 * @throws ColorNoValidoException
	 *             Cuando el color no es v\u00e1lido
	 * @throws MatriculaNoValidaException
	 *             Cuando la matr\u00edcula no es v\u00e1lida
	 */
	public Coche( String matricula,Modelo modelo, ColorCoche color)
			throws ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidaException {
		setMatricula(matricula);
		setModelo(modelo);
		setColor(color);
		
	}

	/**
	 * Constructor de coche
	 * 
	 * @param matricula
	 *            Matr\u00edcula del coche
	 * @throws MatriculaNoValidaException
	 *             Cuando la matr\u00edcula no es v\u00e1lida
	 */
	public Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}

	/**
	 * Obtiene el modelo del coche
	 * 
	 * @return Devuelve el modelo del coche
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * Esteblece el modelo del coche
	 * 
	 * @param modelo
	 *            Modelo del coche
	 * @throws ModeloNoValidoException
	 *             Cuando el modelo no es v\u00e1lido
	 */
	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo == null)
			throw new ModeloNoValidoException("El modelo del coche no puede ser nulo");
		this.modelo = modelo;
	}

	/**
	 * Obtiene el color del coche
	 * 
	 * @return Devuelve el color del coche
	 */
	
	public ColorCoche getColor() {
		return color;
	}
	/**
	 * Establece el color del coche
	 * 
	 * @param color
	 *            Color del coche
	 * @throws ColorNoValidoException
	 *             Cuando el color no es v\u00e1lido
	 */

	private void setColor(ColorCoche color) throws ColorNoValidoException {
		if (color == null)
			throw new ColorNoValidoException("El color del coche no puede ser nulo");
		this.color = color;
	}

	/**
	 * Establece la matr�cula del coche
	 * 
	 * @param matricula
	 *            Matr\u00edcula del coche
	 * @throws MatriculaNoValidaException
	 *             Cuando la matr\u00edcula del coche no es v\u00e1lida
	 */

	private void setMatricula(String matricula) throws MatriculaNoValidaException {
		if (!EsMatriculaValida(matricula))
			throw new MatriculaNoValidaException("La matr\u00edcula no es v\u00e1lida");
		this.matricula = formatearMatricula(matricula);
	}

	/**
	 * Obtiene la matr\u00edcula del coche
	 * 
	 * @return Devuelve la matr\u00edcula del coche
	 */

	public String getMatricula() {
		return matricula;
	}

	/**
	 * Da a la matr\u00edcula a introducir un formato estandar
	 * 
	 * @param matricula
	 *            Matr\u00edcula del coche
	 * @return Devuelve la matr\u00edcula con un formato est\u00e1ndar
	 */
	static String formatearMatricula(String matricula) {
		return matricula.replaceAll("[ -]", "").toUpperCase();
	}

	/**
	 * Comprueba si la matr\u00edcula es v\u00e1lida
	 * 
	 * @param matricula
	 *            Matr\u00edula a comprobar
	 * @return Devuelve true si la matr\u00edcula es v\u00e1lida, false si no lo es
	 */
	public static boolean EsMatriculaValida(String matricula) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(matricula).matches();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equalsIgnoreCase(other.matricula))
			return false;
		return true;
	}

	/**
	 * M\u00e9todo toString
	 */
	@Override
	public String toString() {
		return "Modelo: " + getModelo() + ". Matricula: " + getMatricula() + ". Color: " + getColor();
	}

}
