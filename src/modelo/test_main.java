package modelo;

import java.util.Scanner;

public class test_main {
	private static final Tablero tablero = new Tablero();
	private static final Motor motor = new Motor(tablero);
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 

		int x, y, opcion;
		Tupla inicio, destino;
		MENSAJE mensaje;

		do {
			tablero.mostrar();

			// imprimir jaques
			for (Casilla atacante : motor.getJaqueABlanco())
				System.out.printf("BLANCO EN JAQUE: %s\n", atacante.getPosicion());
			for (Casilla atacante : motor.getJaqueANegro())
				System.out.printf("NEGRO EN JAQUE: %s\n", atacante.getPosicion());

			System.out.print("Fichas Blanco\n");
			for (Casilla ficha : tablero.getBando(COLOR.BLANCO).getFichas())
				System.out.printf("%s,\t", ficha.getPosicion());
			System.out.print('\n');
			System.out.print("Fichas Negro\n");
			for (Casilla ficha : tablero.getBando(COLOR.NEGRO).getFichas())
				System.out.printf("%s,\t", ficha.getPosicion());
			System.out.print('\n');

			System.out.printf("Posicion Rey Blanco: %s\n", tablero.getBando(COLOR.BLANCO).getPosicionRey().getPosicion());
			System.out.printf("Posicion Rey Negro: %s\n", tablero.getBando(COLOR.NEGRO).getPosicionRey().getPosicion());

			print_menu();

			System.out.print("Opcion> ");
			opcion = scan.nextInt();

			System.out.printf("%s\n", motor.getTurno());
			switch (opcion) {
				case 1:
					System.out.print("Inicio> ");
					x = scan.nextInt();
					y = scan.nextInt();
					inicio = new Tupla(x, y);

					System.out.print("Destino> ");
					x = scan.nextInt();
					y = scan.nextInt();
					destino = new Tupla(x, y);

					mensaje = motor.mover(inicio, destino);
					System.out.println(mensaje);
					break;
				case 2:
					System.out.print("Selección> ");
					x = scan.nextInt();
					y = scan.nextInt();
					inicio = new Tupla(x, y);

					for (Tupla posibilidad : motor.seleccionar(inicio))
						System.out.printf("%s,\t", posibilidad.toString());
					System.out.print('\n');
					break;
				default:
					break;
			}
		} while(0 != opcion);
		
	}

	private static void print_menu() {
		System.out.print("0. Salir\n");
		System.out.print("1. Mover\n");
		System.out.print("2. Seleccionar\n");
	}
}

