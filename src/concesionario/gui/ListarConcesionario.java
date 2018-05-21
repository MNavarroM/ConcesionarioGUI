package concesionario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import concesionario.funcionalidad.Coche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import concesionario.funcionalidad.excepciones.IndiceFueraDeRangoException;
import javax.swing.JButton;

/**
 * Dialogo padre para iterar el concesioanrio completo o por color
 * @author d17namam
 *
 */
public class ListarConcesionario extends MostrarCoche {

	private static final long serialVersionUID = 1L;
	protected Coche coche;
	protected ListIterator<Coche> coches;
	private JButton beforeButton;
	private JButton afterButton;
	private int controlMovimiento;
	
	public ListarConcesionario(ListIterator<Coche> coches) throws ConcesionarioVacioException {
		super();
		if(GestionConcesionario.contarCoches()==0)
			throw new ConcesionarioVacioException("No se puede mostrar el concesionario, está vacío");
		this.coches=coches;

		setComponentes();
		controlarAcciones();
		setAnteriorSiguiente();
		coche=coches.next();
		muestraCoche();
		beforeButton.setEnabled(false);

	}
	
	/**
	 * Establece la configuracion de los componentes
	 */
	public void setComponentes() {
		textFieldMatricula.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);		
		beforeButton = new JButton("<");
		beforeButton.setBounds(204, 89, 89, 23);
		contentPanel.add(beforeButton);		
		afterButton = new JButton(">");
		afterButton.setBounds(306, 89, 89, 23);
		contentPanel.add(afterButton);
		okButton.setText("Anterior");
		setTitle("Mostrar concesionario");
		okButton.setVisible(false);
	}
	
	/**
	 * Controla las acciones de los botones y focos
	 */
	public void controlarAcciones() {
		beforeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					mostrarAnterior();
			}
		});
		afterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mostrarSiguiente();
			}

		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}

		});
	}
	
	/**
	 * Controla los botones cuando vamos hacia el siguiente o hacia el anterior
	 */
	protected void setAnteriorSiguiente() {
        beforeButton.setEnabled(true);
        afterButton.setEnabled(true);
        if(GestionConcesionario.contarCoches()==1){
            beforeButton.setEnabled(false);
            afterButton.setEnabled(false);
        }
        if (!coches.hasPrevious()) {
        		beforeButton.setEnabled(false);
        }
        if (!coches.hasNext()) {
        	afterButton.setEnabled(false);
        }
	}
	
	/**
	 * Muestra el coche siguietne
	 */
	protected void mostrarSiguiente() {
		if(coches.hasNext())
			coche = coches.next();
		muestraCoche();
		if(controlMovimiento==1)
			if(coches.hasNext())
				coche=coches.next();
		muestraCoche();
		controlMovimiento=0;
		setAnteriorSiguiente();
	}
	
	/**
	 * Muestra el coche anterior
	 */
	protected void mostrarAnterior() {
		if(coches.hasPrevious())
			coche=coches.previous();
		muestraCoche();
		if(controlMovimiento==0)
			if(coches.hasPrevious())
				coche=coches.previous();
		muestraCoche();
		controlMovimiento=1;
		setAnteriorSiguiente();
	}
	
	/**
	 * Muestra el coche
	 */
	public void muestraCoche() {
		try {
			rellenaCoche(GestionConcesionario.getCoche(coche));
		} catch (IndiceFueraDeRangoException e) {
			JOptionPane.showMessageDialog(contentPanel,e.getMessage(),"ERROR",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
