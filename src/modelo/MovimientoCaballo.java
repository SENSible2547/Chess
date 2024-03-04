package modelo;

import java.util.ArrayList;

public class MovimientoCaballo implements IMovimiento {
	public boolean comprobar(Casilla inicio, Casilla destino) {
		boolean movimiento_correcto = true;

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino = destino.getPosicion();
		Tupla resta = tuplaInicio.restarAbsTupla(tuplaDestino);

		if ((2 != resta.getX() || 1 != resta.getY()) && (2 != resta.getY() || 1 != resta.getX()))
			movimiento_correcto = false;

		return movimiento_correcto;
	}

	public Casilla revisarTrayectoria(Casilla inicio, Casilla destino, Tablero tablero) {
		return destino;
	}

	public void generarPosibilidades(Casilla inicio, Tablero tablero, ArrayList<Casilla> posibilidades) {
		Tupla tuplaInicio = inicio.getPosicion();
		Tupla candidata;

		// generar las 8 posibilidades
		for (int i = -1; i < 2; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				candidata = new Tupla(tuplaInicio.getX() + 1 * j, tuplaInicio.getY() + 2 * i);
				if (tablero.estaDentro(candidata))
					posibilidades.add(tablero.getCasilla(candidata));
				candidata = new Tupla(tuplaInicio.getX() + 2 * j, tuplaInicio.getY() + 1 * i);
				if (tablero.estaDentro(candidata))
					posibilidades.add(tablero.getCasilla(candidata));
			}
		}
	}
}
