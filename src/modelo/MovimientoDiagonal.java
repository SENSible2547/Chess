package modelo;

import java.util.ArrayList;

public class MovimientoDiagonal implements IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, int limite) {
		boolean movimiento_correcto = false;
		Tupla resta = inicio.restarTupla(destino);

		if (resta.getX() == resta.getY() && resta.getX() <= limite)
			movimiento_correcto = true;

		return movimiento_correcto;
	}

	public boolean revisarTrayectoria(Tupla inicio, Tupla destino, Tablero tablero) {
		boolean trayectoria_correcta = true;
		Ficha ficha;
		TableroIterador iterador;
		Tupla aux;

		// organizar por el x menor
		if (1 == inicio.compararX(destino)) {
			aux = destino;
			destino = inicio;
			inicio = aux;
		}

		// movimiento diagonal hacia la derecha diagonal
		iterador = tablero.crearIterador(inicio.getX(), inicio.getY());
		if (inicio.getY() < destino.getY()) {
			for (int i = 1; i < Math.abs(inicio.getX() - destino.getX()); i++) {
				ficha = iterador.siguiente_diagonal_derecha().getFicha();
				if (TIPO.NOFICHA != ficha.getTipo()) {
					trayectoria_correcta = false;
					break;
				}
			}
		} else {
			for (int i = 1; i < Math.abs(inicio.getX() - destino.getX()); i++) {
				ficha = iterador.siguiente_diagonal_izquierda().getFicha();
				if (TIPO.NOFICHA != ficha.getTipo()) {
					trayectoria_correcta = false;
					break;
				}
			}
		}

		return trayectoria_correcta;
	}

	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Tablero tablero) {
		return new ArrayList<Tupla>();
	}
}
