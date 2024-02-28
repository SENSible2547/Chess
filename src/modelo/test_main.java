package modelo;

import java.util.Scanner;

public class test_main {
	private static final Tablero tablero = new Tablero();
	private static final Motor motor = new Motor(tablero);
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 

		tablero.inicializar();
		motor.inicio();

		int x, y;
		boolean movimientoRealizado = false;
		Tupla inicio, destino; 
		
		do {
			tablero.mostrar();

			System.out.printf("%s\n", motor.getTurno());
			System.out.printf("Inicio> ");
			x = scan.nextInt();
			y = scan.nextInt();
			inicio = new Tupla(x, y);

			System.out.printf("Destino> ");
			x = scan.nextInt();
			y = scan.nextInt();
			destino = new Tupla(x, y);

			movimientoRealizado = motor.mover(inicio, destino);

		} while(true);
		
	}
}
