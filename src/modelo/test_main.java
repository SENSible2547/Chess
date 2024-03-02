package modelo;

import java.util.Scanner;

public class test_main {
	private static final Tablero tablero = new Tablero();
	private static final Motor motor = new Motor(tablero);
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 

		tablero.inicializar();
		motor.inicio();

		int x, y, opcion;
		Tupla inicio, destino;

		do {
			tablero.mostrar();

			print_menu();

			System.out.printf("Opcion> ");
			opcion = scan.nextInt();

			System.out.printf("%s\n", motor.getTurno());
			switch (opcion) {
				case 1:
					System.out.printf("Inicio> ");
					x = scan.nextInt();
					y = scan.nextInt();
					inicio = new Tupla(x, y);

					System.out.printf("Destino> ");
					x = scan.nextInt();
					y = scan.nextInt();
					destino = new Tupla(x, y);

					motor.mover(inicio, destino);
					break;
				case 2:
					System.out.printf("Selección> ");
					x = scan.nextInt();
					y = scan.nextInt();
					inicio = new Tupla(x, y);

					for (Tupla posibilidad : motor.seleccionar(inicio)) {
						System.out.printf("%s\n", posibilidad.toString());
					}
					break;
				default:
					break;
			}
		} while(0 != opcion);
		
	}

	private static void print_menu() {
		System.out.printf("0. Salir\n");
		System.out.printf("1. Mover\n");
		System.out.printf("2. Seleccionar\n");
	}
}

