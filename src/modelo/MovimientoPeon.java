package modelo;

import java.util.ArrayList;

public class MovimientoPeon implements IMovimiento {
	public boolean comprobar(Casilla inicio, Casilla destino) {
		boolean movimiento_correcto = false;
		int limite = inicio.getFicha().getLimite();

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino = destino.getPosicion();
		Tupla resta = tuplaInicio.restarTupla(tuplaDestino);

		// comprobar avance peon
		if (0 == resta.getY()) {
			if (limite == resta.getX())
				movimiento_correcto = true;
			else if (2 == Math.abs(limite) && resta.getX() == limite / 2) // movimiento simple al inicio
				movimiento_correcto = true;
		} else if (resta.getX() == resta.getY()) { // comprobar avance diagonal
			if (Math.abs(resta.getX()) == 1)
				movimiento_correcto = true;
		}

		return movimiento_correcto;
	}

	public Casilla revisarTrayectoria(Casilla inicio, Casilla destino, Tablero tablero) {
		Casilla maximo = inicio;
		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino = destino.getPosicion();
		Tupla resta = tuplaInicio.restarTupla(tuplaDestino);

		// avance diagonal
		if (Math.abs(resta.getX()) == Math.abs(resta.getY()) && TIPO.NOFICHA != destino.getFicha().getTipo())
			maximo = destino;
		else if (resta.getY() == 0) { // avance vertical
			if (TIPO.NOFICHA == destino.getFicha().getTipo())
				maximo = destino;

			// avance doble
			if (2 == Math.abs(resta.getX())) {
				TableroIterador iterador = tablero.crearIterador(inicio, destino);
				Casilla medio;

				medio = iterador.siguiente();
				if (TIPO.NOFICHA != medio.getFicha().getTipo())
					maximo = medio;
			}
		}

		return maximo;
	}

	public void generarPosibilidades(Casilla inicio, Tablero tablero, ArrayList<Tupla> posibilidades) {
		ArrayList<Tupla> candidatos = new ArrayList<>();
		Ficha peon = inicio.getFicha();

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino;

		// movimiento diagonal
		tuplaDestino =  new Tupla((int) (tuplaInicio.getX() - Math.signum(peon.getLimite())), tuplaInicio.getY() - 1);
		candidatos.add(tuplaDestino);
		tuplaDestino =  new Tupla((int) (tuplaInicio.getX() - Math.signum(peon.getLimite())), tuplaInicio.getY() + 1);
		candidatos.add(tuplaDestino);

		// movimiento lineal
		tuplaDestino =  new Tupla(tuplaInicio.getX() - peon.getLimite(), tuplaInicio.getY());
		candidatos.add(tuplaDestino);

		Casilla maximo;
		Casilla destino;
		for (Tupla candidata : candidatos) {
			if (tablero.estaDentro(candidata)) {
				destino = tablero.getCasilla(candidata);
				maximo = revisarTrayectoria(inicio, destino, tablero);
				if (maximo != inicio)
					posibilidades.add(maximo.getPosicion());
			}
		}

		// TODO: generar el rango
	}
}
