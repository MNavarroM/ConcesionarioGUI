package concesionario.gui;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import concesionario.funcionalidad.ColorCoche;
import concesionario.funcionalidad.GestionConcesionario;
import concesionario.funcionalidad.excepciones.AbortarException;
import concesionario.funcionalidad.excepciones.ConcesionarioVacioException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * @author Mario Navarro Madrid
 *
 */

public class Principal {
	
	/**
	 * Titulo de la barra.
	 */
	private final String TITULO="Concesionario IES Gran Capitan";
	
	/**
	 * Frame utilizado.
	 */
	private JFrame frame;
	
	/**
	 * Dialogo para los diferentes dialogo a usar.
	 */
	private Dialog dialogo = new Dialog();
	
	/**
	 * FileChooser para abrir y guardar.
	 */
	private JFileChooser fileChooser = new JFileChooser();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					if(deseaGuardar())
						guardar();
					System.exit(0);
				} catch (AbortarException e) {
					// ABORTAR
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(true);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		actualizarTitulo();
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevoConcesionario = new JMenuItem("Nuevo concesionario");
		mntmNuevoConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		mntmNuevoConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnArchivo.add(mntmNuevoConcesionario);
		
		JMenuItem mntmAbrirConcesionario = new JMenuItem("Abrir concesionario...");
		mntmAbrirConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrir();				
			}
		});
		mnArchivo.add(mntmAbrirConcesionario);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();					
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarComo();					
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnCoche = new JMenu("Coche");
		menuBar.add(mnCoche);
		
		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();				
			}
		});
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnCoche.add(mntmAlta);
		
		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				baja();
			}
		});
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnCoche.add(mntmBaja);
		
		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarConcesionario();				
			}
		});
		mnCoche.add(mntmMostrarConcesionario);
		
		JMenu mnBuscar = new JMenu("Buscar");
		menuBar.add(mnBuscar);
		
		JMenuItem mntmPorMatrcula = new JMenuItem("Por matr\u00EDcula");
		mntmPorMatrcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				busquedaMatricula();
			}
		});
		mntmPorMatrcula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mnBuscar.add(mntmPorMatrcula);
		
		JMenuItem mntmPorColor = new JMenuItem("Por Color");
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPorColor();
			}
		});
		mnBuscar.add(mntmPorColor);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Aplicacion desarrollada por Mario Navarro Madrid para PGN","Acerca de",JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmGuia = new JMenuItem("Guia");
		mntmGuia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verAyuda();
			}
		});
		mntmGuia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAyuda.add(mntmGuia);
		frame.getContentPane().setLayout(null);
		
        aplicarFiltroFileChooser();
		
	}

	private void aplicarFiltroFileChooser() {
		fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Objetos", "obj");
        fileChooser.addChoosableFileFilter(filter);
	}
	
	/**
	 * Crea un nuevo archivo
	 */
	private void nuevo() {
			try {
				if(deseaGuardar())
					guardar();						
				GestionConcesionario.nuevo();
				actualizarTitulo();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}catch (AbortarException e) {
				// ABORTAR
			}
	}
	
	/**
	 * Abre un archivo
	 */
	private void abrir() {
		try {
			if(deseaGuardar()){
				guardar();
				}
			int opcion = fileChooser.showDialog(frame,"Abrir concesionario");
			if(opcion==JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();				
				GestionConcesionario.abrir(file);				
				actualizarTitulo();
			}
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		} catch (AbortarException e) {
			// ABORTAR
		}		
	}
	
	/**
	 * Actualiza el titulo
	 */
	private void actualizarTitulo() {
		if(GestionConcesionario.getFile()==null)
			frame.setTitle("Sin nombre: " + TITULO);
		else
			frame.setTitle(GestionConcesionario.getFile().getName() + ": " + TITULO);		
	}
	
	/**
	 * Guarda un archivo
	 */
	private void guardar() {
		try {
			if(GestionConcesionario.getFile()==null){
				guardarComo();
			}
			else{
				GestionConcesionario.guardar();
				JOptionPane.showMessageDialog(frame, "Concesionario guardado");				
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Guarda como... un archivo
	 */
	private void guardarComo(){
		try{
			int opcion = fileChooser.showDialog(frame,"Guardar");
			if(opcion == JFileChooser.APPROVE_OPTION){
				File file = new File(fileChooser.getSelectedFile()+".obj");
				if(file.exists()){
					if(JOptionPane.showConfirmDialog(frame, "Ese fichero ya existe, ¿sobrescribir?", "¿Sobrescribir?", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
						escribrirArchivo(file);
					}
				}else{
					escribrirArchivo(file);
				}										
			}			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
	}

	/**
	 * Escribe la informacion en un archivo
	 * @param file File archivo a guardar
	 * @throws IOException
	 */
	private void escribrirArchivo(File file) throws IOException {
		GestionConcesionario.setFile(file);
		actualizarTitulo();
		GestionConcesionario.guardar();
	}
	
	
	
	/**
	 * Sale del programa
	 */
	private void salir() {
		try {
			if(deseaGuardar())
				guardar();
			System.exit(0);
		} catch (AbortarException e) {
			// ABORTAR
		}
	}
	
	/**
	 * Invoca el dialogo Alta
	 */
	private void alta() {
		dialogo = new Alta();
		dialogo.setModal(true);
		dialogo.setVisible(true);
	}
	
	/**
	 * Invoca el dialogo Baja
	 */
	private void baja() {
			try {
				dialogo = new Baja();
				dialogo.setModal(true);
				dialogo.setVisible(true);
			} catch (ConcesionarioVacioException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}		
	}
	
	/**
	 * Invoca el dialogo MostrarConcesionario
	 */
	private void mostrarConcesionario() {
			try {
				dialogo = new MostrarConcesionario();
				dialogo.setModal(true);
				dialogo.setVisible(true);
			} catch (ConcesionarioVacioException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(),"Errora",JOptionPane.WARNING_MESSAGE);
			}
	}
	
	/**
	 * Invoca el dialogo mostrarConcesionarioColor
	 */
	private void mostrarPorColor(){
		try {
			ColorCoche color = (ColorCoche) JOptionPane.showInputDialog(frame, "Selecciona un color", "Selecciona un color", JOptionPane.INFORMATION_MESSAGE, null, ColorCoche.values(), ColorCoche.AZUL);
			if(color!=null){
				dialogo = new MostrarConcesionarioColor(color);
				dialogo.setVisible(true);
				dialogo.setModal(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Invoca el dialogo BusquedaMatricula
	 */
	private void busquedaMatricula() {
		try {
			dialogo = new BusquedaMatricula();
			dialogo.setVisible(true);
			dialogo.setModal(true);
		} catch (ConcesionarioVacioException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Invoca el dialogo VerAyuda
	 */
	private void verAyuda() {
		VerAyuda ayuda = new VerAyuda();
		ayuda.setVisible(true);
		ayuda.setModal(true);
	}
	
	/**
	 * Pregunta si desea guardar si hay cambios
	 * @return booleano en funcion de si desea guardar o no
	 * @throws AbortarException 
	 */
	private boolean deseaGuardar() throws AbortarException{
		int option=1;
		if(GestionConcesionario.hayCambios()){
			option = JOptionPane.showConfirmDialog(frame, "Si no guarda, perderá los cambios,¿Desea guardar?", "¿Desea guardar?", JOptionPane.YES_NO_OPTION);
		}
		if(option==JOptionPane.CLOSED_OPTION)
			throw new AbortarException();
		return option == JOptionPane.YES_OPTION;
		}	
	}
	
