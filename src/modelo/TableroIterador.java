package modelo;

public abstract class TableroIterador {
	protected Tablero tablero;
	protected int indice;
	protected int camino;
	protected int numIteraciones;
	protected int i;
	protected int avance;

	public static TableroIterador fabricarIterador(Tablero tablero, Tupla inicio, Tupla destino) {
		TableroIterador iteradorAsignado = null;
		Tupla resta = inicio.restarTupla(destino);

		if (0 == resta.getY()) {
			iteradorAsignado = new IteradorVertical(tablero, inicio.getX(), inicio.getY());
			if (resta.getX() > 0)
				iteradorAsignado.setAvance(-1);

			iteradorAsignado.setNumIteraciones(Math.abs(resta.getX()));
		} else if (0 == resta.getX()) {
			iteradorAsignado = new IteradorHorizontal(tablero, inicio.getX(), inicio.getY());
			if (resta.getY() > 0)
				iteradorAsignado.setAvance(-1);

			iteradorAsignado.setNumIteraciones(Math.abs(resta.getY()));
		} else if (Math.abs(resta.getX()) == Math.abs(resta.getY())) {
			if (resta.getX() < 0) {
				iteradorAsignado = new IteradorSurDiagonal(tablero, inicio.getX(), inicio.getY());
				if (resta.getY() > 0)
					iteradorAsignado.setAvance(-1);

				iteradorAsignado.setNumIteraciones(Math.abs(resta.getX()));
			} else if (resta.getX() > 0) {
				iteradorAsignado = new IteradorNorteDiagonal(tablero, inicio.getX(), inicio.getY());
				if (resta.getY() > 0)
					iteradorAsignado.setAvance(-1);

				iteradorAsignado.setNumIteraciones(Math.abs(resta.getX()));
			}
		} else {
			iteradorAsignado = new IteradorCaballo(tablero, destino.getX(), destino.getY());
		}

		return iteradorAsignado;
	}

	public TableroIterador(Tablero tablero, int indice, int camino) {
		this.tablero = tablero;
		this.indice = indice;
		this.camino = camino;
		this.i = 0;
		this.avance = 1;
	}

	public abstract Casilla siguiente();

	public boolean tieneSiguiente() {
		boolean siguiente = i < numIteraciones;
		i++;
		return siguiente;
	}

	public void setNumIteraciones(int numIteraciones) {
		this.numIteraciones = numIteraciones;
	}
	public int getNumIteraciones() { return this.numIteraciones; }

	public void setAvance(int avance) {
		this.avance = avance;
	}
}

class IteradorVertical extends TableroIterador {
	public IteradorVertical(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	public Casilla siguiente() {
		indice += avance;
		return tablero.getCasilla(new Tupla(indice, camino));
	}
}

class IteradorHorizontal extends TableroIterador {
	public IteradorHorizontal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	public Casilla siguiente() {
		camino += avance;
		return tablero.getCasilla(new Tupla(indice, camino));
	}
}

class IteradorNorteDiagonal extends TableroIterador {
	public IteradorNorteDiagonal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	public Casilla siguiente() {
		indice--;
		camino += avance;
		return tablero.getCasilla(new Tupla(indice, camino));
	}
}

class IteradorSurDiagonal extends TableroIterador {
	public IteradorSurDiagonal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	public Casilla siguiente() {
		indice++;
		camino += avance;
		return tablero.getCasilla(new Tupla(indice, camino));
	}
};

class IteradorCaballo extends TableroIterador {
	public IteradorCaballo(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	public Casilla siguiente() {
		return tablero.getCasilla(new Tupla (indice, camino));
	}
}