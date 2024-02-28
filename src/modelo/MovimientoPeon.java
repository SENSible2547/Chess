package modelo;

import java.util.ArrayList;

public class MovimientoPeon implements IMovimiento {
	public boolean comprobar(Tupla inicio, Tupla destino, int limite) {
		boolean movimiento_correcto = false;
		Tupla resta = new Tupla(inicio.getX() - destino.getX(), inicio.getY() - destino.getY());

		// comprobar avance peon
		if (0 == resta.getY()) {
			// si se puede avanzar 2 casillas y se avanza solo 1
			if (2 == Math.abs(limite) && resta.getX() == limite / 2)
				movimiento_correcto = true;
			if (resta.getX() == limite)
				movimiento_correcto = true;

			return movimiento_correcto;
		} else if (resta.getX() == resta.getY() && Math.abs(resta.getX()) == 1) { // avance diagonal
			movimiento_correcto = true;
		}

		return movimiento_correcto;
	}

	public boolean revisarTrayectoria(Tupla inicio, Tupla destino, Tablero tablero) {
		boolean trayectoria_correcta = true;
		Tupla resta = inicio.restarTupla(destino);
		Ficha ficha = tablero.getCasilla(destino).getFicha();

		// avance frontal
		if (0 == resta.getY()) {
			if (TIPO.NOFICHA != ficha.getTipo())
				trayectoria_correcta = false;

			if (2 == Math.abs(resta.getX())) {
				if (1 == inicio.compararX(destino))
					ficha = tablero.getCasilla(new Tupla(destino.getX() + 1, destino.getY())).getFicha();
				else
					ficha = tablero.getCasilla(new Tupla(destino.getX() - 1, destino.getY())).getFicha();

				if (TIPO.NOFICHA != ficha.getTipo())
					trayectoria_correcta = false;
			}
			return trayectoria_correcta;
		} else if (resta.getX() == resta.getY() && TIPO.NOFICHA == ficha.getTipo()) { // avance diagonal
			trayectoria_correcta = false;
		}

		return trayectoria_correcta;
	}

	public ArrayList<Tupla> generarPosibilidades(Tupla inicio, Tablero tablero) {
		return new ArrayList<Tupla>();
	}
}
