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
			iteradorAsignado.setNumIteraciones(1);
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

	public boolean tieneSiguiente() {
		boolean siguiente = false;

		int indiceCopia = indice;
		int caminoCopia = camino;
		avanzar();

		if (i < numIteraciones && tablero.estaDentro(new Tupla(indice, camino)))
			siguiente = true;

		this.indice = indiceCopia;
		this.camino = caminoCopia;

		return siguiente;
	}

	public Casilla siguiente() {
		avanzar();
		i++;
		return tablero.getCasilla(new Tupla(indice, camino));
	}

	protected abstract void avanzar();

	public void setNumIteraciones(int numIteraciones) {
		this.numIteraciones = numIteraciones;
	}
	public int getNumIteraciones() { return this.numIteraciones; }

	public void setAvance(int avance) {
		this.avance = avance;
	}

	public void setInicio(Tupla nuevoInicio) {
		indice = nuevoInicio.getX();
		camino = nuevoInicio.getY();
	}
}

class IteradorVertical extends TableroIterador {
	public IteradorVertical(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	protected void avanzar() {
		indice += avance;
	}
}

class IteradorHorizontal extends TableroIterador {
	public IteradorHorizontal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	protected void avanzar() {
		camino += avance;
	}
}

class IteradorNorteDiagonal extends TableroIterador {
	public IteradorNorteDiagonal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	protected void avanzar() {
		indice--;
		camino += avance;
	}
}

class IteradorSurDiagonal extends TableroIterador {
	public IteradorSurDiagonal(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	protected void avanzar() {
		indice++;
		camino += avance;
	}
};

class IteradorCaballo extends TableroIterador {
	public IteradorCaballo(Tablero tablero, int inicio, int camino) {
		super(tablero, inicio, camino);
	}

	protected void avanzar() {
	}
}