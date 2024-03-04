package modelo;

import java.util.ArrayList;

public class MovimientoLineal implements IMovimiento {
	public boolean comprobar(Casilla inicio, Casilla destino) {
		int limite = inicio.getFicha().getLimite();
		boolean movimiento_correcto = true;

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino= destino.getPosicion();
		Tupla resta = tuplaInicio.restarAbsTupla(tuplaDestino);

		if ((0 != resta.getX() || resta.getY() > limite) && (0 != resta.getY() || resta.getX() > limite))
			movimiento_correcto = false;

		return movimiento_correcto;
	}
	
	public Casilla revisarTrayectoria(Casilla inicio, Casilla destino, Tablero tablero) {
		Casilla maximo = inicio;

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino = destino.getPosicion();
		Tupla resta = tuplaInicio.restarTupla(tuplaDestino);

		TableroIterador	iterador = tablero.crearIterador(inicio, destino);
		int fichaLimite = inicio.getFicha().getLimite();
		int limite = Math.min(iterador.getNumIteraciones(), fichaLimite);

		for (int i = 0; i < limite; i++) {
			maximo = iterador.siguiente();
			if (TIPO.NOFICHA != maximo.getFicha().getTipo())
				break;
		}

		return maximo;
	}

	public void generarPosibilidades(Casilla inicio, Tablero tablero, ArrayList<Casilla> posibilidades) {
		Tupla tuplaInicio = inicio.getPosicion();
		Casilla destino;
		Casilla maximo;

		Tupla tuplaDestino;
		// posibilidades para el norte y el sur
		for (int i = 0; i < 8; i += 7) {
			tuplaDestino = new Tupla(i, tuplaInicio.getY());

			// si la ficha esta en la misma casilla de maximo no agregarla
			if (0 != tuplaInicio.compararX(tuplaDestino)) {
				destino = tablero.getCasilla(tuplaDestino);
				maximo = revisarTrayectoria(inicio, destino, tablero);
				posibilidades.add(maximo);
			}
		}

		// posibilidades para el oriente y occidente
		for (int i = 0; i < 8; i += 7) {
			tuplaDestino = new Tupla(tuplaInicio.getX(), i);

			if (0 != tuplaInicio.compararY(tuplaDestino)) {
				destino = tablero.getCasilla(new Tupla(tuplaInicio.getX(), i));
				maximo = revisarTrayectoria(inicio, destino, tablero);
				posibilidades.add(maximo);
			}
		}
	}
}
