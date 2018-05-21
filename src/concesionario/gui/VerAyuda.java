package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Muestra la ayuda
 * @author Mario Navarro Madrid
 *
 */
public class VerAyuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerAyuda dialog = new VerAyuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerAyuda() {
		setTitle("Ayuda");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnEnElBoton = new JTextPane();
		txtpnEnElBoton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnEnElBoton.setText("En el bot\u00F3n Archivo, puedes crear un concesionario, abrirlo o guardar los cambios realizados.\r\nEn el bot\u00F3n Coche, puedes dar de alta un coche, darlo de baja o mostrar todos los coches del concesionario.\r\nEn el bot\u00F3n buscar puedes realizar b\u00FAsquedas de coches a trav\u00E9s de su matr\u00EDcula.\r\nEn el bot\u00F3n Ayuda, podr\u00E1s consultar la ayuda del programa y la informaci\u00F3n sobre \u00E9l.");
		txtpnEnElBoton.setEditable(false);
		txtpnEnElBoton.setBounds(0, 0, 434, 263);
		txtpnEnElBoton.setBackground(contentPanel.getBackground());
		contentPanel.add(txtpnEnElBoton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
