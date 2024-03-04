package modelo;

import java.util.ArrayList;

public class Bando {
	private Casilla posicionRey;
	private final ArrayList<Casilla> fichas = new ArrayList<>(16);

	public Casilla getPosicionRey() {
		return posicionRey;
	}

	public void setPosicionRey(Casilla posicionRey) {
		this.posicionRey = posicionRey;
	}

	public ArrayList<Casilla> getFichas() {
		return fichas;
	}
}
