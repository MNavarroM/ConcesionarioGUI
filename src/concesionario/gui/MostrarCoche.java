package concesionario.gui;

import concesionario.funcionalidad.Coche;

/**
 * Clase padre que servira para rellenar un coche, ya sea individual o a traves de un iterator
 * @author Mario Navarro Madrid
 *
 */
public class MostrarCoche extends Dialog {
	
	private static final long serialVersionUID = 1L;

	public MostrarCoche() {
		super();
	}
	
	/**
	 * Rellena el dialogo con la informacion del coche pasado
	 * @param coche Coche del cual rellenar el dialgo
	 */
	public void rellenaCoche(Coche coche){
		textFieldMatricula.setText(coche.getMatricula());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.setSelectedItem(coche.getModelo());
		switch (coche.getColor()) {
		case AZUL:
			rdbtnAzul.setSelected(true);
			break;
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		}
	}

}
