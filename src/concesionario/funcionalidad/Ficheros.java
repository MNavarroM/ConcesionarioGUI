package concesionario.funcionalidad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Ficheros {
		
	public static void escribir(Object objeto, File file) throws IOException,NullPointerException {
		try(ObjectOutputStream salida = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {		
			salida.writeObject(objeto);
		} 
	}
		
	public static Object leer(File file) throws ClassNotFoundException, IOException {
		try(ObjectInputStream entrada= new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {			
			return entrada.readObject();
		}
	}

}
