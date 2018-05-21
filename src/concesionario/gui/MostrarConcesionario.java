package concesionario.gui;

import java.util.ListIterator;

import concesionario.funcionalidad.Coche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;

/**
 * Muestra el concesionario completo
 * @author Mario Navarro Madrid
 *
 */
public class MostrarConcesionario extends ListarConcesionario{

	private static final long serialVersionUID = 1L;
	Coche coche;
	
	
	public MostrarConcesionario() throws ConcesionarioVacioException{
		super(obtenerConcesionario());
		if(GestionConcesionario.contarCoches()==0)
			throw new ConcesionarioVacioException("No se puede mostrar el concesionario, está vacío");
		setTitle("Mostrar concesionario");
	}

	/**
	 * Obtiene el ListItetator del ArrayList del concesionario
	 * @return ListItetator del ArrayList del concesionario
	 */
	private static ListIterator<Coche> obtenerConcesionario() {
		return GestionConcesionario.getConcesionario().iterator();
	}
}
