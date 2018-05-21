package concesionario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import concesionario.funcionalidad.ColorCoche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.Modelo;
/**
 * Alta de los coches
 * @author Mario Navarro Madrid
 *
 */
public class Alta extends Dialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que configura el comportamiento del diálogo
	 */
	public Alta(){
		super();
		controlarAcciones();
		setComponentes();
	}
	
	/**
	 * Controla las acciones de los botnes
	 */
	public void controlarAcciones() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
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
		okButton.setText("Añadir");
		setTitle("Alta");
	}
	
	/**
	 * Alta de los coches
	 */
	private void alta(){
		try {
			GestionConcesionario.altaCoche((Modelo)comboBoxModelo.getSelectedItem(), getColor(), textFieldMatricula.getText());
			clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	/**
	 * Obtiene el color del coche
	 * @return Color del coche
	 */
	private ColorCoche getColor(){
		if(rdbtnAzul.isSelected())
			return ColorCoche.AZUL;
		if(rdbtnPlata.isSelected())
			return ColorCoche.PLATA;
		if(rdbtnRojo.isSelected())
			return ColorCoche.ROJO;
		return null;
	}
	
	


}
