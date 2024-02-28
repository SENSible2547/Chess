package modelo;

public class TableroIterador {
	private final Tablero tablero;
	private int indice;
	private int camino;

	public TableroIterador(Tablero tablero, int inicio, int camino) {
		this.tablero = tablero;
		this.indice = inicio;
		this.camino = camino;
	}

	public Casilla siguiente_columna() {
		indice++;
		return tablero.getCasilla(new Tupla(camino, indice));
	}

	public Casilla siguiente_fila() {
		indice++;
		return tablero.getCasilla(new Tupla(indice, camino));
	}

	public Casilla siguiente_diagonal_derecha() {
		indice++;
		camino++;
		return tablero.getCasilla(new Tupla(indice, camino));
	}

	public Casilla siguiente_diagonal_izquierda() {
		indice++;
		camino--;
		return tablero.getCasilla(new Tupla(indice, camino));
	}
}
