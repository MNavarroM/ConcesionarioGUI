package concesionario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
/**
 * Busca un coche a traves de su matricula
 * @author Mario Navarro Madrid
 *
 */
public class BusquedaMatricula extends MostrarCoche{
	
	private static final long serialVersionUID = 1L;


	public BusquedaMatricula() throws ConcesionarioVacioException {
		super();
		if(GestionConcesionario.contarCoches()==0)
			throw new ConcesionarioVacioException("No se pueden realizar busquedas, el concesionario esta vacio");
		setComponentes();
		controlarAcciones();
		
	}

	/**
	 * Controlas las acciones de botones y focos
	 */
	public void controlarAcciones() {
		super.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						rellenaCoche(GestionConcesionario.mostrarCoche(textFieldMatricula.getText()));
					} catch (MatriculaNoValidaException | ConcesionarioVacioException | CocheNoExisteException e) {
					JOptionPane.showMessageDialog(contentPanel,e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		super.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}				
		});
		
		textFieldMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				controlarMatricula();
			}
		});
	}
	
	/**
	 * Establece la configuracion de los componentes
	 */
	public void setComponentes() {
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		cancelButton.setText("Salir");
		okButton.setText("Buscar");
		setTitle("Búsqueda por matrícula");
	}

}
