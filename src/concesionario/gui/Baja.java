package concesionario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import concesionario.funcionalidad.Coche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.CocheNoExisteException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.MatriculaNoValidaException;
import javax.swing.JButton;

/**
 * Baja de los coches
 * @author Mario Navarro Madrid
 *
 */
public class Baja extends MostrarCoche{

	private static final long serialVersionUID = 1L;
	JButton btnBuscar;
	public Baja() throws ConcesionarioVacioException{
		super();
		if(GestionConcesionario.contarCoches()==0)
			throw new ConcesionarioVacioException("No se pueden realizar bajas, el concesionario esta vacio");
		setComponentes();
		controlarAcciones();
		
	}

	/**
	 * Controla las acciones de los botones y focos
	 */
	public void controlarAcciones() {
		textFieldMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				controlarMatricula();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				baja();
				clear();
				okButton.setEnabled(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dispose();
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Coche coche = GestionConcesionario.mostrarCoche(textFieldMatricula.getText());
					if(coche!=null) {
						rellenaCoche(coche);
						okButton.setEnabled(true);;
					}					
				} catch (MatriculaNoValidaException | ConcesionarioVacioException | CocheNoExisteException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
				}
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
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(335, 204, 89, 23);
		contentPanel.add(btnBuscar);
		okButton.setText("Baja");
		okButton.setEnabled(false);
		setTitle("Baja");
	}
	
	private void baja(){
		try {
			GestionConcesionario.bajaCoche(textFieldMatricula.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
