package modelo;

import java.util.ArrayList;

public class MovimientoDiagonal implements IMovimiento {
	public boolean comprobar(Casilla inicio, Casilla destino) {
		int limite = inicio.getFicha().getLimite();
		boolean movimiento_correcto = true;

		Tupla tuplaInicio = inicio.getPosicion();
		Tupla tuplaDestino = destino.getPosicion();
		Tupla resta = tuplaInicio.restarAbsTupla(tuplaDestino);

		if (resta.getX() != resta.getY() || resta.getX() > limite)
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
		ArrayList<Tupla> candidatos = new ArrayList<>();
		Casilla destino;
		Casilla maximo;
		int aux;

		// posibilidades para el nororiente
		// calcular el destino dado que y aumenta
		aux = Tablero.getNumColumnas() - 1 - tuplaInicio.getY();
		if (aux > tuplaInicio.getX())
			aux = tuplaInicio.getX();
		candidatos.add(new Tupla(tuplaInicio.getX() - aux, tuplaInicio.getY() + aux));

		// posibilidades para el suroccidente
		// calcular el destino dado que x aumenta
		aux = Tablero.getNumFilas() - 1 - tuplaInicio.getX();
		if (aux > tuplaInicio.getY())
			aux = tuplaInicio.getY();
		candidatos.add(new Tupla(tuplaInicio.getX() + aux, tuplaInicio.getY() - aux));

		// posibilidades para el noroccidente
		// calcular el destino dado que se resta el menor numero a ambas coordenadas
		aux = Math.min(tuplaInicio.getX(), tuplaInicio.getY());
		candidatos.add(new Tupla(tuplaInicio.getX() - aux, tuplaInicio.getY() - aux));

		// posibilidades para el suroriente
		// calcular el destino dado que se suma el modulo respecto a 7 del mayor numero a ambas coordenadas
		if (tuplaInicio.getX() > tuplaInicio.getY())
			aux = Tablero.getNumFilas() - 1 - tuplaInicio.getX();
		else
			aux = Tablero.getNumColumnas() - 1 - tuplaInicio.getY();
		candidatos.add(new Tupla(tuplaInicio.getX() + aux, tuplaInicio.getY() + aux));

		for (Tupla tuplaDestino : candidatos) {
			if (!tuplaDestino.equals(tuplaInicio)) {
				destino = tablero.getCasilla(tuplaDestino);
				maximo = revisarTrayectoria(inicio, destino, tablero);
				posibilidades.add(maximo);
			}
		}
	}
}