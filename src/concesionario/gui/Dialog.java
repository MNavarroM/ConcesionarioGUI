package concesionario.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionario.funcionalidad.Coche;
import concesionario.funcionalidad.Marca;
import concesionario.funcionalidad.Modelo;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Dialogo padre para la gestión del concesionario
 * @author Mario Navarro Madrid
 */
public class Dialog extends JDialog implements Configurable{

	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textFieldMatricula;
	protected JRadioButton rdbtnRojo;
	protected JRadioButton rdbtnPlata;
	protected JRadioButton rdbtnAzul;
	protected JComboBox comboBoxModelo;
	protected JComboBox comboBoxMarca;
	protected ButtonGroup buttons;
	protected JLabel lblMatrcula;
	protected JLabel lblModelo;
	protected JLabel lblColor;
	protected JButton okButton;
	protected JButton cancelButton;
	protected JLabel labelWarnings;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Create the dialog.
	 */
	public Dialog() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(21, 44, 71, 14);
		contentPanel.add(lblMatrcula);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(102, 41, 86, 20);
		contentPanel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(21, 144, 46, 14);
		contentPanel.add(lblModelo);
		
		lblColor = new JLabel("Color");
		lblColor.setBounds(21, 187, 46, 14);
		contentPanel.add(lblColor);
		
		rdbtnRojo = new JRadioButton("Rojo");
		buttonGroup.add(rdbtnRojo);
		

		rdbtnRojo.setBounds(73, 183, 62, 23);
		contentPanel.add(rdbtnRojo);
		
		rdbtnAzul = new JRadioButton("Azul");
		buttonGroup.add(rdbtnAzul);

		rdbtnAzul.setBounds(130, 183, 72, 23);
		contentPanel.add(rdbtnAzul);
		
		
		rdbtnPlata = new JRadioButton("Plata");
		buttonGroup.add(rdbtnPlata);

		rdbtnPlata.setBounds(204, 183, 86, 23);
		contentPanel.add(rdbtnPlata);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(21, 93, 46, 14);
		contentPanel.add(lblMarca);
		
		comboBoxMarca = new JComboBox();
		comboBoxMarca.setBounds(102, 90, 70, 20);
		contentPanel.add(comboBoxMarca);
		
		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(102, 141, 115, 20);
		contentPanel.add(comboBoxModelo);
		
		labelWarnings = new JLabel("");
		labelWarnings.setBounds(198, 44, 226, 14);
		contentPanel.add(labelWarnings);
		
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values()));
		comboBoxMarca.setSelectedItem(null);
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelos((Marca)comboBoxMarca.getSelectedItem())));
			}
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton();
				okButton.setText("Aceptar");
				okButton.setActionCommand("Ok");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	/**
	 * Obtiene un array de Modelos para rellenar los combobox
	 * @param selectedItem
	 * @return
	 */
	private Object[] getModelos(Marca selectedItem) {
		ArrayList<Modelo> arrayListModelos = new ArrayList<Modelo>();
		for (Modelo modelo : Modelo.values()) {
			if(modelo.getMarca() == selectedItem)
				arrayListModelos.add(modelo);
		}
		return (Object [])arrayListModelos.toArray();
	}
	
	/**
	 * Controla que al perder el foco de la matricula, indique si es correcta o no
	 */
	protected void controlarMatricula() {
		if(!Coche.EsMatriculaValida(textFieldMatricula.getText()) && textFieldMatricula.getText().contains("")){
			textFieldMatricula.setForeground(Color.RED);
			labelWarnings.setForeground(Color.RED);
			labelWarnings.setText("La matricula introducida no es valida");
		}else{
			labelWarnings.setText(null);
			textFieldMatricula.setForeground(Color.BLACK);
		}
	}
	
	/**
	 * Limpia los botones
	 */
	public void clear(){
		textFieldMatricula.setText("");
		textFieldMatricula.setForeground(Color.BLACK);
		labelWarnings.setText("");
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
		rdbtnAzul.setSelected(false);
		rdbtnRojo.setSelected(false);
		rdbtnPlata.setSelected(false);		
	}

	@Override
	public void controlarAcciones() {
		
	}

	@Override
	public void setComponentes() {
		
	}
}

