package utiles;


public class Matematicas{
	/**
	*Genera un número entero aleatorio entre los valores minimos y maximos introducidos
	*
	*@param min: numero minimo
	*@param max: numero maximo
	*
	*@return numeroAleatorio:Devuelve el numero entero generado aleatoriamente
	*/
	public static int generarNumeroAleatorio(int min, int max){
		double decimalAleatorio = Math.random()*(max-min+1);
		int numeroAleatorio = ((int)(decimalAleatorio)) + min;
		return numeroAleatorio;
	}

	/*public static void main(String args[]){
		int num = Matematicas.generarNumeroAleatorio(1,5);
		System.out.println(num);
	}*/
}